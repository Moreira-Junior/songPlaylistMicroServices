package com.MoreiraJunior.cloud.temafinal2.playlistservice;

import com.MoreiraJunior.cloud.temafinal2.playlistservice.model.Playlist;
import com.MoreiraJunior.cloud.temafinal2.playlistservice.repository.PlaylistRepository;
import com.MoreiraJunior.cloud.temafinal2.playlistservice.service.PlaylistService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class PlaylistServiceTest {

    @MockBean
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistService playlistService;

    @Test
    public void shouldSavePlaylist(){
        Playlist playlist = new Playlist ();
        Mockito.when(playlistRepository.save(ArgumentMatchers.eq(playlist))).thenReturn(playlist);
        playlistRepository.save(playlist);
        Mockito.verify(playlistRepository, Mockito.times(1)).save(any(Playlist.class));
    }

    @Test
    public void shouldFindAllPlaylists(){
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(new Playlist());
        Mockito.when(playlistRepository.findAll()).thenReturn(playlists);
        playlistRepository.findAll();
        Mockito.verify(playlistRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void shouldFindPlaylistById(){
        Optional<Playlist> playlist = Optional.of(new Playlist());
        playlist.get().setId("1");
        Mockito.when(playlistRepository.findById("1")).thenReturn(playlist);
        playlistService.findById("1");
        Mockito.verify(playlistRepository, Mockito.times(1)).findById("1");
    }

    @Test
    public void shouldDeleteById(){
        Playlist playlist = new Playlist();
        playlistService.deletePlaylist(playlist);
        Mockito.verify(playlistRepository, Mockito.times(1)).deleteById("1");
    }
}
