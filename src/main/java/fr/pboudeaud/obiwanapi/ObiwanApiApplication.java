package fr.pboudeaud.obiwanapi;

import fr.pboudeaud.obiwanapi.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class ObiwanApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObiwanApiApplication.class, args);
    }

}
