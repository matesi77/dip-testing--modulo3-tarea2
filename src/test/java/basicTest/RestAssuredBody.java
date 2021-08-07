package basicTest;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class RestAssuredBody {

    @Test
    public void createProjectString(){
        given()
                .auth()
                .preemptive()
                .basic("ucbmod3@ucb.com","12345")
                .body("{\n" +
                        "  \"Content\":\"UCB_SolREST\",\n" +
                        "  \"Icon\":\"5\"\n" +
                        "}")
                .log()
                .all()
                .when()
                .post("http://todo.ly/api/projects.json")
                .then()
                .log()
                .all();
    }

    @Test
    public void createProjectExternalFile(){
        given()
                .auth()
                .preemptive()
                .basic("ucbmod3@ucb.com", "12345")
                .body(new File("C:\\Users\\IBM GAMER\\Desktop\\DIP-TESTING\\ucb_rest_api\\src\\test\\resources\\projectBody.json"))
                .log()
                .all()
        .when()
                .post("http://todo.ly/api/projects.json")
        .then()
                .log()
                .all();
    }

    @Test
    public void createProjectJsonObject(){
        JSONObject body= new JSONObject();
        body.put("Content","SolJSONObject");
        body.put("Icon",4);

        given()
                .auth()
                .preemptive()
                .basic("ucbmod3@ucb.com", "12345")
                .body(body.toString())
                .log()
                .all()
                .when()
                .post("http://todo.ly/api/projects.json")
                .then()
                .log()
                .all();
    }
}
