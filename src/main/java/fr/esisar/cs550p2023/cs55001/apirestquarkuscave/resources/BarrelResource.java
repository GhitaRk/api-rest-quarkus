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

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.RestResponse.Status;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOBarrel;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers.BarrelMapper;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Barrel;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Box;

@Path("/barrel")
public class BarrelResource {
	@Inject
	BarrelMapper mapper;

	@GET
	@Operation(summary = "Retourne tous les Barrel")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOBarrel.class, type=SchemaType.ARRAY)))
	@APIResponse(responseCode = "204",description = "Aucun Barrel")
	public Response getBarrels() {
		List<Barrel> barrels = Barrel.listAll();
		List<DTOBarrel> dtoBarrelUp = barrels.stream().map(barrel -> mapper.fromBarrel(barrel))
				.collect(Collectors.toList());
		return Response.ok(dtoBarrelUp).build();
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "Retourne un Barrel à partir de son identifiant")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOBarrel.class)))
	@APIResponse(responseCode = "204",description = "Aucun Barrel avec cet identifiant")
	@APIResponse(responseCode = "400",description = "Identifiant invalide")
	public Response getBarrel(@PathParam("id") Long id) {
		Barrel barrel = Barrel.findById(id);
		if (barrel != null) {
			return Response.ok(mapper.fromBarrel(barrel)).build();
		} else {
			return Response.noContent().build();
		}
	}

	@POST
	@Transactional
	@Operation(summary = "Cree un Barrel")
	@APIResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOBarrel.class)))
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOBarrel.class)))
	@APIResponse(responseCode = "400",description = "Barrel invalide")
	public Response createBarrel(@Valid DTOBarrel dtoBarrel) {
		Barrel barrel = mapper.toBarrel(dtoBarrel);
		barrel.id = null;
		barrel.barrelCellar = null;
		barrel.box = null;
		barrel.persist();
		return Response.status(Status.CREATED).entity(mapper.fromBarrel(barrel)).build();
	}

	@PUT
	@Transactional
	@Operation(summary = "Modifie un Barrel à partir de son identifiant")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOBarrel.class)))
	@APIResponse(responseCode = "400",description = "Barrel invalide")
	public Response updateBarrel(DTOBarrel dtoBarrel) {
		Barrel barrel = mapper.toBarrel(dtoBarrel);
		Barrel entity = Barrel.findById(barrel.id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.name = barrel.name;
		entity.load = barrel.load;
		//entity.barrelCellar = barrel.barrelCellar;
		entity.box = barrel.box;
		
		
		Box entityBox = Box.findById(barrel.box.id);
		entityBox.barrel = barrel;
		entityBox.persist();
		
		entity.persist();
		
		return Response.ok(mapper.fromBarrel(entity)).build();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	@Operation(summary = "Supprime un Type à partir de son identifiant")
	@APIResponse(responseCode = "200", description = "Barrel supprimé")
	@APIResponse(responseCode = "204",description = "Aucun Barrel")
	public Response deleteBarrel(@PathParam("id") Long id) {
		Barrel entity = Barrel.findById(id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.delete();
		return Response.noContent().build();
	}

}
