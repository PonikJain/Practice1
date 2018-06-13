package com.collegeportal.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Tester {
	public static void main(String[] args) {
        String password = "password";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(password));
}
}
