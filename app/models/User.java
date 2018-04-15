package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class User extends Model {

    public String email;
    public String password;
    public String fullname;
    public boolean isAdmin;

    //Relacions:
    @OneToOne //Unidireccional
    public Adress AdrUser;

    @OneToMany
    public List<Contacts> contlist = new ArrayList<Contacts>() ;

    //@ManyToOne
    //public University university;

    //Constructor: (per inicialitzar amb aquests parametres)
    public User(String email, String password, String fullname) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

    //Creo los getters
   /* public String getEmail() {
        return email;
    }
    public String getFullname() {
        return fullname;
    }
    public String getPassword() {
        return password;
    }
    public boolean isAdmin() {
        return isAdmin;
    }

    //Creo los setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setLocUser(Location locUser) {
        LocUser = locUser;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/


}

