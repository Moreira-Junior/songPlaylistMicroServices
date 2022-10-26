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

public class FindPlaylistByIdCommand extends HystrixCommand<Playlist> {

    private final String command;
    private String id;
    private String microService;

    public FindPlaylistByIdCommand(String command, String id, String microService) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(command)).andCommandKey(HystrixCommandKey.Factory.asKey(command)));
        this.command = command;
        this.id = id;
        this.microService = microService;
    }

    @Override
    protected Playlist run() throws Exception {
        FeignClient feignClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(FeignClient.class, this.microService);
        Playlist playlist = feignClient.findPlaylistById(id);
        return playlist;
    }
}
