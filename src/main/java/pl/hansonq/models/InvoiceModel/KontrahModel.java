package pl.hansonq.models.InvoiceModel;

public class KontrahModel {
    private int idKontrah;
    private int nrKontrah;

    public KontrahModel(int idKontrah, int nrKontrah) {
        this.idKontrah = idKontrah;
        this.nrKontrah = nrKontrah;
    }

    public int getNrKontrah() {
        return nrKontrah;
    }

    public void setNrKontrah(int nrKontrah) {
        this.nrKontrah = nrKontrah;
    }

    public int getIdKontrah() {
        return idKontrah;
    }

    public void setIdKontrah(int idKontrah) {
        this.idKontrah = idKontrah;
    }
}
