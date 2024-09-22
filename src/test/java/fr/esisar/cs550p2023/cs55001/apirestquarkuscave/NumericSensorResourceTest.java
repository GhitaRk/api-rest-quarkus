package fr.esisar.cs550p2023.cs55001.apirestquarkuscave;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTONumericSensor;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NumericSensorResourceTest {

	private static final int NB_NumericSensors = 4;
	private static final String DEFAULT_NOM = "Numeric sensor de test";
	private static final String DEFAULT_SN = "XXXX";

	private static final String UPDATED_NOM = "Updated Numeric sensor de test";
	private static final String UPDATED_SR = "YYYY";
	private static Long idNumericSensor;

	@Test
	void shouldPingOpenAPI() {
		given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().get("/q/openapi").then()
				.statusCode(Status.OK.getStatusCode());
	}

	@Test
	void shouldNotGetUnknownNumericSensor() {
		given().pathParam("id", -1).when().get("/numericsensors/{id}").then()
				.statusCode(Status.NO_CONTENT.getStatusCode());
	}

	@Test
	@Order(1)
	void shouldGetInitialNumericSensors() {
		List<DTONumericSensor> NumericSensors = given().when().get("/numericsensors").then()
				.statusCode(Status.OK.getStatusCode()).contentType(MediaType.APPLICATION_JSON).extract().body()
				.jsonPath().getList(".", DTONumericSensor.class);
		assertEquals(NB_NumericSensors, NumericSensors.size());
	}

	@Test
	@Order(2)
	void shouldAddNumericSensor() {
		DTONumericSensor dtoNumericSensor = new DTONumericSensor();
		dtoNumericSensor.serialNumber = DEFAULT_SN;
		dtoNumericSensor.name = DEFAULT_NOM;
		DTONumericSensor numericSensor = given().body(dtoNumericSensor).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).when().post("/numericsensors").then()
				.statusCode(Status.CREATED.getStatusCode()).extract().body().as(DTONumericSensor.class);

		assertNotNull(numericSensor.id);
		idNumericSensor = numericSensor.id;

		List<DTONumericSensor> numericSensors = given().when().get("/numericsensors").then()
				.statusCode(Status.OK.getStatusCode()).contentType(MediaType.APPLICATION_JSON).extract().body()
				.jsonPath().getList(".", DTONumericSensor.class);
		assertEquals(NB_NumericSensors + 1, numericSensors.size());
	}

	@Test

	@Order(3)
	void shouldGetNumericSensorById() {
		given().pathParam("id", idNumericSensor).when().get("/numericsensors/{id}").then()
				.statusCode(Status.OK.getStatusCode()).contentType(MediaType.APPLICATION_JSON)
				.body("name", Is.is(DEFAULT_NOM)).body("serialNumber", Is.is(DEFAULT_SN));
	}

	@Test
	@Order(4)
	void shouldUpdateNumericSensor() {
		DTONumericSensor dtoNumericSensor = new DTONumericSensor();
		dtoNumericSensor.id = idNumericSensor;
		dtoNumericSensor.name = UPDATED_NOM;
		dtoNumericSensor.serialNumber = UPDATED_SR;
		given().body(dtoNumericSensor).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).when()
				.put("/numericsensors").then().statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON).body("name", Is.is(UPDATED_NOM))
				.body("serialNumber", Is.is(UPDATED_SR));

		DTONumericSensor numericSensor = given().pathParam("id", idNumericSensor).when().get("/numericsensors/{id}")
				.then().statusCode(Status.OK.getStatusCode()).contentType(MediaType.APPLICATION_JSON).extract().body()
				.as(DTONumericSensor.class);
		assertEquals(UPDATED_NOM, numericSensor.name);
		assertEquals(UPDATED_SR, numericSensor.serialNumber);
	}

	@Test
	@Order(5)
	void shouldRemoveNumericSensor() {
		given().pathParam("id", idNumericSensor).when().delete("/numericsensors/{id}").then()
				.statusCode(Status.NO_CONTENT.getStatusCode());
		List<DTONumericSensor> NumericSensors = given().when().get("/numericsensors").then()
				.statusCode(Status.OK.getStatusCode()).contentType(MediaType.APPLICATION_JSON).extract().body()
				.jsonPath().getList(".", DTONumericSensor.class);
		assertEquals(NB_NumericSensors, NumericSensors.size());
	}

}
