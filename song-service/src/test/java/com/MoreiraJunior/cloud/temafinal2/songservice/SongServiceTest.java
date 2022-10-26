package com.MoreiraJunior.cloud.temafinal2.songservice;

import com.MoreiraJunior.cloud.temafinal2.songservice.hystrix.SaveCommand;
import com.MoreiraJunior.cloud.temafinal2.songservice.model.Song;
import com.MoreiraJunior.cloud.temafinal2.songservice.repository.SongRepository;
import com.MoreiraJunior.cloud.temafinal2.songservice.service.SongService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@AutoConfigureMockMvc

public class SongServiceTest {

    @MockBean
    private SongRepository songRepository;
    @Autowired
    private SongService songService;

    @Test
    public void shouldSaveSong(){
        Song song = new Song ("wave");
        Mockito.when(songRepository.save(ArgumentMatchers.eq(song))).thenReturn(song);
        songService.save("wave");
        Mockito.verify(songRepository, Mockito.times(1)).save(any(Song.class));
    }

    @Test
    public void shouldFindAllSongs(){
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("wave"));
        Mockito.when(songRepository.findAll()).thenReturn(songs);
        songService.findAll();
        Mockito.verify(songRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void shouldFindSongById(){
        Optional<Song> song = Optional.of(new Song("wave"));
        song.get().setId("1");
        Mockito.when(songRepository.findById("1")).thenReturn(song);
        songService.findById("1");
        Mockito.verify(songRepository, Mockito.times(1)).findById("1");
    }

    @Test
    public void shouldFindByIds(){
        Optional<Song> song = Optional.of(new Song("wave"));
        song.get().setId("1");
        Optional<Song> song2 = Optional.of(new Song("wave2"));
        song2.get().setId("2");
        List<Song> songs = Arrays.asList(song.get(), song2.get());
        Iterable<Song> songsIterable = songs;
        List<String> strings = Arrays.asList("1", "2");
        Iterable<String> ids = strings;
        Mockito.when(songRepository.findAllById(strings)).thenReturn(songsIterable);
        songService.findByIds(strings);
        Mockito.verify(songRepository, Mockito.times(1)).findAllById(ids);
    }

    @Test
    public void shouldFindSongByName(){
        Optional<Song> song = Optional.of(new Song("wave"));
        Mockito.when(songRepository.findByName("wave")).thenReturn(song);
        songService.findById("wave");
        Mockito.verify(songRepository, Mockito.times(1)).findById("wave");
    }

    @Test
    public void shouldDeleteById(){
        songService.deleteById("1");
        Mockito.verify(songRepository, Mockito.times(1)).deleteById("1");
    }
}
