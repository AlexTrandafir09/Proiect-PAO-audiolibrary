package audiolibrary.allSongs;

import audiolibrary.song.Song;
import audiolibrary.song.SongLoaderJson;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class AllSongs implements IAllSongs {
    private static AllSongs instance = null;
    private List<Song> songs;

    private AllSongs() {
        songs =
                SongLoaderJson.loadSongs(
                        "C:\\Users\\trand\\IdeaProjects\\Proiect-PAO-audiolibrary\\src\\main\\r"
                                + "esources\\songs.json");
    }

    public static AllSongs getInstance() {
        if (instance == null) {
            instance = new AllSongs();
        }
        return instance;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public Song getSongBySongId(int songId) {
        for (Song song : songs) {
            if (song.getId() == songId) {
                return song;
            }
        }
        return null;
    }

    public List<Song> getSongsByTitle(String title) {
        List<Song> matchedSongs = new ArrayList<>();
        for (Song song : songs) {
            if (song.getName().startsWith(title)) {
                matchedSongs.add(song);
            }
        }
        return matchedSongs;
    }

    public List<Song> getSongsByAuthor(String author) {
        List<Song> matchedSongs = new ArrayList<>();
        for (Song song : songs) {
            if (song.getArtist().startsWith(author)) {
                matchedSongs.add(song);
            }
        }
        return matchedSongs;
    }

    public boolean checkCreation(Song song) {
        for (Song song1 : this.getSongs()) {
            if (song1.getId() == song.getId()
                    || (song1.getName().equals(song.getName())
                            && song1.getArtist().equals(song.getArtist()))) {
                return true;
            }
        }
        return false;
    }
}
