package basic.generic;

public class Box2 {
	private Integer data;

	public void setData(Integer data ) {
		this.data = data;
	}
	
	public Integer getData() {
		return this.data;
	}

	public void show() {
		System.out.println("This is Box2, data is " + data);
    }
	
	public static void main(String[] args) {
		Box2 b = new Box2();
		b.setData(new Integer(5));
		b.show();
	}
}

