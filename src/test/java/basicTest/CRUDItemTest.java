package basicTest;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CRUDItemTest {

    @Test
    public void crudItemRestAPI(){
        //creacion
        JSONObject body = new JSONObject();
        body.put("Content", "New Item");
        Response response = given()
                .auth()
                .preemptive()
                .basic("ucbmod3@ucb.com", "12345")
                .body(body.toString())
                .log()
                .all()
                .when()
                .post("https://todo.ly/api/items.json");
        response.then()
                .statusCode(200)
                .body("Content", equalTo("New Item"))
                .log()
                .all();
        int id = response.then().extract().path("Id");
        System.out.println("****** ID ***** : " + id);

        // Actualizacion
        body.put("Content","Item updated");

        response=given()
                .auth()
                .preemptive()
                .basic("ucbmod3@ucb.com","12345")
                .body(body.toString())
                .log()
                .all()
                .when()
                .put("https://todo.ly/api/items/" + id + ".json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("Item updated"))
                .log()
                .all();

        // Get
        response=given()
                .auth()
                .preemptive()
                .basic("ucbmod3@ucb.com","12345")
                .log()
                .all()
                .when()
                .get("https://todo.ly/api/items/" + id + ".json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("Item updated"))
                .log()
                .all();

        // Delete
        response=given()
                .auth()
                .preemptive()
                .basic("ucbmod3@ucb.com","12345")
                .log()
                .all()
                .when()
                .delete("https://todo.ly/api/items/" + id + ".json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("Item updated"))
                .body("Deleted",equalTo(true))
                .log()
                .all();

 
    }
}
