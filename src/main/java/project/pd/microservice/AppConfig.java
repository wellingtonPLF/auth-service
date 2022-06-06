package project.pd.microservice;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration 
//@EnableEurekaServer
public class AppConfig {
	
   @Bean 
   public WebMvcConfigurer corsConfigurer() {
       return new WebMvcConfigurer() {
           @Override public void addCorsMappings(CorsRegistry registry) {
               registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
           }
       };
   }
   
   //Used to send and receive messages of any type (example: User and Decision)
   @Bean
   public Jackson2JsonMessageConverter messageConverter() {
	   return new Jackson2JsonMessageConverter();
   }
}