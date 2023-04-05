package pl.ibd.cloud.itscloudy;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ApplicationConfiguration {

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
//                registry.addMapping("/clients/**").allowedOrigins("http://localhost:3000");
//                registry.addMapping("/services/**").allowedOrigins("http://localhost:3000");
//                registry.addMapping("/reservations/**").allowedOrigins("http://localhost:3000");
//                registry.addMapping("/barbershops/**").allowedOrigins("http://localhost:3000");
//                registry.addMapping("/services/**").allowedOrigins("http://localhost:3000");
//            }
//        };
//    }
}
