package audiolibrary;


import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Builder
public class Playlist {
    private static int lastId = 0;
    @Getter private int id;
    @Getter private String name;
    @Getter private List<Song> songs;

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
