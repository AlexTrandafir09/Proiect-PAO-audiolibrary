package functions.user_functions;

import audiolibrary.allSongs.AllSongs;
import audiolibrary.playlist.Playlist;
import audiolibrary.song.Song;
import database.AuditDatabase;
import exception.NonexistentSongException;
import exception.PlaylistNotExistentException;
import user.User;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public final class AddSongToPlaylist {
    private static final String path = "C:\\Users\\trand\\IdeaProjects\\Proiect-PAO-audiolibrary\\src\\main\\resources\\playlists.txt";

    public static User returned(String[] values, User user) {
        AllSongs allSongs = AllSongs.getInstance();
        List<String> songsIds = Arrays.asList(values).subList(3, values.length);
        Playlist targetPlaylist = null;
        try {
            Integer playlistId = null;
            String playlistName = null;

            if (values[1].equals("byName")) {
                playlistName = values[2];
            } else if (values[1].equals("byId")) {
                playlistId = Integer.parseInt(values[2]);
            }

            boolean playlistFound = false;

            if (playlistName != null) {
                targetPlaylist = user.checkPlaylistbyName(playlistName);
            } else {
                targetPlaylist = user.checkPlaylistbyId(playlistId);
            }
            if (targetPlaylist != null) playlistFound = true;

            if (!playlistFound) {
                throw new PlaylistNotExistentException();
            }
        } catch (PlaylistNotExistentException e) {
            AuditDatabase.insert(user, "add song to playlist " + e.getMessage(), false);
            System.out.println(e.getMessage());
            return user;
        }
        finally {
            try {
                File file = new File(path);
                File tempFile = new File(path + ".tmp");

                try (BufferedReader reader = new BufferedReader(new FileReader(file));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                    String lineToRemove = user.getId() + "/" + targetPlaylist.getId() + "/";
                    String currentLine;

                    while ((currentLine = reader.readLine()) != null) {
                        if (currentLine.startsWith(lineToRemove)) {
                            StringBuilder line = new StringBuilder(currentLine);
                            for (String songId : songsIds) {
                                int id = Integer.parseInt(songId);
                                Song song = allSongs.getSongBySongId(id);
                                if (song != null) {
                                    if (!targetPlaylist.checkSong(song)) {
                                        line.append(songId).append("/");
                                        targetPlaylist.addSong(song);
                                        System.out.println(
                                                "Added "
                                                        + song.getName()
                                                        + " by "
                                                        + song.getArtist()
                                                        + " to "
                                                        + targetPlaylist.getName());
                                    } else System.out.println("Song " + song.getName() + " by " + song.getArtist() + " is already part of " + targetPlaylist.getName());

                                } else throw new NonexistentSongException(id);

                            }
                            writer.write(line.toString());
                            writer.newLine();
                        } else {
                            writer.write(currentLine);
                            writer.newLine();
                        }
                    }
                }

                if (!file.delete()) {
                    System.out.println("Could not delete original file.");
                }

                if (!tempFile.renameTo(file)) {
                    System.out.println("Could not rename temp file.");
                }

                AuditDatabase.insert(user, "add song to playlist", true);

            } catch (IOException | NonexistentSongException e) {
                System.out.println(e.getMessage());
            }

        }
        return user;
    }
}
