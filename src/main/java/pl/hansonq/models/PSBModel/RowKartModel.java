package pl.hansonq.models.PSBModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RowKartModel {

    private int lp;
    private SimpleStringProperty pozycja_zam;
    private ComboBox<String> kartList;
    private CheckBox select;

    public RowKartModel(int lp,String pozycja_zam) {
        this.lp = lp;
        this.pozycja_zam = new SimpleStringProperty(pozycja_zam);
        this.kartList = new ComboBox<>();
        this.select=new CheckBox();
    }

    public RowKartModel() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RowKartModel that = (RowKartModel) o;
        return lp == that.lp &&
                Objects.equals(pozycja_zam, that.pozycja_zam) &&
                Objects.equals(kartList, that.kartList) &&
                Objects.equals(select, that.select);
    }

    @Override
    public int hashCode() {

        return Objects.hash(lp, pozycja_zam, kartList, select);
    }
}
