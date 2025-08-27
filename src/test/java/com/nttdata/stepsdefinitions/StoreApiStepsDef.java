package com.nttdata.stepsdefinitions;

import com.nttdata.steps.StoreApiSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.Assertions;

import static org.hamcrest.Matchers.equalTo;

public class StoreApiStepsDef {

    private StoreApiSteps storeApi = new StoreApiSteps();

    @Dado("que preparo la solicitud para crear un nuevo pedido con id {string}, petId {string} y cantidad {string}")
    public void preparoLaSolicitudParaCrearUnNuevoPedido(String id, String petId, String quantity) {
        storeApi.prepareOrderCreation(id, petId, quantity);
    }

    @Cuando("envío la solicitud POST al endpoint {string}")
    public void envíoLaSolicitudPOSTAlEndpoint(String endpoint) {
        storeApi.sendPostRequest(endpoint);
    }

    @Entonces("el código de estado de la respuesta debe ser {int}")
    public void elCódigoDeEstadoDeLaRespuestaDebeSer(int statusCode) {
        int actualStatusCode = storeApi.getResponse().getStatusCode();
        Assertions.assertEquals(statusCode, actualStatusCode, "El código de estado no es el esperado.");
    }

    @Y("el cuerpo de la respuesta debe contener los detalles del pedido con id {string}, petId {string} y estado {string}")
    public void elCuerpoDeLaRespuestaDebeContenerLosDetallesDelPedido(String id, String petId, String status) {
        storeApi.getResponse().then()
                .body("id", equalTo(Integer.parseInt(id)))
                .body("petId", equalTo(Integer.parseInt(petId)))
                .body("status", equalTo(status));
    }

    @Dado("que un pedido con id {string} y petId {string} fue creado")
    public void queUnPedidoConIdFueCreado(String id, String petId) {
        storeApi.prepareOrderCreation(id, petId, "1");
        storeApi.sendPostRequest("/store/order");
        Assertions.assertEquals(200, storeApi.getResponse().getStatusCode(),
                "FALLO EN LA PRECONDICIÓN: No se pudo crear el pedido " + id + " para la prueba.");
        System.out.println("PRECONDICIÓN CUMPLIDA: El pedido con id " + id + " fue creado para la prueba.");
    }


    @Cuando("envío la solicitud GET al endpoint {string}")
    public void envíoLaSolicitudGETAlEndpoint(String endpoint) {
        try {
            System.out.println("Esperando 1 segundo para que la API procese el pedido...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Enviando solicitud GET a: " + endpoint);
        storeApi.prepareGetRequest();
        storeApi.sendGetRequest(endpoint);
    }

    @Y("el cuerpo de la respuesta debe corresponder al pedido con id {string} y petId {string}")
    public void elCuerpoDeLaRespuestaDebeCorresponderAlPedido(String id, String petId) {
        storeApi.getResponse().then()
                .body("id", equalTo(Integer.parseInt(id)))
                .body("petId", equalTo(Integer.parseInt(petId)));
    }
}
