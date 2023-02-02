package org.nickhoefle.freezeoutvmc;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration
public class FreezeoutBandWebsiteApplication {

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(FreezeoutBandWebsiteApplication.class, args);
	}

	public static void restart() {
		System.out.println("Restarting spring boot app in 3 seconds !!! ");
		try {
			Thread.sleep(3000L);
		} catch (Exception e) {
		}

		ApplicationArguments args = context.getBean(ApplicationArguments.class);
		Thread thread = new Thread(() -> {
			context.close();
			context = SpringApplication.run(FreezeoutBandWebsiteApplication.class, args.getSourceArgs());
		});
		thread.setDaemon(false);
		System.out.println("Restart done.");
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Spring Boot working.");
		};
	}

}

