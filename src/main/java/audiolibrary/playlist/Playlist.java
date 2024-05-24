package audiolibrary.playlist;


import audiolibrary.song.Song;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Builder
public class Playlist implements IPlaylist {
    private static int lastId = 0;
    private int id;
    private String name;
    private List<Song> songs;

    public static PlaylistBuilder builder() {
        return new PlaylistBuilder();
    }


    public static class PlaylistBuilder {
        private int id = ++lastId;
        private List<Song> songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public boolean checkSong(Song song) {
        return songs.contains(song);
    }

    public String toString() {
        return this.getName()+ " [ID: " + this.getId() + "]";
    }
}
