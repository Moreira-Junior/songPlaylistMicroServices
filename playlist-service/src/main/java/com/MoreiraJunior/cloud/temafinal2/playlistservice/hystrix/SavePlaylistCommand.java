package com.MoreiraJunior.cloud.temafinal2.playlistservice.hystrix;

import com.MoreiraJunior.cloud.temafinal2.playlistservice.model.Playlist;
import com.MoreiraJunior.cloud.temafinal2.playlistservice.repository.PlaylistRepository;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SavePlaylistCommand extends HystrixCommand<Optional<Playlist>> {

    private final String command;
    private List<String> ids;
    private final PlaylistRepository playlistRepository;

    public SavePlaylistCommand(String command, List<String> ids, PlaylistRepository playlistRepository) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(command)).andCommandKey(HystrixCommandKey.Factory.asKey(command)));
        this.command = command;
        this.ids = ids;
        this.playlistRepository = playlistRepository;
    }


    @Override
    protected Optional<Playlist> run() throws Exception {
        Playlist playlist = new Playlist();
        playlist.setSongs(ids);
        return Optional.of(playlistRepository.save(playlist));
    }

    @Override
    protected Optional<Playlist> getFallback() {
        if(isCircuitBreakerOpen()){
            HystrixCommandProperties.Setter()
                    .withCircuitBreakerForceClosed(true);
        }
        return Optional.empty();
    }
}
