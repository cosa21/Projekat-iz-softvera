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
public class Dobavljac extends AbstractObjekat{
    private String dobavljacID;
    private String nazivDobavljaca;
    private String PIB;
    private String maticniBroj;
    private String tekuciRacun;
    private String banka;
    private String grad;
    private Drzava drzava;

    public Dobavljac() {
    }

    public Dobavljac(String dobavljacID) {
        this.dobavljacID = dobavljacID;
    }

    public Dobavljac(String dobavljacID, String nazivDobavljaca, String PIB, String maticniBroj, String tekuciRacun, String banka, String grad, Drzava drzava) {
        this.dobavljacID = dobavljacID;
        this.nazivDobavljaca = nazivDobavljaca;
        this.PIB = PIB;
        this.maticniBroj = maticniBroj;
        this.tekuciRacun = tekuciRacun;
        this.banka = banka;
        this.grad = grad;
        this.drzava = drzava;
    }
    


    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    public String getDobavljacID() {
        return dobavljacID;
    }

    public void setDobavljacID(String dobavljacID) {
        this.dobavljacID = dobavljacID;
    }

    public String getNazivDobavljaca() {
        return nazivDobavljaca;
    }

    public void setNazivDobavljaca(String nazivDobavljaca) {
        this.nazivDobavljaca = nazivDobavljaca;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }
    
      public String getTekuciRacun() {
        return tekuciRacun;
    }

    public void setTekuciRacun(String tekuciRacun) {
        this.tekuciRacun = tekuciRacun;
    }

    @Override
    public String toString() {
        return nazivDobavljaca;
    }

    @Override
    public String vratiImeTabele() {

        return "dobavljac";
    }

    @Override
    public String vratiParametre() {
       return String.format("'%s', '%s', '%s', '%s', '%s','%s', '%s', '%s'", dobavljacID, nazivDobavljaca,PIB,maticniBroj, tekuciRacun,banka, grad, drzava.getDrzavaID());
    }

    @Override
    public String vratiPK() {
        return "dobavljacID";
    }

    @Override
    public String vratiVrednostPK() {

        return dobavljacID;
    }

    @Override
    public String vratiSlozenPK() {
      
        return null;
    }

    @Override
    public List<AbstractObjekat> RSuTabelu(ResultSet rs) {

        List<AbstractObjekat> dobavljaci = new ArrayList<>();
        
        try {
            while (rs.next()) {
                int dobavljacID = rs.getInt("dobavljacID");
                String NazivDobavljaca = rs.getString("nazivDobavljaca");
                String PIB = rs.getString("PIB");
                String maticniBroj = rs.getString("maticniBroj");

                String tekuciRacun = rs.getString("tekuciRacun");
                String banka = rs.getString("banka");
                String grad = rs.getString("grad");
                int drzavaID = rs.getInt("drzavaID");
               
                
                Dobavljac dob = new Dobavljac(dobavljacID+"", NazivDobavljaca,PIB,maticniBroj, tekuciRacun, banka,grad, new Drzava(drzavaID+""));
                dobavljaci.add(dob);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Greska kod RSuTabelu za Dobavljac");
        }
        return dobavljaci;
    }

    @Override
    public String vratiUpdate() {
         return String.format("dobavljacID='%s', nazivDobavljaca='%s',PIB='%s',maticniBroj='%s', tekuciRacun='%s',banka='%s', grad='%s',drzavaID='%s'", dobavljacID, nazivDobavljaca,PIB,maticniBroj, tekuciRacun,banka, grad, drzava.getDrzavaID());
    }

    @Override
    public void postaviVrednostPK(String pk) {
        this.dobavljacID = pk;
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
        final Dobavljac other = (Dobavljac) obj;
        if (!Objects.equals(this.dobavljacID, other.dobavljacID)) {
            return false;
        }
        return true;
    }

    public String getPIB() {
        return PIB;
    }

    public void setPIB(String PIB) {
        this.PIB = PIB;
    }

    public String getMaticniBroj() {
        return maticniBroj;
    }

    public void setMaticniBroj(String maticniBroj) {
        this.maticniBroj = maticniBroj;
    }

    public String getBanka() {
        return banka;
    }

    public void setBanka(String banka) {
        this.banka = banka;
    }

  
    
}
