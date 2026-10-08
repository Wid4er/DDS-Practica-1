package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ejemplo.practica1.Practica1Application;

@SpringBootTest(classes = Practica1Application.class, properties = {
        "spring.datasource.url=jdbc:h2:mem:practica1-test;DB_CLOSE_DELAY=-1",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.h2.console.enabled=false"
})
class Practica1ApplicationTests {

	@Test
	void contextLoads() {
	}

}
