package com.MoreiraJunior.cloud.temafinal2.appservice.hystrix;

import com.MoreiraJunior.cloud.temafinal2.appservice.feign.FeignClient;
import com.MoreiraJunior.cloud.temafinal2.appservice.model.Playlist;
import com.MoreiraJunior.cloud.temafinal2.appservice.model.Song;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindPlaylistsCommand extends HystrixCommand<List<Playlist>> {

    private final String command;
    private String microService;

    public FindPlaylistsCommand(String command, String microService) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(command)).andCommandKey(HystrixCommandKey.Factory.asKey(command)));
        this.command = command;
        this.microService = microService;
    }

    @Override
    protected List<Playlist> run() throws Exception {
        FeignClient feignClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(FeignClient.class, this.microService);
        List<Playlist> playlists = feignClient.findPlaylists();
        return Collections.unmodifiableList(playlists);
    }
}
