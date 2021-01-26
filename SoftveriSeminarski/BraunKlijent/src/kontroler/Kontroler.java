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
import java.io.IOException;
import java.util.List;
import komunikacija.Komunikacija;
import konstante.Operacije;
import transfer.KlijentTransfer;
import transfer.ServerTransfer;

/**
 *
 * @author Biljana
 */
public class Kontroler {

    private static Kontroler instanca;

    private Kontroler() {
    }

    public static Kontroler vratiInstancu() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    private Object posaljiZahtev(int operacija, Object parametar) throws Exception {
        KlijentTransfer kt = new KlijentTransfer();
        kt.setOperacija(operacija);
        kt.setParametar(parametar);
        Komunikacija.vratiInstancu().posaljiZahtev(kt);
        ServerTransfer st = Komunikacija.vratiInstancu().procitajOdgovor();
        if (st.getUspesnost() == 1) {
            return st.getPodaci();
        } else {
            Exception ex = st.getException();
            throw ex;
        }
    }
    
    private Object posaljiZahtev(int operacija) throws Exception {
        return posaljiZahtev(operacija, null);
    }

     
     public AbstractObjekat ulogujKorisnika(String sifra) throws Exception {
         Menadzer m = new Menadzer(null, null, null, sifra, false);
         return (AbstractObjekat) posaljiZahtev(Operacije.LOGIN, m);
    }
    
    public List<AbstractObjekat> ucitajDrzave() throws Exception {
        return (List<AbstractObjekat>) posaljiZahtev(Operacije.UCITAJDRZAVE);
    }
  
    public AbstractObjekat sacuvajDobavljaca(Dobavljac d) throws Exception {
        return (AbstractObjekat) posaljiZahtev(Operacije.SACUVAJ_DOBAVLJACA, d);
    }

   public List<AbstractObjekat> ucitajDobavljace() throws Exception {
        return (List<AbstractObjekat>) posaljiZahtev(Operacije.UCITAJ_DOBAVLJACE);
    }
   
   public List<AbstractObjekat> pretraziDobavljace(Drzava drzava) throws Exception {
        return (List<AbstractObjekat>) posaljiZahtev(Operacije.PRETRAZI_DOBAVLJACE, drzava);
    }

     public AbstractObjekat obrisiDobavljaca(Dobavljac izabraniDobavljac) throws Exception {
        return (AbstractObjekat) posaljiZahtev(Operacije.OBRISI_DOBAVLJACA, izabraniDobavljac);
    }

      public AbstractObjekat izmeniDobavljaca(Dobavljac izabraniDobavljac) throws Exception {
        return (AbstractObjekat) posaljiZahtev(Operacije.IZMENI_DOBAVLJACA, izabraniDobavljac);
    }

    public List<AbstractObjekat> ucitajProizvode() throws Exception {
        return (List<AbstractObjekat>) posaljiZahtev(Operacije.UCITAJ_PROIZVODE);
    }
    
    public AbstractObjekat sacuvajNarudzbenicu(Narudzbenica nar) throws IOException, ClassNotFoundException, Exception {
        return (AbstractObjekat) posaljiZahtev(Operacije.SACUVAJ_NARUDZBENICU, nar);
    }

     public List<AbstractObjekat> ucitajNarudzbenice() throws Exception {
        return (List<AbstractObjekat>) posaljiZahtev(Operacije.UCITAJ_NARUDZBENICE);
    }

    public List<AbstractObjekat> pretraziNarudzbenice(String datum) throws IOException, Exception {
        return (List<AbstractObjekat>) posaljiZahtev(Operacije.PRETRAZI_NARUDZBENICE, datum);
    }


    public AbstractObjekat obrisiNarudzbenicu(Narudzbenica nar) throws Exception {
        return (AbstractObjekat) posaljiZahtev(Operacije.OBRISI_NARUDZBENICU, nar);
    }
    
     public AbstractObjekat izmeniNarudzbenicu(Narudzbenica nar) throws IOException, ClassNotFoundException, Exception {
        return (AbstractObjekat) posaljiZahtev(Operacije.IZMENI_NARUDZBENICU, nar);
    }
     
      public AbstractObjekat izlogujMenadzera(Menadzer menadzer) throws Exception {
        return (AbstractObjekat) posaljiZahtev(Operacije.IZLOGUJ_MENADZERA, menadzer);
    }

   
    

}
