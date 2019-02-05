/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundo;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

/**
 *
 * @author ACER
 */
public class Billetera {
    //criptografia curva eliptica 
    public PrivateKey privateKey;
    public PublicKey publicKey;//actua como la direccion 
    
    public Billetera(){
        generateKeyPair();
    }
    public void generateKeyPair(){
        try{
            KeyPairGenerator keyGen= KeyPairGenerator.getInstance("ECDSA","BC");
            SecureRandom random= SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            //se inicializa el generador de las claves y generar keypair
            keyGen .initialize(ecSpec,random);
            KeyPair keyPair=keyGen.generateKeyPair();
            //se establece las claves publicas y privadas desde keypair
            privateKey =keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        }catch(Exception e){
            throw  new RuntimeException(e);
        }
    }
    
}
