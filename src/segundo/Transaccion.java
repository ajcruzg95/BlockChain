/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundo;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class Transaccion {
    public String TransactionId; //es el hash de la transaccion
    public PublicKey sender; //direccion del remitente
    public PublicKey reciepient;//direccion del destinatario
    public float value;
    public byte[] signature;//firma :evita que alguien gaste fondos o utilice mal la billetera;
    public ArrayList<TransactionInput> inputs =new ArrayList<TransactionInput>();
    public ArrayList<TransactionOuput> ouputs =new ArrayList<TransactionOuput>();
    
    private static int sequence=0; //recuento de transaccion generadas
    
    public Transaccion(PublicKey from,PublicKey to, float value, ArrayList<TransactionInput> inputs){
        this.sender=from;
        this.reciepient=to;
        this.value=value;
        this.inputs=inputs;
    }
    
    //calcula el hash de la transaccion
    private String calculateHash(){
        sequence++;
        return StringUtil.applySha256(StringUtil.getStringFromKey(sender)+StringUtil.getStringFromKey(reciepient)+Float.toString(value)+sequence);
        
    }
    //firma los datos que no desean ser manupulados
    public void generateSignature(PrivateKey privateKey){
        String data=StringUtil.getStringFromKey(sender)+StringUtil.getStringFromKey(reciepient)+Float.toString(value);
        signature=StringUtil.applyECDSASig(privateKey, data);
    }
    //verifica que los datos firmados no han sido manipulados
    public boolean verifiySignature(){
        String data=StringUtil.getStringFromKey(sender)+StringUtil.getStringFromKey(reciepient)+Float.toString(value);
        return StringUtil.verifyECDSASig(sender, data, signature);
        
    } 
    
    
}
