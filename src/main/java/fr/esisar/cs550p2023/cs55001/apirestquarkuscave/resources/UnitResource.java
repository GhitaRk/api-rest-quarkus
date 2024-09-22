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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOUnit;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers.UnitMapper;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Unit;

@Path("/units")
public class UnitResource {
	@Inject
	UnitMapper mapper;

	@GET
	public Response getAllUnits() {
		List<Unit> units = Unit.listAll();
		List<DTOUnit> dtoUnits = units.stream().map(unit -> mapper.fromUnit(unit)).collect(Collectors.toList());
		return Response.ok(dtoUnits).build();
	}

	@GET
	@Path("/{id}")
	public Response getUnit(@PathParam("id") Long id) {
		Unit unit = Unit.findById(id);
		if (unit != null) {
			return Response.ok(mapper.fromUnit(unit)).build();
		} else {
			return Response.noContent().build();
		}
	}

	@POST
	@Transactional
	public Response createUnit(@Valid DTOUnit dtoUnit) {
		Unit unit = mapper.toUnit(dtoUnit);
		unit.id = null;
		unit.persist();
		return Response.status(Status.CREATED).entity(mapper.fromUnit(unit)).build();
	}

	@PUT
	@Transactional
	public Response updateUnit(DTOUnit dtoUnit) {
		Unit unit = mapper.toUnit(dtoUnit);
		Unit entity = Unit.findById(unit.id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity.name = unit.name;
		entity.symbol = unit.symbol;
		return Response.ok(mapper.fromUnit(entity)).build();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public Response deleteUnit(@PathParam("id") Long id) {
	Unit entity = Unit.findById(id);
	if (entity == null) {
	return Response.status(Status.NOT_FOUND).build();
	}
	entity.delete();
	return Response.noContent().build();

	}
}	
