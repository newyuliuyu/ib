package com.ez.ib.web.config;


import com.ez.common.spring.SpringContextUtil;
import com.ez.common.util.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * ClassName: CommonConfig <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-31 上午10:21 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Configuration
@PropertySource("classpath:ezconfig.properties")
public class CommonConfig {

    @Bean
    public SpringContextUtil springContextUtil() {
        return new SpringContextUtil();
    }

    @Bean
    public IdGenerator idGenerator() {
        return new IdGenerator();
    }

}
