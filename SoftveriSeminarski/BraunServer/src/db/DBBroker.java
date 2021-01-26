/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import domen.AbstractObjekat;
import domen.Dobavljac;
import domen.Drzava;
import domen.Proizvod;
import domen.StavkaNarudzbenice;

import exception.ServerskiException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Biljana
 */
public class DBBroker {

    private Connection konekcija;

    public DBBroker() {
    }

    public void uspostaviKonekciju() throws ServerskiException {
        try {
            Class.forName(Util.getInstance().getDriver());
            String url = Util.getInstance().getURL();
            String user = Util.getInstance().getUser();
            String password = Util.getInstance().getPassword();
            konekcija = DriverManager.getConnection(url, user, password);
            konekcija.setAutoCommit(false);
            System.out.println("Uspesna konekcija sa bazom!");
        } catch (IOException ex) {
            throw new ServerskiException("Greska kod citanja iz properties fajla!");
        } catch (ClassNotFoundException ex) {
            throw new ServerskiException("Drajver nije pronadjen!");
        } catch (SQLException ex) {
            throw new ServerskiException("Konekcija na bazu neuspesna!");
        }
    }

    public void raskiniKonekciju() throws ServerskiException {
        try {
            konekcija.close();
            System.out.println("Konekcija uspesno raskinuta");
        } catch (SQLException ex) {
            throw new ServerskiException("Raskidanje konekcije neuspesno!");
        }
    }

    public void potvrdiTransakciju() throws ServerskiException {
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            throw new ServerskiException("Transakcija nije uspesno potvrdjena");
        }
    }

    public void ponistiTransakciju() {
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            System.out.println("Nije uspelo ponistavanje transakcije");
        }
    }

    public List<AbstractObjekat> vratiSveObjekte(AbstractObjekat o) throws ServerskiException {
        try {
            String upit = "SELECT * FROM " + o.vratiImeTabele();
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            List<AbstractObjekat> listaObjekata = o.RSuTabelu(rs);
            s.close();
            System.out.println("Uspesno izvrsen SELECT");
            return listaObjekata;
        } catch (SQLException ex) {
            throw new ServerskiException("Server ne moze da prikaze podatke o " + o.getClass().getName() + ".");
        }
    }

