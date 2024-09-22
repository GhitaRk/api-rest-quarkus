package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOUnit;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Unit;

@Mapper(componentModel = "cdi")
public interface UnitMapper {
	
	DTOUnit fromUnit(Unit unit);
	
	@InheritInverseConfiguration(name = "fromUnit")
	Unit toUnit(DTOUnit dtoUnit);

}
