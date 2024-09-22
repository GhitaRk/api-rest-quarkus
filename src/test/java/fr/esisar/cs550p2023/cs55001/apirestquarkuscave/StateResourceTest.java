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

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOState;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StateResourceTest {

	private static final String DEFAULT_UP = "Up test";
	private static final String DEFAULT_DOWN = "Down test";
	private static final String UPDATED_UP = "Up test updated";
	private static final String UPDATED_DOWN = "Down test updated";
	private static final int NB_STATES = 7;
	private static Long idState;

	@Test
	@Order(1)
	void shouldPingOpenAPI() {
		given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().get("/q/openapi").then()
				.statusCode(Status.OK.getStatusCode());
	}

	@Test
	@Order(2)
	void shouldNotGetUnknownState() {
		given().pathParam("id", -1).when().get("/state/{id}").then().statusCode(Status.NO_CONTENT.getStatusCode());
	}

	@Test
	@Order(3)
	void shouldGetInitialState() {
		List<DTOState> states = given().when().get("/state").then().statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON).extract().body().jsonPath().getList(".", DTOState.class);
		assertEquals(NB_STATES, states.size());
	}

	@Test
	@Order(4)
	void shouldAddAState() {
		DTOState dtoState = new DTOState();
		dtoState.up = DEFAULT_UP;
		dtoState.down = DEFAULT_DOWN;
		DTOState state = given().body(dtoState).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().post("/state").then()
				.statusCode(Status.CREATED.getStatusCode()).extract().body().as(DTOState.class);
		assertNotNull(state.id);
		idState = state.id;

		List<DTOState> states = given().when().get("/state").then().statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON).extract().body().jsonPath().getList(".", DTOState.class);
		assertEquals(NB_STATES + 1, states.size());
	}

	@Test
	@Order(5)
	void shouldGetStateById() {
		given().pathParam("id", idState).when().get("/state/{id}").then().statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON).body("up", Is.is(DEFAULT_UP))
				.body("down", Is.is(DEFAULT_DOWN));
	}

	@Test
	@Order(6)
	void shouldUpdateAState() {
		DTOState dtoState = new DTOState();
		dtoState.id = idState;
		dtoState.up = UPDATED_UP;
		dtoState.down = UPDATED_DOWN;

		given().body(dtoState).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().put("/state").then()
				.statusCode(Status.OK.getStatusCode()).contentType(MediaType.APPLICATION_JSON)
				.body("up", Is.is(UPDATED_UP)).body("down", Is.is(UPDATED_DOWN));

		List<DTOState> states = given().when().get("/state").then().statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON).extract().body().jsonPath().getList(".", DTOState.class);
		assertEquals(NB_STATES + 1, states.size());
	}

	@Test
	@Order(7)
	void shouldRemoveAState() {
		given().pathParam("id", idState).when().delete("/state/{id}").then()
				.statusCode(Status.NO_CONTENT.getStatusCode());
		List<DTOState> states = given().when().get("/state").then().statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON).extract().body().jsonPath().getList(".", DTOState.class);
		assertEquals(NB_STATES, states.size());
	}

}
