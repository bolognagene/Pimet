package com.nohpe.pimet.fronted.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        //1 Create mybatisPlusInterceptor
        MybatisPlusInterceptor mpInterceptor=new MybatisPlusInterceptor();

        //2 Add PaginationInnerInterceptor
        mpInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());

        return mpInterceptor;
    }
}
