package controllers;

import play.*;
import play.mvc.*;

import java.util.*;
import models.*;

public class Application extends Controller {

    public static void index() {

        //recordar que tengo que crear una funcion para inicializar los datos de las bases de datos y un boton
        //en mi app para que se inicialicen todas una sola vez, y de esta manera que no me duplique los datos.
        //sino otra cosa que puedo hacer es poner todos los datos desde zampp y borrar la funcion que deberia de crear
        // nombrefuncion(); <- asi inicializo cualquier funcion

        System.out.println("Començem!");

        /*User u = new User("sara@gmail.com","sara1","Sara Padilla Romero").save();
        University uu= new University("0001","Universitat Politècnica de Barcelona","UPC");
       // u.university= uu;
        //u.save();
        uu.Userlist.add(u);
        uu.save();*/

        inicialitzarDades();
        //newUser("sebas","sebasito@gmail.com","123","UPC");
        render();
    }

    public static void inicialitzarDades () { // Inizialitzo totes les dades necessaries per les bases de dades
        //inicialitzo els Usuaris
        User Us1 = new User("sara@gmail.com","sara1","Sara Padilla Romero");
        User Us2 = new User("natalia@gmail.com","natalia1","Natalia Silva Rosario");
        User Us3 = new User("maria@gmail.com","maria1","Maria Valles");
        User Us4 = new User("paula@gmail.com","paula1","Paula Rioja Vitory");

        //inicialitzo les Universitats
        University Un1 = new University("0001","Universitat Politècnica de Catalunya","UPC", "upc");
        University Un2 = new University("0002","Universitat Pompeu Fabra","UPF", "upf");
        University Un3 = new University("0003","Universitat de Barcelona","UB", "ub");
        University Un4 = new University("0004","Universitat Autonoma de Barcelona","UAB", "uab");

        //inicialitzo cada adreça dels llocs i poso les coordenades d'aquesta pagina web: https://www.coordenadas-gps.com/
        Location Loc1 = new Location("UPC","Esteve Terradas","7",41.2755505f, 1.986488300000019f, "upc").save();
        Location Loc2 = new Location("UPF", "Ramon Trias Fargas","25",41.389611927563564f, 2.1903160214424133f, "upf").save();
        Location Loc3 = new Location("UB", "Gran Via de les Corts Catalanes","585",41.386608f, 2.1640199999999368f, "ub").save();
        Location Loc4 = new Location("UAB", "Campus de la UAB","0",41.5019255f, 2.1048538000000008f, "uab").save();

        //inicialitzo cada adreça dels usuaris i poso les coordenades d'aquesta pagina web: https://www.coordenadas-gps.com/
        Adress Adre1 = new Adress("Centre civic Sagrada Familia","Carrer Padilla","254",41.4064037f, 2.1764464000000316f).save();
        Adress Adre2= new Adress("Centre civic San Feliu","Paseo comte de Viladraga","4",41.384284f, 2.0471255999999585f).save();
        Adress Adre3 = new Adress("Centre civic Castelldefels","Passeig dels Tarongers","8",41.2678467f, 1.9531098999999585f).save();
        Adress Adre4 = new Adress("Centre civic Clot", "Carrer Biscaia","322",41.4141016f, 2.190923200000043f).save();

        //Agreguem els usuaris a cada universitat UNIDIRECCIONAL
        Un1.Userlist.add(Us1);
        Un2.Userlist.add(Us2);
        Un3.Userlist.add(Us3);
        Un4.Userlist.add(Us4);

        //Agreguem les direccións a cada universitat i centre civic UNIDIRECCIONAL
        Un1.LocUni = (Loc1);
        Un2.LocUni = (Loc2);
        Un3.LocUni = (Loc3);
        Un4.LocUni = (Loc4);

        //Agreguem les direccións a cada usuari UNIDIRECCIONAL
        Us1.AdrUser = (Adre1);
        Us2.AdrUser = (Adre2);
        Us3.AdrUser= (Adre3);
        Us4.AdrUser = (Adre4);

        //Guardes els usuaris amb les seves localitats
        Us1.save();
        Us2.save();
        Us3.save();
        Us4.save();

        //Guardes les universitats amb les seves localitats i usuaris
        Un1.save();
        Un2.save();
        Un3.save();
        Un4.save();
    }


    //haig de fer que sigui UNIDIRECCIONAL per tal de que funcioni, sino no anirà perque és un problema del JAVA,
    //miro de possar qualsevol dels dos elements, si user o universtity a UNIDIRECCIONAL, i l'altre a res.
    public static void allUsers(){ //Em tornarà la llista de tots els usuaris
        List<User> UserList = User.findAll();

        if (UserList.isEmpty())
        {
            renderText("There are not users");
        }
            renderJSON(UserList);
    }

    //probo amb només un objecte per veure si el JSON va bé
    public static void oneUser(String email){ // Em tornarà el primer usuari a partir de l'email
        User sara = User.find("byEmail", email).first();
        //renderText("Hola");
        renderJSON(sara);
    }

