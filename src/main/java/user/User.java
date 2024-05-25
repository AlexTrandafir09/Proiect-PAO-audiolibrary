package user;

import audiolibrary.playlist.Playlist;
import audiolibrary.playlist.ReadPlaylists;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString(of = {"id", "user", "role"})
public class User {
    private static int lastId = 0;
    private int id;
    private String user;
    private String password;
    private Roles role;
    private List<Playlist> playlists;


    public User(int id, String user, String password, Roles role) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.role = role;
        this.playlists = ReadPlaylists.returned(this);
    }
    @Builder
    public User(int id, String user, String password, Roles role, List<Playlist> playlists) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.role = role;
        this.playlists = (playlists != null) ? playlists : ReadPlaylists.returned(this);
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
