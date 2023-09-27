/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cloudsimplus.blockchain.node;

import cloudsimplus.blockchain.network.IP;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author emiliano
 */
public abstract class Node {
	
	private int id;
	private IP ip;
	private Date inputNetDate;
	private Status status;
	private LinkedList ipFullNodes;
	private LinkedList ipLightNodes;
	private LinkedList ipMiners;
	private final int blockAdjust = 2016;
	private int countBlocksFromLastAdjust;
	private int countTotalBlocks;
	private float totalHashRateNetwork;
	private int difficult;

	public enum Status {
		ONLINE, IDLE, OFFLINE
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the ip
	 */
	public IP getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(IP ip) {
		this.ip = ip;
	}

	/**
	 * @return the inputNetDate
	 */
	public Date getInputNetDate() {
		return inputNetDate;
	}

	/**
	 * @param inputNetDate the inputNetDate to set
	 */
	public void setInputNetDate(Date inputNetDate) {
		this.inputNetDate = inputNetDate;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the ipFullNodes
	 */
	public LinkedList getIpFullNodes() {
		return ipFullNodes;
	}

	/**
	 * @param ipFullNodes the ipFullNodes to set
	 */
	public void setIpFullNodes(LinkedList ipFullNodes) {
		this.ipFullNodes = ipFullNodes;
	}

	/**
	 * @return the ipLightNodes
	 */
	public LinkedList getIpLightNodes() {
		return ipLightNodes;
	}

	/**
	 * @param ipLightNodes the ipLightNodes to set
	 */
	public void setIpLightNodes(LinkedList ipLightNodes) {
		this.ipLightNodes = ipLightNodes;
	}

	/**
	 * @return the ipMiners
	 */
	public LinkedList getIpMiners() {
		return ipMiners;
	}

	/**
	 * @param ipMiners the ipMiners to set
	 */
	public void setIpMiners(LinkedList ipMiners) {
		this.ipMiners = ipMiners;
	}

	/**
	 * @return the blockAdjust
	 */
	public int getBlockAdjust() {
		return blockAdjust;
	}

	/**
	 * @return the countBlocksFromLastAdjust
	 */
	public int getCountBlocksFromLastAdjust() {
		return countBlocksFromLastAdjust;
	}

	/**
	 * @param countBlocksFromLastAdjust the countBlocksFromLastAdjust to set
	 */
	public void setCountBlocksFromLastAdjust(int countBlocksFromLastAdjust) {
		this.countBlocksFromLastAdjust = countBlocksFromLastAdjust;
	}

	/**
	 * @return the countTotalBlocks
	 */
	public int getCountTotalBlocks() {
		return countTotalBlocks;
	}

	/**
	 * @param countTotalBlocks the countTotalBlocks to set
	 */
	public void setCountTotalBlocks(int countTotalBlocks) {
		this.countTotalBlocks = countTotalBlocks;
	}

	/**
	 * @return the totalHashRateNetwork
	 */
	public float getTotalHashRateNetwork() {
		return totalHashRateNetwork;
	}

	/**
	 * @param totalHashRateNetwork the totalHashRateNetwork to set
	 */
	public void setTotalHashRateNetwork(float totalHashRateNetwork) {
		this.totalHashRateNetwork = totalHashRateNetwork;
	}

	/**
	 * @return the difficult
	 */
	public int getDifficult() {
		return difficult;
	}

	/**
	 * @param difficult the difficult to set
	 */
	public void setDifficult(int difficult) {
		this.difficult = difficult;
	}
}
