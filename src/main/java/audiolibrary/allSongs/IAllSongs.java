package audiolibrary.allSongs;

import audiolibrary.song.Song;

import java.util.List;

public interface IAllSongs {
    void addSong(Song song);

    Song getSongBySongId(int songId);

    List<Song> getSongsByTitle(String title);

    List<Song> getSongsByAuthor(String author);

    boolean checkCreation(Song song);
}
