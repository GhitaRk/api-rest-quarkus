package fr.esisar.cs550p2023.cs55001.apirestquarkuscave;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.google.common.net.HttpHeaders;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOAnalogicData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOAnalogicSensorFromAnalogicData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTONumericData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTONumericSensorFromNumericData;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DataResourceTest {

	private static final int NB_DATAS = 3;
	private static final Float DEFAULT_ANALOGICVALUE = 12.34f;
	private static final Boolean DEFAULT_NUMERICVALUE = true;
	private static final LocalDateTime DEFAULT_MEASUREDATE = LocalDateTime.of(LocalDate.of(2000, 1, 1),
			LocalTime.of(1, 2, 3));
	private static final String DEFAULT_ANALOGICSENSOR_SERIALNUMBER = "4n4l0g1c";
	private static final String DEFAULT_NUMERICSENSOR_SERIALNUMBER = "num3r1c";
	private static final String DEFAULT_ANALOGICSENSOR_NAME = "Analogic-sensor-1";
	private static final String DEFAULT_NUMERICSENSOR_NAME = "Numeric-sensor-1";

	private static Long id_analogicDataAdded;
	private static Long id_numericDataAdded;

	@Test
	void shouldPingOpenAPI() {
		given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().get("/q/openapi").then()
				.statusCode(Status.OK.getStatusCode());
	}

	@Test
	void shouldNotGetUnknownData() {
		given().pathParam("id", -1).when().get("/data/{id}").then().statusCode(Status.NO_CONTENT.getStatusCode());
	}

	@Test
	@Order(1)
	void shouldGetInitialDatas() {
		List<DTOData> datas = given().when().get("/data").then().statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON).extract().body().jsonPath().getList(".");
		assertEquals(NB_DATAS, datas.size());
	}

	@Test
	@Order(4)
	void shouldAddAnalogicData() {
		DTOAnalogicData dtoAnalogicData = new DTOAnalogicData();
		dtoAnalogicData.measureDate = DEFAULT_MEASUREDATE;
		dtoAnalogicData.analogicValue = DEFAULT_ANALOGICVALUE;

		DTOAnalogicData analogicDataAdded = given().body(dtoAnalogicData).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).when().post("/data/analogic").then()
				.statusCode(Status.CREATED.getStatusCode()).extract().body().as(DTOAnalogicData.class);

		assertNotNull(analogicDataAdded);
		id_analogicDataAdded = analogicDataAdded.id;

		List<DTOData> datas = given().when().get("/data").then().statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON).extract().body().jsonPath().getList(".");
		assertEquals(NB_DATAS + 1, datas.size());
	}

	@Test
	@Order(5)
	void shouldAddNumericData() {
		DTONumericData dtoNumericData = new DTONumericData();
		dtoNumericData.measureDate = DEFAULT_MEASUREDATE;
		dtoNumericData.numericValue = DEFAULT_NUMERICVALUE;

		DTONumericData numericDataAdded = given().body(dtoNumericData).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).when().post("/data/numeric").then()
				.statusCode(Status.CREATED.getStatusCode()).extract().body().as(DTONumericData.class);

		assertNotNull(numericDataAdded);
		id_numericDataAdded = numericDataAdded.id;

		List<DTOData> datas = given().when().get("/data").then().statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON).extract().body().jsonPath().getList(".");
		assertEquals(NB_DATAS + 2, datas.size());
	}

	@Test
	@Order(6)
	void shouldGetAnalogicDataById() {
		given().pathParam("id", id_analogicDataAdded).when().get("/data/{id}").then()
				.statusCode(Status.OK.getStatusCode()).contentType(MediaType.APPLICATION_JSON)
				.body("analogicValue", Is.is(DEFAULT_ANALOGICVALUE)).body("measureDate",
						Is.is(DEFAULT_MEASUREDATE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
	}

	@Test
	@Order(7)
	void shouldGetNumericDataById() {
		given().pathParam("id", id_numericDataAdded).when().get("/data/{id}").then()
				.statusCode(Status.OK.getStatusCode()).contentType(MediaType.APPLICATION_JSON)
				.body("numericValue", Is.is(DEFAULT_NUMERICVALUE)).body("measureDate",
						Is.is(DEFAULT_MEASUREDATE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
	}

}
