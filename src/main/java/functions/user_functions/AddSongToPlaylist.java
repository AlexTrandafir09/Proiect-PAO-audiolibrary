package functions.user_functions;

import audiolibrary.allSongs.AllSongs;
import audiolibrary.playlist.Playlist;
import audiolibrary.song.Song;
import authentification.User;
import database.AuditDatabase;
import exception.NonexistentSongException;
import exception.PlaylistNotExistentException;
import exception.SongsAlreadyIsInPlaylistException;

import java.util.Arrays;
import java.util.List;

public class AddSongToPlaylist {

    public static User returned(String[] values, User user) {
        try {
            Integer playlistId = null;
            String playlistName = null;
            List<String> songIdentifiers = Arrays.stream(values).skip(3).toList();

            if (values[1].equals("byName")) {
                playlistName = values[2];
            } else if (values[1].equals("byId")) {
                playlistId = Integer.parseInt(values[2]);
            }


                AllSongs allSongs = AllSongs.getInstance();
                boolean playlistFound = false;
                Playlist targetPlaylist;

                if (playlistName != null) {
                    targetPlaylist=user.checkPlaylistbyName(playlistName);

                }
                else {
                    targetPlaylist=user.checkPlaylistbyId(playlistId);
                }
            if (targetPlaylist != null)
                playlistFound = true;

            if (!playlistFound) {
                    throw new PlaylistNotExistentException();
                }

                for (String identifier : songIdentifiers) {
                    int songId = Integer.parseInt(identifier);
                    Song temp = allSongs.getSongBySongId(songId);
                    if (temp != null) {
                        if (!targetPlaylist.checkSong(temp)) {
                            targetPlaylist.addSong(temp);
                            System.out.println("Added " + temp.getName() + " by " + temp.getArtist() + " to " + targetPlaylist.getName());
                        } else {
                            throw new SongsAlreadyIsInPlaylistException(temp.getName(), temp.getArtist(), targetPlaylist.getName());
                        }
                    } else {
                        throw new NonexistentSongException(songId);
                    }
                }

                AuditDatabase.insert(user, "add song to playlist", true);
                return user;
        } catch (NonexistentSongException | PlaylistNotExistentException | SongsAlreadyIsInPlaylistException e) {
            AuditDatabase.insert(user, "add song to playlist", false);
            System.out.println(e.getMessage());
            return user;
        }
    }
}