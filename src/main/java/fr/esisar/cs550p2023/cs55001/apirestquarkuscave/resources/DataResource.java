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

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOAnalogicData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTONumericData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers.DataMapper;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.AnalogicData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Data;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.NumericData;

@Path("/data")
public class DataResource {

	@Inject
	DataMapper mapper;

	@GET
	@Operation(summary = "Retourne toutes les Data")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTOData.class, type = SchemaType.ARRAY)))
	@APIResponse(responseCode = "204", description = "Aucune Data")
	public Response getAllData() {
		List<Data> datas = Data.listAll();
		List<DTOData> dtoDatas = datas.stream().map(data -> mapper.fromData(data)).collect(Collectors.toList());

		return Response.ok(dtoDatas).build();
	}

	@GET
	@Path("/analogic")
	@Operation(summary = "Retourne toutes les AnalogicData")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTOAnalogicData.class, type = SchemaType.ARRAY)))
	@APIResponse(responseCode = "204", description = "Aucune AnalogicData")
	public Response getAllAnalogicData() {
		List<AnalogicData> datas = AnalogicData.listAll();
		List<DTOData> dtoDatas = datas.stream().map(data -> mapper.fromData(data)).collect(Collectors.toList());

		return Response.ok(dtoDatas).build();
	}
	
	@GET
	@Path("/numeric")
	@Operation(summary = "Retourne toutes les AnalogicData")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTONumericData.class, type = SchemaType.ARRAY)))
	@APIResponse(responseCode = "204", description = "Aucune NumericData")
	public Response getAllNumericData() {
		List<NumericData> datas = NumericData.listAll();
		List<DTOData> dtoDatas = datas.stream().map(data -> mapper.fromData(data)).collect(Collectors.toList());

		return Response.ok(dtoDatas).build();
	}
	
	@GET
	@Path("/{id}")
	@Operation(summary = "Retourne une Data à partir de son identifiant")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTOData.class)))
	@APIResponse(responseCode = "204", description = "Aucune Data avec cet identifiant")
	public Response getData(@PathParam("id") Long id) {
		Data data = Data.findById(id);
		if (data != null) {
			return Response.ok(mapper.fromData(data)).build();
		} else {
			return Response.noContent().build();
		}
	}

	@POST
	@Path("/analogic")
	@Operation(summary = "Cree une AnalogicData")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTOAnalogicData.class)))
	@APIResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTOAnalogicData.class)))
	@APIResponse(responseCode = "400", description = "AnalogicData invalide")
	@Transactional
	public Response createData(@Valid DTOAnalogicData dtoAnalogicData) {
		Data data = mapper.toData(dtoAnalogicData);
		data.id = null;
		data.persist();

		return Response.status(Status.CREATED).entity(mapper.fromData(data)).build();
	}

	@POST
	@Path("/numeric")
	@Operation(summary = "Cree une NumericData")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTONumericData.class)))
	@APIResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTONumericData.class)))
	@APIResponse(responseCode = "400", description = "NumericData invalide")
	@Transactional
	public Response createData(@Valid DTONumericData dtoNumericData) {
		Data data = mapper.toData(dtoNumericData);
		data.id = null;
		data.persist();

		return Response.status(Status.CREATED).entity(mapper.fromData(data)).build();
	}

	@PUT
	@Path("/analogic")
	@Operation(summary = "Modifie une AnalogicData à partir de son identifiant")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTOAnalogicData.class)))
	@APIResponse(responseCode = "400", description = "AnalogicData invalide")
	@Transactional
	public Response updateData(DTOAnalogicData dtoAnalogicData) {
		Data data = mapper.toData(dtoAnalogicData);
		Data entity = Data.findById(data.id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.measureDate = data.measureDate;
		((AnalogicData) entity).analogicValue = ((AnalogicData) data).analogicValue;
		((AnalogicData) entity).analogicSensor1 = ((AnalogicData) data).analogicSensor1;

		return Response.ok(mapper.fromData(entity)).build();
	}

	@PUT
	@Path("/numeric")
	@Operation(summary = "Modifie une NumericDat à partir de son identifiant")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DTONumericData.class)))
	@APIResponse(responseCode = "400", description = "NumericDat invalide")
	@Transactional
	public Response updateData(DTONumericData dtoNumericData) {
		Data data = mapper.toData(dtoNumericData);
		Data entity = Data.findById(data.id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.measureDate = data.measureDate;
		((NumericData) entity).numericValue = ((NumericData) data).numericValue;
		((NumericData) entity).numericSensor1 = ((NumericData) data).numericSensor1;

		return Response.ok(mapper.fromData(entity)).build();
	}

	@DELETE
	@Path("/{id}")
	@Operation(summary = "Supprime une Data à partir de mon identifiant")
	@APIResponse(responseCode = "200", description = "Data supprimé")
	@APIResponse(responseCode = "204", description = "Aucune Data")
	@Transactional
	public Response deleteSensor(@PathParam("id") Long id) {
		Data entity = Data.findById(id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.delete();
		return Response.noContent().build();
	}

}
