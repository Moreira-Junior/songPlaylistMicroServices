package com.MoreiraJunior.cloud.temafinal2.appservice.controller;

import com.MoreiraJunior.cloud.temafinal2.appservice.model.Playlist;
import com.MoreiraJunior.cloud.temafinal2.appservice.model.Song;
import com.MoreiraJunior.cloud.temafinal2.appservice.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/app")
public class AppServiceController {

    @Autowired
    private AppService appService;

    @GetMapping("/song/name/{name}")
    public ResponseEntity<Object> getSongByName(@PathVariable(value = "name") String name){
        return ResponseEntity.status(HttpStatus.OK).body(appService.findSongByName(name));
    }

    @GetMapping("/song/id/{id}")
    public ResponseEntity<Object> getSongById(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(appService.findSongById(id));
    }

    @GetMapping("/song")
    public ResponseEntity<Object> getAllSongs(){
        return ResponseEntity.status(HttpStatus.OK).body(appService.findAllSongs());
    }

    @PostMapping("/song/{name}")
    public ResponseEntity<Object> saveSong(@PathVariable(value = "name") String name){
        return ResponseEntity.status(HttpStatus.CREATED).body(appService.saveSong(name));
    }

    @DeleteMapping("/song/name/{name}")
    public ResponseEntity<Object> deleteSong(@PathVariable(value = "name") String name){
        Object obj = appService.deleteSong(name);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted " + name);
    }

    @DeleteMapping("/song/id/{id}")
    public ResponseEntity<Object> deleteSongById(@PathVariable(value = "id") String id){
        Object obj = appService.deleteSongById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted " + id);
    }


    @GetMapping("/playlist")
    public ResponseEntity<Object> getPlaylists(){
        return ResponseEntity.status(HttpStatus.OK).body(appService.findPlaylists());
    }

    @GetMapping("/playlist/{id}")
    public ResponseEntity<Object> getPlaylistById(@PathVariable(value = "id") String id){
        Playlist playlist = appService.findPlaylistById(id);
        List<Song> list = appService.findSongsByIds(playlist.getSongs());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/playlist/{ids}")
    public ResponseEntity<Object> saveSong(@PathVariable(value = "ids") String [] ids){
        List<Song> songs = appService.findSongsByIds(List.of(ids));
        if(songs.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found any of the songs!");
        }
        List<String> songsIds =new ArrayList<>();
        for(Song song : songs){
            songsIds.add(song.getId());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(appService.savePlaylist(songsIds));
    }

    @DeleteMapping("/playlist/{id}")
    public ResponseEntity<Object> deletePlaylist(@PathVariable(value = "id") String id){
        Object obj = appService.deletePlaylist(id);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }
}
