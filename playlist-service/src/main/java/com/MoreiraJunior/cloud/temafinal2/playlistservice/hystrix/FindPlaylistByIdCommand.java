package com.MoreiraJunior.cloud.temafinal2.playlistservice.hystrix;

import com.MoreiraJunior.cloud.temafinal2.playlistservice.model.Playlist;
import com.MoreiraJunior.cloud.temafinal2.playlistservice.repository.PlaylistRepository;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.Arrays;
import java.util.Optional;

public class FindPlaylistByIdCommand extends HystrixCommand<Optional<Playlist>> {

    private final String command;
    private String id;
    private final PlaylistRepository playlistRepository;

    public FindPlaylistByIdCommand(String command, String id, PlaylistRepository playlistRepository) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(command)).andCommandKey(HystrixCommandKey.Factory.asKey(command)));
        this.command = command;
        this.id = id;
        this.playlistRepository = playlistRepository;
    }


    @Override
    protected Optional<Playlist> run() throws Exception {
        return playlistRepository.findById(id);
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
