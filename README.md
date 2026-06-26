<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ECommerce Application README</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            line-height: 1.6;
        }

        h1 {
            font-size: 2.5em;
            margin-bottom: 10px;
        }

        h2 {
            font-size: 2em;
            margin-top: 30px;
            color: #0d6efd;
        }

        h3 {
            font-size: 1.5em;
            margin-top: 20px;
        }

        p {
            font-size: 1.1em;
        }

        ul {
            margin-left: 20px;
        }

        code {
            background-color: #f4f4f4;
            padding: 3px 6px;
            border-radius: 4px;
        }

        .section {
            margin-bottom: 30px;
        }
    </style>
</head>

<body>

    <h1>🛒 ECommerce Application</h1>
    <p>A full-stack eCommerce backend application built with Java and Spring Boot. This project provides RESTful APIs for managing users, products, categories, orders, and shopping carts.</p>

    <div class="section">
        <h2>🚀 Features</h2>
        <ul>
            <li>User authentication and authorization</li>
            <li>Product management (CRUD)</li>
            <li>Category management</li>
            <li>Shopping cart functionality</li>
            <li>Order processing</li>
            <li>Profile management</li>
            <li>Exception handling</li>
            <li>Secure API endpoints</li>
        </ul>
    </div>

    <div class="section">
        <h2>📂 Project Structure</h2>
        <pre>
controllers/
├── AuthenticationController
├── CategoriesController
├── OrderController
├── ProductsController
├── ProfileController
└── ShoppingCartController

exception/
models/
repository/
security/
service/
ECommerceApplication
        </pre>
    </div>

    <div class="section">
        <h2>🔐 Authentication</h2>
        <p>Handles user registration and login through secure endpoints using Spring Security.</p>
    </div>

    <div class="section">
        <h2>🛍️ Core Functionality</h2>

        <h3>👤 User</h3>
        <ul>
            <li>Register new users</li>
            <li>Login authentication</li>
            <li>Manage user profile</li>
        </ul>

        <h3>📦 Products</h3>
        <ul>
            <li>Create, update, delete products</li>
            <li>View product listings</li>
            <li>Manage stock</li>
        </ul>

        <h3>🗂️ Categories</h3>
        <ul>
            <li>Organize products into categories</li>
        </ul>

        <h3>🛒 Shopping Cart</h3>
        <ul>
            <li>Add products to cart</li>
            <li>Update quantities with stock validation</li>
        </ul>

        <h3>📑 Orders</h3>
        <ul>
            <li>Place orders</li>
            <li>View order history</li>
        </ul>
    </div>

    <div class="section">
        <h2>⚙️ Technologies Used</h2>
        <ul>
            <li>Java</li>
            <li>Spring Boot</li>
            <li>Spring Data JPA</li>
            <li>Spring Security</li>
            <li>REST APIs</li>
        </ul>
    </div>

    <div class="section">
        <h2>▶️ Getting Started</h2>

        <h3>Clone the repository</h3>
        <code>git clone https://github.com/your-username/ecommerce-app.git</code>

        <h3>Build the project</h3>
        <code>mvn clean install</code>

        <h3>Run the application</h3>
        <code>mvn spring-boot:run</code>
    </div>

    <div class="section">
        <h2>🔧 Configuration</h2>
        <code>
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db<br>
spring.datasource.username=your_username<br>
spring.datasource.password=your_password<br>
spring.jpa.hibernate.ddl-auto=update
        </code>
    </div>

    <div class="section">
        <h2>📌 Example Endpoints</h2>
        <ul>
            <li><code>POST /auth/register</code> – Register user</li>
            <li><code>POST /auth/login</code> – Login user</li>
            <li><code>GET /products</code> – Get all products</li>
            <li><code>POST /cart</code> – Add item to cart</li>
            <li><code>POST /orders</code> – Place order</li>
        </ul>
    </div>

    <div class="section">
        <h2>🎯 Future Improvements</h2>
        <ul>
            <li>Frontend integration (React / Vue)</li>
            <li>Payment gateway integration</li>
            <li>Admin dashboard</li>
            <li>Wishlist feature</li>
            <li>Order tracking system</li>
        </ul>
    </div>

    <div class="section">
        <h2>👤 Author</h2>
        <p>Noah Logan</p>
    </div>

</body>
</html>
