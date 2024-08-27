package org.delivery.api.config.objectmapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper(){
        var objectMapper = new ObjectMapper();

        objectMapper.registerModule(new Jdk8Module()); // jdk 8 버전 이후 클래스 들을 파싱하거나 시리얼라이즈 하기위한 모듈이 들어가있음

        objectMapper.registerModule(new JavaTimeModule()); // << local date 시리즈

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 모르는 컬럼, 제이슨 밸류에 대해서는 무시한다. 모르는건 무시하고 나머지 파싱.

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 날짜 관련 직렬화 설정
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 스네이크 케이스
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());

        //스프링부트가 스타트가 될떄 오브젝트매퍼에 대한 빈이 정의가 안되어있으면 본인이 디폴트로 만드는데 / 설정이 되어있으면 설정된 값으로 만든다.
        return objectMapper;
    }
}
