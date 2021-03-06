package com.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.Objects;

/**
 * Created by gary on 16/11/29.
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryApp {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryApp.class, args);
	}

	private void method1() {
		System.out.println("method1");
	}

	public void method2() {
		System.out.println("method2");
		Objects.equals("", "");

	}
}
