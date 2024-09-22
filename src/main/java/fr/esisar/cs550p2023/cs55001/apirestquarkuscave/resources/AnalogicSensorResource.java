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

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOAnalogicDataFromAnalogicSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOAnalogicSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers.DataMapper;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers.SensorMapper;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.AnalogicData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.AnalogicSensor;

@Path("/analogicsensors")
public class AnalogicSensorResource {
	@Inject
	SensorMapper mapper;

	@Inject
	DataMapper dataMapper;

	@GET
	@Operation(summary = "Retourne tous les AnalogicSensor")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTOAnalogicSensor.class, type = SchemaType.ARRAY)))
	@APIResponse(responseCode = "204", description = "Aucun AnalogicSensor")
	public Response getAllAnalogicSensors() {
		List<AnalogicSensor> analogicSensors = AnalogicSensor.listAll();
		List<DTOAnalogicSensor> dtoAnalogicSensors = analogicSensors.stream()
				.map(analogicSensor -> mapper.fromAnalogicSensor(analogicSensor)).collect(Collectors.toList());
		return Response.ok(dtoAnalogicSensors).build();
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "Retourne un AnalogicSensor à partir de son identifiant")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTOAnalogicSensor.class)))
	@APIResponse(responseCode = "204", description = "Aucun AnalogicSensor avec cet identifiant")
	public Response getAnalogicSensor(@PathParam("id") Long id) {
		AnalogicSensor analogicSensor = AnalogicSensor.findById(id);
		if (analogicSensor != null) {
			return Response.ok(mapper.fromAnalogicSensor(analogicSensor)).build();
		} else {
			return Response.noContent().build();
		}
	}

	@GET
	@Path("/{id}/data")
	@Operation(summary = "Retourne les AnalogicData depuis un AnalogicSensor à partir de son identifiant")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTOAnalogicDataFromAnalogicSensor.class, type = SchemaType.ARRAY)))
	@APIResponse(responseCode = "204", description = "Aucun AnalogicSensor avec cet identifiant")
	public Response getDataFromSensor(@PathParam("id") Long id) {
		AnalogicSensor analogicSensor = AnalogicSensor.findById(id);
		if (analogicSensor != null) {
			List<AnalogicData> analogicDatas = analogicSensor.analogicDatas;
			List<DTOAnalogicDataFromAnalogicSensor> dtoDatas = analogicDatas.stream()
					.map(data -> dataMapper.fromAnalogicDataFromSensor(data)).collect(Collectors.toList());
			return Response.ok(dtoDatas).build();
		} else {
			return Response.noContent().build();
		}
	}

	@POST
	@Transactional
	@Operation(summary = "Cree un AnalogicSensor")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTOAnalogicSensor.class)))
	@APIResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTOAnalogicSensor.class)))
	@APIResponse(responseCode = "400", description = "AnalogicSensor invalide")
	public Response createAnalogicSensor(@Valid DTOAnalogicSensor dtoAnalogicSensor) {
		AnalogicSensor analogicSensor = mapper.toAnalogicSensor(dtoAnalogicSensor);
		analogicSensor.id = null;
		analogicSensor.persist();
		return Response.status(Status.CREATED).entity(mapper.fromAnalogicSensor(analogicSensor)).build();
	}

	@POST
	@Transactional
	@Path("/{id}/data")
	@Operation(summary = "Ajoute un AnalogicData dans la liste d'AnalogicData d'un AnalogicSensor à partir de son identifiant")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTOAnalogicSensor.class)))
	@APIResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTOAnalogicSensor.class)))
	@APIResponse(responseCode = "400", description = "AnalogicData invalide")
	public Response addDataToSensor(@PathParam("id") Long id,
			@Valid DTOAnalogicDataFromAnalogicSensor dtoAnalogicDataFromAnalogicSensor) {
		AnalogicSensor analogicSensor = AnalogicSensor.findById(id);
		if (analogicSensor != null) {
			AnalogicData analogicData = dataMapper.toAnalogicData(dtoAnalogicDataFromAnalogicSensor);
			analogicData.id = null;
			analogicData.analogicSensor1 = analogicSensor;
			analogicData.persist();
			analogicSensor.analogicDatas.add(analogicData);
			return Response.status(Status.CREATED).entity(mapper.fromAnalogicSensor(analogicSensor)).build();
		} else {
			return Response.noContent().build();
		}
	}

	@PUT
	@Transactional
	@Operation(summary = "Modifie un AnalogicSensor à partir de son identifiant")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTOAnalogicSensor.class)))
	@APIResponse(responseCode = "400", description = "AnalogicSensor invalide")
	public Response updateAnalogicSensor(DTOAnalogicSensor dtoAnalogicSensor) {
		AnalogicSensor analogicSensor = mapper.toAnalogicSensor(dtoAnalogicSensor);
		AnalogicSensor entity = AnalogicSensor.findById(analogicSensor.id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.name = analogicSensor.name;
		entity.serialNumber = analogicSensor.serialNumber;
		entity.box1 = analogicSensor.box1;
		entity.type = analogicSensor.type;
		entity.unit = analogicSensor.unit;
		entity.analogicDatas = analogicSensor.analogicDatas;
		return Response.ok(mapper.fromAnalogicSensor(entity)).build();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	@Operation(summary = "Supprime un AnalogicSensor à partir de mon identifiant")
	@APIResponse(responseCode = "200", description = "AnalogicSensor supprimé")
	@APIResponse(responseCode = "204", description = "Aucun AnalogicSensor")
	public Response deleteAnalogicSensor(@PathParam("id") Long id) {
		AnalogicSensor entity = AnalogicSensor.findById(id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.delete();
		return Response.noContent().build();

	}

}
