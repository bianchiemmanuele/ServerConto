
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
public class ContiManager {
    private HashMap<String, ContoCorrente> lista;
    private String[] lol;
        
        
    public ContiManager() {
        lista = new HashMap();
        lol = new String[10];
        genera();
        
    }
    
    private void genera(){
        for(int i = 0; i < 10; i++){
            lista.put(""+i, new ContoCorrente(""+i));
            lol[i] = ""+i;
        }
        
    }
    
    public String getListaConti(){
        String l="";
        for(String s : lista.keySet()){
            l = l +"::"+s;
        }
        return l;
    }
    
    public boolean addClient(Socket s, String nconto){
        lista.get(nconto).addClient(s);
        return true;
    } 
    
    public void setLista(HashMap<String, ContoCorrente> lista){
        this.lista = lista;
    }
   //metodo nuovo 
    public void Salvattaggio(){
        File f = new File("");
        File f1 = new File(f.getAbsoluteFile()+"\\Conti.txt");
        FileWriter fr;
        try {
            fr = new FileWriter(f1.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fr);
            for(int i = 0; i< lista.size(); i++){
                bw.write(lol[i]+":"+lista.get(i).getSaldo());
                bw.newLine();     
            }
            bw.flush();
            bw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContiManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContiManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
