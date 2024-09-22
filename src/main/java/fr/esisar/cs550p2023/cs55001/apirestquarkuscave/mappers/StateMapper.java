package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOState;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.State;

@Mapper(componentModel = "cdi")
public interface StateMapper {
	
	DTOState fromState(State state);
	
	@InheritInverseConfiguration(name = "fromState")
	State toState(DTOState dtoState);

}
