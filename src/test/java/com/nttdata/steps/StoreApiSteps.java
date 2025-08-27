package com.nttdata.steps;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class StoreApiSteps {

    private final String BASE_URL = "https://petstore.swagger.io/v2";
    private RequestSpecification request;
    private Response response;

    /**
     * Preparación de la solicitud de la orden.
     * @param id El ID de la orden.
     * @param petId El ID de la mascota.
     * @param quantity La cantidad.
     */
    public void prepareOrderCreation(String id, String petId, String quantity) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", Integer.parseInt(id));
        requestBody.put("petId", Integer.parseInt(petId));
        requestBody.put("quantity", Integer.parseInt(quantity));
        requestBody.put("shipDate", Instant.now().toString());
        requestBody.put("status", "placed");
        requestBody.put("complete", true);

        request = given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(requestBody);
    }

    public void prepareGetRequest() {
        request = given()
                .baseUri(BASE_URL)
                .contentType("application/json");
    }

    public void sendPostRequest(String endpoint) {
        response = request.when().post(endpoint);
    }

    public void sendGetRequest(String endpoint) {
        response = request.when().get(endpoint);
    }

    public Response getResponse() {
        return response;
    }
}
