
# Audio Library
  Java application that implements functionalities of an audio library: create, manage, export, list playlists, search songs, etc. 

## Features
  login / register
  
  create playlist
  
  add songs to playlist
  
  export playlist in .csv format
  
  list playlists
  
  search songs
  
  admin tools: promote users,check all users activity, add new songs in application

## Commands
### register (user) (pass)
  Description : create a new account ! Command fails if username is already taken !
  For: anonymous users

### login (user) (pass)
  Description : login to account ! Command fails if data does not match any account !
  For: anonymous users

### create playlist (playlistname)
  Description : create a new playlist on the account. ! Command fails if a playlist with the same name already exists. ! 
  For: Logged users, Admins

### add byName/byId (playlistName)/(playlistId) (songId1) [(songId2) ...]
  Description : add songs to playlist ! Command fails if playlist does not exist, if song identifier is already in playlist, or if song identifier does not match any song !
  For: Logged users, Admins


### export (playlistName)/(playlistId)
  Description : export playlist to .csv file ! Command fails if input does not match any playlist !
  For: Logged users, Admins

### logout
  Description : log out of account
  For: Logged users, Admins

### list playlists
  Description : list all playlists linked with account
  For: Logged users, Admins

### search author/name (key)
  Description : list all authors/songs that contain key in their name
  For: Logged users, Admins

### create song (title) (author) (launch year)
  Description : add a new song on the app for all users ! Command fails if admin doesn't provide all the information needed !
  For: Admins

### promote (username)
  Description : give user with matching username admin role ! Promote fails if user is not existent !
  For: Admins

### audit (username)
  Description: list user's activity on the app ! Audit fails if user is not existent !
  For: Admins

 


