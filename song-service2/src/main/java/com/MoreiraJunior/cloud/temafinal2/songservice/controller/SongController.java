package com.MoreiraJunior.cloud.temafinal2.songservice.controller;

import com.MoreiraJunior.cloud.temafinal2.songservice.model.Song;
import com.MoreiraJunior.cloud.temafinal2.songservice.service.SongService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping
    public ResponseEntity<Object> getAllSongs(){
        return ResponseEntity.status(HttpStatus.OK).body(songService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getSongById(@PathVariable(value = "id")String id){
        Optional<Song> song = songService.findById(id);
        if(!song.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Song not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(song.get());
    }

    @GetMapping("/ids/{ids}")
    public ResponseEntity<Object> getSongsByIds(@PathVariable(value = "ids") String [] ids){
        List<Song> songs = songService.findByIds(List.of(ids));
        return ResponseEntity.status(HttpStatus.OK).body(songs);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getSongByName(@PathVariable(value = "name")String name){
        Optional<Song> song = songService.findByName(name);
        if(!song.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Song not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(song.get());
    }

    @PostMapping("/{name}")
    public ResponseEntity<Object> saveSong(@PathVariable(value = "name") String name){
        if(songService.findByName(name).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: The song is already registered");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(songService.save(name));
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Object> deleteSongByName(@PathVariable(value = "name") String name){
        Optional<Song> song = songService.findByName(name);
        if(!song.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: The song is not registered");
        }
        boolean bool = songService.delete(song.get());
        if(bool == false){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not delete song!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Deleted " + name);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Object> deleteSongById(@PathVariable(value = "id") String id){
        Optional<Song> song = songService.findById(id);
        if(!song.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: The song is not registered");
        }
        boolean bool = songService.deleteById(id);
        if(bool == false){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not delete song!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Deleted " + id);
    }
}
