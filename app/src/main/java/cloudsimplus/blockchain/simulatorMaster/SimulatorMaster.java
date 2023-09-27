
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cloudsimplus.blockchain.simulatorMaster;

/**
 *
 * @author Fede
 */

import java.security.PublicKey;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
//import java.util.Base64;
import java.util.HashMap;
//import com.google.gson.GsonBuilder;
import java.util.Map;
import cloudsimplus.blockchain.transaction.*;
import cloudsimplus.blockchain.block.*;
import java.util.List;
public class SimulatorMaster {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	
	public static HashMap<String,TransactionOutput> UTXOs = new HashMap<String,TransactionOutput>(); //list of all unspent transactions. 
	
	public static int difficulty = 3;
	public static float minimumTransaction = 0.1f;
	public static Wallet university;
	public static Wallet student;

	public static Transaction genesisTransaction;
   	public static void main(String[] args) {	
               
		
		//Create wallets:
		university = new Wallet();
		student = new Wallet();		
		Wallet coinbase = new Wallet();
		
		//create genesis transaction 
		genesisTransaction = new Transaction(coinbase.publicKey, university.publicKey, 100, null);
		genesisTransaction.generateSignature(coinbase.privateKey);	 //manually sign the genesis transaction	
		genesisTransaction.transactionId = "0"; //manually set the transaction id
		genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.reciepient, genesisTransaction.value, genesisTransaction.transactionId)); //manually add the Transactions Output
		UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0)); //its important to store our first transaction in the UTXOs list.
		
		System.out.println("Creating and Mining Genesis block... ");
		Block genesis = new Block("0");
		genesis.addTransaction(genesisTransaction);
		addBlock(genesis);
				
		Block block1 = new Block(genesis.getHash());		
		System.out.println("\nUniversity is Attempting to send coins to Student...");

		block1.addTransaction(university.sendFunds(student.publicKey, 10));
		addBlock(block1);
		int balanceStudent =  student.getBalance();
		System.out.println("\nStudent's balance is: " + balanceStudent);						
		
		isChainValid();
				
		// System.out.println("\nUniversity issued certifcate to Student: " + (university.publicKey == certs.get(0).publicKey));
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		HashMap<String,TransactionOutput> tempUTXOs = new HashMap<String,TransactionOutput>(); //a temporary working list of unspent transactions at a given block state.
		tempUTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.getHash().equals(currentBlock.calculateHash()) ){
				System.out.println("#Current Hashes not equal");
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.getHash().equals(currentBlock.getPreviousHash()) ) {
				System.out.println("#Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.getHash().substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("#This block hasn't been mined");
				return false;
			}
			
			//loop thru blockchains transactions:
			TransactionOutput tempOutput;
			for(int t=0; t <currentBlock.getTransactions().size(); t++) {
				Transaction currentTransaction = currentBlock.getTransactions().get(t);
				
				if(!currentTransaction.verifySignature()) {
					System.out.println("#Signature on Transaction(" + t + ") is Invalid");
					return false; 
				}				
				
				for(TransactionInput input: currentTransaction.inputs) {	
					tempOutput = tempUTXOs.get(input.transactionOutputId);
					
					if(tempOutput == null) {
						System.out.println("#Referenced input on Transaction(" + t + ") is Missing");
						return false;
					}
					
					if(input.UTXO.value != tempOutput.value) {
						System.out.println("#Referenced input Transaction(" + t + ") value is Invalid");
						return false;
					}
					
					tempUTXOs.remove(input.transactionOutputId);
				}
				
				for(TransactionOutput output: currentTransaction.outputs) {
					tempUTXOs.put(output.id, output);
				}
				
				if( currentTransaction.outputs.get(0).reciepient != currentTransaction.reciepient) {
					System.out.println("#Transaction(" + t + ") output reciepient is not who it should be");
					return false;
				}
				if( currentTransaction.outputs.get(1).reciepient != currentTransaction.sender) {
					System.out.println("#Transaction(" + t + ") output 'change' is not sender.");
					return false;
				}
				
			}
			
		}
		System.out.println("Blockchain is valid");
		return true;
	}
	
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}

}