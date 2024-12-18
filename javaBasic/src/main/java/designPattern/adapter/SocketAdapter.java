package designPattern.adapter;

import designPattern.adapter.interfacer.DBSocketInterface;
import designPattern.adapter.interfacer.GBSocketInterface;

/**
 * 充电器转化器, 也称适配器,类似 3转2 插座,
 * 适配器, 实现德国 插座 接口, 添加 国产 插座 
 * @author Dangdang
 *
 */
public class SocketAdapter implements DBSocketInterface {

	private GBSocketInterface gbSocket;
	
	public GBSocketInterface getGbSocket() {
		return gbSocket;
	}

	public void setGbSocket(GBSocketInterface gbSocket) {
		this.gbSocket = gbSocket;
	}

	public SocketAdapter(GBSocketInterface gbSocket) {
		//super();
		this.gbSocket = gbSocket;
	}

	public void powerWithTwoRound() {
		// TODO Auto-generated method stub
		gbSocket.powerWithThreeFlat();
	}

}
