package functions.user_functions;

import audiolibrary.playlist.Playlist;
import database.AuditDatabase;
import exception.AlreadyExistingPlaylistException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import user.User;

public final class AddPlaylist {
    public static final String path="C:\\Users\\trand\\IdeaProjects\\Proiect-PAO-audiolibrary\\src\\main\\resources\\playlists.txt";
    public static User returned(String name, User user) {
        try {

            Playlist playlist = Playlist.builder().name(name).build();
            for (Playlist playlist1 : user.getPlaylists())
                if (playlist1.getName().equals(playlist.getName()))
                    throw new AlreadyExistingPlaylistException(name);
            user.getPlaylists().add(playlist);
            addPlaylistInTxt(user,playlist);
            AuditDatabase.insert(user, "create playlist", true);
            System.out.println(name + " was created successfully!");
            return user;
        } catch (AlreadyExistingPlaylistException e) {
            AuditDatabase.insert(user, "create playlist", false);
            System.out.println(e.getMessage());
            return user;
        }
    }
    public static void addPlaylistInTxt(User user, Playlist playlist) {
        String line = user.getId() + "/" + playlist.getId() + "/"+playlist.getName()+ "/";
        try (FileWriter fw = new FileWriter(path, true);
             PrintWriter writer = new PrintWriter(fw)) {
            writer.println(line);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
