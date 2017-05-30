package dang.advance.job.thread;

public class StaticProxy {
	public static void main(String[] args) {
		You you = new You();
		WeddingCompany company = new WeddingCompany(you);
		company.marry();
	}
}

/**
 * 代理角色
 * 
 * @author Dangdang
 *
 */
class WeddingCompany implements Marry {
	private You you;

	public WeddingCompany(You you) {
		// TODO Auto-generated constructor stub
		this.you = you;
	}

	private void before() {
		System.out.println("before marry.");
	}

	private void after() {
		System.out.println("after marry");
	}

	@Override
	public void marry() {
		// TODO Auto-generated method stub
		before();
		you.marry();
		after();
	}

}

/**
 * 真实角色
 * 
 * @author Dangdang
 *
 */
class You implements Marry {

	@Override
	public void marry() {
		// TODO Auto-generated method stub
		System.out.println("you marray.");
	}

}

/**
 * 共同使用的接口
 * 
 * @author Dangdang
 *
 */
interface Marry {
	void marry();
}
