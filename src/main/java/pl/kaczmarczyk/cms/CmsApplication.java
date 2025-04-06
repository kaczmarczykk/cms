package pl.kaczmarczyk.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsApplication.class, args);
	}

}
