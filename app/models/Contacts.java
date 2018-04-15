package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Contacts extends Model{

    public String email;
    public String fullname;
    public String imagen;


    //Constructor: (per inicialitzar amb aquests parametres)
    public Contacts(String email, String fullname, String imagen) {
        this.email = email;
        this.fullname = fullname;
        this.imagen = imagen;
    }

}