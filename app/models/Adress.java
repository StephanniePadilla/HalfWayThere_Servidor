package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Adress extends Model{

    public String Loca;
    public String street;
    public String num;
    public Float latitud;
    public Float longitud;



    //Constructor: (per inicialitzar amb aquests parametres)
    public Adress(String Loca, String street, String num, Float latitud, Float longitud) {
        this.Loca = Loca;
        this.street = street;
        this.num = num;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
