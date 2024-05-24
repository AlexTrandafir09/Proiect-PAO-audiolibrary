package functions.user_functions;

import audiolibrary.playlist.Playlist;
import database.AuditDatabase;
import user.User;

public class ListPlaylists {
    public static void returned(User user) {
        Pagination<Playlist> pagination =
                new Pagination<>(user.getPlaylists(), 5, Playlist::toString);
        pagination.display();
        AuditDatabase.insert(user, "list playlists", true);
    }
}
