package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOBarrelFromBox;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOBox;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOCellarFromBox;
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
public class BoxMapperImpl implements BoxMapper {

    @Override
    public DTOBox fromBox(Box box) {
        if ( box == null ) {
            return null;
        }

        DTOBox dTOBox = new DTOBox();

        dTOBox.id = box.id;
        dTOBox.serialNumber = box.serialNumber;
        dTOBox.name = box.name;
        dTOBox.barrel = barrelToDTOBarrelFromBox( box.barrel );
        dTOBox.boxCellar = cellarToDTOCellarFromBox( box.boxCellar );

        return dTOBox;
    }

    @Override
    public Box toBox(DTOBox box) {
        if ( box == null ) {
            return null;
        }

        Box box1 = new Box();

        box1.id = box.id;
        box1.serialNumber = box.serialNumber;
        box1.name = box.name;
        box1.boxCellar = dTOCellarFromBoxToCellar( box.boxCellar );
        box1.barrel = dTOBarrelFromBoxToBarrel( box.barrel );

        return box1;
    }

    protected DTOBarrelFromBox barrelToDTOBarrelFromBox(Barrel barrel) {
        if ( barrel == null ) {
            return null;
        }

        DTOBarrelFromBox dTOBarrelFromBox = new DTOBarrelFromBox();

        dTOBarrelFromBox.id = barrel.id;
        dTOBarrelFromBox.name = barrel.name;
        dTOBarrelFromBox.load = barrel.load;

        return dTOBarrelFromBox;
    }

    protected DTOCellarFromBox cellarToDTOCellarFromBox(Cellar cellar) {
        if ( cellar == null ) {
            return null;
        }

        DTOCellarFromBox dTOCellarFromBox = new DTOCellarFromBox();

        dTOCellarFromBox.id = cellar.id;
        dTOCellarFromBox.name = cellar.name;
        dTOCellarFromBox.capacity = cellar.capacity;

        return dTOCellarFromBox;
    }

    protected Cellar dTOCellarFromBoxToCellar(DTOCellarFromBox dTOCellarFromBox) {
        if ( dTOCellarFromBox == null ) {
            return null;
        }

        Cellar cellar = new Cellar();

        cellar.id = dTOCellarFromBox.id;
        cellar.name = dTOCellarFromBox.name;
        cellar.capacity = dTOCellarFromBox.capacity;

        return cellar;
    }

    protected Barrel dTOBarrelFromBoxToBarrel(DTOBarrelFromBox dTOBarrelFromBox) {
        if ( dTOBarrelFromBox == null ) {
            return null;
        }

        Barrel barrel = new Barrel();

        barrel.id = dTOBarrelFromBox.id;
        barrel.name = dTOBarrelFromBox.name;
        barrel.load = dTOBarrelFromBox.load;

        return barrel;
    }
}
