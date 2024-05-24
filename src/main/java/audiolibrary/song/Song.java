package audiolibrary.song;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Getter
public class Song implements ISong {
    private static int lastId = getLastSongId();
    private int id;
    private String name;
    private String artist;
    private int launchYear;

    private Song() {
        this.id = ++lastId;
    }

    @JsonCreator
    public Song(
            @JsonProperty("name") String name,
            @JsonProperty("artist") String artist,
            @JsonProperty("launch_year") int launchYear) {
        this();
        this.name = name;
        this.artist = artist;
        this.launchYear = launchYear;
    }
    @JsonCreator
    public Song(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name,
            @JsonProperty("artist") String artist,
            @JsonProperty("launch_year") int launchYear) {
        this.id=id;
        this.name = name;
        this.artist = artist;
        this.launchYear = launchYear;
    }

    public String toStringCsv() {
        return id + "," + name + "," + artist + "," + launchYear;
    }

    public String toString() {
        return this.getArtist()
                + " - "
                + this.getName()
                + " ( "
                + this.getLaunchYear()
                + " )"
                + "[ ID: "
                + this.getId()
                + " ] ";
    }
    public static int getLastSongId() {
        int lastId = -1;
        ObjectMapper mapper = new ObjectMapper();

        try {
            File file = new File("C:\\Users\\trand\\IdeaProjects\\Proiect-PAO-audiolibrary\\src\\main\\resources\\songs.json");
            if (file.exists()) {
                List<Song> songs = mapper.readValue(file, new TypeReference<>() {
                });
                if (!songs.isEmpty()) {
                    lastId = songs.getLast().getId();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lastId;
    }

    public static void main(String[] args) {
        int lastSongId = getLastSongId();
        System.out.println("Last song ID: " + lastSongId);
    }
}
