/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andjelka
 */
public class Proizvod extends AbstractObjekat{
    private String proizvodID;
    private String nazivProizvoda; 
    private double cena;

    public Proizvod() {
    }

    public Proizvod(String proizvodID) {
        this.proizvodID = proizvodID;
    }

    public Proizvod(String proizvodID, String nazivProizvoda, double cena) {
        this.proizvodID = proizvodID;
        this.nazivProizvoda = nazivProizvoda;
        this.cena = cena;
    }
    

    public String getProizvodID() {
        return proizvodID;
    }

    public void setProizvodID(String proizvodID) {
        this.proizvodID = proizvodID;
    }

    
    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }

  
    @Override
    public String toString() {
        return nazivProizvoda;
    }

    @Override
    public String vratiImeTabele() {
        return "proizvod";
    }

    @Override
    public String vratiParametre() {

        return String.format("'%s', '%s','%s'", proizvodID, nazivProizvoda,cena);   
    }

    @Override
    public String vratiPK() {

        return "proizvodID";
    }

    @Override
    public String vratiVrednostPK() {

        return proizvodID;
    }

    @Override
    public String vratiSlozenPK() {

        return null;
    }

    @Override
    public List<AbstractObjekat> RSuTabelu(ResultSet rs) {

        
        List<AbstractObjekat> proizvodi = new ArrayList<>();
        try {
            while (rs.next()) {
                int proizvodID = rs.getInt("proizvodID");
                String nazivProizvoda = rs.getString("nazivProizvoda");
                double cena = rs.getDouble("cena");
                Proizvod p = new Proizvod(proizvodID+"", nazivProizvoda,cena);
              
                proizvodi.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Greska kod RSuTabelu za Proizvod");
        }
        return proizvodi;
    }

    @Override
    public String vratiUpdate() {
   
        return String.format("proizvodID='%s', nazivProizvoda='%s',cena='%s'", proizvodID, nazivProizvoda,cena);
    }

    @Override
    public void postaviVrednostPK(String pk) {

        this.proizvodID = pk;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
    
}
