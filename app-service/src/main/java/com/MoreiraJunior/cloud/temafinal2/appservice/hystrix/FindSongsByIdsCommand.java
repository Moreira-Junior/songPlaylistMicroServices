package com.MoreiraJunior.cloud.temafinal2.appservice.hystrix;

import com.MoreiraJunior.cloud.temafinal2.appservice.feign.FeignClient;
import com.MoreiraJunior.cloud.temafinal2.appservice.model.Song;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;

public class FindSongsByIdsCommand extends HystrixCommand<List<Song>> {

    private final String command;
    private List<String> strings;
    private String microService;

    public FindSongsByIdsCommand(String command, List<String> strings, String microService) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(command)).andCommandKey(HystrixCommandKey.Factory.asKey(command)));
        this.command = command;
        this.strings = strings;
        this.microService = microService;
    }

    @Override
    protected List<Song> run() throws Exception {
        FeignClient feignClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(FeignClient.class, this.microService);
        List<Song> songs = feignClient.findSongsByIds(strings);
        return songs;
    }
}
