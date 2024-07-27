# Semokin Care Store REST API

This project is a REST API for a skincare store, built using Java Spring. It is currently under development and will be updated continuously until the final version is released.

### Introduction

This project aims to provide a comprehensive REST API for managing a skincare store. It includes functionalities for managing products, orders, customers, reviews, and more. The API is designed to be scalable and easy to integrate with various front-end applications.

### Entity Models

**Brand**: Represents the brand of skincare products.
**Cart**: Represents a shopping cart.
**CartItem**: Represents an item in a shopping cart.
**Category**: Represents a product category.
**Customer**: Represents a customer.
**Order**: Represents an order placed by a customer.
**OrderItem**: Represents an item in an order.
**OrderStatus**: Represents the status of an order.
**Payment**: Represents payment information.
**Product**: Represents a product in the store.
**ProductPicture**: Represents pictures of products.
**ProfilePicture**: Represents a customer's profile picture.
**Review**: Represents a review of a product.
**ReviewPicture**: Represents pictures in a review.
**Role**: Represents user roles.
**Staff**: Represents a staff member.
**StaffPosition**: Represents the position of a staff member.
**User**: Represents a user of the system.

### Getting Started

To get started with the project, follow these steps:

1. **Clone the repos:**
   ```bash
   git clone git@github.com:sandiindika/project-semokin-care.git
   cd project-semokin-care
   ```
   
2. **Build the project:**
   ```bash
   ./mvnw clean install
   ```

3. **Run the app:**
   ```bash
   ./mvnw spring-boot:run
   ```

### Endpoints

Detailed API documentation will be provided soon.

### Technologies Used

- Java Spring Boot
- Maven
- JPA/Hibernate
- H2 Database (for development)
- Swagger (for API documentation)

### Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

### License

This project is licensed under the MIT License.
