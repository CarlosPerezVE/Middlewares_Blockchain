package cloudsimplus.blockchain.block;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author emiliano
 */
public class Block implements Comparable<Block>{
	private int foundInTime;
	
	@Override
	public int compareTo(Block otherBlock) {
		return Integer.compare(this.foundInTime, otherBlock.foundInTime);
	}
	
	public int getFoundInTime() {
		return foundInTime;
	}
}
