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

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOType;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers.TypeMapper;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Type;

@Path("/type")
public class TypeResources {

	@Inject
	TypeMapper mapper;

	@GET
	@Operation(summary = "Retourne tous les Type")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOType.class, type=SchemaType.ARRAY)))
	@APIResponse(responseCode = "204",description = "Aucun Type")
	public Response getAllType() {
		List<Type> types = Type.listAll();
		List<DTOType> dtoTypes = types.stream().map(type -> mapper.fromType(type))
				.collect(Collectors.toList());
		return Response.ok(dtoTypes).build();
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "Retourne un Type à partir de son identifiant")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOType.class)))
	@APIResponse(responseCode = "204",description = "Aucun Type avec cet identifiant")
	@APIResponse(responseCode = "400",description = "Identifiant invalide")
	public Response getType(@PathParam("id") Long id) {
		Type type = Type.findById(id);
		if (type != null) {
			return Response.ok(mapper.fromType(type)).build();
		} else {
			return Response.noContent().build();
		}
	}

	@POST
	@Operation(summary = "Cree un Type")
	@APIResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOType.class)))
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOType.class)))
	@APIResponse(responseCode = "400",description = "Type invalide")
	@Transactional
	public Response createType(@Valid DTOType dtoType) {
		Type type = mapper.toType(dtoType);
		type.id = null;
		type.persist();
		return Response.status(Status.CREATED).entity(mapper.fromType(type)).build();
	}

	@PUT
	@Operation(summary = "Modifie un Type à partir de mon identifiant")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOType.class)))
	@APIResponse(responseCode = "400",description = "Type invalide")
	@Transactional
	public Response updateType(DTOType dtoType) {
		Type type = mapper.toType(dtoType);
		Type entity = Type.findById(type.id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.nameType = type.nameType;
		entity.sensors = type.sensors;
		return Response.ok(mapper.fromType(entity)).build();
	}

	@DELETE
	@Path("/{id}")
	@Operation(summary = "Supprime un Type à partir de mon identifiant")
	@APIResponse(responseCode = "200", description = "State supprimé")
	@APIResponse(responseCode = "204",description = "Aucun State")
	@Transactional
	public Response deleteType(@PathParam("id") Long id) {
		Type entity = Type.findById(id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.delete();
		return Response.noContent().build();
	}

}
