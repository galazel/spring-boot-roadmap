package com.example.password_hasher_cli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PasswordHasherCliApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PasswordHasherCliApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = "galagar123";
		System.out.println("5 Hashed Password: ");
		for(int i = 0; i < 5; ++i){
			System.out.println(encoder.encode(password));
		}
		String hashedPassword = encoder.encode(password);
		System.out.println("Password match: "+encoder.matches(password, hashedPassword));
		System.out.println("BCrypt vs Argon2");
		Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder(16,32, 1, 1 << 14, 3);
		String password2 = argon2PasswordEncoder.encode(password);
		System.out.println("Argon Hashed: "+argon2PasswordEncoder.encode(password2));
		System.out.println("Argon2 Password match: "+argon2PasswordEncoder.matches(password, password2));
	}
}
