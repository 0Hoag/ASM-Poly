# 🛒 E-Commerce
A modern web-based platform designed to connect buyers and sellers, enabling seamless online transactions and content sharing.

## 🚀 About  
E-commerce Platform is a full-stack application built to facilitate online commerce. The platform allows users to:
Browse and purchase products effortlessly.
Authenticate securely using Keycloak.
Manage their profiles with user-friendly interfaces.
Developed using a microservices architecture, the project ensures scalability, maintainability, and adaptability for future enhancements.

## ✨ Features  
- User authentication with platform (Keycloak)
- Create, edit, and delete posts with multimedia uploads (images/videos)  
- Privacy settings and content moderation to manage posts visibility  
- Payment gateway integration with VNPay for transactions  
- Cloud storage for media files via Cloudinary  

## 🛠️ Technologies  
- **Backend**: Spring boot 
- **Frontend**: Vue
- **Database**: SQL  
- **Authentication**: OAuth 2.0 (Keycloak as Authorization Server), JWT for token-based authentication
- **Payment Integration**: VNPay
- **Cloud Storage**: Cloudinary

## 1. Tools
#### Mockery
- **Used** for generating mocks for testing
- **Installation**: 
  - Following the instructions [here](https://spring.io/guides/gs/spring-boot)
#### Make
- **Used** for running the project
- **Installation**:
- **Windows**:
  - Download Make for Windows from [here](https://www.oracle.com/java/technologies/downloads/)
  - Install the executable
  - Add Make to your system's environment variables PATH
  ```bash
  JAVA_HOME = C:\Program Files\Java\jdk-17
  PATH = %PATH%;%JAVA_HOME%\bin
  ```
  
- **Linux**:
  ```bash
  sudo apt update
  sudo apt install openjdk-17-jdk
  java -version
  ```

- **macOS**:
  ```bash
  brew install openjdk@17
  echo 'export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
  source ~/.zshrc
  java -version
  ```

- **Usage**:
  - Install in form [here](https://maven.apache.org/download.cgi)

## 2. Project Structure
Clean Architecture is used to structure the project.

![Project Structure](https://raw.githubusercontent.com/bxcodec/go-clean-arch/master/clean-arch.png)

- `cmd`: Contains the main packages for the API and CLI
- `internal`: Contains the core logic of the project
- `pkg`: Contains the external packages

### 2.1. `cmd`
- `api`: Contains the main package for the API
- `consumer`: Contains the main package for the Message Queue consumer

### 2.2. `internal`
- `model`: Contains the data models
- `domain`: Contains the domain logic
  + `delivery`: Contains the delivery layer logic, responsible for handling the HTTP and others requests
  + `usecase`: Contains the use case layer logic, responsible for handling the business logic
  + `repository`: Contains the repository layer logic, responsible for handling the data storage and retrieval
- `middleware`: Contains the middleware
- `appconfig`: Contains the application configuration
- `httpserver`: Contains the HTTP server implementation


