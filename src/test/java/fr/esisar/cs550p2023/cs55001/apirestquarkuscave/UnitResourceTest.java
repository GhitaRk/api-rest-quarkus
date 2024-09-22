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

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOUnit;
import io.quarkus.test.junit.QuarkusTest;


@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UnitResourceTest {
	
	private static final int NB_Units = 2;
	private static final String DEFAULT_NOM = "Unit de test";
	private static final String DEFAULT_Sym = "XX";
	
	private static final String UPDATED_NOM = "Updated unit de test";
	private static final String UPDATED_Sym = "YY";
	private static Long idUnit;
	
	@Test
	void shouldPingOpenAPI() {
	given()
	.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
	.when()
	.get("/q/openapi")
	.then()
	.statusCode(Status.OK.getStatusCode());
	}
	
	@Test
	void shouldNotGetUnknownUnit() {
	given()
	.pathParam("id", -1)
	.when()
	.get("/units/{id}")
	.then()
	.statusCode(Status.NO_CONTENT.getStatusCode());
	}
	
	@Test
	@Order(1)
	void shouldGetInitialUnits() {
	List<DTOUnit> Units = given()
	.when()
	.get("/units")
	.then()
	.statusCode(Status.OK.getStatusCode())
	.contentType(MediaType.APPLICATION_JSON)
	.extract()
	.body()
	.jsonPath()
	.getList(".", DTOUnit.class);
	assertEquals(NB_Units, Units.size());
	}
	
	@Test
	@Order(2)
	void shouldAddUnit() {
	DTOUnit dtoUnit = new DTOUnit();
	dtoUnit.name = DEFAULT_NOM;
	dtoUnit.symbol = DEFAULT_Sym;
	DTOUnit unit = given()
	.body(dtoUnit)
	.header(HttpHeaders.CONTENT_TYPE,
	MediaType.APPLICATION_JSON)
	.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
	.when()
	.post("/units")
	.then()
	.statusCode(Status.CREATED.getStatusCode())
	.extract()
	.body()
	.as(DTOUnit.class);
	assertNotNull(unit.id);
	idUnit = unit.id;
	List<DTOUnit> units = given()
	.when()
	.get("/units")
	.then()
	.statusCode(Status.OK.getStatusCode())
	.contentType(MediaType.APPLICATION_JSON)
	.extract()
	.body()
	.jsonPath()
	.getList(".", DTOUnit.class);
	assertEquals(NB_Units + 1, units.size());
	}
	
	@Test
	@Order(3)
	void shouldGetUnitById() {
	given()
	.pathParam("id", idUnit)
	.when()
	.get("/units/{id}")
	.then()
	.statusCode(Status.OK.getStatusCode())
	.contentType(MediaType.APPLICATION_JSON)
	.body("name", Is.is(DEFAULT_NOM))
	.body("symbol", Is.is(DEFAULT_Sym));
	}
	
	@Test
	@Order(4)
	void shouldUpdateUnit() {
	DTOUnit dtoUnit = new DTOUnit();
	dtoUnit.id = idUnit;
	dtoUnit.name = UPDATED_NOM;
	dtoUnit.symbol = UPDATED_Sym;
	given()
	.body(dtoUnit)
	.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
	.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
	.when()
	.put("/units")
	.then()
	.statusCode(Status.OK.getStatusCode())
	.contentType(MediaType.APPLICATION_JSON)
	.body("name", Is.is(UPDATED_NOM))
	.body("symbol", Is.is(UPDATED_Sym));
	List<DTOUnit> unit = given()
	.when()
	.get("/units")
	.then()
	.statusCode(Status.OK.getStatusCode())
	.contentType(MediaType.APPLICATION_JSON)
	.extract()
	.body()
	.jsonPath()
	.getList(".", DTOUnit.class);
	assertEquals(NB_Units + 1, unit.size());
	}
	
	@Test
	@Order(5)
	void shouldRemoveUnit() {
	given()
	.pathParam("id", idUnit)
	.when()
	.delete("/units/{id}")
	.then()
	.statusCode(Status.NO_CONTENT.getStatusCode());
	List<DTOUnit> Units = given()
	.when()
	.get("/units")
	.then()
	.statusCode(Status.OK.getStatusCode())
	.contentType(MediaType.APPLICATION_JSON)
	.extract()
	.body()
	.jsonPath()
	.getList(".", DTOUnit.class);
	assertEquals(NB_Units, Units.size());
	}

}
