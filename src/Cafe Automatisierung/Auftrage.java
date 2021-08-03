package hmyr;

public class Auftrage {
    
    private String getrankeName , kundenName;
    private int preis,menge,gesammt;

    public Auftrage(String getrankeName, String kundenName, int preis, int menge, int gesammt) {
        this.getrankeName = getrankeName;
        this.kundenName = kundenName;
        this.preis = preis;
        this.menge = menge;
        this.gesammt = gesammt;
    }

    public String getGetrankeName() {
        return getrankeName;
    }

    public void setGetrankeName(String getrankeName) {
        this.getrankeName = getrankeName;
    }

    public String getKundenName() {
        return kundenName;
    }

    public void setKundenName(String kundenName) {
        this.kundenName = kundenName;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public int getGesammt() {
        return gesammt;
    }

    public void setGesammt(int gesammt) {
        this.gesammt = gesammt;
    }
    
    
    
    
}
