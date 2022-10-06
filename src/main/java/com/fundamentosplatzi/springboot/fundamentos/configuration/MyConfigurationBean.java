package com.fundamentosplatzi.springboot.fundamentos.configuration;

import com.fundamentosplatzi.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    @Bean
    public MyBean beanOperation(){
        return new MyBean2Impl();
    }

    @Bean
    public MyOperation beanOperationOperation(){
        return new MyOperation2Impl();
    }

    @Bean
    public MyBeanWithDependency beanOperationOperationWithDependency(MyOperation myOperation){
        return new MyBeanWithDependencyImpl(myOperation);
    }

    @Bean
    public MyOtherBean beanOtherOperation(){
        return new MyOtherBeanImpl();
    }
}
