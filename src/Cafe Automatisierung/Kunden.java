package hmyr;

class Kunden {  
    private int id;
    private String name,geburtsdatum,adresse;   

    public Kunden(int id, String name, String geburtsdatum, String adresse) {
        this.id = id;
        this.name = name;
        this.geburtsdatum = geburtsdatum;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    

}
