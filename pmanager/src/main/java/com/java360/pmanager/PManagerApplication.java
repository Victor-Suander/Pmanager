package com.java360.pmanager;

import com.java360.pmanager.infrastructure.config.AppConfigProperties;
import jdk.jfr.Enabled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(
		exclude = { SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class }
)
@EnableConfigurationProperties(AppConfigProperties.class)
public class PManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PManagerApplication.class, args);
	}

}
