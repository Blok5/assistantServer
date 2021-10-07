package ru.sberbank.assistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.sql.Time;
import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class AssistantServerApplication {

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));
	}

	public static void main(String[] args) {
		SpringApplication.run(AssistantServerApplication.class, args);
	}

}
