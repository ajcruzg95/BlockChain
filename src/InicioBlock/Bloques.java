/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InicioBlock;



import com.google.gson.GsonBuilder;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class Bloques {

    /**
     * @param args the command line arguments
     */
    public static ArrayList<Block> blockChain = new ArrayList<Block>();
    public static int difficulty = 5;

    public static void main(String[] args) {
        /*Block genesisBlock= new Block("Primer Bloque", "0");
        System.out.println("Bloque 1: "+ genesisBlock.hash);
        
        Block segundoBlock=new Block("segundo Bloque", genesisBlock.hash);
        System.out.println("Bloque 2: "+ segundoBlock.hash);
        
        Block tercerBlock=new Block("tercer Bloque", segundoBlock.hash);
        System.out.println("Bloque 3: "+ tercerBlock.hash);*/
        //add our blocks to the blockchain ArrayList:

        System.out.println("Trying to Mine block 1... ");
        addBlock(new Block("Hi im the first block", "0"));

        System.out.println("Trying to Mine block 2... ");
        addBlock(new Block("Yo im the second block", blockChain.get(blockChain.size() - 1).hash));

        System.out.println("Trying to Mine block 3... ");
        addBlock(new Block("Hey im the third block", blockChain.get(blockChain.size() - 1).hash));

        System.out.println("\nBlockchain is Valid: " + isChainValid());

        String blockchainJson = StringUtil.getJson(blockChain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for (int i = 1; i < blockChain.size(); i++) {
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i - 1);
            //compare registered hash and calculated hash:
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }

        }
        return true;
    }

    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockChain.add(newBlock);
    }
}
