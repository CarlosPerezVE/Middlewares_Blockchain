/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cloudsimplus.blockchain.network;

/**
 *
 * @author emiliano
 */
public class IP {
	private String IP;
	private int port = 8333;
	
	IP(String IP) {
		this.IP = IP;
	}
	
	public String getIP() {
		return IP;
	}
	
	public void setIP(String IP) {
		this.IP = IP;
	}
}
