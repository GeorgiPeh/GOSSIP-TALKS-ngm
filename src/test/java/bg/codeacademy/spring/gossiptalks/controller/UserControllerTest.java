package bg.codeacademy.spring.gossiptalks.controller;

import bg.codeacademy.spring.gossiptalks.Main;
import bg.codeacademy.spring.gossiptalks.dto.CreateUserRequestDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

import static org.testng.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Main.class)
@ActiveProfiles("dev")
public class UserControllerTest extends AbstractTestNGSpringContextTests
{

  @LocalServerPort
  int port;


  @BeforeClass(alwaysRun = true, dependsOnMethods = "springTestContextPrepareTestInstance")
  protected void setupRestAssured()
  {
    RestAssured.port = port;
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

  }


  @Test
  public void TestCreateUserRequest() throws NullPointerException
  {

    RestAssured

        .given()
        .multiPart("email", "mail2@gm.com")
        .multiPart("name", "NameOfTheUser")
        .multiPart("username", "username2")
        .multiPart("password", "userpassword1!")
        .multiPart("passwordConfirmation", "userpassword1!")
        .when()
        .post("/api/v1/users")
        .then()
        .assertThat()
        .statusCode(HttpStatus.CREATED.value());
  }

  @Test(dependsOnMethods = "TestCreateUserRequest")
  public void testGetUsers()
  {
    RestAssured
        .given()
        .auth()
        .basic("username2", "userpassword1!")
        .when()
        .get("/api/v1/users")
        .then()
        .assertThat()
        .statusCode(HttpStatus.OK.value());
  }
}




