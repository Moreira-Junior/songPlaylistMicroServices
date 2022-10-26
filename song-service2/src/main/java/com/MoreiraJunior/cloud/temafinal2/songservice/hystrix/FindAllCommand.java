package com.MoreiraJunior.cloud.temafinal2.songservice.hystrix;

import com.MoreiraJunior.cloud.temafinal2.songservice.model.Song;
import com.MoreiraJunior.cloud.temafinal2.songservice.repository.SongRepository;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.ArrayList;
import java.util.List;


public class FindAllCommand extends HystrixCommand<List<Song>> {

    private final String command;
    private final SongRepository songRepository;

    public FindAllCommand(String command, SongRepository songRepository) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(command)).andCommandKey(HystrixCommandKey.Factory.asKey(command)));
        this.command = command;
        this.songRepository = songRepository;
    }

    @Override
    protected List<Song> run() throws Exception {
        return songRepository.findAll();
    }


    @Override
    protected List<Song> getFallback() {
        List<Song> song = new ArrayList<>();
        song.add(new Song("Error during find all songs!"));
        if(isCircuitBreakerOpen()){
            HystrixCommandProperties.Setter()
                    .withCircuitBreakerForceClosed(true);
        }
        return song;
    }

}
