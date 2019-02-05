/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundo;

import InicioBlock.*;
import com.google.gson.GsonBuilder;
import java.security.Key;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

/**
 *
 * @author ACER
 */
public class StringUtil {

     
    public static String applySha256(String input) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

             
            byte[] hash = digest.digest(input.getBytes("UTF-8"));

            StringBuffer hexString = new StringBuffer(); 
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Short hand helper to turn Object into a json string
    public static String getJson(Object o) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(o);
    }

    //Returns difficulty string target, to compare to hash. eg difficulty of 5 will return "00000"  
    public static String getDificultyString(int difficulty) {
        return new String(new char[difficulty]).replace('\0', '0');
    }
    
    //aplica la firma ECDSA  y devuelve el resultado en bytes
    /*toma la clave privada y el String del remitente, la firma y devuelve una matriz de bytes*/
    public static byte[] applyECDSASig(PrivateKey privateKey, String input){
        Signature dsa;
        byte[] output=new byte[0];
        try{
            dsa=Signature.getInstance("ECDSA","BC");
            dsa.initSign(privateKey);
            byte[] strByte=input.getBytes();
            dsa.update(strByte);
            byte [] realSig=dsa.sign();
            output=realSig;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
        return output;
    }
    
    //verifica la firma de la cadena
    //toma los datos de la firma, clave publica y String y devuelve verdadero o false si la firma es valida 
    public static boolean verifyECDSASig(PublicKey publicKey,String data,byte[] signature){
        try {
            Signature ecdsaVerify =Signature.getInstance("ECDSA","BC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data.getBytes());
            return ecdsaVerify.verify(signature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String getStringFromKey(Key key){
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

}
