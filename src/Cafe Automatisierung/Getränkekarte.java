
package hmyr;

class Getränkekarte {
    
    private int id , preis;

    public Getränkekarte(int id, int preis, String name, String Materialien, String getränkecol) {
        this.id = id;
        this.preis = preis;
        this.name = name;
        this.Materialien = Materialien;
        this.getränkecol = getränkecol;
    }
    private String name , Materialien , getränkecol ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterialien() {
        return Materialien;
    }

    public void setMaterialien(String Materialien) {
        this.Materialien = Materialien;
    }

    public String getGetränkecol() {
        return getränkecol;
    }

    public void setGetränkecol(String getränkecol) {
        this.getränkecol = getränkecol;
    }



}
