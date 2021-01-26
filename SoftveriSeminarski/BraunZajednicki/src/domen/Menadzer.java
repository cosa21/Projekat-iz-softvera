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
public class Menadzer extends AbstractObjekat{
    private String menadzerID;
    private String ime;
    private String prezime;
    private String sifra;
    private boolean ulogovan;

    public Menadzer() {
    }

    public Menadzer(String menadzerID, String ime, String prezime, String sifra, boolean ulogovan) {
        this.menadzerID = menadzerID;
        this.ime = ime;
        this.prezime = prezime;
        this.sifra = sifra;
        this.ulogovan = ulogovan;
    }

   

    public Menadzer(String menadzerID) {
        this.menadzerID = menadzerID;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getMenadzerID() {
        return menadzerID;
    }

    public void setMenadzerID(String menadzerID) {
        this.menadzerID = menadzerID;
    }

  
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public boolean isUlogovan() {
        return ulogovan;
    }

    public void setUlogovan(boolean ulogovan) {
        this.ulogovan = ulogovan;
    }

    
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
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
        final Menadzer other = (Menadzer) obj;
        if (!Objects.equals(this.sifra, other.sifra)) {
            return false;
        }
        return true;
    }

  
    
    @Override
    public String vratiImeTabele() {

        return "menadzer";
    }

    @Override
    public String vratiParametre() {

        return String.format("'%s', '%s', '%s','%s'", menadzerID, ime, prezime, sifra);   
    }

    @Override
    public String vratiPK() {

        return "menadzerID";
    }

    @Override
    public String vratiVrednostPK() {

        return menadzerID;
    }

    @Override
    public String vratiSlozenPK() {

        return null;
    }

    @Override
    public List<AbstractObjekat> RSuTabelu(ResultSet rs) {

         
        List<AbstractObjekat> menadzeri = new ArrayList<>();
        try {
            while (rs.next()) {
                int menadzerID = rs.getInt("menadzerID");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");

                String sifra = rs.getString("sifraMenadzera");

                Menadzer men = new Menadzer(menadzerID+"", ime, prezime, sifra,false);
              
                menadzeri.add(men);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Greska kod RSuTabelu za Menadzer");
        }
        return menadzeri;
    }

    @Override
    public String vratiUpdate() {

       return String.format("menadzerID='%s', ime='%s',prezime='%s',sifraMenadzera='%s'", menadzerID, ime,prezime,sifra);

    }

    @Override
    public void postaviVrednostPK(String pk) {

        this.menadzerID = pk;
    }
    
}
