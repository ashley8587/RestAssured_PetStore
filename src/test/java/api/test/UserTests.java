package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class UserTests {
    Faker faker;
    User userPayload;

    //for logs
    public Logger logger;
//    private static final Logger logger = LogManager.getLogger(UserTests.class);

    @BeforeClass
    public void setupData(){
        System.out.println("This is a test setup message.");

        faker = new Faker();
        userPayload = new User();

        //Passing fake data to user payload
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //logs
        logger = LogManager.getLogger(this.getClass());
        logger.debug("**************** debugging ****************");
    }

    @Test(priority = 1)
    public void testPostUser(){
        logger.info("**************** Creating User ****************");
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("**************** User Created ****************");
    }

    @Test(priority = 2)
    public void testGetUserByName(){
        logger.info("**************** Reading User  Info ****************");
        Response response = UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("**************** User  Info Displayed****************");
    }

    @Test(priority = 3)
    public void testUpdateUserByName(){
        logger.info("**************** Updating User By Name ****************");
        //Update data using payload --except username
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());

        Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        //Check data after update
        Response resAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
        Assert.assertEquals(resAfterUpdate.getStatusCode(), 200);
        logger.info("**************** User Updated ****************");
    }

    @Test(priority = 4)
    public void testDeleteUserByName(){
        logger.info("**************** Deleting User By Name ****************");
        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        response.then().log().body().statusCode(200);
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("**************** User Deleted ****************");
    }

}
