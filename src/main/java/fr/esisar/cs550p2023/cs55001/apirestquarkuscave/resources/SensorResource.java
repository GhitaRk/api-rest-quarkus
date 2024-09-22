package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers.SensorMapper;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Sensor;

@Path("/sensors")
public class SensorResource {

	@Inject
	SensorMapper mapper;

	@GET
	@Operation(summary = "Retourne tous les Sensors")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTOSensor.class, type = SchemaType.ARRAY)))
	@APIResponse(responseCode = "204", description = "Aucun Sensors")
	public Response getAllSensor() {
		List<Sensor> sensors = Sensor.listAll();

		List<DTOSensor> dtoSensors = sensors.stream().map(sensor -> mapper.fromSensor(sensor))
				.collect(Collectors.toList());
		return Response.ok(dtoSensors).build();
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "Retourne un Sensor à partir de son identifiant")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTOSensor.class)))
	@APIResponse(responseCode = "204", description = "Aucun Sensor avec cet identifiant")
	public Response getSensor(@PathParam("id") Long id) {
		Sensor sensor = Sensor.findById(id);
		if (sensor != null) {
			return Response.ok(mapper.fromSensor(sensor)).build();
		} else {
			return Response.noContent().build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	@Operation(summary = "Supprime un Sensor à partir de mon identifiant")
	@APIResponse(responseCode = "200", description = "Sensor supprimé")
	@APIResponse(responseCode = "204", description = "Aucun Sensor")
	public Response deleteSensor(@PathParam("id") Long id) {
		Sensor entity = Sensor.findById(id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.delete();
		return Response.noContent().build();
	}

}
