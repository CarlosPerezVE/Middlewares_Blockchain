/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cloudsimplus.blockchain.block;

import cloudsimplus.blockchain.stringUtil.StringUtil;
import cloudsimplus.blockchain.transaction.Transaction;
/**
 *
 * @author Fede
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/**
 * Bloque de la blockchain
 * 
 */
public class Block implements Comparable<Block>{
        private int id;
        private String hash;
        private String previousHash; 
        private String merkleRoot;
        private ArrayList<Transaction> transactions = new ArrayList<Transaction>(); //lista de transacciones
        private long timeStamp; //as number
        private int foundInTime;
        private int nonce;
        private String estadoBloque;
        public Miner minadoPor;
        private int chainWork;
        
        
        /**
        * Block Constructor para crear el primer bloque de la blockchain.  
        * 
        */
        
        public Block() {
            this.previousHash = "0";
            this.estadoBloque="minando";
            this.timeStamp = new Date().getTime();
            this.chainWork=0;

        }
        /**
        * Block Constructor para crear un bloque nuevo en la blockchain.  
        * 
        */
        
        public Block(LinkedList<Block> CurrentBlockChain){
            Block lastBlock=CurrentBlockChain.getLast();
            previousHash=lastBlock.getHash();
            this.estadoBloque="minando";
            id=lastBlock.getId()+1;
            this.chainWork=lastBlock.getChainWork();
        }
        
        /**
        * Calcula el Hash del bloque
        * @return String calculatedhash
        */
        public String calculateHash() {
            String calculatedhash = StringUtil.applySha256(getPreviousHash() +
                    Long.toString(getTimeStamp()) +
                    Integer.toString(getNonce()) + 
                    getMerkleRoot()
                    );
            return calculatedhash;
        }
            
            /**
            * Añade una transaccion al bloque, retorna boolean si fue posible realizar la transaccion
            * @return boolean
            */
        public boolean addTransaction(Transaction transaction) {
            //process transaction and check if valid, unless block is genesis block then ignore.
            if(transaction == null) return false;		
            if((!"0".equals(previousHash))) {
                if((transaction.processTransaction() != true)) {
                    System.out.println("Transaction failed to process. Discarded.");
                    return false;
                }
            }

            getTransactions().add(transaction);
            System.out.println("Transaction Successfully added to Block");
            return true;
        }
        
        public int setChainWork(){
            this.chainWork=this.getChainWork()+ this.minadoPor.getHashRate()*this.getFoundInTime();
        }
            
    //	public void mineBlock(int difficulty) {
    //		setMerkleRoot(StringUtil.getMerkleRoot(getTransactions()));
    //		String target = StringUtil.getDificultyString(difficulty); //Create a string with difficulty * "0" 
    //		while(!hash.substring( 0, difficulty).equals(target)) {
    //			this.nonce ++;
    //                        this.setHash(calculateHash());
    //		}
    //            
    //		System.out.println("Block Mined!!! : " + getHash());
    //	}

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @return the hash
         */
        public String getHash() {
            return hash;
        }

        /**
         * @return the previousHash
         */
        public String getPreviousHash() {
            return previousHash;
        }

        /**
         * @return the merkleRoot
         */
        public String getMerkleRoot() {
            return merkleRoot;
        }

        /**
         * @return the transactions
         */
        public ArrayList<Transaction> getTransactions() {
            return transactions;
        }

        /**
         * @return the timeStamp
         */
        public long getTimeStamp() {
            return timeStamp;
        }

        /**
         * @return the nonce
         */
        public int getNonce() {
            return nonce;
        }

        /**
         * @return the estadoBloque
         */
        public String getEstadoBloque() {
            return estadoBloque;
        }

        /**
         * @param id the id to set
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * @param hash the hash to set
         */
        public void setHash(String hash) {
            this.hash = hash;
        }

        /**
         * @param previousHash the previousHash to set
         */
        public void setPreviousHash(String previousHash) {
            this.previousHash = previousHash;
        }

        /**
         * @param merkleRoot the merkleRoot to set
         */
        public void setMerkleRoot(String merkleRoot) {
            this.merkleRoot = merkleRoot;
        }

        /**
         * @param transactions the transactions to set
         */
        public void setTransactions(ArrayList<Transaction> transactions) {
            this.transactions = transactions;
        }

        /**
         * @param timeStamp the timeStamp to set
         */
        public void setTimeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
        }

        /**
         * @param nonce the nonce to set
         */
        public void setNonce(int nonce) {
            this.nonce = nonce;
        }

        /**
         * @param estadoBloque the estadoBloque to set
         */
        public void setEstadoBloque(String estadoBloque) {
            this.estadoBloque = estadoBloque;
        }

        /**
         * @param foundInTime the foundInTime to set
         */
        public void setFoundInTime(int foundInTime) {
            this.foundInTime = foundInTime;
        }

        /**
         * @return the foundInTime
         */
        public int getFoundInTime() {
            return foundInTime;
        }

    /**
     * @return the chainWork
     */
    public int getChainWork() {
        return chainWork;
    }
	
	/*
	 * For sort with Collections.sort
	 */
	@Override
	public int compareTo(Block otherBlock) {
		return Integer.compare(this.foundInTime, otherBlock.getFoundInTime());
	}
}
