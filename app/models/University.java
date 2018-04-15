package models;

import java.util.*;
import javax.persistence.*;

import com.sun.javafx.css.CascadingStyle;
import play.db.jpa.*;

@Entity
public class University extends Model {

    public String fullname;
    public String code;
    public String abreviation;
    public String imagen;

    public boolean isAdmin;

    //Relacions:
    @OneToMany //(mappedBy = "University", cascade = CascadeType.ALL)
    public List<User> Userlist = new ArrayList<User>();

    @OneToOne //Unidireccional
    public Location LocUni;

   /* @OneToOne
   public user owner;
   public double price; */

   //Constructor: (per inicialitzar amb aquests parametres)
    public University(String code, String fullname, String abreviation, String imagen) {
        this.code = code;
        this.fullname = fullname;
        this.abreviation = abreviation;
        this.imagen = imagen;
    }

    /* public University (User user) //constructor per inicialitzar amb aquests parametres (editar)
    {
        this.owner = user;
        this.price = 0;
    } */

}
