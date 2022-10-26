package com.MoreiraJunior.cloud.temafinal2.songservice.hystrix;

import com.MoreiraJunior.cloud.temafinal2.songservice.model.Song;
import com.MoreiraJunior.cloud.temafinal2.songservice.repository.SongRepository;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.ArrayList;
import java.util.List;

public class FindByIdsCommand extends HystrixCommand<List<Song>> {

    private final String command;
    private List<String> ids;
    private final SongRepository songRepository;

    public FindByIdsCommand(String command, List<String> ids, SongRepository songRepository) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(command)).andCommandKey(HystrixCommandKey.Factory.asKey(command)));
        this.command = command;
        this.ids = ids;
        this.songRepository = songRepository;
    }

    @Override
    protected List<Song> run() throws Exception {
        Iterable<String> strings = ids;
        return (List<Song>) songRepository.findAllById(strings);
    }

    @Override
    protected List<Song> getFallback() {
        List<Song> song = new ArrayList<>();
        song.add(new Song("Error during find songs!"));
        if(isCircuitBreakerOpen()){
            HystrixCommandProperties.Setter()
                    .withCircuitBreakerForceClosed(true);
        }
        return song;
    }
}
