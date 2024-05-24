package user;

import audiolibrary.playlist.Playlist;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString(of = {"id", "user", "role"})
public class User {
    private static int lastId = 0;
    private int id;
    private String user;
    private String password;
    private Roles role;
    private List<Playlist> playlists;

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private int id = ++lastId;
        private Roles role = Roles.standard_user;
        private List<Playlist> playlists = new ArrayList<>();
    }

    public Playlist checkPlaylistbyName(String name) {
        for (Playlist playlist1 : playlists) {
            if (playlist1.getName().equals(name)) return playlist1;
        }
        return null;
    }

    public Playlist checkPlaylistbyId(int id) {
        for (Playlist playlist : playlists) {
            if (playlist.getId() == id) {
                return playlist;
            }
        }
        return null;
    }
}
