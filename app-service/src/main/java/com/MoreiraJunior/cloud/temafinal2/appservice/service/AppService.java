package com.MoreiraJunior.cloud.temafinal2.appservice.service;

import com.MoreiraJunior.cloud.temafinal2.appservice.exceptions.PlaylistServiceNotFoundException;
import com.MoreiraJunior.cloud.temafinal2.appservice.exceptions.SongServiceNotFoundException;
import com.MoreiraJunior.cloud.temafinal2.appservice.hystrix.*;
import com.MoreiraJunior.cloud.temafinal2.appservice.model.Playlist;
import com.MoreiraJunior.cloud.temafinal2.appservice.model.Song;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppService {

    @Autowired
    private EurekaClient eurekaClient;
    private final String songService = "song-service";
    private final String playlistService = "playlist-service";


    public EurekaClient getEurekaClient(){
        return this.eurekaClient;
    }

    private String getServerIP(String microservice){
        try{
            List<InstanceInfo> list = getEurekaClient().getApplication(microservice.toUpperCase()).getInstances();
            List<Server> serverList = list.stream().map(instance -> (new Server(instance.getIPAddr(), instance.getPort()))).collect(Collectors.toList());
            BaseLoadBalancer baseLoadBalancer = new BaseLoadBalancer();
            baseLoadBalancer.addServers(serverList);
            Server server = baseLoadBalancer.chooseServer(serverList);
            String serverPort =  "http://" + server.getHost() + ":" + server.getPort();
            return serverPort;
        }catch(Exception e){
            if(microservice.equals(this.playlistService)){
                throw new PlaylistServiceNotFoundException();
            } else if (microservice.equals(this.songService)) {
                throw new SongServiceNotFoundException();
            }
            throw new RuntimeException("Could not get Microservice IP:PORT. EX: " + e);
        }
    }
    public Song findSongByName(String name){
        return new FindSongByNameCommand("FindSongByName", name, getServerIP(this.songService)).execute();
    }

    public Song findSongById(String id){
        return new FindSongByIdCommand("FindSongById", id, getServerIP(this.songService)).execute();
    }

    public List<Song> findSongsByIds(List<String> strings){
        return new FindSongsByIdsCommand("FindSongsByIds", strings, getServerIP(this.songService)).execute();
    }

    public List<Song> findAllSongs(){
        return new FindAllSongsCommand("FindAllSongs", getServerIP(this.songService)).execute();
    }

    public Song saveSong(String name){
        return new SaveSongCommand("SaveSong", name, getServerIP(this.songService)).execute();
    }

    public Object deleteSong(String name){
        return new DeleteSongCommand("DeleteSong", name, getServerIP(this.songService)).execute();
    }

    public Object deleteSongById(String id){
        return new DeleteSongByIdCommand("DeleteSongById", id, getServerIP(this.songService)).execute();
    }

    public Playlist findPlaylistById(String id){
        return new FindPlaylistByIdCommand("PlaylistById", id, getServerIP(this.playlistService)).execute();
    }

    public Object savePlaylist(List<String> strings){
        return new SavePlaylistCommand("SavePlaylist", strings, getServerIP(this.playlistService)).execute();
    }

    public List<Playlist> findPlaylists(){
        return new FindPlaylistsCommand("FindPlaylists", getServerIP(this.playlistService)).execute();
    }

    public Object deletePlaylist(String id){
        return new DeletePlaylistCommand("DeletePlaylist", id, getServerIP(this.playlistService)).execute();
    }
}
