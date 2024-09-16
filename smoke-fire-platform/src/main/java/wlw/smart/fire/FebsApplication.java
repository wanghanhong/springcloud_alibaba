package wlw.smart.fire;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.unit.DataSize;
import javax.servlet.MultipartConfigElement;

/**
 * @description:    java类作用描述
 * @author:         SanDuo
 * @date:           2020/6/10 17:35
 * @version:        1.0
 */

@EnableTransactionManagement
@EnableScheduling
@EnableAsync
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class FebsApplication{

    public static void main(String[] args) {
        new SpringApplicationBuilder(FebsApplication.class)
                .run(args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize(DataSize.ofKilobytes(5120));
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.ofKilobytes(5120));
        return factory.createMultipartConfig();
    }


}
