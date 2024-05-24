package functions.admin_functions;

import audiolibrary.allSongs.AllSongs;
import audiolibrary.song.Song;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.AuditDatabase;
import exception.SongAlreadyCreatedException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import user.User;

public class CreateSong {
    private static final String JSON_FILE_PATH =
            "C:\\Users\\trand\\IdeaProjects\\Proiect-PAO-audiolibrary\\src\\main\\r"
                    + "esources\\songs.json";

    public static User returned(
            User user, String songName, String songArtist, String songLaunchYear) {
        try {
            int year = Integer.parseInt(songLaunchYear);
            Song song = new Song(songName, songArtist, year);

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
            System.out.println("Added " + songName + " by " + songArtist + " to the library.");
            AuditDatabase.insert(user, "create song", true);
            return user;

        } catch (SongAlreadyCreatedException | IOException e) {
            AuditDatabase.insert(user, "Create song." + e.getMessage(), false);
            System.out.println(e.getMessage());
            return user;
        }
    }
}
