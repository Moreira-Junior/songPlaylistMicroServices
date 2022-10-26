package com.MoreiraJunior.cloud.temafinal2.appservice.hystrix;

import com.MoreiraJunior.cloud.temafinal2.appservice.feign.FeignClient;
import com.MoreiraJunior.cloud.temafinal2.appservice.model.Song;
import com.google.gson.stream.JsonReader;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;

public class SavePlaylistCommand extends HystrixCommand<Object> {

    private final String command;
    private List<String> strings;
    private String microService;

    public SavePlaylistCommand(String command, List<String> strings, String microService) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(command)).andCommandKey(HystrixCommandKey.Factory.asKey(command)));
        this.command = command;
        this.strings = strings;
        this.microService = microService;
    }

    @Override
    protected Object run() throws Exception {
        FeignClient feignClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(FeignClient.class, this.microService);
        return feignClient.savePlaylist(strings);
    }
}
