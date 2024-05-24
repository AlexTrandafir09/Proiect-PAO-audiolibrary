package audiolibrary;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Song {
    private int id;
    private String name;
    private String artist;
    private int launchYear;

    public Song() {
    }

    @JsonCreator
    public Song(@JsonProperty("id") int id,
                @JsonProperty("title") String name,
                @JsonProperty("artist") String artist,
                @JsonProperty("launch_year") int launchYear) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.launchYear = launchYear;
    }

    public String toStringCsv() {
        return id + "," + name + "," + artist + "," + launchYear;
    }

    public String toString() {
        return this.getArtist() + " - " + this.getName() + " ( " + this.getLaunchYear() + " )" + "[ ID: " + this.getId() + " ] ";
    }

}