    //http://192.168.1.41:9000/Application/allUniversities
    public static void allUniversities(){ //Em tornarà la llista de totes les universitats
        List<University> UniList = University.findAll();

        if (UniList.isEmpty())
        {
            renderText("There are not universities/");
        }
        renderJSON(UniList);
    }


    public static void checkUser(String email, String password){ //Comprovara si l'email i el password per aquell usuari coincideix
        //pongo en el url: http://192.168.43.40:9000/Application/checkUser?email=sara@gmail.com&password=sara1
        //pongo en el url: http://192.168.1.41:3306/Application/checkUser?email=sara@gmail.com&password=sara1
        User u = User.find("byEmailAndPassword", email, password).first();
        if (u == null)
        {
            renderText("error/");
        }

        renderText("ok/");

    }



    // http://192.168.1.41:9000/Application/newUser?name=Sebas&email=sebas@gmail.com&password=sebas1&university=UPC
    public static void newUser (String name, String email, String password, String university) {
        User n = User.find("byEmail", email).first();
        //si no existeix et crea el nou usuari
      if (n == null) {
            //inicialitzo l'Usuari i el guardo
            User Usi = new User(email, password, name).save();
            //inicialitzo la Universitat
            University Uni = University.find("byAbreviation", university).first();

            if (Uni == null){
                renderText("This university is not correct/error/");
            }

            else {//Agreguem els usuaris a cada universitat UNIDIRECCIONAL
                Uni.Userlist.add(Usi);

                //Guardo la universitat
                Uni.save();
                renderText("User created or actualized/ok/");
            }
        }
        //si existeix et diu que ja hi ha un amb aquell e-mail
        else {
            renderText("This e-mail is already used/error/");
        }
    }

    //http://localhost:9000/Application/deleteUser?email=sebas@gmail.com&password=sebas1
    public static void deleteUser (String email, String password) {
        //trobo l'usuari amb el seu e-mail i password
        User u = User.find("byEmailAndPassword", email, password).first();
        //si no existeix t'envia "error"
        if (u == null) {
            renderText("error, user doesn't exist/");
        }
        //si existeix l'elimina
        else {
            u.delete();
            renderText("Usuari eliminat/");
        }
    }

    //http://localhost:9000/application/newContact?myemail=natalia@gmail.com&youremail=sara@gmail.com
   public static void newContact(String myemail, String youremail){

        User n1 = User.find("byEmail", myemail).first();
        User n2 = User.find("byEmail", youremail).first();

        //si no existeix l'usuari t'avisa
        if (n1 == null|| n2 == null) {
            renderText("Usuario inexistente/error/");
        }
        if (n1!= null && n2!=null){
            String email = n2.email;
            String fullname = n2.fullname;

            Contacts c1 = new Contacts(""+email, ""+fullname, "miniatura").save();
            //c1.AdrUser = (n2.AdrUser);
            c1.save();
            n1.contlist.add(c1);
            n1.save();

            //renderText("Agregado/ok/");

            List<Contacts> list = n1.contlist;
            renderJSON(list);
        }

        else {
            renderText("error/error/");
        }

    }
    //http://localhost:9000/application/ContactList?myemail=sara@gmail.com
    public static void ContactList(String myemail){
        User n3 = User.find("byEmail", myemail).first();

        if (n3== null){
            renderText("Ha ocurrit un error amb el teu email, torna-ho a intentar");
        }

        else {
            List<Contacts> list = n3.contlist;

            if (list.isEmpty()){
                renderText("Aun no tienes contactos agregados");

            }

            else {
                renderJSON(list);
            }
        }
    }

    public static void editUser (String name, String email, String password, String university) {
        //trobo l'usuari amb el seu e-mail i password
        User u = User.find("byEmailAndPassword", email, password).first();
        //si no existeix t'envia "error"
        if (u == null) {
            renderText("wrong email or password/error/");
        }
        //si existeix l'actualitza
        else {
            University Uni = University.find("byAbreviation", university).first();

            if (Uni == null){
                renderText("This university is not yours/error/");
            }

            else {
                Uni.Userlist.remove(u); //Trec l'usiari de la universitat UNIDIRECCIONAL (borro la relació)
                Uni.save(); //Guardo la universitat
                u.delete(); //borro l'usuari

                newUser(name, email, password, university);//Torno a crear l'usuari
            }
        }
    }

    //me calcula la distancia de las localizaciones
    public static float getDistance(float lat, float lon, float la, float lo){
        int R = 6371; // radius of earth in km
        float dLat =  ((lat-la)*((float)Math.PI / 180.0f));
        float dLon = (lon-lo)*((float)Math.PI / 180.0f);
        lat = lat*((float)Math.PI / 180.0f);
        la = la*((float)Math.PI / 180.0f);
        float a = (float)Math.sin(dLat/2) * (float)Math.sin(dLat/2) + (float)Math.sin(dLon/2) * (float)Math.sin(dLon/2) * (float)Math.cos(lat) *(float)Math.cos(la);
        float  c = 2 * (float)Math.atan2((float)Math.sqrt(a), (float)Math.sqrt(1-a));
        float d = R * c;
        return d; //distance in kms
    }

