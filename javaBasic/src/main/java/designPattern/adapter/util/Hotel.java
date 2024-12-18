package designPattern.adapter.util;

import designPattern.adapter.interfacer.DBSocketInterface;

/**
 * 德国旅馆，人家只有 德国 插座，用德国充电器 充电
 * @author Dangdang
 *
 */
public class Hotel {
	private DBSocketInterface dbSocket;

	public DBSocketInterface getDbSocket() {
		return dbSocket;
	}

	public void setDbSocket(DBSocketInterface dbSocket) {
		this.dbSocket = dbSocket;
	}
	
	public Hotel() {
		//super();
		// TODO Auto-generated constructor stub
	}
	
	public void charge() {
		//System.out.println("Charge with German Socket in  Hotel.");
		dbSocket.powerWithTwoRound();
	}
	
}
