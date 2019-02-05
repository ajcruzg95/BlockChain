/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundo;

import InicioBlock.*;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class Block {

    public String hash;
    public String previousHash;
    private String data; 
    private long timeStamp; 
    private int nonce;

      
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

        this.hash = calculateHash(); 
    }

    
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(previousHash+ Long.toString(timeStamp)+ Integer.toString(nonce)+ data);
        return calculatedhash;
    }

    //Increases nonce value until hash target is reached.
    public void mineBlock(int difficulty) {
        String target = StringUtil.getDificultyString(difficulty); 
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

}
