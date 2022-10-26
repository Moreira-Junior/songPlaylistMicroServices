package com.MoreiraJunior.cloud.temafinal2.songservice.hystrix;

import com.MoreiraJunior.cloud.temafinal2.songservice.model.Song;
import com.MoreiraJunior.cloud.temafinal2.songservice.repository.SongRepository;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.ArrayList;
import java.util.List;

public class DeleteByIdCommand extends HystrixCommand<Boolean> {

    private final String command;
    private String id;
    private final SongRepository songRepository;

    public DeleteByIdCommand(String command, String id, SongRepository songRepository) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(command)).andCommandKey(HystrixCommandKey.Factory.asKey(command)));
        this.command = command;
        this.id = id;
        this.songRepository = songRepository;
    }

    @Override
    protected Boolean run() throws Exception {
        songRepository.deleteById(id);
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
