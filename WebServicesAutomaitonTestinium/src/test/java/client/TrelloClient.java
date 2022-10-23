package client;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class TrelloClient {

    private static Dotenv env = Dotenv.load();

    private static RequestSpecification requestSpecification() {
        return given().baseUri("https://api.trello.com/1/")
                .queryParam("token", env.get("TRELLO_TOKEN"))
                .queryParam("key", env.get("TRELLO_KEY"));
    }

    public static RequestSpecification request() {
        return given().spec(requestSpecification()).when()
                .header("Content-type", "application/json");
    }

}
