package functions.user_functions;

import audiolibrary.allSongs.AllSongs;
import audiolibrary.song.Song;
import database.AuditDatabase;
import java.util.List;
import user.User;

public class SearchSongs {
    public static void returned(User user, String command, String filter) {
        List<Song> desired_songs = null;
        AllSongs allSongs = AllSongs.getInstance();
        if (command.equals("author")) desired_songs = allSongs.getSongsByAuthor(filter);
        if (command.equals("name")) desired_songs = allSongs.getSongsByTitle(filter);

        Pagination<Song> pagination = new Pagination<>(desired_songs, 5, Song::toString);
        pagination.display();
        AuditDatabase.insert(user, "search", true);
    }
}
