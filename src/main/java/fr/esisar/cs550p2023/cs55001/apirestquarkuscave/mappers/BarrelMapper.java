package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOBarrel;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Barrel;

@Mapper(componentModel = "cdi")
public interface BarrelMapper {

	DTOBarrel fromBarrel(Barrel barrel);

	@InheritInverseConfiguration(name = "fromBarrel")
	Barrel toBarrel(DTOBarrel barrel);
}
