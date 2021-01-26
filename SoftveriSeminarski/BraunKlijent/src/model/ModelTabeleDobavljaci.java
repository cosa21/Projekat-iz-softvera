/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.AbstractObjekat;
import domen.Dobavljac;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Biljana
 */
public class ModelTabeleDobavljaci extends AbstractTableModel {

    List<AbstractObjekat> lista = new ArrayList<>();
    String[] kolone = {"Naziv", "PIB", "Maticni broj", "Tekuci racun", "Banka"," Grad","Drzava"};
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public ModelTabeleDobavljaci() {
    }

    public ModelTabeleDobavljaci(List<AbstractObjekat> lista) {
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Dobavljac dob = (Dobavljac) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dob.getNazivDobavljaca();
            case 1:
                return dob.getPIB();
            case 2:
                return dob.getMaticniBroj();
            case 3:
                return dob.getTekuciRacun();
            case 4:
                return dob.getBanka();
            case 5:
                return dob.getGrad();
            case 6:
                return dob.getDrzava();
                
            default:
                return " ";
        }

    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<AbstractObjekat> getLista() {
        return lista;
    }

    public void setLista(List<AbstractObjekat> lista) {
        this.lista = lista;
    }

}
