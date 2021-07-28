package test;

import data.*;
import data.Error;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ApiTest {

    @Test
    public void testApi2_1_1(){

        Specification.installSpec(Specification.requestSpec(), Specification.responseSpec(200));
        Resource_DataUser resourse = given()
                .when()
                .get("api/users?page=2")
                .then()
                .extract()
                .body()
                .as(Resource_DataUser.class);
        String firstNameFileAva = resourse.getData().get(0).getNameFileAva();
        Assert.assertTrue(resourse.getData().stream().allMatch(x -> x.getNameFileAva().equals(firstNameFileAva)));
    }

    @Test
    public void testApi2_1_2(){

        Specification.installSpec(Specification.requestSpec(), Specification.responseSpec(200));

        Credentials credentials = new Credentials("eve.holt@reqres.in", "cityslicka");

        Token token = given()
                .body(credentials)
                .when()
                .post("/api/login")
                .then()
                .log().all()
                .extract()
                .body().as(Token.class);

        Assert.assertNotNull(token.getToken());
    }

    @Test
    public void testApi2_1_3(){
        Specification.installSpec(Specification.requestSpec(), Specification.responseSpec(400));

        Credentials credentials = new Credentials();
        credentials.setEmail("peter@klaven");

        Error error = given()
                .body(credentials)
                .when()
                .post("/api/login")
                .then()
                .log().all()
                .extract()
                .body().as(Error.class);

        Assert.assertEquals(error.getError(), "Missing password");
    }

    @Test
    public void testApi2_2(){

        Specification.installSpec(Specification.requestSpec(), Specification.responseSpec(200));

        Resource_DataResource resource = given()
                .when()
                .get("/api/unknown")
                .then()
                .log().all()
                .extract()
                .body().as(Resource_DataResource.class);

        List<DataResource> data = resource.getData();
        Assert.assertEquals(data, data.stream().sorted().collect(Collectors.toList()));
    }
}
