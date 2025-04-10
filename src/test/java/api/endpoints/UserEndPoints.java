package api.endpoints;

import api.payload.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints {
    //Get all URLs from properties file
   static ResourceBundle getURL(){
        //Load propreties file "routes"
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }

    //Create User
    public static Response createUser(User payload) {
       String post_url= getURL().getString("post_url");
        Response res=   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);
        return res;
    }

    //Get User
    public static Response readUser(String user_name) {
        String get_url= getURL().getString("get_url");
        Response res=   given()
                .pathParam("username", user_name)
                .when()
                .get(get_url);
        return res;
    }

    //Update User
    public static Response updateUser(String user_name, User payload) {
        String update_url= getURL().getString("update_url");
        Response res=   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", user_name)
                .body(payload)
                .when()
                .put(update_url);
        return res;
    }

    //Delete User
    public static Response deleteUser(String user_name) {
        String delete_url= getURL().getString("delete_url");
        Response res=   given()
                .pathParam("username", user_name)
                .when()
                .delete(delete_url);
        return res;
    }
}
