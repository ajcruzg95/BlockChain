/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundo;

import java.security.PublicKey;

/**
 *
 * @author ACER
 */
class TransactionOuput {
    public String id;
    public PublicKey reciepient; //nuevo propietario de esta moneda
    public float value; //la cantidad de monedas que posee
    public String parentTransactionId; //el id de la transacción en la que se creó esta salida
    
    TransactionOuput(PublicKey reciepient, float value, String parentTransactionId){
        this.reciepient = reciepient;
	this.value = value;
	this.parentTransactionId = parentTransactionId;
	this.id = StringUtil.applySha256(StringUtil.getStringFromKey(reciepient)+Float.toString(value)+parentTransactionId);
    
    }
   
    //comprueba si la moneda te pertenece
    public boolean isMine(PublicKey publicKey) {
		return (publicKey == reciepient);
	}
    
}
