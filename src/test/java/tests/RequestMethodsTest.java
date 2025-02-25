package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestMethodsTest {

    private static final String BASE_URL = "https://postman-echo.com";

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    void testGetRequest() {
        given()
                .queryParam("foo", "bar")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo", equalTo("bar"));
    }

    @Test
    void testPostRequest() {
        String requestBody = "{ \"name\": \"John\" }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("json.name", equalTo("John"));
    }

    @Test
    void testPutRequest() {
        String requestBody = "{ \"name\": \"Alice\" }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("json.name", equalTo("Alice"));
    }

    @Test
    void testDeleteRequest() {
        given()
                .when()
                .delete("/delete")
                .then()
                .statusCode(200);
    }

    @Test
    void testPatchRequest() {
        String requestBody = "{ \"age\": 30 }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("json.age", equalTo(30));
    }

    @Test
    void testOptionsRequest() {
        Response response = given()
                .when()
                .options("/options");

        assertEquals(200, response.statusCode());
    }

    @Test
    void testHeadRequest() {
        given()
                .when()
                .head("/head")
                .then()
                .statusCode(200);
    }
}