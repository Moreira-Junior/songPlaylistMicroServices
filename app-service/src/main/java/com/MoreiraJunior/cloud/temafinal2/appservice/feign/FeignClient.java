package com.MoreiraJunior.cloud.temafinal2.appservice.feign;

import com.MoreiraJunior.cloud.temafinal2.appservice.model.Playlist;
import com.MoreiraJunior.cloud.temafinal2.appservice.model.Song;
import feign.Param;
import feign.RequestLine;



import java.util.List;

public interface FeignClient {

    @RequestLine("GET /song/name/{name}")
    Song findSongByName(@Param("name")String name);

    @RequestLine("GET /song/id/{id}")
    Song findSongById(@Param("id")String id);

    @RequestLine("GET /song")
    List<Song> findAllSongs();

    @RequestLine(("POST /song/{name}"))
    Song saveSong(@Param("name")String name);

    @RequestLine("DELETE /song/name/{name}")
    Object deleteSong(@Param("name")String name);

    @RequestLine("DELETE /song/id/{id}")
    Object deleteSongById(@Param("id")String id);

    @RequestLine("GET /playlist/id/{id}")
    Playlist findPlaylistById(@Param("id") String id);

    @RequestLine("GET /song/ids/{ids}")
    List<Song> findSongsByIds(@Param("ids") List<String> ids);

    @RequestLine("POST /playlist/{ids}")
    Object savePlaylist(@Param("ids") List<String> ids);

    @RequestLine("GET /playlist")
    List<Playlist> findPlaylists();

    @RequestLine("DELETE /playlist/{id}")
    Object deletePlaylist(@Param("id")String id);
}
