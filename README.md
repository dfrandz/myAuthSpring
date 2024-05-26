# Spring Boot Project with Spring Security and OTP Email Verification

This project is a starter template for a Spring Boot application that includes user authentication with Spring Security and an OTP (One-Time Password) email verification system.

## Features

- Spring Boot
- Spring Security for authentication and authorization
- OTP (One-Time Password) system for email verification
- JavaMailSender for sending emails

## Prerequisites

- Java 17 or higher
- Maven
- An SMTP server for sending emails (Gmail SMTP)

## Getting Started

### Clone the repository

git clone https://github.com/dfrandz/myAuthSpring
cd myAuthSpring

### Database configuration
- SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/yourdb
- SPRING_DATASOURCE_USERNAME=root
- SPRING_DATASOURCE_PASSWORD=password

### Email configuration
- SPRING_MAIL_HOST=smtp.gmail.com
- SPRING_MAIL_PORT=587
- SPRING_MAIL_USERNAME=your-email@gmail.com
- SPRING_MAIL_PASSWORD=your-email-password
- SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH=true
- SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE=true

### Application configuration
- APP_URL=http://localhost:8080

### Build and run
- ./mvnw clean install
- ./mvnw spring-boot:run



