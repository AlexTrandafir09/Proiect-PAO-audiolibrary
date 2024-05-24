package functions.user_functions;

import audiolibrary.playlist.Playlist;
import user.User;
import database.AuditDatabase;

public class ListPlaylists {
    public static void returned(User user) {
        Pagination<Playlist> pagination =
                new Pagination<>(user.getPlaylists(), 5, Playlist::toString);
        pagination.display();
        AuditDatabase.insert(user, "list playlists", true);
    }
}
