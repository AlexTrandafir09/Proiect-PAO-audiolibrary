package functions;

import database.AuditDatabase;
import exception.UnknownCommandException;
import functions.user_functions.*;
import java.util.ArrayList;
import user.Roles;
import user.User;

public abstract class BaseFunctions {
    protected abstract int getChoice(String[] values);

    public static User common(User user, String[] values, BaseFunctions instance) {
        if (user.getPlaylists() == null) {
            user.setPlaylists(new ArrayList<>());
        }

        int choice = instance.getChoice(values);
        switch (choice) {
            case 1: // search
                SearchSongs.returned(user, values[1], values[2]);
                return user;
            case 2: // create playlist
                return AddPlaylist.returned(values[2], user);
            case 3: // add song to playlist
                return AddSongToPlaylist.returned(values, user);
            case 4: // logout
                return Logout.returned(user);
            case 5: // list playlists
                ListPlaylists.returned(user);
                return user;
            case 6: // export
                ExportPlaylist.returned(values[2], user);
                return user;
            default:
                try {
                    if (user.getRole() == Roles.admin_user) return null;
                    throw new UnknownCommandException();
                } catch (UnknownCommandException e) {
                    AuditDatabase.insert(user, e.getMessage(), false);
                    System.out.println(e.getMessage());
                    return user;
                }
        }
    }
}
