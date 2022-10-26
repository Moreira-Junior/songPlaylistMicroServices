package com.MoreiraJunior.cloud.temafinal2.appservice;

import com.MoreiraJunior.cloud.temafinal2.appservice.service.AppService;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AppServiceTest {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private AppService appService;


}
