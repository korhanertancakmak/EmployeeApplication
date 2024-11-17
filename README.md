# [Project Description]()
<div style="text-align:justify">

This project is a Spring Boot application that demonstrates 
the use of **Spring Data JPA**, **Spring Security**, 
and **Thymeleaf** in creating an employee management system. 
The application allows for the management of employees, roles, 
and user authentication using **MySQL** as the database.
Application includes all CRUD operations to manage employee data (`ID`, `first name`, `last name`, `email`).
For the user authentication we use login system using **Spring Security** with roles (`Employee`, `Manager`, `Admin`).
Server-side is rendered **HTML** pages using **Thymeleaf**.
Data integration with **MySQL** to persist data and perform CRUD operations are handled with **Spring Data JPA**.
Authentication and authorization based on user roles are handled by using **Spring Security**.
</div>

## [Technologies Used]()
<div style="text-align:justify">

In this application, the following technologies are used:

* **Spring Boot:** For building the backend. 
* **Spring Data JPA:** For data persistence. 
* **Spring Security:** For user authentication and role-based access control. 
* **Thymeleaf:** For rendering HTML views. 
* **MySQL:** Database to store employee and user data. 
* **Maven:** For dependency management and build automation.
</div>

## [Database Structure]()
<div style="text-align:justify">

The application uses a MySQL database with the following tables:

1. **Employee:** Stores employee information. 
2. **User:** Stores user information for authentication. 
3. **Role:** Stores roles for users (e.g., Employee, Manager, Admin). 
4. **Users_Roles:** Maps users to their respective roles.
</div>

## [Sample Data]()
<div style="text-align:justify">

Here’s an example of data inserted into the database:

* **Employee Table:** Stores employee records such as first name, last name, and email.
* **User Table:** Stores user credentials and enables authentication.
* **Role Table:** Defines roles like "ROLE_EMPLOYEE", "ROLE_MANAGER", "ROLE_ADMIN".
* **Users_Roles Table:** Maps users to roles for proper access control.
</div>

## [Getting Started]()
### [Prerequisites]()
<div style="text-align:justify">

* Java 23 or higher
* MySQL Database 
* Maven 
* Spring Boot 3.x
</div>

### [Clone the Repository]()
<div style="text-align:justify">

````shell
git clone https://github.com/korhanertancakmak/employeeApplication.git
cd employeeApplication
````
</div>

### [Set Up MySQL Database]()
<div style="text-align:justify">

Run the following commands in your **MySQL** environment:

````shell
CREATE DATABASE IF NOT EXISTS `employee_directory`;
USE `employee_directory`;

-- Create employee table
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Insert sample employee data
INSERT INTO `employee` VALUES 
    (1,'Leslie','Andrews','leslie@luv2code.com'),
    (2,'Emma','Baumgarten','emma@luv2code.com'),
    (3,'Avani','Gupta','avani@luv2code.com'),
    (4,'Yuri','Petrov','yuri@luv2code.com'),
    (5,'Juan','Vega','juan@luv2code.com');

-- Create user table
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `enabled` tinyint NOT NULL,
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
);

-- Insert sample user data
INSERT INTO `user` (`username`,`password`,`enabled`, `first_name`, `last_name`, `email`)
VALUES
('john','$2a$10$y3bVe1ZDCX3gQlcDxewL9u.A.BLP2T1Lb4Uf5EN4teYuoPWH2Ogj6',1,'John', 'Doe', 'john@luv2code.com'),
('mary','$2a$10$zINYdUaeFJo4tMWRgs4A0.8ZIW2niKlUm2M3y4PATyYUTUMQdpbgC',1,'Mary', 'Smith', 'mary@luv2code.com'),
('susan','$2a$10$B.0mZlB1WjtBjol4dJ2wk.Z2Zaeny5H1aNfQTlTvQdzb2X9RVVVJe',1,'Susan', 'Public', 'susan@luv2code.com');

-- Create role table
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Insert sample roles data
INSERT INTO `role` (name)
VALUES
('ROLE_EMPLOYEE'),('ROLE_MANAGER'),('ROLE_ADMIN');

-- Create users_roles table to map users to roles
CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
);

-- Map users to roles
INSERT INTO `users_roles` (user_id,role_id)
VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(3, 3);
````
</div>

### [Run the Application]()
<div style="text-align:justify">

To run the application, use **Maven** to build and start the **Spring Boot** application:

````shell
mvn spring-boot:run
````

You can access the application by navigating to `http://localhost:8080`
</div>

### [License]()
<div style="text-align:justify">

This project is licensed under the MIT License - 
see the [LICENSE](https://github.com/korhanertancakmak/EmployeeApplication/blob/master/LICENSE) file for details.
</div>