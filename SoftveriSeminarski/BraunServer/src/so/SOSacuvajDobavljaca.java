/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import exception.ServerskiException;

/**
 *
 * @author Biljana
 */
public class SOSacuvajDobavljaca extends AbstractSO{

    private AbstractObjekat param;
    private AbstractObjekat dobavljac;
    
    public SOSacuvajDobavljaca(AbstractObjekat param){
        this.param=param;
    }
    
    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        dobavljac = dbb.sacuvajObjekat(param);
    }
    public AbstractObjekat getParam() {
        return param;
    }
    public void setParam(AbstractObjekat param) {
        this.param = param;
    }
    public AbstractObjekat getDobavljac() {
        return dobavljac;
    }

    
    
}
