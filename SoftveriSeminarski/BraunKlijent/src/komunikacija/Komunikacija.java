/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.KlijentTransfer;
import transfer.ServerTransfer;

/**
 *
 * @author Biljana
 */
public class Komunikacija {
    
    private static Komunikacija instanca;
    
    private static Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    
    private Komunikacija(){    
    }
    
    public static Komunikacija vratiInstancu() {
        if (instanca == null) {
            instanca = new Komunikacija();
        }
        return instanca;
    }
    
    public Socket getSocket() {
        return socket;
    }
    
    public void setSocket(Socket socket) throws IOException {
        Komunikacija.socket = socket;
        out = new ObjectOutputStream(Komunikacija.socket.getOutputStream());
        in = new ObjectInputStream(Komunikacija.socket.getInputStream());
        System.out.println("Podeseni in i out stream");
    }

    public void posaljiZahtev(KlijentTransfer kt) throws IOException {
        out.writeUnshared(kt);
    }

    public ServerTransfer procitajOdgovor() throws IOException, ClassNotFoundException {
        return (ServerTransfer) in.readUnshared();
    }
}
