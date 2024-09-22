//package fr.esisar.cs550p2023.cs55001.apirestquarkuscave;
//
//import static io.restassured.RestAssured.given;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.List;
//
//import javax.ws.rs.core.HttpHeaders;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response.Status;
//
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//
//import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOSensor;
//import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOState;
//
//public class SensorResourceTest {
//	
//	private static final int NB_STATES = 4;
//	
//	@Test
//	@Order(1)
//	void shouldPingOpenAPI() {
//		given()
//		.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
//		.when()
//		.get("/q/openapi")
//		.then()
//		.statusCode(Status.OK.getStatusCode());
//	}
//	
//	@Test
//	@Order(2)
//	void shouldNotGetUnknownState() {
//		given()
//		.pathParam("id", -1)
//		.when()
//		.get("/Sensors/{id}")
//		.then()
//		.statusCode(Status.NO_CONTENT.getStatusCode());
//	}
//	
//	
//	@Test
//	@Order(3)
//	void shouldGetInitialState() {
//		List<DTOSensor> sensors = given()
//			.when()
//			.get("/Sensors")
//			.then()
//			.statusCode(Status.OK.getStatusCode())
//			.contentType(MediaType.APPLICATION_JSON)
//			.extract()
//			.body()
//			.jsonPath()
//			.getList(".", DTOSensor.class);
//		assertEquals(NB_STATES, sensors.size());
//	}
//	
//
//}
