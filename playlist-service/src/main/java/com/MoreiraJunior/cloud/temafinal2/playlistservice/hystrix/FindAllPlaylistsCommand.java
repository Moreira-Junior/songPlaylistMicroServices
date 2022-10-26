package com.MoreiraJunior.cloud.temafinal2.playlistservice.hystrix;

import com.MoreiraJunior.cloud.temafinal2.playlistservice.model.Playlist;
import com.MoreiraJunior.cloud.temafinal2.playlistservice.repository.PlaylistRepository;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllPlaylistsCommand extends HystrixCommand<List<Playlist>> {

    private final String command;
    private final PlaylistRepository playlistRepository;

    public FindAllPlaylistsCommand(String command, PlaylistRepository playlistRepository) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(command)).andCommandKey(HystrixCommandKey.Factory.asKey(command)));
        this.command = command;
        this.playlistRepository = playlistRepository;
    }


    @Override
    protected List<Playlist> run() throws Exception {
        return playlistRepository.findAll();
    }

    @Override
    protected List<Playlist> getFallback() {
        if(isCircuitBreakerOpen()){
            HystrixCommandProperties.Setter()
                    .withCircuitBreakerForceClosed(true);
        }
        return new ArrayList<>();
    }
}
