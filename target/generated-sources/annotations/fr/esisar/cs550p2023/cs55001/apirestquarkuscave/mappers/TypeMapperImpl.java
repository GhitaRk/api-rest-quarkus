package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOType;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Type;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-21T23:33:49+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@ApplicationScoped
public class TypeMapperImpl implements TypeMapper {

    @Override
    public DTOType fromType(Type type) {
        if ( type == null ) {
            return null;
        }

        DTOType dTOType = new DTOType();

        dTOType.id = type.id;
        dTOType.nameType = type.nameType;

        return dTOType;
    }

    @Override
    public Type toType(DTOType dtoType) {
        if ( dtoType == null ) {
            return null;
        }

        Type type = new Type();

        type.id = dtoType.id;
        type.nameType = dtoType.nameType;

        return type;
    }
}
