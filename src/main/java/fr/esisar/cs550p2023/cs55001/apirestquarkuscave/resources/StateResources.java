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

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOState;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers.StateMapper;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.State;

@Path("/state")
public class StateResources {

	@Inject
	StateMapper mapper;

	@GET
	@Operation(summary = "Retourne tous les States")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOState.class, type=SchemaType.ARRAY)))
	@APIResponse(responseCode = "204",description = "Aucun State")
	public Response getAllState() {
		List<State> states = State.listAll();
		List<DTOState> dtoState = states.stream().map(state -> mapper.fromState(state))
				.collect(Collectors.toList());
		return Response.ok(dtoState).build();
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "Retourne un State à partir de son identifiant")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOState.class)))
	@APIResponse(responseCode = "204",description = "Aucun State avec cet identifiant")
	@APIResponse(responseCode = "400",description = "Identifiant invalide")
	public Response getState(@PathParam("id") Long id) {
		State state = State.findById(id);
		if (state != null) {
			return Response.ok(mapper.fromState(state)).build();
		} else {
			return Response.noContent().build();
		}
	}

	@POST
	@Operation(summary = "Cree un State")
	@APIResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOState.class)))
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOState.class)))
	@APIResponse(responseCode = "400",description = "State invalide")
	@Transactional
	public Response createState(@Valid DTOState dtoState) {
		State state = mapper.toState(dtoState);
		state.id = null;
		state.persist();
		return Response.status(Status.CREATED).entity(mapper.fromState(state)).build();
	}

	@PUT
	@Operation(summary = "Modifie un State à partir de mon identifiant")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema =@Schema(implementation = DTOState.class)))
	@APIResponse(responseCode = "400",description = "State invalide")
	@Transactional
	public Response updateState(DTOState dtoState) {
		State state = mapper.toState(dtoState);
		State entity = State.findById(state.id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.up = state.up;
		entity.down = state.down;
		entity.numericSensor=state.numericSensor;
		return Response.ok(mapper.fromState(entity)).build();
	}

	@DELETE
	@Path("/{id}")
	@Operation(summary = "Supprime un State à partir de mon identifiant")
	@APIResponse(responseCode = "200", description = "State supprimé")
	@APIResponse(responseCode = "204",description = "Aucun State")
	@Transactional
	public Response deleteState(@PathParam("id") Long id) {
		State entity = State.findById(id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.delete();
		return Response.noContent().build();
	}

}

