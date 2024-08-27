package org.delivery.api.config.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper) {
        //스프링 컨테이너가 오브젝트 매퍼 빈을 찾아서 그것이 스웨거로 들어온다. (오브젝트 매퍼가 스네이크 케이스니까 그것을 그대로 리턴)
        return new ModelResolver(objectMapper);
    }
}
