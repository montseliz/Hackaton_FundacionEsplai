# Login & Register Server

This project is a server API with authentication based on JSON Web Tokens (JWT). The server provides three main endpoints: register, login and users. Users must first register before they can log in and access users information stored in the database.

JWT authentication is a popular and secure mechanism for protecting an application's resources. It allows users to log in and receive a JWT token, which can be used to authenticate subsequent requests. The token contains information about the user, such as user ID and roles, which allows the application to validate requests without having to query the database each time.

## Stack

- Java 19
- Spring Boot 3.0.6
- Gradle-Kotlin
- MongoDB
- JWT Authentication
- Open Api Documentation with Swagger

## Installation and Endpoints

- Clone the repository. 

- Import the project into your IDE. 

- Create the "users" database and the "users" collection in MongoDB.

- Build and run the project.

- Navigate to the register path: http://localhost:9005/Login&RegisterServer/register

- Obtain the token authentication: http://localhost:9005/Login&RegisterServer/login

- Get access with JWT to view all registered users: http://localhost:9005/Login&RegisterServer/users 

- View the Open Api Documentation at: http://localhost:9005/swagger-ui/index.html

## Media

**MongoDB:**

![mongo.PNG](media%2Fmongo.PNG)

**Swagger Documentation:**

![openapi1.PNG](media%2Fopenapi1.PNG)
![openapi2.PNG](media%2Fopenapi2.PNG)
![openapi3.PNG](media%2Fopenapi3.PNG)

**Postman Endpoints:**

- Register: 

![register.PNG](media%2Fregister.PNG)

- Login: 

![login.PNG](media%2Flogin.PNG)

- Users: 

![users.PNG](media%2Fusers.PNG)
