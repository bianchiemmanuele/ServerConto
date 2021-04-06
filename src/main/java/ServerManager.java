
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Megaport
 */
public class ServerManager implements Runnable{
    private Socket s;
    private String conti;

    public ServerManager(Socket s, String conti) {
        this.s = s;
        this.conti = conti;
    }
    
    
    @Override
    public void run() {
        String nConto;
        try {
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            
            //1. mando la lista dei conti al client
            pw.println(conti);
            //2. aspetto il numero di conto, verificato dal client (MVC)
            nConto = in.readLine();
            System.out.println(nConto);
            ServerInstance.cm.addClient(s, nConto);
            // riga 44 new
            ServerInstance.cm.Salvattaggio();
//            Thread t = new Thread((Runnable) ServerInstance.cm);
        } catch (IOException ex) {
            Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
