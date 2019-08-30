package com.sophia.cloud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public TestConfigure testConfigure() {
        return new TestConfigure();
    }

    @Bean
    public Test2 test2(TestConfigure testConfigure) {
        return new Test2(testConfigure);
    }
}

class Test2 {

    public Test2(TestConfigure testConfigure) {

    }

    public void print(){
        System.out.println(">>>>>>>>>>>>>>>>");
    }
}

class TestConfigure {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}