package com.MoreiraJunior.cloud.temafinal2.playlistservice.service;

import com.MoreiraJunior.cloud.temafinal2.playlistservice.hystrix.DeletePlaylistCommand;
import com.MoreiraJunior.cloud.temafinal2.playlistservice.hystrix.FindAllPlaylistsCommand;
import com.MoreiraJunior.cloud.temafinal2.playlistservice.hystrix.FindPlaylistByIdCommand;
import com.MoreiraJunior.cloud.temafinal2.playlistservice.hystrix.SavePlaylistCommand;
import com.MoreiraJunior.cloud.temafinal2.playlistservice.model.Playlist;
import com.MoreiraJunior.cloud.temafinal2.playlistservice.repository.PlaylistRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    @Autowired
    PlaylistRepository playlistRepository;

    public Playlist savePlaylist(List<String> ids){
        return new SavePlaylistCommand("SavePlaylist", ids, this.playlistRepository).execute().get();
    }

    public List<Playlist> findAll(){
        return new FindAllPlaylistsCommand("FindAllPlaylists", this.playlistRepository).execute();
    }

    public Optional<Playlist> findById(String id){
        return new FindPlaylistByIdCommand("FindPlaylistById", id, this.playlistRepository).execute();
    }

    public Boolean deletePlaylist(Playlist playlist){
        return new DeletePlaylistCommand("DeletePlaylist", playlist, this.playlistRepository).execute();
    }
}
