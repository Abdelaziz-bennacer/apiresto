package fr.abdel.apiresto.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Menu {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idMenu;

    private String entrees;

    private String plats;

    private String desserts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        Menu menu = (Menu) o;
        return getEntrees().equals(menu.getEntrees()) && getPlats().equals(menu.getPlats()) && getDesserts().equals(menu.getDesserts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntrees(), getPlats(), getDesserts());
    }

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public String getEntrees() {
        return entrees;
    }

    public void setEntrees(String entrees) {
        this.entrees = entrees;
    }

    public String getPlats() {
        return plats;
    }

    public void setPlats(String plats) {
        this.plats = plats;
    }

    public String getDesserts() {
        return desserts;
    }

    public void setDesserts(String desserts) {
        this.desserts = desserts;
    }
}
