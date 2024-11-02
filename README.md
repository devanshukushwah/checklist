# Checklist - JSP Project

## Overview
**Checklist** is a web application built using Java Server Pages (JSP) to manage tasks. This project features task creation, updates, and deletion, along with task history and user authentication.

## Features
- Create tasks
- Mark tasks as completed
- History of tasks
- User authentication
- Responsive design with Semantic UI

## Technologies Used
- **Java** (OpenJDK 8 or higher)
- **JSP/Servlets**
- **PostgreSQL** (Database)
- **Maven** (Dependency management)
- **Semantic UI** (UI Components)
- **Docker** (Containerization)

## Prerequisites
- **Java** (JDK 8 or higher)
- **Apache Tomcat** (v9.0 or higher)
- **PostgreSQL** (v5.7 or higher)
- **Maven** (v3.8.6 or higher)
- **Docker** (Optional for containerization)

## Installation and Setup

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/your-repo-name.git
cd your-repo-name
```

### 2. Setup the Database
- Create a MySQL database:
```sql
CREATE DATABASE checklist_db;
```
- Import the SQL script to create tables:
```bash
mysql -u username -p checklist_db < checklist_db.sql
```

### 3. Configure the Project
- Update database configuration in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/checklistDatabaseName
spring.datasource.username=username
spring.datasource.password=password
```

### 4. Build the Project
```bash
mvn clean install
```

### 5. Deploy to Tomcat
- Copy the WAR file (`target/checklist.war`) to the `webapps` folder of your Tomcat installation.

### 6. Run the Application
- Start the Tomcat server and access the application:
```
http://localhost:8080/checklist
```

### 7. Docker Setup (Optional)
- Build the Docker image:
```bash
docker build -t checklist-app .
```
- Run the Docker container:
```bash
docker run -p 8080:8080 checklist-app
```

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/checklist/  # Java source files
│   ├── resources/
│   │   └── application.properties  # Application/Database configuration
│   └── webapp/
│       ├── WEB-INF/
│       │   └── web.xml  # Servlet and JSP configuration
│       ├── jsp/         # JSP files
│       ├── css/         # Stylesheets
│       ├── js/          # JavaScript files
│       └── images/      # Static assets
└── test/                # Unit and integration tests
```

## Contributing
Contributions are welcome! Feel free to create a pull request or open an issue for any bugs or feature requests.

## License
This project is licensed under the [MIT License](LICENSE).
