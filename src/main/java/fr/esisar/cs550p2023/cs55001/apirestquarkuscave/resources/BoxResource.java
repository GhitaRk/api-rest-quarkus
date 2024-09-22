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

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOBox;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers.BoxMapper;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Barrel;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Box;

@Path("/box")
public class BoxResource {
	@Inject
	BoxMapper mapper;

	@GET
	@Operation(summary = "Retourne toutes les Box")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOBox.class, type=SchemaType.ARRAY)))
	@APIResponse(responseCode = "204",description = "Aucun Box")
	public Response getBoxs() {
		List<Box> boxs = Box.listAll();
		List<DTOBox> dtoBox = boxs.stream().map(box -> mapper.fromBox(box)).collect(Collectors.toList());
		return Response.ok(dtoBox).build();
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "Retourne une Box à partir de son identifiant. Permet aussi de faire l'association entre Box et Barrel")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOBox.class)))
	@APIResponse(responseCode = "204",description = "Aucun Box avec cet identifiant")
	@APIResponse(responseCode = "400",description = "Identifiant invalide")
	public Response getBox(@PathParam("id") Long id) {
		Box box = Box.findById(id);
		if (box != null) {
			return Response.ok(mapper.fromBox(box)).build();
		} else {
			return Response.noContent().build();
		}
	}

	@POST
	@Transactional
	@Operation(summary = "Cree une Box")
	@APIResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOBox.class)))
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOBox.class)))
	@APIResponse(responseCode = "400",description = "Box invalide")
	public Response createBox(@Valid DTOBox dtoBox) {
		Box box = mapper.toBox(dtoBox);
		box.id = null;
		box.boxCellar = null;
		box.barrel = null;
		box.persist();
		return Response.status(Status.CREATED).entity(mapper.fromBox(box)).build();
	}
	
	@PUT
	@Transactional
	@Operation(summary = "Modifie une Box à partir de son identifiant")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOBox.class)))
	@APIResponse(responseCode = "400",description = "Box invalide")
	public Response updateBox(DTOBox dtoBox) {
		Box box = mapper.toBox(dtoBox);
		Box entity = Box.findById(box.id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.name = box.name;
		entity.serialNumber = box.serialNumber;
		//entity.boxCellar = box.boxCellar;
		
		Barrel entityBarrel = Barrel.findById(box.barrel.id);
		entityBarrel.box = box;
		entityBarrel.persist();
		entity.persist();
				
		return Response.ok(mapper.fromBox(entity)).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Transactional
	@Operation(summary = "Supprime une Box à partir de son identifiant")
	@APIResponse(responseCode = "200", description = "Box supprimé")
	@APIResponse(responseCode = "204",description = "Aucune Box avec cet identifiant")
	public Response deleteBox(@PathParam("id") Long id) {
		Box entity = Box.findById(id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.delete();
		return Response.noContent().build();
	}

}
