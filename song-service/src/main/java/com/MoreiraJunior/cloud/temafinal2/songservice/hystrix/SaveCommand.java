package com.MoreiraJunior.cloud.temafinal2.songservice.hystrix;

import com.MoreiraJunior.cloud.temafinal2.songservice.model.Song;
import com.MoreiraJunior.cloud.temafinal2.songservice.repository.SongRepository;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.Optional;

public class SaveCommand extends HystrixCommand<Optional<Song>> {

    private final String command;
    private String name;
    private final SongRepository songRepository;

    public SaveCommand(String command, String name, SongRepository songRepository) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(command)).andCommandKey(HystrixCommandKey.Factory.asKey(command)));
        this.command = command;
        this.name = name;
        this.songRepository = songRepository;
    }

    @Override
    protected Optional<Song> run() throws Exception {
        return Optional.of(songRepository.save(new Song(name)));
    }

    @Override
    protected Optional<Song> getFallback() {
        if(isCircuitBreakerOpen()){
            HystrixCommandProperties.Setter()
                    .withCircuitBreakerForceClosed(true);
        }
        return Optional.empty();
    }
}
