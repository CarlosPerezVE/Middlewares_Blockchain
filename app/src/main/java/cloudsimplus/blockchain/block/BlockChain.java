/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cloudsimplus.blockchain.block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author emiliano
 */
public class BlockChain {
	private ArrayList<Block> blockChain;
	
	public BlockChain(List<Block> subBlockChain) {
		blockChain = new ArrayList<Block>(subBlockChain);
	}
	
	public int lastBlockNumber() {
		return blockChain.size();
	}
	
	public int getBlockTime(int blockNumber) {
		return blockChain.get(blockNumber).getFoundInTime();
	}
	
	public Block getBlock(int blockNumber) {
		return blockChain.get(blockNumber);
	}
	
	public BlockChain getSubChain(int from, int to) {
		return new BlockChain(blockChain.subList(from, to));
	}
	
	public void sortByFoundInTime() {
		Collections.sort(blockChain);
	}
}
