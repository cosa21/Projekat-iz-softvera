/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.AbstractObjekat;
import domen.Proizvod;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Biljana
 */
public class ModelTabeleProizvod extends AbstractTableModel {

    List<AbstractObjekat> lista;
    String[] kolone = {"Naziv", "Cena"};
 

    public ModelTabeleProizvod(List<AbstractObjekat> proizvodi) {
        this.lista = proizvodi;
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
        Proizvod p = (Proizvod) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getNazivProizvoda();
            case 1:
                return p.getCena();
            
             
            default:
                return " ";
        }
    }

    public List<AbstractObjekat> getLista() {
        return lista;
    }

    public void setLista(List<AbstractObjekat> lista) {
        this.lista = lista;
    }
    
    

}
