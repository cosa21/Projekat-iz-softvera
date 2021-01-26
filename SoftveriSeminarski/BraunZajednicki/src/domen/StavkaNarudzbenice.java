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
import java.util.Objects;

/**
 *
 * @author Andjelka
 */
public class StavkaNarudzbenice extends AbstractObjekat{
    private Narudzbenica narudzbenica;
    private int sifraStavke;
    private int kolicina;
    private double vrednost;
    private Proizvod proizvod;

    private String stanje;
    
    public StavkaNarudzbenice() {
    }

    public StavkaNarudzbenice(int sifraStavke, Narudzbenica narudzbenica, int kolicina, double vrednost, Proizvod proizvod) {
        this.sifraStavke = sifraStavke;
        this.narudzbenica = narudzbenica;
        
        this.kolicina = kolicina;
        this.vrednost = vrednost;
        this.proizvod = proizvod;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

  
    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

   
    public double getVrednost() {
        return vrednost;
    }

    public void setVrednost(double vrednost) {
        this.vrednost = vrednost;
    }

   
    public Narudzbenica getNarudzbenica() {
        return narudzbenica;
    }

    public void setNarudzbenica(Narudzbenica narudzbenica) {
        this.narudzbenica = narudzbenica;
    }


     public int getSifraStavke() {
        return sifraStavke;
    }

    public void setSifraStavke(int sifraStavke) {
        this.sifraStavke = sifraStavke;
    }

      public String getStanje() {
        return stanje;
    }

    public void setStanje(String stanje) {
        this.stanje = stanje;
    }
     
    
    
    @Override
    public String vratiImeTabele() {

        return "stavkanarudzbenice";
    }

    @Override
    public String vratiParametre() {
        return String.format("'%s', '%s', '%s', '%s', '%s'", sifraStavke, narudzbenica.getNarudzbenicaID(), kolicina,vrednost,  proizvod.getProizvodID());
    }

    @Override
    public String vratiPK() {

        return null;
    }

    @Override
    public String vratiVrednostPK() {
        return null;
    }

    @Override
    public String vratiSlozenPK() {

        return String.format(" sifraStavke='%s' AND narudzbenicaID='%s' ",  sifraStavke,narudzbenica.getNarudzbenicaID());
    }

    @Override
    public List<AbstractObjekat> RSuTabelu(ResultSet rs) {

           List<AbstractObjekat> stavke = new ArrayList<>();
        try {
            while (rs.next()) {
                int sifraRB = rs.getInt("sifraStavke");
                int narudzbenicaID = rs.getInt("narudzbenicaID");
                int kolicina = rs.getInt("kolicina");
                double vrednost = rs.getDouble("vrednost");
       
                int proizvodID = rs.getInt("proizvodID");
                
                Narudzbenica nar = new Narudzbenica(narudzbenicaID+"");
                
                Proizvod proizvod = new Proizvod(proizvodID + "");
                stavke.add(new StavkaNarudzbenice(sifraRB, nar, kolicina, vrednost,  proizvod));
            }
        } catch (SQLException ex) {
            System.out.println("Greska kod RSuTabelu za StavkaNarudzbenice");
        }
        return stavke;
    }

    @Override
    public String vratiUpdate() {

        return String.format("sifraStavke='%s',narudzbenicaID='%s', kolicina='%s', vrednost='%s', proizvodID='%s'", sifraStavke,narudzbenica.getNarudzbenicaID(), kolicina,vrednost, proizvod.getProizvodID());
    }

    @Override
    public void postaviVrednostPK(String pk) {
    }

    @Override
     public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StavkaNarudzbenice other = (StavkaNarudzbenice) obj;
        if (this.sifraStavke != other.sifraStavke) {
            return false;
        }
        if (!Objects.equals(this.narudzbenica, other.narudzbenica)) {
            return false;
        }
        return true;
    }
   
    

   
    
}
