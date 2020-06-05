import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JuiceShopTest {

    @Test
    public void newUserCreation(){
        RestAssured.baseURI = "http://juice-shop:3000";
        Response response = RestAssured
                .given()
                .header("Content-Type","application/json").
                when()
                .body("{\n" +
                "  \"email\": \"abcde@gmail.com\",\n" +
                "  \"password\": \"password\",\n" +
                "  \"passwordRepeat\": \"password\",\n" +
                "  \"securityQuestion\": {\n" +
                "    \"id\": 1,\n" +
                "    \"question\": \"Your eldest siblings middle name?\",\n" +
                "    \"createdAt\": \"2020-06-05T08:56:52.477Z\",\n" +
                "    \"updatedAt\": \"2020-06-05T08:56:52.477Z\"\n" +
                "  },\n" +
                "  \"securityAnswer\": \"test\"\n" +
                "}").log().all()
                .post("/api/Users")
                .then().assertThat().statusCode(201).log().all()
                .extract().response();
    }

    @Test
    public void getAProduct(){
        RestAssured.baseURI = "http://juice-shop:3000";
        Response response = RestAssured
                .given()
                .header("Content-Type","application/json")
                .get("/#/search?q=apple")
                .then().assertThat().statusCode(200).log().all().extract().response();
    }

}


