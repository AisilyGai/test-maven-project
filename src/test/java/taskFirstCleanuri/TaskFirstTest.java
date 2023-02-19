package taskFirstCleanuri;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;

public class TaskFirstTest {

    public final static String URL = "https://cleanuri.com/api/v1/shorten";
    @Test
    public void testApiGet_Failed() throws IOException {
        //System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        //String urlFromProperty = System.getProperty("url");
        Response response = RestAssured
                .get(URL)
                .andReturn();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 405);
    }

    @Test
    public void checkUrl() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        String urlFromProperty = System.getProperty("url");
        Response response = given()
                .param("url", urlFromProperty)
                .when()
                .post(URL)
                .andReturn();
        var shortUrl = response.body().jsonPath().get("result_url").toString();
        //System.out.println(shortUrl);
        //var getShortUrl = given().header("Host", "cleanuri.com").header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36").redirects().follow(true).when().get(shortUrl).andReturn();
        //getShortUrl.prettyPrint();
        ///var header = getShortUrl.getHeader("location");
        //System.out.println(header);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(shortUrl, "https://cleanuri.com/WPA26V");
    }
    @Test
    public void checkUrlFailed() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        String urlFromProperty = System.getProperty("urlFailed");
        Response response = given()
                .param("url", urlFromProperty)
                .when()
                .post(URL)
                .andReturn();
        Assert.assertEquals(response.getStatusCode(), 404);
    }
    @Test
    public void checkUrlFailed2() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        String urlFromProperty = System.getProperty("urlFailed2");
        Response response = given()
                .param("url", urlFromProperty)
                .when()
                .post(URL)
                .andReturn();
        Assert.assertEquals(response.getStatusCode(), 400);
    }
    @Test
    public void checkUrlFailed3() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        String urlFromProperty = System.getProperty("urlFailed3");
        Response response = given()
                .param("url", urlFromProperty)
                .when()
                .post(URL)
                .andReturn();
        Assert.assertEquals(response.getStatusCode(), 400);
    }
}
