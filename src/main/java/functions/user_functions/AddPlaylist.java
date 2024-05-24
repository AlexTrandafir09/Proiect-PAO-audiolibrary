package functions.user_functions;

import audiolibrary.playlist.Playlist;
import authentification.User;
import database.AuditDatabase;
import exception.AlreadyExistingPlaylistException;

import java.util.ArrayList;

public class AddPlaylist {
    public static User returned(String name,User user){
        try {
            if (user.getPlaylists() == null) {
                user.setPlaylists(new ArrayList<>());
            }
            Playlist playlist = Playlist.builder().name(name).build();
            for (Playlist playlist1 : user.getPlaylists())
                if (playlist1.getName().equals(playlist.getName()))
                    throw new AlreadyExistingPlaylistException(name);
            user.getPlaylists().add(playlist);
            AuditDatabase.insert(user,"create playlist",true);
            System.out.println(name+" was created successfully!");
            return user;
        }
        catch (AlreadyExistingPlaylistException e){
            AuditDatabase.insert(user,"create playlist",false);
            System.out.println(e.getMessage());
            return user;
        }
    }
}