//    public List<AbstractObjekat> vratiSortiraneDrzave(AbstractObjekat o) throws ServerskiException {
//        System.out.println("ovo uradi");
//        try {
//            String upit = "SELECT * FROM" + o.vratiImeTabele() + " ORDER by " + o.vratiVrednostPK();
//            Statement s = konekcija.createStatement();
//            ResultSet rs = s.executeQuery(upit);
//            List<AbstractObjekat> listaObjekata = o.RSuTabelu(rs);
//            s.close();
//            System.out.println("Uspesno izvrsen SELECT");
//            return listaObjekata;
//        } catch (SQLException ex) {
//            throw new ServerskiException("Server ne moze da prikaze podatke o " + o.getClass().getName() + ".");
//        }
//    }

    public AbstractObjekat sacuvajObjekat(AbstractObjekat o) throws ServerskiException {
        try {

            String upit = String.format("INSERT INTO %s VALUES (%s)", o.vratiImeTabele(), o.vratiParametre());
            System.out.println("Upit:\n" + upit);

            Statement s = konekcija.createStatement();
            s.executeUpdate(upit);
            ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID() as last_id from " + o.vratiImeTabele());
            while (rs.next()) {
                String lastid = rs.getString("last_id");
                o.postaviVrednostPK(lastid);
                break;
            }
            s.close();
            return o;
        } catch (MySQLIntegrityConstraintViolationException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            if (o.vratiImeTabele().equals("dobavljac")) {
                throw new ServerskiException("Već postoji dobavljac sa unetim IDem!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<AbstractObjekat> ucitajDobavljace(AbstractObjekat o) throws ServerskiException {
        try {

            String upit = "SELECT * FROM dobavljac ORDER by nazivDobavljaca";
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);

            List<AbstractObjekat> listaObjekata = o.RSuTabelu(rs);
            for (AbstractObjekat ao : listaObjekata) {
                Dobavljac dob = (Dobavljac) ao;
                Drzava drz = (Drzava) vratiObjekatPoKljucu(new Drzava(), dob.getDrzava().getDrzavaID());
                dob.setDrzava(drz);
            }
            s.close();
            return listaObjekata;
        } catch (SQLException ex) {
            throw new ServerskiException(ex.getMessage());
        }
    }

    public AbstractObjekat vratiObjekatPoKljucu(AbstractObjekat o, String ID) throws ServerskiException {
        String upit;
        if (o.vratiPK() != null) {
            upit = "SELECT * FROM " + o.vratiImeTabele() + " WHERE " + o.vratiPK() + "=" + ID;
        } else {
            upit = "SELECT * FROM " + o.vratiImeTabele() + " WHERE " + o.vratiSlozenPK();
        }
        try (Statement s = konekcija.createStatement();) {
            ResultSet rs = s.executeQuery(upit);
            List<AbstractObjekat> listaObjekata = o.RSuTabelu(rs);
            s.close();
            return listaObjekata.get(0);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServerskiException(ex.getMessage());
        }
    }

    public AbstractObjekat izmeniObjekat(AbstractObjekat o) {
        try {
            String upit;
            if (o.vratiPK() != null) {
                upit = String.format("UPDATE %s SET %s WHERE %s = %s", o.vratiImeTabele(), o.vratiUpdate(), o.vratiPK(), o.vratiVrednostPK());
            } else {
                upit = String.format("UPDATE %s SET %s WHERE %s", o.vratiImeTabele(), o.vratiUpdate(), o.vratiSlozenPK());
            }
            System.out.println("Upit:\n" + upit);
            Statement s = konekcija.createStatement();
            s.executeUpdate(upit);
            s.close();
            return o;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public AbstractObjekat obrisiObjekat(AbstractObjekat o) throws ServerskiException {
        try {
            String upit = String.format("DELETE FROM %s WHERE %s = %s", o.vratiImeTabele(), o.vratiPK(), o.vratiVrednostPK());
            Statement s = konekcija.createStatement();
            System.out.println(upit);
            s.executeUpdate(upit);
            potvrdiTransakciju();
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServerskiException(ex.getMessage());
        }
        return o;
    }

    public List<AbstractObjekat> ucitajStavkePoIDNarudzbenice(String narudzbenicaID) throws ServerskiException {
        try {
            StavkaNarudzbenice sta = new StavkaNarudzbenice();
            String upit = "SELECT * FROM stavkanarudzbenice where narudzbenicaID = '" + narudzbenicaID + "'";
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);

            List<AbstractObjekat> listaObjekata = sta.RSuTabelu(rs);
            for (AbstractObjekat ao : listaObjekata) {
                StavkaNarudzbenice sn = (StavkaNarudzbenice) ao;
                Proizvod pr = (Proizvod) vratiObjekatPoKljucu(new Proizvod(), sn.getProizvod().getProizvodID());
                sn.setProizvod(pr);
            }
            s.close();
            return listaObjekata;
        } catch (SQLException ex) {
            throw new ServerskiException(ex.getMessage());
        }
    }

    public AbstractObjekat obrisiStavku(StavkaNarudzbenice o) throws ServerskiException {
        try {
            String upit = "DELETE FROM stavkanarudzbenice WHERE " + o.vratiSlozenPK();
            Statement s = konekcija.createStatement();
            System.out.println(upit);
            s.executeUpdate(upit);
            potvrdiTransakciju();
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServerskiException(ex.getMessage());
        }
        return o;
    }

    public AbstractObjekat sacuvajIliAzurirajObjekat(AbstractObjekat o) throws ServerskiException {
        try {
            List<AbstractObjekat> lista = vratiSveObjekte(o);
            String upit;
            String tipUpita;
            boolean postoji = false;
            for (AbstractObjekat ao : lista) {
                StavkaNarudzbenice sn = (StavkaNarudzbenice) ao;
                StavkaNarudzbenice sn1 = (StavkaNarudzbenice) o;
                if (sn.getNarudzbenica().getNarudzbenicaID().equals(sn1.getNarudzbenica().getNarudzbenicaID()) && sn.getSifraStavke() == sn1.getSifraStavke()) {
                    postoji = true;
                }
            }
            if (postoji) {
                tipUpita = "UPDATE";
                if (o.vratiPK() != null) {
                    upit = String.format("UPDATE %s SET %s WHERE %s = %s", o.vratiImeTabele(), o.vratiUpdate(), o.vratiPK(), o.vratiVrednostPK());
                } else {
                    upit = String.format("UPDATE %s SET %s WHERE %s", o.vratiImeTabele(), o.vratiUpdate(), o.vratiSlozenPK());
                }
            } else {
                tipUpita = "INSERT";
                upit = String.format("INSERT INTO %s VALUES (%s)", o.vratiImeTabele(), o.vratiParametre());
            }
            System.out.println(upit);
            Statement s = konekcija.createStatement();
            s.executeUpdate(upit);
            if (tipUpita.equals("INSERT")) {
                ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID() as last_id from " + o.vratiImeTabele());
                while (rs.next()) {
                    String lastid = rs.getString("last_id");
                    System.out.println(lastid);
                    o.postaviVrednostPK(lastid);
                    break;
                }
            }
            s.close();
            return o;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServerskiException(ex.getMessage());
        }
    }

}
