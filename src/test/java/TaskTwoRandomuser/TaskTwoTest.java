package TaskTwoRandomuser;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import  static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
public class TaskTwoTest {

    @Test
    public void testGet(){
       given().
                baseUri("https://randomuser.me").

        when().
                log().all().
                get("/api").
        //System.out.printf("status code : %s\n", response.getStatusCode());
        //System.out.printf("Response : %s\n", response.asString());
        then().
                log().all();

    }
    @Test
    public void testGetUser(){
        Response response = given().
                baseUri("https://randomuser.me").

         when().
                log().all().
                get("/api");

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test
    public void testGetUser_Failed(){
        given().
                baseUri("https://randomuser.me").

        when().
                log().all().
                get("/api").

        then().assertThat().
            log().all().
            statusCode(201).
            statusLine("HTTP/1.1 201 OK").
            header("Content-Type", "application/json; charset=utf-16").
            header("Connection", "keep-aliv");


    }
    @Test
    public void testGetUser_Success(){
        given().
                baseUri("https://randomuser.me").

        when().
                log().all().
                get("/api").

        then().assertThat().
                log().all().
                statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                header("Content-Type", "application/json; charset=utf-8").
                header("Connection", "keep-alive");

    }
    @Test
    public void testGetUserTwo_Failed() {
        given().
                baseUri("https://randomuser.me").

        when().
                log().all().
                get("/api/{postcode}", 63104).

        then().assertThat().
                log().all().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found").
                header("Content-Type", "text/plain; charset=utf-8").
                header("Connection", "keep-alive");
    }
    @Test
    public void testUserQeryParam() {
        given().
                baseUri("https://randomuser.me").
                queryParam("gender", "male").
                queryParam("results", 2).

        when().
                log().all().
                get("/api").
        then().assertThat().
                log().all().
                statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                header("Content-Type", "application/json; charset=utf-8").
                header("Connection", "keep-alive").
                body("results[0].gender", equalTo("male")).
                body("info.results", equalTo(2));
    }
    @Test
    public void testUserQeryParams() {
        given().
                baseUri("https://randomuser.me").
                queryParam("gender", "male").
                queryParam("results", 1).
                queryParam("results[0].name.first", "Joe").
                queryParam("results[0].name.last", "Simmons").

        when().
                log().all().
                get("/api").
        then().assertThat().
                log().all().
                statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                header("Content-Type", "application/json; charset=utf-8").
                header("Connection", "keep-alive").
                body("results[0].gender", equalTo("male")).
                body("info.results", equalTo(2));
    }

    @Test
    public void testUserQeryParam_Failed() {
        given().
                baseUri("https://randomuser.me").
                queryParam("gender", 123).
                queryParam("results", "text").

        when().
                log().all().
                get("/api").
        then().assertThat().
                log().all().
                statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                header("Content-Type", "application/json; charset=utf-8").
                header("Connection", "keep-alive").
                body("results[0].gender", not(123)).
                body("info.results", not("text"));
    }

    @Test
    public void chekGetTest2(){
        var response = given()
                .queryParam("gender", "male")
                .queryParam("results", 2)
                .when()
                .contentType(ContentType.JSON)
                .get("https://randomuser.me/api")
                .andReturn();
        var root = response.getBody().as(Root.class);
        Assert.assertEquals(root.info.results, 2);
        Assert.assertEquals(root.results[0].gender, "male");
        Assert.assertEquals(root.results[1].gender, "male");
    }
    @Test
    public void chekGetTest_Failed(){
        var response = given()
                .queryParam("gender", "mal")
                .when()
                .contentType(ContentType.JSON)
                .get("https://randomuser.me/api")
                .andReturn();
        var root = response.getBody().as(Root.class);
        Assert.assertEquals(root.info.results, 1);
        Assert.assertNotEquals(root.results[0].gender, "mal");
    }
}
