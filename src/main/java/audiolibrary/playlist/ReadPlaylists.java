package audiolibrary.playlist;

import audiolibrary.allSongs.AllSongs;
import audiolibrary.song.Song;
import user.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadPlaylists {
    public static final String path = "C:\\Users\\trand\\IdeaProjects\\Proiect-PAO-audiolibrary\\src\\main\\resources\\playlists.txt";
    public static List<Playlist> returned(User user) {
        File file = new File(path);
        List<Playlist> playlists = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String lineToFind = user.getId() + "/";
            String currentLine = reader.readLine();
            while (currentLine != null) {
                if (currentLine.startsWith(lineToFind)) {
                    List<String> line = Arrays.asList(currentLine.split("/"));
                    List<Integer> indexes = line.subList(3, line.size()).stream().map(Integer::parseInt).toList();
                    List<Song> songs = new ArrayList<>();
                    AllSongs allSongs = AllSongs.getInstance();
                    for (Integer index : indexes) {
                        Song song = allSongs.getSongBySongId(index);
                        if (song != null) {
                            songs.add(song);
                        }
                    }
                    Playlist playlist = Playlist.builder()
                            .id(Integer.parseInt(line.get(1)))
                            .name(line.get(2))
                            .songs(songs)
                            .build();
                    playlists.add(playlist);
                }
                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
        return playlists;
    }
}
