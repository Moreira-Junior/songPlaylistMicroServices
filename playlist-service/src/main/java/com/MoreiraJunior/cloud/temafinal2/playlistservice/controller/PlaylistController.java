package com.MoreiraJunior.cloud.temafinal2.playlistservice.controller;

import com.MoreiraJunior.cloud.temafinal2.playlistservice.model.Playlist;
import com.MoreiraJunior.cloud.temafinal2.playlistservice.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylists(){
        return ResponseEntity.status(HttpStatus.OK).body(playlistService.findAll());
    }

    @PostMapping("/{ids}")
    public ResponseEntity<Object> savePlaylist(@PathVariable String [] ids){
        Playlist playlist = playlistService.savePlaylist(List.of(ids));
        return ResponseEntity.status(HttpStatus.CREATED).body(playlist);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getPlaylistById(@PathVariable(value = "id")String id){
        Optional<Playlist> playlist = playlistService.findById(id);
        if(!playlist.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Playlist not found " + id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(playlist.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSongById(@PathVariable(value = "id") String id){
        Optional<Playlist> playlist = playlistService.findById(id);
        if(!playlist.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(("Conflict: The playlist is not registered"));
        }
        boolean bool = playlistService.deletePlaylist(playlist.get());
        if(bool == false){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not delete playlist!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(playlist.get());
    }
}
