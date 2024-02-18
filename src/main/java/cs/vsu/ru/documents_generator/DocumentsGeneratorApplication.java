package cs.vsu.ru.documents_generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DocumentsGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentsGeneratorApplication.class, args);
    }

}
