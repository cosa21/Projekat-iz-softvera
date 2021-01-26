/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.Narudzbenica;
import domen.StavkaNarudzbenice;
import exception.ServerskiException;

/**
 *
 * @author Biljana
 */
public class SOIzmeniNarudzbenicu extends AbstractSO{
    AbstractObjekat param;
    AbstractObjekat narudzbenica;

    public SOIzmeniNarudzbenicu(Narudzbenica param) {
        this.param = param;
    }

    public AbstractObjekat getNarudbzenica() {
        return narudzbenica;
    }
    
    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        narudzbenica = dbb.izmeniObjekat(param);
        Narudzbenica nar = (Narudzbenica) narudzbenica;
        for (AbstractObjekat ao:  nar.getListaStavki()) {
            StavkaNarudzbenice sn = (StavkaNarudzbenice) ao;
            if(sn.getStanje()!=null && sn.getStanje().equals("obrisana")){
                dbb.obrisiStavku(sn);
            }else{
                dbb.sacuvajIliAzurirajObjekat(sn);
            }
        }
    }
    
}
