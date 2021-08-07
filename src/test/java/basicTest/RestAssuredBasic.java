package basicTest;

//import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredBasic {
    /*
      given()----> configuracion
      when() ----> request action --> URL ---> Method
      then() ----> verificaciones
     */

    @Test
    public void createProject(){
        given()
                .header("Authorization","Basic dWNibW9kM0B1Y2IuY29tOjEyMzQ1")
                .body("{\n" +
                        "  \"Content\":\"UCB_Sol\",\n" +
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
    public void createProject2(){
        given()
                .auth()
                .preemptive()
                .basic("ucbmod3@ucb.com", "12345")
                .body("{\n" +
                        "  \"Content\":\"UCB_Sol-rest\",\n" +
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
}
