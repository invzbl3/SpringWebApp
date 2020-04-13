# SpringWebApp
A Full-featured Web Application using AngularJS with Spring RESTful 
which includes back-end as well as front-end part. 

Due to this program you can create new users, retrieve users, retrieve individual users, 
update users and delete.

### Dependencies
* h2database 1.3.150
* Spring Boot 2.2.5
* Angular JS 1.4.9
* Bootstrap 3.3.6
* Junit 5

### REST Endpoints for SpringWebApp
| HTTP Method                | REST Endpoint      | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
|--------------------|-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `GET`       | `/api/user/`  | `Returns the list of users`
| `GET`       | `/api/user/{id}`  | `Returns user details for the given user {id}`
| `POST`       | `/api/user/`  | `Updates the details of the given User {id}`
| `PUT`       | `/api/user/{id}`  | `Returns the list of users`
| `DELETE`       | `/api/user`  | `Deletes a user for the given user {id}`

All these REST endpoints are intended for use in the application.