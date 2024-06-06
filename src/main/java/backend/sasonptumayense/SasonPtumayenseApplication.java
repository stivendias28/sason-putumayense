package backend.sasonptumayense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SasonPtumayenseApplication {

	public static void main(String[] args) {
		//initialPort = 8080;
		//
		//while(true) {
		//	try {
		//		System.out.println("server.port: " + initialPort);
		//		System.setProperty("server.port", String.valueOf(initialPort));
		//		ConfigurableApplicationContext context = SpringApplication.run(BackendSasonPutumayenseApplication.class,);
		//		break;
		//	} catch (WebServerException e) {
		//		initialPort = (initialPort == 8096)? 8080 : initialPort + 1;
		//	}
		//}
				
		System.setProperty("server.port", "8080");
		SpringApplication.run(SasonPtumayenseApplication.class, args);
	}

}
