package functions.admin_functions;

import audiolibrary.allSongs.AllSongs;
import audiolibrary.song.Song;
import authentification.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.AuditDatabase;
import exception.SongAlreadyCreatedException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateSong {
    private static final String JSON_FILE_PATH = "C:\\Users\\trand\\IdeaProjects\\Proiect-PAO-audiolibrary\\src\\main\\resources\\songs.json";

    public static User returned(User user) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter song id: ");
            int songId = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter song name: ");
            String songName = scanner.nextLine();
            System.out.println("Enter song artist: ");
            String songArtist = scanner.nextLine();
            System.out.println("Enter song launch year: ");
            int songLaunchYear = Integer.parseInt(scanner.nextLine());

            Song song = new Song(songId, songName, songArtist, songLaunchYear);

            AllSongs allSongs = AllSongs.getInstance();
            if (allSongs.checkCreation(song)) {
                throw new SongAlreadyCreatedException();
            } else {
                allSongs.addSong(song);
            }

            ObjectMapper mapper = new ObjectMapper();
            List<Song> songs = new ArrayList<>();


            File file = new File(JSON_FILE_PATH);
            if (file.exists()) {
                songs = mapper.readValue(file, new TypeReference<List<Song>>() {});
            }

            songs.add(song);
            mapper.writeValue(file, songs);
            System.out.println("Song added successfully!");
            AuditDatabase.insert(user, "create song", true);
            return user;

        }
        catch (SongAlreadyCreatedException| IOException e) {
            AuditDatabase.insert(user, "Create song."+e.getMessage(), false);
            System.out.println(e.getMessage());
            return user;
        }

    }
}
