package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTONumericDataFromNumericSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTONumericSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers.DataMapper;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers.SensorMapper;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.NumericData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.NumericSensor;

@Path("/numericsensors")
public class NumericSensorResource {
	@Inject
	SensorMapper mapper;

	@Inject
	DataMapper dataMapper;

	@GET
	public Response getAllNumericSensorsFrom() {
		List<NumericSensor> numericSensors = NumericSensor.listAll();
		List<DTONumericSensor> dtoNumericSensors = numericSensors.stream()
				.map(numericSensor -> mapper.fromNumericSensor(numericSensor)).collect(Collectors.toList());
		return Response.ok(dtoNumericSensors).build();
	}

	@GET
	@Path("/{id}")
	public Response getNumericSensor(@PathParam("id") Long id) {
		NumericSensor numericSensor = NumericSensor.findById(id);
		if (numericSensor != null) {
			return Response.ok(mapper.fromNumericSensor(numericSensor)).build();
		} else {
			return Response.noContent().build();
		}
	}

	@GET
	@Path("/{id}/data")
	@Operation(summary = "Retourne les NumericData depuis un NumericSensor à partir de son identifiant")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTONumericDataFromNumericSensor.class, type = SchemaType.ARRAY)))
	@APIResponse(responseCode = "204", description = "Aucun NumericSensor avec cet identifiant")
	public Response getDataFromSensor(@PathParam("id") Long id) {
		NumericSensor numericSensor = NumericSensor.findById(id);
		if (numericSensor != null) {
			List<NumericData> numericDatas = numericSensor.numericDatas;
			List<DTONumericDataFromNumericSensor> dtoDatas = numericDatas.stream()
					.map(data -> dataMapper.fromNumericDataFromSensor(data)).collect(Collectors.toList());
			return Response.ok(dtoDatas).build();
		} else {
			return Response.noContent().build();
		}
	}

	@POST
	@Transactional
	public Response createNumericSensor(@Valid DTONumericSensor dtoNumericSensor) {
		NumericSensor numericSensor = mapper.toNumericSensor(dtoNumericSensor);
		numericSensor.id = null;
		numericSensor.persist();
		return Response.status(Status.CREATED).entity(mapper.fromNumericSensor(numericSensor)).build();
	}

	@POST
	@Transactional
	@Path("/{id}/data")
	@Operation(summary = "Ajoute un NumericData dans la liste de NumericData d'un NumericSensor à partir de son identifiant")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTONumericDataFromNumericSensor.class)))
	@APIResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTONumericDataFromNumericSensor.class)))
	@APIResponse(responseCode = "400", description = "NumericData invalide")
	public Response addDataToSensor(@PathParam("id") Long id,
			@Valid DTONumericDataFromNumericSensor dtoNumericDataFromNumericSensor) {
		NumericSensor numericSensor = NumericSensor.findById(id);
		if (numericSensor != null) {
			NumericData numericData = dataMapper.toNumericData(dtoNumericDataFromNumericSensor);
			numericData.id = null;
			numericData.numericSensor1 = numericSensor;
			numericData.persist();
			numericSensor.numericDatas.add(numericData);
			return Response.status(Status.CREATED).entity(mapper.fromNumericSensor(numericSensor)).build();
		} else {
			return Response.noContent().build();
		}
	}

	@PUT
	@Transactional
	public Response updateNumericSensor(DTONumericSensor dtoNumericSensor) {
		NumericSensor numericsensor = mapper.toNumericSensor(dtoNumericSensor);
		NumericSensor entity = NumericSensor.findById(numericsensor.id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.name = numericsensor.name;
		entity.serialNumber = numericsensor.serialNumber;
		entity.box1 = numericsensor.box1;
		entity.type = numericsensor.type;
		entity.state = numericsensor.state;
		entity.numericDatas = numericsensor.numericDatas;
		return Response.ok(mapper.fromNumericSensor(entity)).build();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public Response deleteNumericSensor(@PathParam("id") Long id) {
		NumericSensor entity = NumericSensor.findById(id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.delete();
		return Response.noContent().build();

	}

}
