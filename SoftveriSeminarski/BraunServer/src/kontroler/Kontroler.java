/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;


import domen.AbstractObjekat;
import domen.Dobavljac;
import domen.Drzava;
import domen.Menadzer;
import domen.Narudzbenica;
import exception.ServerskiException;
import java.util.List;
import so.SOIzlogujMenadzera;
import so.SOIzmeniDobavljaca;
import so.SOIzmeniNarudzbenicu;
import so.SOObrisiDobavljaca;
import so.SOObrisiNarudzbenicu;
import so.SOPretraziDobavljace;
import so.SOPretraziNarudzbenice;
import so.SOSacuvajDobavljaca;
import so.SOSacuvajNarudzbenicu;
import so.SOUcitajDobavljace;
import so.SOUcitajDrzave;
import so.SOUcitajListuKorisnika;
import so.SOUcitajListuNarudzbenica;
import so.SOUcitajProizvode;
import so.SOUlogujMenadzera;


/**
 *
 * @author Biljana
 */
public class Kontroler {

    private static Kontroler instanca;
    private List<AbstractObjekat> listaKorisnika;

    private Kontroler() {
    }

    public static Kontroler vratiInstancu() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public List<AbstractObjekat> getListaKorisnika() throws ServerskiException {
        if (listaKorisnika == null) {
            listaKorisnika = vratiListuKorisnika();
        }
        return listaKorisnika;
    }
    
    private List<AbstractObjekat> vratiListuKorisnika() throws ServerskiException {
        SOUcitajListuKorisnika souk = new SOUcitajListuKorisnika();
        souk.izvrsiOperaciju();
        return souk.getLista();

    }

    public AbstractObjekat ulogujKorisnika(Menadzer men) throws ServerskiException {
        SOUlogujMenadzera som = new SOUlogujMenadzera();
        som.setUnetiParametri(men);
        som.izvrsiOperaciju();
        return som.getUlogovanKorisnik();
    }

    public List<AbstractObjekat> vratiListuDrzava() throws ServerskiException {
        SOUcitajDrzave soud = new SOUcitajDrzave();
        soud.izvrsiOperaciju();
        return soud.getLista();
    }
    
    public AbstractObjekat kreirajDobavljaca(Dobavljac d) throws ServerskiException {
        SOSacuvajDobavljaca so = new SOSacuvajDobavljaca(d);
        so.izvrsiOperaciju();
        return so.getDobavljac();
    }
    
    
    public List<AbstractObjekat> vratiListuDobavljaca() throws ServerskiException {
        SOUcitajDobavljace soud = new SOUcitajDobavljace();
        soud.izvrsiOperaciju();
        return soud.getLista();
    }
    
     public List<AbstractObjekat> pretraziDobavljace(Drzava drz) throws ServerskiException {
        SOPretraziDobavljace sopd = new SOPretraziDobavljace(drz);
        sopd.izvrsiOperaciju();
        return sopd.getLista();
    }

     public AbstractObjekat izmeniDobavljaca(Dobavljac d) throws ServerskiException {
         SOIzmeniDobavljaca so = new SOIzmeniDobavljaca(d);
        so.izvrsiOperaciju();
        return so.getDobavljac();
    }
     
       public AbstractObjekat obrisiDobavljaca(Dobavljac dob) throws ServerskiException {
        SOObrisiDobavljaca soop = new SOObrisiDobavljaca(dob);
        soop.izvrsiOperaciju();
        return soop.getObrisan();
    }
     
       public List<AbstractObjekat> vratiListuProizvoda() throws ServerskiException {
        SOUcitajProizvode soup = new SOUcitajProizvode();
        soup.izvrsiOperaciju();
        return soup.getLista();
    }
       
     public AbstractObjekat sacuvajNarudzbenicu(Narudzbenica nar) throws ServerskiException {
        SOSacuvajNarudzbenicu sokt = new SOSacuvajNarudzbenicu(nar);
        sokt.izvrsiOperaciju();
        return sokt.getNarudzbenica();
    }

     public List<AbstractObjekat> vratiListuNarudzbenica() throws ServerskiException {
       SOUcitajListuNarudzbenica souln = new SOUcitajListuNarudzbenica();
        souln.izvrsiOperaciju();
        return souln.getListaNarudzbenica();
    }
     
      public List<AbstractObjekat> pretraziNarudzbenice(String datum) throws ServerskiException {
        SOPretraziNarudzbenice sopn = new SOPretraziNarudzbenice(datum);
        sopn.izvrsiOperaciju();
        return sopn.getListaNadjenih();
    }
      public AbstractObjekat obrisiNarudzbenicu(Narudzbenica nar) throws ServerskiException {
        SOObrisiNarudzbenicu soon = new SOObrisiNarudzbenicu(nar);
        soon.izvrsiOperaciju();
        return soon.getObrisan();
    }

    public AbstractObjekat izmeniNarudzbenicu(Narudzbenica narudzbenicaZaIzmenu) throws ServerskiException {

        SOIzmeniNarudzbenicu soin = new SOIzmeniNarudzbenicu(narudzbenicaZaIzmenu);
        soin.izvrsiOperaciju();
        return soin.getNarudbzenica();
    }
    public void izlogujMenadzera(AbstractObjekat menadzer) throws ServerskiException {
        SOIzlogujMenadzera soik = new SOIzlogujMenadzera(menadzer);
        soik.izvrsiOperaciju();
    }
}
