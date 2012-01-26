package com.wiecia.springtest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.wiecia.springtest.service" })
public class ApplicationConfig {

}
