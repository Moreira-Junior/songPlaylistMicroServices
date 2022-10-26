package com.MoreiraJunior.cloud.temafinal2.playlistservice.hystrix;

import com.MoreiraJunior.cloud.temafinal2.playlistservice.model.Playlist;
import com.MoreiraJunior.cloud.temafinal2.playlistservice.repository.PlaylistRepository;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class DeletePlaylistCommand extends HystrixCommand<Boolean> {

    private final String command;
    private Playlist playlist;
    private final PlaylistRepository playlistRepository;

    public DeletePlaylistCommand(String command, Playlist playlist, PlaylistRepository playlistRepository) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(command)).andCommandKey(HystrixCommandKey.Factory.asKey(command)));
        this.command = command;
        this.playlist = playlist;
        this.playlistRepository = playlistRepository;
    }


    @Override
    protected Boolean run() throws Exception {
        playlistRepository.delete(playlist);
        return true;
    }

    @Override
    protected Boolean getFallback() {
        if(isCircuitBreakerOpen()){
            HystrixCommandProperties.Setter()
                    .withCircuitBreakerForceClosed(true);
        }
        return false;
    }
}
