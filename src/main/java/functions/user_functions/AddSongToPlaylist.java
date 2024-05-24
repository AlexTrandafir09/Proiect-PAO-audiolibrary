package functions.user_functions;

import audiolibrary.allSongs.AllSongs;
import audiolibrary.playlist.Playlist;
import audiolibrary.song.Song;
import database.AuditDatabase;
import exception.NonexistentSongException;
import exception.PlaylistNotExistentException;
import exception.SongsAlreadyIsInPlaylistException;
import java.util.Arrays;
import java.util.List;
import user.User;

public class AddSongToPlaylist {

    public static User returned(String[] values, User user) {
        AllSongs allSongs = AllSongs.getInstance();
        List<String> songs_ids = Arrays.stream(values).skip(3).toList();
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
            AuditDatabase.insert(user, "add song to playlist", false);
            System.out.println(e.getMessage());
            return user;
        } finally {
            for (String identifier : songs_ids) {
                int songId = Integer.parseInt(identifier);
                Song temp = allSongs.getSongBySongId(songId);
                try {
                    if (temp != null) {
                        if (!targetPlaylist.checkSong(temp)) {
                            targetPlaylist.addSong(temp);
                            System.out.println(
                                    "Added "
                                            + temp.getName()
                                            + " by "
                                            + temp.getArtist()
                                            + " to "
                                            + targetPlaylist.getName());
                        } else {
                            throw new SongsAlreadyIsInPlaylistException(
                                    temp.getName(), temp.getArtist(), targetPlaylist.getName());
                        }
                    } else {
                        throw new NonexistentSongException(songId);
                    }
                } catch (SongsAlreadyIsInPlaylistException | NonexistentSongException e) {
                    System.out.println(e.getMessage());
                    AuditDatabase.insert(user, "add song to playlist", false);
                }
            }
            AuditDatabase.insert(user, "add song to playlist", true);
        }
        return user;
    }
}
