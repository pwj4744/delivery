package org.delivery.api.config.jpa;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.delivery.db")
@EnableJpaRepositories(basePackages = "org.delivery.db")
public class JpaConfig {
    //자신의 하위 패키지에서 여러가지 어노테이션을 찾아서 빈을 등록하는게 default , 멀티모듈은 패키지가 멀리 떨어져있고 이름도 다르다. 그것을 jpa로 등록할떄 Config.
}
