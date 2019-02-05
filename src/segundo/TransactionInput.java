/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundo;

/**
 *
 * @author ACER
 */
class TransactionInput {
    public String transactionOutputId;//Referencia a TransactionOutputs -> transactionId
    public TransactionOuput UTXO;//Contiene el resultado de la transacción no utilizada
    public TransactionInput(String transactionOutputId ){
        this.transactionOutputId=transactionOutputId;
    }
    
}
