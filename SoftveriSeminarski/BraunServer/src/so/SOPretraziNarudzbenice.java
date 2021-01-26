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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Biljana
 */
public class SOPretraziNarudzbenice extends AbstractSO {

    private String datum;
    private List<AbstractObjekat> listaNadjenih;

    public SOPretraziNarudzbenice(String datum) {
        this.datum = datum;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        List<AbstractObjekat> izBaze = dbb.vratiSveObjekte(new Narudzbenica());
      
        listaNadjenih = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        
        
        for (AbstractObjekat abstractObjekat : izBaze) {
            Narudzbenica nar = (Narudzbenica) abstractObjekat;

            String datumizBaze = sdf.format(nar.getDatum().getTime());
            if (datumizBaze.equals(datum)) {
                
                Dobavljac dob = (Dobavljac) dbb.vratiObjekatPoKljucu(new Dobavljac(), nar.getDobavljac().getDobavljacID());
                
                Menadzer menadzer = (Menadzer) dbb.vratiObjekatPoKljucu(new Menadzer(), nar.getMenadzer().getMenadzerID());

                nar.setDobavljac(dob);
                nar.setMenadzer(menadzer);
              

                List<AbstractObjekat> listaStavki = dbb.ucitajStavkePoIDNarudzbenice(nar.getNarudzbenicaID());
                nar.setListaStavki(listaStavki);

                listaNadjenih.add(nar);
            }
        }
    }

    public List<AbstractObjekat> getListaNadjenih() {
        return listaNadjenih;
    }

}
