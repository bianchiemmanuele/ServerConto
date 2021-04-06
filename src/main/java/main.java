
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Megaport
 */
public class main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException {
        ContiManager cm = new ContiManager();
        Scanner sc = new Scanner(System.in);       
        System.out.println("Inserisci la porta");
        new ServerInstance(sc.nextInt()).goSever();
//        System.out.println("lista conti\n"+cm.getListaConti());
        
        
    }
    
}
