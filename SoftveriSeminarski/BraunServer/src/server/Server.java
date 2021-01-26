/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.util.List;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Biljana
 */
public class Server extends Thread {

    private ServerSocket serverSocket;
    public static int brojPorta = 9000;
    static List<KlijentNit> klijenti = new ArrayList<>();
    private static boolean radi = false;

    public Server() {
        try {
            serverSocket = new ServerSocket(brojPorta);
            System.out.println("Kreiran je server socket na portu " + brojPorta);
        } catch (Exception ex) {
            System.out.println("Greska. Server socket nije kreiran.");
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Cekaju se klijenti za povezivanje");
            while (!isInterrupted()) {
                Socket socket = serverSocket.accept();
                KlijentNit kn = new KlijentNit(socket, klijenti);
                kn.start();
                klijenti.add(kn);
                System.out.println("Novi klijent se povezao!");
            }
        } catch (SocketException ex) {
            System.out.println("Konekcija sa serverom je prekinuta");
        } catch (IOException e) {
            System.out.println("Greska kod povezivanja klijenta!");
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static boolean isRadi() {
        return radi;
    }

    public static void setRadi(boolean radi) {
        Server.radi = radi;
    }

    public void zaustaviNiti() {
        try {
            serverSocket.close();
            for (KlijentNit klijentNit : klijenti) {
                klijentNit.getSocket().close();
            }
        } catch (IOException ioe) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ioe);
        }
    }

}
