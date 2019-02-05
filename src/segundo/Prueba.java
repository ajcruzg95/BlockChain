/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundo;

import java.security.Security;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author ACER
 */
public class Prueba {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;
    public static Billetera billeteraA;
    public static Billetera billeteraB;
    
    public static void main(String args[]){
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); 
        billeteraA=new Billetera();
        billeteraB=new Billetera();
        System.out.println(StringUtil.getStringFromKey(billeteraA.privateKey));
        System.out.println(StringUtil.getStringFromKey(billeteraA.publicKey));
        
        Transaccion transaccion=new Transaccion(billeteraA.publicKey, billeteraB.publicKey, 5, null);
        transaccion.generateSignature(billeteraA.privateKey);
        
        System.out.println("verificando firma");
        System.out.println(transaccion.verifiySignature());
        
    }
    
    /*saldo de la billetera es la suma de todas las salidas de transacciones no gastadas que 
    se le dirigen7
    */
    
    
    
}
