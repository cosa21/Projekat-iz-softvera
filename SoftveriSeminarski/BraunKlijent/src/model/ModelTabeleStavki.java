/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.AbstractObjekat;
import domen.Narudzbenica;
import domen.Proizvod;
import domen.StavkaNarudzbenice;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Biljana
 */
public class ModelTabeleStavki extends AbstractTableModel{

    List<AbstractObjekat> lista = new ArrayList<>();
     List<AbstractObjekat> originalnaLista = new ArrayList<>();
    JTextField txtIznos;

    String[] kolone = {"RB","Naziv", "Cena", "Kolicina","Iznos"};
    
   
    public ModelTabeleStavki(List<AbstractObjekat> lista) {
        this.lista = lista;
       
    }

    public ModelTabeleStavki() {
    }

     @Override
    public int getRowCount() {

        return lista.size();
    }

    @Override
    public int getColumnCount() {

        return kolone.length;
    }

    @Override
     public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaNarudzbenice sn = (StavkaNarudzbenice) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sn.getSifraStavke();
            case 1:
                return sn.getProizvod().getNazivProizvoda();
            case 2:
                return sn.getProizvod().getCena();
            case 3:
                return sn.getKolicina();
            case 4:
                return sn.getVrednost();
            default:
                return "-";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        StavkaNarudzbenice stavka = (StavkaNarudzbenice) lista.get(rowIndex);
        String kol = (String) aValue;
        try {
            int kolicina = Integer.parseInt(kol);
            stavka.setKolicina(kolicina);
            stavka.setVrednost(stavka.getProizvod().getCena() * kolicina);
            postaviIznos();
            fireTableDataChanged();
        } catch (NumberFormatException nfe) {
            return;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 3) {
            return true;
        }
        return false;
    }
    
    
    public void dodajStavku(Proizvod proizvod, int kol, Narudzbenica narudzbenica) {
        StavkaNarudzbenice stavka = daLiPostoji(proizvod);
        if (stavka != null) {
            int kolicina = stavka.getKolicina() + kol;
            double iznos = proizvod.getCena() * kolicina;
            stavka.setKolicina(kolicina);
            stavka.setVrednost(iznos);
            
        } else {
            stavka = new StavkaNarudzbenice(lista.size() + 1,narudzbenica, kol, proizvod.getCena()*kol, proizvod);
            lista.add(stavka);
        }
        
        postaviIznos();
        fireTableDataChanged();
    }

    public List<AbstractObjekat> getLista() {
        return lista;
    }

    
    private void postaviIznos() {
        double iznosN = 0.0;
        for (AbstractObjekat abstractObjekat : lista) {
            StavkaNarudzbenice stavka = (StavkaNarudzbenice) abstractObjekat;
            iznosN += stavka.getVrednost();
        }
        this.txtIznos.setText(iznosN + "");
    }

    private StavkaNarudzbenice daLiPostoji(Proizvod p) {
        for (AbstractObjekat abstractObjekat : lista) {
            StavkaNarudzbenice sn = (StavkaNarudzbenice) abstractObjekat;
//            if (sn.getProizvod().equals(p)) {
//                return sn;
//            }
              if(sn.getProizvod().getNazivProizvoda().equals(p.getNazivProizvoda())){
                  return sn;
              }  
        }
        return null;
    }

    public void izbaciStavku(int red) {

        lista.remove(red);
        postaviIznos();
    }

    public void postaviRB() {

          int rb = 1;
        for (AbstractObjekat abstractObjekat : lista) {
            StavkaNarudzbenice sp = (StavkaNarudzbenice) abstractObjekat;
            sp.setSifraStavke(rb);
            rb++;
        }
        fireTableDataChanged();
        
    
    }
     public void setTxtIznos(JTextField txtIznos) {
        this.txtIznos = txtIznos;
    }

     
    public void setLista(List<AbstractObjekat> lista) {
        this.lista = lista;
    }

     public void popuniOriginalnuListu(List<AbstractObjekat> lista) {
        for (AbstractObjekat abstractObjekat : lista) {
            StavkaNarudzbenice sn = (StavkaNarudzbenice) abstractObjekat;
            originalnaLista.add(abstractObjekat);
        }
    }

    public List<AbstractObjekat> getOriginalnaLista() {
        return originalnaLista;
    }
}
