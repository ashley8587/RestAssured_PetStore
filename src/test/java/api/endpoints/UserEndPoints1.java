package api.endpoints;

import api.payload.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

//UserEndPoints1.java
// Created for perform CRUD - Create, Read, Update Delete requests to the user API
public class UserEndPoints1 {
    //Create User
    public static Response createUser(User payload) {
        Response res=   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
       .when()
                .post(Routes.post_url);
        return res;
    }

    //Get User
    public static Response readUser(String user_name) {
        Response res=   given()
                .pathParam("username", user_name)
        .when()
                .get(Routes.get_url);
        return res;
    }

    //Update User
    public static Response updateUser(String user_name, User payload) {
        Response res=   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", user_name)
                .body(payload)
        .when()
                .put(Routes.update_url);
        return res;
    }

    //Delete User
    public static Response deleteUser(String user_name) {
        Response res=   given()
                .pathParam("username", user_name)
        .when()
                .delete(Routes.delete_url);
        return res;
    }
}
