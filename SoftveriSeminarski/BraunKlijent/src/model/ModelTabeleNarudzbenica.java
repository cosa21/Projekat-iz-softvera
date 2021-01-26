/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.AbstractObjekat;
import domen.Narudzbenica;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Biljana
 */
public class ModelTabeleNarudzbenica extends AbstractTableModel{

   List<AbstractObjekat> lista = new ArrayList<>();
    String[] kolone ={"Datum","Ukupno","Menadzer","Dobavljac"};

    public ModelTabeleNarudzbenica(List<AbstractObjekat> lista) {
        this.lista = lista;
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
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Narudzbenica nar = (Narudzbenica) lista.get(rowIndex);
        switch(columnIndex){
            
            case 0:
                return sdf.format(nar.getDatum());
            case 1:
                return nar.getUkupno();
            case 2:
                return nar.getMenadzer().toString();
            case 3:
                return nar.getDobavljac().toString();
            default:
                return "";
        }
    }

    public List<AbstractObjekat> getLista() {
        return lista;
    }
    
}