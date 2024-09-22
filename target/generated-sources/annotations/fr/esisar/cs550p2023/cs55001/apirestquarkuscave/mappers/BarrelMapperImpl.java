package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOBarrel;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOBoxFromBarrel;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOCellarFromBarrel;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Barrel;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Box;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Cellar;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-21T23:33:49+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@ApplicationScoped
public class BarrelMapperImpl implements BarrelMapper {

    @Override
    public DTOBarrel fromBarrel(Barrel barrel) {
        if ( barrel == null ) {
            return null;
        }

        DTOBarrel dTOBarrel = new DTOBarrel();

        dTOBarrel.id = barrel.id;
        dTOBarrel.name = barrel.name;
        dTOBarrel.load = barrel.load;
        dTOBarrel.box = boxToDTOBoxFromBarrel( barrel.box );
        dTOBarrel.barrelCellar = cellarToDTOCellarFromBarrel( barrel.barrelCellar );

        return dTOBarrel;
    }

    @Override
    public Barrel toBarrel(DTOBarrel barrel) {
        if ( barrel == null ) {
            return null;
        }

        Barrel barrel1 = new Barrel();

        barrel1.id = barrel.id;
        barrel1.name = barrel.name;
        barrel1.load = barrel.load;
        barrel1.barrelCellar = dTOCellarFromBarrelToCellar( barrel.barrelCellar );
        barrel1.box = dTOBoxFromBarrelToBox( barrel.box );

        return barrel1;
    }

    protected DTOBoxFromBarrel boxToDTOBoxFromBarrel(Box box) {
        if ( box == null ) {
            return null;
        }

        DTOBoxFromBarrel dTOBoxFromBarrel = new DTOBoxFromBarrel();

        dTOBoxFromBarrel.id = box.id;
        dTOBoxFromBarrel.serialNumber = box.serialNumber;
        dTOBoxFromBarrel.name = box.name;

        return dTOBoxFromBarrel;
    }

    protected DTOCellarFromBarrel cellarToDTOCellarFromBarrel(Cellar cellar) {
        if ( cellar == null ) {
            return null;
        }

        DTOCellarFromBarrel dTOCellarFromBarrel = new DTOCellarFromBarrel();

        dTOCellarFromBarrel.id = cellar.id;
        dTOCellarFromBarrel.name = cellar.name;
        dTOCellarFromBarrel.capacity = cellar.capacity;

        return dTOCellarFromBarrel;
    }

    protected Cellar dTOCellarFromBarrelToCellar(DTOCellarFromBarrel dTOCellarFromBarrel) {
        if ( dTOCellarFromBarrel == null ) {
            return null;
        }

        Cellar cellar = new Cellar();

        cellar.id = dTOCellarFromBarrel.id;
        cellar.name = dTOCellarFromBarrel.name;
        cellar.capacity = dTOCellarFromBarrel.capacity;

        return cellar;
    }

    protected Box dTOBoxFromBarrelToBox(DTOBoxFromBarrel dTOBoxFromBarrel) {
        if ( dTOBoxFromBarrel == null ) {
            return null;
        }

        Box box = new Box();

        box.id = dTOBoxFromBarrel.id;
        box.serialNumber = dTOBoxFromBarrel.serialNumber;
        box.name = dTOBoxFromBarrel.name;

        return box;
    }
}
