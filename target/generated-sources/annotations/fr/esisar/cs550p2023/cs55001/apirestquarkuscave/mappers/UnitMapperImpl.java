package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOUnit;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Unit;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-21T23:33:48+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@ApplicationScoped
public class UnitMapperImpl implements UnitMapper {

    @Override
    public DTOUnit fromUnit(Unit unit) {
        if ( unit == null ) {
            return null;
        }

        DTOUnit dTOUnit = new DTOUnit();

        dTOUnit.id = unit.id;
        dTOUnit.name = unit.name;
        dTOUnit.symbol = unit.symbol;

        return dTOUnit;
    }

    @Override
    public Unit toUnit(DTOUnit dtoUnit) {
        if ( dtoUnit == null ) {
            return null;
        }

        Unit unit = new Unit();

        unit.id = dtoUnit.id;
        unit.name = dtoUnit.name;
        unit.symbol = dtoUnit.symbol;

        return unit;
    }
}
