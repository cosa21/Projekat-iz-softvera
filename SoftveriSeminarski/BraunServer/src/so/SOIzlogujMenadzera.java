/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.Menadzer;
import exception.ServerskiException;
import kontroler.Kontroler;

/**
 *
 * @author Biljana
 */
public class SOIzlogujMenadzera extends AbstractSO {

    private AbstractObjekat menadzer;

    public SOIzlogujMenadzera(AbstractObjekat menadzer) {
        this.menadzer = menadzer;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        int indeks = Kontroler.vratiInstancu().getListaKorisnika().indexOf(menadzer);
        if (indeks != -1) {
            ((Menadzer) Kontroler.vratiInstancu().getListaKorisnika().get(indeks)).setUlogovan(false);
        }
    }

    public AbstractObjekat getMenadzer() {
        return menadzer;
    }

    public void setMenadzer(AbstractObjekat menadzer) {
        this.menadzer = menadzer;
    }

    

}
