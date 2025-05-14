# Blood Donor API

## Overview
The Blood Donor API is a Java Spring Boot application designed to manage and process blood donor data. It provides endpoints for adding donor information, retrieving statistics, and performing advanced data analysis such as calculating BMI, obesity percentages, and donor compatibility.

## Features
- **Add Donors**: Add single or multiple donors to the database.
- **Retrieve Donor Data**: Fetch all donor records.
- **Statistics and Analysis**:
  - Count candidates by state.
  - Calculate average BMI by age range.
  - Determine obesity percentages by gender.
  - Calculate average age by blood type.
  - Identify potential donors for each blood type recipient.
- **Swagger Documentation**: Interactive API documentation available at `/swagger-ui/index.html`.

## Prerequisites
To run the application locally, ensure you have the following installed:
- Java 17 or higher
- Maven 3.8+
- Docker (optional, for running MySQL via Docker Compose)

## Setup Instructions

### 1. Clone the Repository
```bash
# Clone the repository
git clone <repository-url>
cd blood-donor-api
```

### 2. Configure the Database
The application uses MySQL as the database. You can set it up in two ways:

#### Option 1: Using Docker Compose
Run the following command to start a MySQL container:
```bash
docker-compose up -d
```
This will start a MySQL instance with the configuration specified in `docker-compose.yml`.

#### Option 2: Manual Setup
- Install MySQL locally.
- Create a database named `blood_donor_db`.
- Update the `src/main/resources/application.properties` file with your MySQL credentials:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/blood_donor_db
  spring.datasource.username=<your-username>
  spring.datasource.password=<your-password>
  ```

### 3. Build the Application
Use Maven to build the project:
```bash
mvn clean install
```

### 4. Run the Application
Start the application using the following command:
```bash
mvn spring-boot:run
```

The application will be accessible at `http://localhost:8080`.

### 5. Access Swagger Documentation
Visit the following URL to explore the API endpoints:
```
http://localhost:8080/swagger-ui/index.html
```

## API Endpoints

### Donor Management
- **Add a Single Donor**: `POST /api/donors`
- **Add Multiple Donors**: `POST /api/donors/batch`
- **Get All Donors**: `GET /api/donors`

### Statistics and Analysis
- **Count Candidates by State**: `GET /api/donors/count-by-state`
- **Average BMI by Age Range**: `GET /api/donors/average-bmi-by-age-range`
- **Obesity Percentage by Gender**: `GET /api/donors/obesity-percentage-by-gender`
- **Average Age by Blood Type**: `GET /api/donors/average-age-by-blood-type`
- **Potential Donors by Recipient Type**: `GET /api/donors/potential-donors-by-recipient`

## Project Structure
- **`src/main/java`**: Contains the main application code.
  - **`controller`**: REST controllers for handling API requests.
  - **`service`**: Business logic and data processing.
  - **`repository`**: JPA repositories for database interaction.
  - **`model`**: Entity classes representing database tables.
  - **`dto`**: Data Transfer Objects for API communication.
  - **`config`**: Configuration classes (e.g., Swagger, ModelMapper).
- **`src/main/resources`**: Contains application properties and static resources.
- **`docker-compose.yml`**: Docker Compose file for setting up MySQL.

## Testing
Run the tests using Maven:
```bash
mvn test
```

## Troubleshooting
- **Lombok Issues**: Ensure annotation processing is enabled in your IDE.
- **Database Connection Errors**: Verify your MySQL credentials and database URL in `application.properties`.
- **Port Conflicts**: Ensure port `8080` is not in use by another application.

## License
This project is licensed under the MIT License. See the LICENSE file for details.
