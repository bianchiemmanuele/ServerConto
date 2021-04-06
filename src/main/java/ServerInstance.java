
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
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
public class ServerInstance {
    private ServerSocket ss;
    private Socket s;
    private String lista ="";
    private ServerManager sm;
    public static final ContiManager cm = new ContiManager();

    public ServerInstance(int port) {
        try {
            ss = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(ServerInstance.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void goSever(){
//        try {
//
//            File file = new File("");
//            File f=new File(file.getAbsoluteFile()+"\\Conti.txt");
//            
//            if(f.exists()){
//                FileReader fr=new FileReader(f);
//            BufferedReader br=new BufferedReader(fr);
//                HashMap<String, ContoCorrente> lista=new HashMap();
//                for(int i=0;i<10;i++){
//
//                    String s=br.readLine();
//                    String[]s1=s.split(":");
//                    ContoCorrente c=new ContoCorrente(s1[0]);
//                    c.versa(Float.parseFloat(s1[1]));
//                    lista.put(s1[0],c);
//                }
//                cm.setLista(lista);
//
//            }else{
//                f.createNewFile();
//
//            }
//        } catch (IOException ex) {
//                Logger.getLogger(ServerInstance.class.getName()).log(Level.SEVERE, null, ex);
//            } 
        do{
            try {
                s = ss.accept();
                sm = new ServerManager(s, cm.getListaConti());
                Thread t = new Thread(sm);
                t.start();
            } catch (IOException ex) {
                Logger.getLogger(ServerInstance.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }while(true);
    }
    
}
