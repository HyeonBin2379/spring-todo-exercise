package com.ssg.todoservice.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration: 해당 클래스가 스프링 빈에 관한 설정 클래스임을 표기
// 스프링 빈으로 등록해야 할 대상이기도 함
@Configuration
public class ModelMapperConfig {

    // @Bean: 메서드에 사용 -> 해당 메서드로부터 반환되는 객체를 스프링 빈으로 등록
    @Bean
    public ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);
        return modelMapper;
    }
}
