package com.pets.helpers;
import com.pets.constants.Endpoints;
import com.pets.model.AddPet_Pojo;
import com.pets.model.PlaceOrder_Pojo;
import org.junit.jupiter.api.Assertions;
import com.pets.utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import static org.testng.Assert.assertEquals;


public class PetServiceHelper {

    private static final String BASE_URL = ConfigManager.getInstance().getString("base_url");

    public PetServiceHelper(){

        RestAssured.baseURI = BASE_URL;
        RestAssured.useRelaxedHTTPSValidation();
    }

    public Response AddNewPet(){
        AddPet_Pojo pet = new AddPet_Pojo();
        pet.setId(19);
        pet.setName("Rabbit");
        pet.setStatus("available");

        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Content-type", "application/json")
                .log().all()
                .when()
                .body(pet)
                .post(Endpoints.AddNew_Pet)
                .andReturn();

        assertEquals(response.getStatusCode( ), HttpStatus.SC_OK,"Created");
        Assertions.assertEquals("19", response.jsonPath().getString("id"));
        Assertions.assertEquals("available", response.jsonPath().getString("status"));
        return response;

    }
    public Response DeletePet(Integer id){
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .header("Content-type", "application/json")
                .pathParam("petId",19)
                .log().all()
                .when().delete(Endpoints.Delete_Pet)
                .andReturn();

        assertEquals(response.getStatusCode( ),HttpStatus.SC_OK,"Deleted");
        Assertions.assertEquals("unknown", response.jsonPath().getString("type"));
        Assertions.assertEquals("19", response.jsonPath().getString("message"));
        Assertions.assertEquals("200", response.jsonPath().getString("code"));
        return response;

    }
    public Response PlaceOrder(){

        PlaceOrder_Pojo pet = new PlaceOrder_Pojo();
        pet.setId(3);
        pet.setPetId(0);
        pet.setQuantity(0);
        pet.setShipDate("2021-08-12T14:54:45.538Z");
        pet.setStatus("placed");
        pet.setComplete( true);

        Response response = RestAssured.given().log().all()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .when()
                .body(pet)
                .post(Endpoints.Buy_Pet)
                .andReturn();

        assertEquals(response.getStatusCode( ),HttpStatus.SC_OK,"Placed");
        Assertions.assertEquals("3", response.jsonPath().getString("id"));
        Assertions.assertEquals("placed", response.jsonPath().getString("status"));
        return response;
    }

    public Response OrderNotFound(Integer id) {

        Response response = RestAssured.given().log().all()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .pathParam("orderId",id)
                .when()
                .get(Endpoints.Find_PurchaseOrder)
                .andReturn();

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("3", response.jsonPath().getString("id"));
       // Assertions.assertEquals("Order not found", response.jsonPath().getString("message"));

    return response;
    }




}
