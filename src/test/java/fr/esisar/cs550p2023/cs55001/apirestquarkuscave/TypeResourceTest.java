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

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOType;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TypeResourceTest {

	private static final String DEFAULT_NOM = "Type test";
	private static final String UPDATED_NOM = "Type test updated";
	private static final int NB_TYPES = 7;
	private static Long idType;

	@Test
	void shouldPingOpenAPI() {
		given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().get("/q/openapi").then()
				.statusCode(Status.OK.getStatusCode());
	}

	@Test
	void shouldNotGetUnknownType() {
		given().pathParam("id", -1).when().get("/type/{id}").then().statusCode(Status.NO_CONTENT.getStatusCode());
	}

	@Test
	@Order(1)
	void shouldGetInitialType() {
		List<DTOType> types = given().when().get("/type").then().statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON).extract().body().jsonPath().getList(".", DTOType.class);
		assertEquals(NB_TYPES, types.size());
	}

	@Test
	@Order(2)
	void shouldAddAType() {
		DTOType dtoType = new DTOType();
		dtoType.nameType = DEFAULT_NOM;
		DTOType type = given().body(dtoType).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().post("/type").then()
				.statusCode(Status.CREATED.getStatusCode()).extract().body().as(DTOType.class);
		assertNotNull(type.id);
		idType = type.id;

		List<DTOType> types = given().when().get("/type").then().statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON).extract().body().jsonPath().getList(".", DTOType.class);
		assertEquals(NB_TYPES + 1, types.size());
	}

	@Test
	@Order(3)
	void shouldGetTypeById() {
		given().pathParam("id", idType).when().get("/type/{id}").then().statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON).body("nameType", Is.is(DEFAULT_NOM));
	}

	@Test
	@Order(4)
	void shouldUpdateAType() {
		DTOType dtoType = new DTOType();
		dtoType.id = idType;
		dtoType.nameType = UPDATED_NOM;

		given().body(dtoType).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().put("/type").then()
				.statusCode(Status.OK.getStatusCode()).contentType(MediaType.APPLICATION_JSON)
				.body("nameType", Is.is(UPDATED_NOM));

		List<DTOType> types = given().when().get("/type").then().statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON).extract().body().jsonPath().getList(".", DTOType.class);
		assertEquals(NB_TYPES + 1, types.size());
	}

	@Test
	@Order(5)
	void shouldRemoveAType() {
		given().pathParam("id", idType).when().delete("/type/{id}").then()
				.statusCode(Status.NO_CONTENT.getStatusCode());
		List<DTOType> types = given().when().get("/type").then().statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON).extract().body().jsonPath().getList(".", DTOType.class);
		assertEquals(NB_TYPES, types.size());
	}

}
