package audiolibrary.playlist;

import audiolibrary.song.Song;

public interface IPlaylist {
    void addSong(Song song);

    boolean checkSong(Song song);

    String toString();
}
