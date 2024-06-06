package backend.sasonptumayense.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@RequestMapping("/api/v1/")
@CrossOrigin(origins = {"http://localhost:3000"})
public class GlobalMappingConfig {}
