/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.Dobavljac;
import domen.Drzava;
import exception.ServerskiException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Biljana
 */
public class SOPretraziDobavljace extends AbstractSO {

    List<AbstractObjekat> lista = new ArrayList<>();
    Drzava drzava;

    public SOPretraziDobavljace(Drzava drzava) {
        this.drzava = drzava;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        List<AbstractObjekat> dobavljaci = dbb.ucitajDobavljace(new Dobavljac());
        if (drzava.getDrzavaID().equals("-1")) {
            lista.addAll(dobavljaci);
        } else {
            for (AbstractObjekat ao : dobavljaci) {
                Dobavljac d = (Dobavljac) ao;
                if (d.getDrzava().equals(drzava)) {
                    lista.add(d);
                }
            }
        }
    }

    public List<AbstractObjekat> getLista() {
        return lista;
    }
}
