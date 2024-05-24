package audiolibrary.song;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class SongLoaderJson {

    public static List<Song> loadSongs(String filePath) {
        List<Song> songList = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Song[] songDataArray = objectMapper.readValue(new File(filePath), Song[].class);

            for (Song songData : songDataArray) {
                Song song =
                        new Song(
                                songData.getId(),
                                songData.getName(),
                                songData.getArtist(),
                                songData.getLaunchYear());
                songList.add(song);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return songList;
    }
}
