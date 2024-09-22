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

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOAnalogicSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOType;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOUnit;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnalogicSensorResourceTest {

	private static final int NB_AnalogicSensors = 4;
	private static final String DEFAULT_NOM = "Analogic sensor de test";
	private static final String DEFAULT_SN = "XXXX";

	private static final String UPDATED_NOM = "Updated Analogic sensor de test";
	private static final String UPDATED_SR = "YYYY";
	private static Long idAnalogicSensor;
	private static Long idType = (long) 3;
	private static Long idUnit = (long) 1;

	@Test
	void shouldPingOpenAPI() {
		given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().get("/q/openapi").then()
				.statusCode(Status.OK.getStatusCode());
	}

	@Test
	void shouldNotGetUnknownAnalogicSensor() {
		given().pathParam("id", -1).when().get("/analogicsensors/{id}").then()
				.statusCode(Status.NO_CONTENT.getStatusCode());
	}

	@Test
	@Order(1)
	void shouldGetInitialAnalogicSensors() {
		List<DTOAnalogicSensor> AnalogicSensors = given().when().get("/analogicsensors").then()
				.statusCode(Status.OK.getStatusCode()).contentType(MediaType.APPLICATION_JSON).extract().body()
				.jsonPath().getList(".", DTOAnalogicSensor.class);
		assertEquals(NB_AnalogicSensors, AnalogicSensors.size());
	}

	@Test
	@Order(2)
	void shouldAddAnalogicSensor() {
		DTOAnalogicSensor dtoAnalogicSensor = new DTOAnalogicSensor();
		dtoAnalogicSensor.serialNumber = DEFAULT_SN;
		dtoAnalogicSensor.name = DEFAULT_NOM;
		dtoAnalogicSensor.type = new DTOType();
		dtoAnalogicSensor.unit = new DTOUnit();
		dtoAnalogicSensor.type.id = idType;
		dtoAnalogicSensor.unit.id = idUnit;
		DTOAnalogicSensor analogicSensor = given().body(dtoAnalogicSensor)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().post("/analogicsensors").then()
				.statusCode(Status.CREATED.getStatusCode()).extract().body().as(DTOAnalogicSensor.class);
		assertNotNull(analogicSensor.id);
		idAnalogicSensor = analogicSensor.id;
		List<DTOAnalogicSensor> analogicSensors = given().when().get("/analogicsensors").then()
				.statusCode(Status.OK.getStatusCode()).contentType(MediaType.APPLICATION_JSON).extract().body()
				.jsonPath().getList(".", DTOAnalogicSensor.class);
		assertEquals(NB_AnalogicSensors + 1, analogicSensors.size());
	}

	@Test
	@Order(3)
	void shouldGetAnalogicSensorById() {
		given().pathParam("id", idAnalogicSensor).when().get("/analogicsensors/{id}").then()
				.statusCode(Status.OK.getStatusCode()).contentType(MediaType.APPLICATION_JSON)
				.body("name", Is.is(DEFAULT_NOM)).body("serialNumber", Is.is(DEFAULT_SN));
	}

	@Test
	@Order(4)
	void shouldUpdateAnalogicSensor() {
		DTOAnalogicSensor dtoAnalogicSensor = new DTOAnalogicSensor();
		dtoAnalogicSensor.id = idAnalogicSensor;
		dtoAnalogicSensor.name = UPDATED_NOM;
		dtoAnalogicSensor.serialNumber = UPDATED_SR;
		dtoAnalogicSensor.type = new DTOType();
		dtoAnalogicSensor.unit = new DTOUnit();
		dtoAnalogicSensor.type.id = idType;
		dtoAnalogicSensor.unit.id = idUnit;
		given().body(dtoAnalogicSensor).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().put("/analogicsensors").then()
				.statusCode(Status.OK.getStatusCode()).contentType(MediaType.APPLICATION_JSON)
				.body("name", Is.is(UPDATED_NOM)).body("serialNumber", Is.is(UPDATED_SR));
		List<DTOAnalogicSensor> analogicSensor = given().when().get("/analogicsensors").then()
				.statusCode(Status.OK.getStatusCode()).contentType(MediaType.APPLICATION_JSON).extract().body()
				.jsonPath().getList(".", DTOAnalogicSensor.class);
		assertEquals(NB_AnalogicSensors + 1, analogicSensor.size());
	}

	@Test
	@Order(5)
	void shouldRemoveAnalogicSensor() {
		given().pathParam("id", idAnalogicSensor).when().delete("/analogicsensors/{id}").then()
				.statusCode(Status.NO_CONTENT.getStatusCode());
		List<DTOAnalogicSensor> AnalogicSensors = given().when().get("/analogicsensors").then()
				.statusCode(Status.OK.getStatusCode()).contentType(MediaType.APPLICATION_JSON).extract().body()
				.jsonPath().getList(".", DTOAnalogicSensor.class);
		assertEquals(NB_AnalogicSensors, AnalogicSensors.size());
	}

}
