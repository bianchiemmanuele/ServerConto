
import java.net.Socket;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Megaport
 */
public class ContoCorrente {
    private String id;
    private Vector<CommunicationManager> listaClient;
    private float saldo;
    
    public ContoCorrente(String id) {
        this.id = id;       
        saldo = 0;
        listaClient = new Vector <CommunicationManager>();
    }

    //passo la socket e l'istanza di se stesso
    public void addClient(Socket s){
        CommunicationManager ccc = new CommunicationManager(s, this);
        listaClient.add(ccc);
//        new Thread(listaClient.elementAt(listaClient.size())).start();
        Thread t = new Thread(ccc);
        t.start();
        // riga 34-35 new
        if(!ccc.var())
            t.stop();
    }
    
    // da fare!
    public synchronized boolean  versa(float cifra){
        if(cifra != 0){
            saldo = saldo+cifra;
            return true;
        }else{
            return false;
        }
        
    }
    
    // da fare!
    public synchronized boolean preleva(float cifra){
        if(saldo-cifra > 0){
            saldo = saldo-cifra;
            return true;
        }else{
            return false;
        }        
    }
    
    //getSaldo()
    public float getSaldo(){
        return saldo;
    }
}
