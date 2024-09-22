package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOState;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.State;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-21T23:33:49+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@ApplicationScoped
public class StateMapperImpl implements StateMapper {

    @Override
    public DTOState fromState(State state) {
        if ( state == null ) {
            return null;
        }

        DTOState dTOState = new DTOState();

        dTOState.id = state.id;
        dTOState.up = state.up;
        dTOState.down = state.down;

        return dTOState;
    }

    @Override
    public State toState(DTOState dtoState) {
        if ( dtoState == null ) {
            return null;
        }

        State state = new State();

        state.id = dtoState.id;
        state.up = dtoState.up;
        state.down = dtoState.down;

        return state;
    }
}
