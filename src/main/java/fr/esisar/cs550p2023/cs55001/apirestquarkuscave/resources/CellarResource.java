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

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOCellar;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers.CellarMapper;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Barrel;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Box;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Cellar;

@Path("/cellar")
public class CellarResource {
	@Inject
	CellarMapper mapper;

	@GET
	@Operation(summary = "Retourne tous les Cellar")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOCellar.class, type=SchemaType.ARRAY)))
	@APIResponse(responseCode = "204",description = "Aucun Cellar")
	public Response getAllCellars() {
		List<Cellar> cellars = Cellar.listAll();
		List<DTOCellar> dtoCellar = cellars.stream().map(cellar -> mapper.fromCellar(cellar))
				.collect(Collectors.toList());
		return Response.ok(dtoCellar).build();
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "Retourne un Cellar à partir de son identifiant(id)")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOCellar.class)))
	@APIResponse(responseCode = "204",description = "Aucun Cellar avec cet identifiant")
	@APIResponse(responseCode = "400",description = "Identifiant invalide")
	public Response getCellar(@PathParam("id") Long id) {
		Cellar cellar = Cellar.findById(id);
		if (cellar != null) {
			return Response.ok(mapper.fromCellar(cellar)).build();
		} else {
			return Response.noContent().build();
		}
	}

	@POST
	@Transactional
	@Operation(summary = "Cree un Cellar, les attributs Box et Barrel ne sont pas obligatoires")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOCellar.class)))
	@APIResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOCellar.class)))
	@APIResponse(responseCode = "400",description = "Cellar invalide")
	public Response createCellar(@Valid DTOCellar dtoCellar) {
		Cellar cellar = mapper.toCellar(dtoCellar);
		cellar.id = null;
		cellar.persist();
		return Response.status(Status.CREATED).entity(mapper.fromCellar(cellar)).build();
	}

	@PUT
	@Transactional
	@Operation(summary = "Modifie un Cellar à partir son identifiant. Il sert aussi a faire les association Cellar-Barrel et Cellar-Box")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOCellar.class)))
	@APIResponse(responseCode = "400",description = "Cellar invalide")
	
	public Response updateCellar(DTOCellar dtoCellar) {
		Cellar cellar = mapper.toCellar(dtoCellar);
		Cellar entity = Cellar.findById(cellar.id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.name = cellar.name;
		entity.capacity = cellar.capacity;

		for (Box box : cellar.boxs) {
			if (Box.findById(box.id) != null) {
				Box entityBox = Box.findById(box.id);
				entityBox.boxCellar = entity;
				entityBox.persist();
			}
		}

		for (Barrel barrel : cellar.barrels) {
			if (Barrel.findById(barrel.id) != null) {
				Barrel entityBarrel = Barrel.findById(barrel.id);
				entityBarrel.barrelCellar = entity;
				entityBarrel.persist();
			}
		}

		entity.persist();
		return Response.ok(mapper.fromCellar(entity)).build();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	@Operation(summary = "Supprime un Cellar à partir de son identifiant")
	@APIResponse(responseCode = "200", description = "Cellar supprimé")
	@APIResponse(responseCode = "204",description = "Aucun Cellar avec cet identifiant")
	public Response deleteCellar(@PathParam("id") Long id) {
		Cellar entity = Cellar.findById(id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.delete();
		return Response.noContent().build();
	}

}
