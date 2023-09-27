/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cloudsimplus.blockchain.node;

import cloudsimplus.blockchain.block.Block;
import cloudsimplus.blockchain.block.BlockChain;
import java.util.Collections;

/**
 *
 * @author emiliano
 */
public class FullNode extends Node {
	
	private BlockChain blockChain;
	private final int numberOfBlockForCalc = 146;
	
	/**
	 * Naive approach
	 * Number of zeros
	 */
	public void calculateNewDifficult() {
		float avgTime = averageTimeBlockMined();
		int expectedTime = 60*10; // seg = 60 * 10 min
		int diff = getDifficult();
		
		if (avgTime > expectedTime) {
			diff--;
		}
		else {
			diff++;
		}
		
		setDifficult(diff);
	}
	
	/**
	 * Calc: sum_time_last_2016_blocks/2016
	 */
	public float averageTimeBlockMined() {
		int time = 0;
		for (int i=blockChain.lastBlockNumber()-numberOfBlockForCalc; 
				 i<blockChain.lastBlockNumber();
				 i++) {
			time += blockChain.getBlockTime(i);
		}
		return time / numberOfBlockForCalc;
	}
	
	/**
	 * CW-144 of Bitcoin Cash
	 */
	/*
	private void calculateDifficult() {
		Block oldMedian = getMedian();
		Block newMedian = getMedian(true);
		int timestamp = newMedian.getFoundInTime() - oldMedian.getFoundInTime();
		int chainWork = newMedian.getChainWork() - oldMedian.getChainWork();
		
		// estimation: timestamp
		if (timestamp<72*600) {
			timestamp = 72*600;
		}
		else if (timestamp > 288*600) {
			timestamp = 288*600;
		}
	}
	
	public Block getMedian() {
		return getMedian(false);
	}
	
	public Block getMedian(boolean last) {
		BlockChain chain = null;
		if (last) {
			chain = blockChain.getSubChain(blockChain.lastBlockNumber()-numberOfBlockForCalc,
											blockChain.lastBlockNumber()-numberOfBlockForCalc+2);	
		}
		else {
			chain = blockChain.getSubChain(blockChain.lastBlockNumber(),
											blockChain.lastBlockNumber()-2);
		}
		chain.sortByFoundInTime();
		return chain.getBlock(1);
	}*/
}
