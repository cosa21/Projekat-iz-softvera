/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.Menadzer;
import exception.ServerskiException;
import java.util.List;
import kontroler.Kontroler;

/**
 *
 * @author Biljana
 */
public class SOUlogujMenadzera extends AbstractSO {

    private AbstractObjekat unetiParametri;
    private AbstractObjekat ulogovanKorisnik;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        List<AbstractObjekat> listaMenadzera = dbb.vratiSveObjekte(new Menadzer());
        Menadzer unetiMenadzer = (Menadzer) unetiParametri;
        for (AbstractObjekat abstractObjekat : listaMenadzera) {
            Menadzer menadzerIzBaze = (Menadzer) abstractObjekat;
            if (menadzerIzBaze.equals(unetiMenadzer)) {
                ulogovanKorisnik = menadzerIzBaze;
                int indeks = Kontroler.vratiInstancu().getListaKorisnika().indexOf(menadzerIzBaze);
                Menadzer izListe = (Menadzer) Kontroler.vratiInstancu().getListaKorisnika().get(indeks);
                if (izListe.isUlogovan()) {
                    throw new ServerskiException("Menadžer je vec ulogovan!");
                } else {
                    izListe.setUlogovan(true);
                }
                System.out.println("Postavio menadžera da je ulogovan.");
                return;
            }
            }
        
        throw new ServerskiException("Sistem ne može da pronadje menadžera!");
    }

    public AbstractObjekat getUnetiParametri() {
        return unetiParametri;
    }

    public void setUnetiParametri(AbstractObjekat unetiParametri) {
        this.unetiParametri = unetiParametri;
    }

    public AbstractObjekat getUlogovanKorisnik() {
        return ulogovanKorisnik;
    }

    public void setUlogovanKorisnik(AbstractObjekat ulogovanKorisnik) {
        this.ulogovanKorisnik = ulogovanKorisnik;
    }

}
