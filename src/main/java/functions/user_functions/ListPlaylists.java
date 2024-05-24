package functions.user_functions;

import audiolibrary.Playlist;
import authentification.User;
import database.AuditDatabase;

public class ListPlaylists {
    public static void returned(User user){
        Pagination<Playlist> pagination = new Pagination<>(user.getPlaylists(), 5, Playlist::toString);
        pagination.display();
        AuditDatabase.Insert(user,"list playlists",true);
    }
}
