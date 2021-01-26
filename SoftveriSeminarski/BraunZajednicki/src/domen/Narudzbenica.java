/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Andjelka
 */
public class Narudzbenica extends AbstractObjekat{
    private String narudzbenicaID;
    private Date datum;
    private double ukupno;
    private List<AbstractObjekat> listaStavki;
    private Menadzer menadzer;
    private Dobavljac dobavljac;

    public Narudzbenica() {
    }

    public Narudzbenica(String narudzbenicaID, Date datum, double ukupno, Menadzer menadzer, Dobavljac dobavljac) {
        this.narudzbenicaID = narudzbenicaID;
        this.datum = datum;
        this.ukupno = ukupno;
        this.listaStavki = new ArrayList<>();
        this.menadzer = menadzer;
        this.dobavljac = dobavljac;
    }

   public Narudzbenica(String narudzbenicaID) {
        this.narudzbenicaID = narudzbenicaID;
    }

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }

   
    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public double getUkupno() {
        return ukupno;
    }

    public void setUkupno(double ukupno) {
        this.ukupno = ukupno;
    }

    

    
    public List<AbstractObjekat> getListaStavki() {
        return listaStavki;
    }
    public void setListaStavki(List<AbstractObjekat> listaStavki) {
        this.listaStavki = listaStavki;
    }

    public Menadzer getMenadzer() {
        return menadzer;
    }

    public void setMenadzer(Menadzer menadzer) {
        this.menadzer = menadzer;
    }

   

    @Override
    public String vratiImeTabele() {

        return "narudzbenica";
    }

    @Override
    public String vratiParametre() {

        java.sql.Date datumNar = new java.sql.Date(datum.getTime());
         return String.format("'%s', '%s', '%s', '%s', '%s'", narudzbenicaID, datumNar, ukupno, menadzer.getMenadzerID(), dobavljac.getDobavljacID());
    }

    @Override
    public String vratiPK() {

        return "narudzbenicaID";
    }

    @Override
    public String vratiVrednostPK() {

        return narudzbenicaID;
    }

    @Override
    public String vratiSlozenPK() {
        return null;
    }

    @Override
    public List<AbstractObjekat> RSuTabelu(ResultSet rs) {

         List<AbstractObjekat> narudzbenice = new ArrayList<>();
        try {
            while (rs.next()) {
                int narudzbenicaID = rs.getInt("narudzbenicaID");
                Date datum = rs.getDate("datum");
                double ukupno = rs.getDouble("ukupniIznos");
                int menadzerID = rs.getInt("menadzerID");
                int dobavljacID = rs.getInt("dobavljacID");
                
                Narudzbenica nar = new Narudzbenica(narudzbenicaID+"", datum, ukupno, new Menadzer(menadzerID+""), new Dobavljac(dobavljacID+""));
                
                narudzbenice.add(nar);
                
            }
        } catch (SQLException ex) {
            System.out.println("Greska kod RSuTabelu za Narudzbenica");
        }
        return narudzbenice;
    }

    @Override
    public String vratiUpdate() {

        java.sql.Date datumSQL = new java.sql.Date(datum.getTime());
    return String.format("narudzbenicaID='%s', datum='%s', ukupniIznos='%s', menadzerID='%s',dobavljacID='%s'", narudzbenicaID, datumSQL,ukupno, menadzer.getMenadzerID(), dobavljac.getDobavljacID());

    }

    @Override
    public String toString() {
        return "NarudzbenicaID: "+narudzbenicaID+"\nDatum: "+datum+"\nUkupno:"+ukupno;
    }

    
    @Override
    public void postaviVrednostPK(String pk) {

        this.narudzbenicaID = pk;
    }

    public String getNarudzbenicaID() {
        return narudzbenicaID;
    }

    public void setNarudzbenicaID(String narudzbenicaID) {
        this.narudzbenicaID = narudzbenicaID;
    }
    
    
}
