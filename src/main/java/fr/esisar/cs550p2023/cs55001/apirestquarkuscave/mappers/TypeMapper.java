package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOType;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Type;

@Mapper(componentModel = "cdi")
public interface TypeMapper {
	
	DTOType fromType(Type type);
	
	@InheritInverseConfiguration(name = "fromType")
	Type toType(DTOType dtoType);

}
