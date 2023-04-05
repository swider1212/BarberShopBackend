package pl.ibd.cloud.itscloudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@ServletComponentScan
@SpringBootApplication
@Configuration
public class ItSCloudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItSCloudyApplication.class, args);
	}
}
