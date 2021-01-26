/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;


import domen.AbstractObjekat;
import domen.Dobavljac;
import domen.Drzava;
import domen.Menadzer;
import domen.Narudzbenica;
import exception.ServerskiException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Operacije;
import kontroler.Kontroler;
import transfer.KlijentTransfer;
import transfer.ServerTransfer;

/**
 *
 * @author Biljana
 */
public class KlijentNit extends Thread {

    private Socket socket;
    private final List<KlijentNit> klijenti;
    ObjectInputStream in;
    ObjectOutputStream out;
    AbstractObjekat korisnik;

    public KlijentNit(Socket socket, List<KlijentNit> klijenti) {
        this.socket = socket;
        this.klijenti = klijenti;
    }

    @Override
    public void run() {
        System.out.println("Klijent nit pokrenuta.");
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                System.out.println("Cekam zahtev");
                KlijentTransfer kt = (KlijentTransfer) in.readUnshared();
                ServerTransfer st = new ServerTransfer();
                try{
                int operacija = kt.getOperacija();
                switch (operacija) {
                    case Operacije.LOGIN:
                            Menadzer men = (Menadzer) kt.getParametar();
                            korisnik = Kontroler.vratiInstancu().ulogujKorisnika(men);
                            st.setPodaci(korisnik);
                            break;
                    case Operacije.UCITAJDRZAVE:
                        System.out.println("stigo");
                            List<AbstractObjekat> listaDrzava = Kontroler.vratiInstancu().vratiListuDrzava();
                            st.setPodaci(listaDrzava);
                            break;
                   case Operacije.SACUVAJ_DOBAVLJACA:
                            Dobavljac d = (Dobavljac) kt.getParametar();
                            AbstractObjekat dob = Kontroler.vratiInstancu().kreirajDobavljaca(d);
                            st.setPodaci(dob);
                            break;
                            
                    case Operacije.UCITAJ_DOBAVLJACE:
                            List<AbstractObjekat> listaDobavljaca = Kontroler.vratiInstancu().vratiListuDobavljaca();
                            st.setPodaci(listaDobavljaca);
                            break;
                    case Operacije.PRETRAZI_DOBAVLJACE:
                            Drzava drz = (Drzava) kt.getParametar();
                            List<AbstractObjekat> listaD = Kontroler.vratiInstancu().pretraziDobavljace(drz);
                            st.setPodaci(listaD);
                            break;
                     case Operacije.OBRISI_DOBAVLJACA:
                            Dobavljac doba = (Dobavljac) kt.getParametar();
                            Dobavljac doba1 = (Dobavljac) Kontroler.vratiInstancu().obrisiDobavljaca(doba);
                            st.setPodaci(doba1);
                            break;
                     case Operacije.IZMENI_DOBAVLJACA:
                            Dobavljac dobav = (Dobavljac) kt.getParametar();
                            AbstractObjekat dobav1 = Kontroler.vratiInstancu().izmeniDobavljaca(dobav);
                            st.setPodaci(dobav1);
                            break;
                    case Operacije.UCITAJ_PROIZVODE:
                            List<AbstractObjekat> listaProizvoda = Kontroler.vratiInstancu().vratiListuProizvoda();
                            st.setPodaci(listaProizvoda);
                            break;
                     case Operacije.SACUVAJ_NARUDZBENICU:
                            Narudzbenica nar = (Narudzbenica) kt.getParametar();
                            AbstractObjekat nar1 = Kontroler.vratiInstancu().sacuvajNarudzbenicu(nar);
                            st.setPodaci(nar1);
                            break;
                    case Operacije.UCITAJ_NARUDZBENICE:
                            List<AbstractObjekat> listaNarudzbenica = Kontroler.vratiInstancu().vratiListuNarudzbenica();
                            st.setPodaci(listaNarudzbenica);
                            break;
                    case Operacije.PRETRAZI_NARUDZBENICE:
                            String datumNarudzbenice = (String) kt.getParametar();
                            List<AbstractObjekat> listaPronadjenihNarudzbenica = Kontroler.vratiInstancu().pretraziNarudzbenice(datumNarudzbenice);
                            st.setPodaci(listaPronadjenihNarudzbenica);
                            break;
                    case Operacije.OBRISI_NARUDZBENICU:
                            Narudzbenica zaBrisanje = (Narudzbenica) kt.getParametar();
                            Narudzbenica zaBrisanje1 = (Narudzbenica) Kontroler.vratiInstancu().obrisiNarudzbenicu(zaBrisanje);
                            st.setPodaci(zaBrisanje1);
                            break;
                    case Operacije.IZMENI_NARUDZBENICU:
                            Narudzbenica narudzbenicaZaIzmenu = (Narudzbenica) kt.getParametar();
                            AbstractObjekat narudzbenicaZaIzmenu1 = Kontroler.vratiInstancu().izmeniNarudzbenicu(narudzbenicaZaIzmenu);
                            st.setPodaci(narudzbenicaZaIzmenu1);
                            break;
                    case Operacije.IZLOGUJ_MENADZERA:
                            Menadzer menadzer = (Menadzer) kt.getParametar();
                            Kontroler.vratiInstancu().izlogujMenadzera(menadzer);
                            break;

                }
                st.setUspesnost(1);
                } catch (ServerskiException ex) {
                    st.setUspesnost(-1);
                    st.setException(ex);
                }
                out.writeUnshared(st);
            }
        } catch (SocketException ex) {
            try {
                System.out.println("Klijent se iskljucuje...");
                Kontroler.vratiInstancu().izlogujMenadzera(korisnik);
                in.close();
                out.close();
                socket.close();
                klijenti.remove(this);
            } catch (IOException ex1) {
                Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (ServerskiException ex1) {
                Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

     public AbstractObjekat getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(AbstractObjekat korisnik) {
        this.korisnik = korisnik;
    }



}
