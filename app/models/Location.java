package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Location extends Model {

    public String Loca;
    public String street;
    public String num;
    public Float latitud;
    public Float longitud;
    public String image;


    //Constructor: (per inicialitzar amb aquests parametres)
    public Location(String Loca, String street, String num, Float latitud, Float longitud, String image) {
        this.Loca = Loca;
        this.street = street;
        this.num = num;
        this.latitud = latitud;
        this.longitud = longitud;
        this.image = image;
    }

}