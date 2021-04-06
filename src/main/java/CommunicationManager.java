
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
public class CommunicationManager implements Runnable{
    private  ContoCorrente cc;
    private Socket s;
        
    private InputStream in ;
    private OutputStream out;
    private PrintWriter scrittore;
    private BufferedReader lettore;
    boolean a = true;
    //stream
    public CommunicationManager(Socket s, ContoCorrente cc) {
        this.s = s;
        this.cc = cc;
        try {
            in = s.getInputStream();
            out = s.getOutputStream();
            scrittore = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
            lettore = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(CommunicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    @Override
    public void run() {
        try {
            //gestione dei messaggio ricevuti dal client e le funzioni del contoCorrente
            
            
            
            while(a){
            String comando = lettore.readLine();
            int opzione = Integer.parseInt(comando);   
            switch(opzione){
                case 1:
                    comando = lettore.readLine();
                    float app = Float.parseFloat(comando); 
                    
                    if(cc.versa(app))
                        scrittore.println("Operazione riusicta");
                    else
                        scrittore.println("Operazione fallita");
                   System.out.println(cc.getSaldo());
                   break;
                 case 2:
                    comando = lettore.readLine();
                    float a2 = Float.parseFloat(comando);  
                    if(cc.preleva(a2))
                        scrittore.println("Operazione riusicta");
                    else
                        scrittore.println("Operazione fallita");
                    System.out.println(cc.getSaldo());
                   break;  
                 case 3:
                     String conto = ""+cc.getSaldo();
                                            System.out.println(conto);
                     scrittore.println(conto);
                     break;
                 default:
                     scrittore.println("Error");
                     break;
            }
            if(lettore.readLine().equalsIgnoreCase("si"))
                a = false;  
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(CommunicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //metodo nuovo
    public boolean var(){
        return a;
    }
    
}

