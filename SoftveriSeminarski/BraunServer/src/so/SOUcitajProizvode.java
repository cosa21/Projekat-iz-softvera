/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.Proizvod;
import exception.ServerskiException;
import java.util.List;

/**
 *
 * @author Biljana
 */
public class SOUcitajProizvode extends AbstractSO{
    List<AbstractObjekat> lista;
    
    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        lista = dbb.vratiSveObjekte(new Proizvod());
    }

    public List<AbstractObjekat> getLista() {
        return lista;
    }
    
    
    
}