    public static float getMidpoint(float lat, float lon, float la, float lo){
        Float d;
        Float m;

        d = getDistance (lat,lon,la,lo);
        m = d/2;
        return m;
    }

    //me calcula los sitios segun el rango de distancias que tendré
    public static ArrayList<Location> calculatePlace (float md, float lat, float lon, float la, float lo) {
        float md2 = md+5;
        float d1;
        float d2;

        List <Location> meetingpoint = Location.findAll();
        ArrayList <Location> finalplace = new ArrayList<Location>();

        for (Location l: meetingpoint)
        {
            float L1 = l.latitud;
            float L2 = l.longitud;

            d1 = getDistance (lat,lon, L1, L2);
            d2 = getDistance (la,lo, L1, L2);

            if (d1<=md2 && d2 <=md2){
                finalplace.add(l);
                //renderJSON(finalplace);
            }
        }

        return finalplace;
    }

   /* public static void prueba (){

        List <Location> meetingpoint = Location.findAll();

        //meetingpoint = Location.findAll();
        //Location place = Location.get(i);

        renderJSON(meetingpoint);
    }*/

    //http://localhost:9000/application/search?minombre=natalia@gmail.com&sunombre=UPF
    public static void search(String minombre, String sunombre){
        //defino variables
        Float lat;
        Float lon;
        Float la;
        Float lo;
        Float m;
        ArrayList<Location> res = new ArrayList<Location> () ;


        //Miro si es mi casa o mi uni
        User mius = User.find("byEmail", minombre).first();
        University miun = University.find("byAbreviation", minombre).first();

        //miro si es su casa o su uni
        User suus = User.find("byEmail", sunombre).first();
        University suun = University.find("byAbreviation", sunombre).first();

        if (mius == null && miun == null || suus == null && suun == null ){ //si no encuentra ni casa ni uni de cualquiera de los dos me da error
            renderText("Error");
        }
        if (mius != null && suus!=null){ // si encuentra el usuario de ambos, busca la direccion de sus casa
            //miro mis coordenadas
            lat = mius.AdrUser.latitud;
            lon = mius.AdrUser.longitud;
            //miro sus cordenadas
            la = suus.AdrUser.latitud;
            lo = suus.AdrUser.longitud;

            //calculo distancia
            m = getMidpoint (lat,lon,la,lo);
           // renderText(m);

            //calculo lista de sitios
            res = calculatePlace (m, lat, lon, la, lo);

            if (res.isEmpty()){
             renderText("No hay localizaciones");
            }

            else{
                renderJSON(res);
            }

        }
        if (miun != null && suun != null){ // si encuentra la uni de ambos, busca la direccion de la uni
            //miro mis coordenadas
            lat = miun.LocUni.latitud;
            lon = miun.LocUni.longitud;
            //miro sus cordenadas
            la = suun.LocUni.latitud;
            lo = suun.LocUni.longitud;

            //calculo distancia
            m = getMidpoint (lat,lon,la,lo);
            //renderText(m);

            //calculo lista de sitios
            res = calculatePlace (m, lat, lon, la, lo);
            if (res.isEmpty()){
                renderText("No hay localizaciones");
            }

            else{
                renderJSON(res);
            }
        }
        if (miun != null && suus != null){ // si encuentra mi uni y su usuario, busca mi direccion y la dirección de su uni
            //miro mis coordenadas
            lat = miun.LocUni.latitud;
            lon = miun.LocUni.longitud;
            //miro sus cordenadas
            la = suus.AdrUser.latitud;
            lo = suus.AdrUser.longitud;

            //calculo distancia
            m = getMidpoint (lat,lon,la,lo);
            //renderText(m);

            //calculo lista de sitios
            res = calculatePlace (m, lat, lon, la, lo);
            if (res.isEmpty()){
                renderText("No hay localizaciones");
            }

            else{
                renderJSON(res);
            }
        }
        if (mius != null && suun != null){ // si encuentra su uni y mi usuario, busca su direccion y la dirección de mi uni
            //miro mis coordenadas
            lat = mius.AdrUser.latitud;
            lon = mius.AdrUser.longitud;
            //miro sus cordenadas
            la = suun.LocUni.latitud;
            lo = suun.LocUni.longitud;

            //calculo distancia
            m = getMidpoint(lat,lon,la,lo);
           // renderText(m);
            //calculo lista de sitios
            res = calculatePlace (m, lat, lon, la, lo);
            if (res.isEmpty()){
                renderText("No hay localizaciones");
            }

            else{
                renderJSON(res);
            }
        }
    }
}