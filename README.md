User Management API
Features
•	Create new users
•	A User account can CREATE, READ, UPDATE Posts
•	An Admin account can CREATE, READ, UPDATE, DELETE Posts
•	Authenticated users can create, read, update, and delete blog posts.
 ● Users can comment on blog posts, and comments can be edited and deleted. 
● Implement role-based access control (RBAC) where only authorized users can perform certain actions (e.g., only administrators can delete posts).
 ● Implement pagination and sorting for blog posts and comments. 
● Implement search functionality to search for blog posts by title or content.

Running the API
This is a Spring Boot application. To run:
Build with Maven
Execute the JAR file
API will run on port 8080 by default
Testing
Unit and integration tests are included in the src/test directory. Run them with:
mvn test
The API can also be tested manually with cURL or Postman.
 APIs
Create User
POST /auth/signup
Request:
{
"email":"test42@test.com",
"password":"test2",
"fullName":"test"
}
Response:
{
    "id": 1102,
    "fullName": "test",
    "email": "test42@test.com",
    "password": "$2a$10$QQdSMxxUbi7aICFkPOihP.m2iENrB4bfTA7g6HD0eMrWbhN02giY.",
    "createdAt": "2024-03-28T22:21:32.022+00:00",
    "updatedAt": "2024-03-28T22:21:32.023+00:00",
    "role": {
        "id": 1,
        "name": "USER",
        "description": "Default user role",
        "createdAt": "2024-03-13T15:15:31.968+00:00",
        "updatedAt": "2024-03-13T15:15:31.968+00:00"
    },
    "enabled": true,
    "authorities": [
        {
            "authority": "ROLE_USER"
        }
    ],
    "username": "test42@test.com",
    "accountNonLocked": true,
    "accountNonExpired": true,
    "credentialsNonExpired": true
}
Authenticate user
POST /auth/login
Request:
{
    "email":"super.admin@email.com",
    "password":"123456"
}
Response:
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXBlci5hZG1pbkBlbWFpbC5jb20iLCJpYXQiOjE3MTE2NTczMDUsImV4cCI6MTcxMTY2MDkwNX0.x4NhyKUO9pS35_5sOuOrPmL7XNw0hzriTUKRjWIruJM",
    "expiresIn": 3600000
}

Create Blog Posts
POST /api/posts
Request:
{
    "title":"test3",
    "description":"cool stuff",
    "content":"spring boot spring"
}

Response:
{
    "id": 3,
    "title": "test3",
    "description": "weird stuff",
    "content": "spring boot spring",
    "comments": null
}
Get Posts( single) :
GET /api/posts/title?post=test3
Get Posts( all) :
GET /api/posts
Update posts:
PUT /api/posts/title
Delete posts:
DELETE /api/posts/title

Create Comments:
POST /api/v1/posts/1/comments
Get all comments from a post Comments:
GET /posts/{postId}/comments
Get a comment from a post:
GET /posts/{postId}/comments/{id}
Update a comment:
PUT /posts/{postId}/comments/{id}
Delete a comment:
DELETE /posts/{postId}/comments/{id}

