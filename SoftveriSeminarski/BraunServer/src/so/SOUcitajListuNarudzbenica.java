/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.Dobavljac;
import domen.Menadzer;
import domen.Narudzbenica;
import exception.ServerskiException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Biljana
 */
public class SOUcitajListuNarudzbenica extends AbstractSO {

    private List<AbstractObjekat> listaNarudzbenica;
    private List<AbstractObjekat> listaSvihNarudzbenica;

    public SOUcitajListuNarudzbenica() {
        listaNarudzbenica = new ArrayList<>();
        listaSvihNarudzbenica = new ArrayList<>();
    }

    public List<AbstractObjekat> getListaNarudzbenica() {
        return listaNarudzbenica;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        listaSvihNarudzbenica = dbb.vratiSveObjekte(new Narudzbenica());
        System.out.println(listaSvihNarudzbenica.size());
        ucitajDetalje();
        for (AbstractObjekat abstractObjekat : listaSvihNarudzbenica) {
            Narudzbenica n = (Narudzbenica) abstractObjekat;
            System.out.println(n);
            listaNarudzbenica.add(n);
        }
    }

      private void ucitajDetalje() throws ServerskiException {
   

        for (AbstractObjekat nar : listaSvihNarudzbenica) {
            Narudzbenica narudzbenica = (Narudzbenica) nar;

            Dobavljac dobavljac = (Dobavljac) dbb.vratiObjekatPoKljucu(new Dobavljac(), narudzbenica.getDobavljac().getDobavljacID());
            
            Menadzer menadzer = (Menadzer) dbb.vratiObjekatPoKljucu(new Menadzer(), narudzbenica.getMenadzer().getMenadzerID());

            narudzbenica.setMenadzer(menadzer);
            narudzbenica.setDobavljac(dobavljac);
            List<AbstractObjekat> listaStavki = dbb.ucitajStavkePoIDNarudzbenice(narudzbenica.getNarudzbenicaID());
            narudzbenica.setListaStavki(listaStavki);
           

        }
    }

 
  
}
