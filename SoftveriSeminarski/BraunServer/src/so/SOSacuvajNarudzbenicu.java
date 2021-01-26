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
import java.util.List;

/**
 *
 * @author Biljana
 */
public class SOSacuvajNarudzbenicu extends AbstractSO{
    AbstractObjekat param;
    AbstractObjekat narudzbenica;

    public SOSacuvajNarudzbenicu(AbstractObjekat param) {
        this.param = param;
    }
    
    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        narudzbenica = dbb.sacuvajObjekat(param);
        sacuvajStavke();
    }

    public AbstractObjekat getNarudzbenica() {
        return narudzbenica;
    }

    public AbstractObjekat getParam() {
        return param;
    }

    private void sacuvajStavke() throws ServerskiException {
        Narudzbenica nar = (Narudzbenica) param;
        List<AbstractObjekat> stavke = nar.getListaStavki();
        for (AbstractObjekat abstractObjekat : stavke) {
            StavkaNarudzbenice sn = (StavkaNarudzbenice) abstractObjekat;
            dbb.sacuvajObjekat(sn);
        }
    }

    
    
    
    
    
}
