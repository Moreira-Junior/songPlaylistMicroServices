package com.MoreiraJunior.cloud.temafinal2.playlistservice.config;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> hystrixMetrics() {
        return new ServletRegistrationBean<>(new HystrixMetricsStreamServlet(), "/hystrix.stream");
    }

    @Bean
    public EurekaClient eurekaClient(){
        InstanceInfo instanceInfo = new EurekaConfigBasedInstanceInfoProvider(new MyDataCenterInstanceConfig()).get();
        ApplicationInfoManager applicationInfoManager = new ApplicationInfoManager(new MyDataCenterInstanceConfig(), instanceInfo);
        EurekaClient eurekaClient = new DiscoveryClient(applicationInfoManager, new DefaultEurekaClientConfig());
        applicationInfoManager.setInstanceStatus(InstanceInfo.InstanceStatus.UP);
        return eurekaClient;
    }
}
