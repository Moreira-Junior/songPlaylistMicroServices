package com.MoreiraJunior.cloud.temafinal2.appservice.hystrix;

import com.MoreiraJunior.cloud.temafinal2.appservice.feign.FeignClient;
import com.MoreiraJunior.cloud.temafinal2.appservice.model.Playlist;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;

import java.util.Arrays;

public class DeletePlaylistCommand extends HystrixCommand<Object> {

    private final String command;
    private String id;
    private String microService;

    public DeletePlaylistCommand(String command, String id, String microService) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(command)).andCommandKey(HystrixCommandKey.Factory.asKey(command)));
        this.command = command;
        this.id = id;
        this.microService = microService;
    }

    @Override
    protected Object run() throws Exception {
        FeignClient feignClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(FeignClient.class, this.microService);
        feignClient.deletePlaylist(id);
        Playlist playlist = new Playlist();
        playlist.setId(id);
        return playlist;
    }
}
