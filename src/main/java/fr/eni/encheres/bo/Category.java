package fr.eni.encheres.bo;

import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCat;
    private String label;

    public Category(String label, int idCat) {
        this.label = label;
        this.idCat = idCat;
    }

    public Category() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCat=" + idCat +
                ", label='" + label + '\'' +
                '}';
    }
}
