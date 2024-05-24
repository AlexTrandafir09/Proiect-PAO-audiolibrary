package functions.user_functions;

import audiolibrary.Playlist;
import audiolibrary.Song;
import authentification.User;
import com.opencsv.CSVWriter;
import database.AuditDatabase;
import exception.PlaylistNotExistentException;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExportPlaylist {
    public static void returned(String playlist_name,User user) {
        try {
            Playlist playlist = user.checkPlaylistbyName(playlist_name);
            if (playlist != null)
                throw new PlaylistNotExistentException();

            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String formattedDate = currentDate.format(formatter);
            String path = "C:\\Users\\trand\\IdeaProjects\\Proiect-PAO-audiolibrary\\target\\generated-sources\\";
            String file_name = path + "export_" + user.getUser() + "_" + playlist.getName() + "_" + formattedDate + ".csv";
            FileWriter fileWriter = new FileWriter(file_name);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            for (Song song : playlist.getSongs()) {
                String[] csvLine = song.toStringCsv().split(",");
                csvWriter.writeNext(csvLine);
            }
            csvWriter.close();
            System.out.println("Successfully exported.");
            AuditDatabase.Insert(user,"Export playlist",true);

        }
        catch (PlaylistNotExistentException | IOException e) {
            AuditDatabase.Insert(user,"Export playlist."+e.getMessage(),false);
            System.out.println(e.getMessage());
        }
    }
}
