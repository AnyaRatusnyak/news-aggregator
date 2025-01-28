
#  🌟 News Aggregator API 🌟
The News Aggregator API is a backend application that aggregates news from NewsAPI https://newsapi.org/ and stores it in MongoDB and Elasticsearch. It enables clients to:

- Access Hot News (trending and breaking stories).
- Fetch news based on their preferences.
- Fetch news by keywords.
- Navigate to external news sources.  

The API also includes user management features, such as registration and login, with user information stored in database.

### 🛠️ Technologies and Tools Used
- Spring Boot: For building the application framework.
- Spring Data MongoDB: For database interactions.
- Spring Web: For building web-based applications with Spring MVC.
- MongoDB: Primary database for storing user data and news articles.
- Elasticsearch: Search and filter capabilities for news articles based on preferences and keywords.
- Maven: For project management and dependency management.

### 🚀 Current Functionalities
- ##### Authentication
    - Register a new user: POST /api/auth/register
        - Example of request body:
          ```json
          {
          "email": "john.doe@example.com",
          "password": "securePassword123",
          "repeatPassword": "securePassword123",
          "firstName": "John",
          "lastName": "Doe"
          }
          ```
        - Example of response body:
          ```json
          {
          "id": 1,
          "email": "john.doe@example.com",
          "firstName": "John",
          "lastName": "Doe"
          }
          ```
    - Authenticate a user: POST /api/auth/login

        - Example of request body:
          ```json
          {
          "email": "john.doe@example.com",
          "password": "securePassword123"
          }
          ```
        - Example of response body:
          ```json
          {
          "id": 1,
          "email": "john.doe@example.com",
          "firstName": "John",
          "lastName": "Doe"
          }
          ```
- ##### Article
    - Get news by current category: GET /api/articles/category
  
- ##### Searching news
    - Get news by keywords: GET /api/search
    - Get news based on user's preferences: GET /api/search/{id}
    - Get hot news : GET /api/search/latest
    
- ##### Source
    - Get all sources: GET /api/sources
  
- ##### UserPreferences
    - Get user's preferences by userId: GET /api/users/preferences/{id}
    - Update user's preferences: PATCH /api/users/preferences/{id}
  
### 🌟 Getting Started
##### Prerequisites
- Java 17+
- Maven 4+
- MongoDB 8+
- Elasticsearch 8+
- Access to NewsAPI with an API key.
##### Installation
To run the application locally, follow these steps:

1. Clone the Repository
```bash
git clone https://github.com/AnyaRatusnyak/news-aggregator.git  
cd news-aggregator
```
2. Set up MongoDB
3. Set up Elasticsearch
3. Configure environment variables:

   NEWS_API_KEY=your_news_api_key

4. Install dependencies and build the project:
```bash
mvn clean install
```
5. Run the application:
```bash
mvn spring-boot:run
```
The server will start on http://localhost:8080.

##### 👷Author
- LinkedIn: **[Hanna Ratushniak](https://www.linkedin.com/in/hanna-ratushnyak/)**
- Github: **[AnyaRatusnyak](https://github.com/AnyaRatusnyak)**