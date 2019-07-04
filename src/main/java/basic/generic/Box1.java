package basic.generic;

public class Box1 {

	private String data;

	public void setData(String data ) {
		this.data = data;
	}
	
	public String getData() {
		return this.data;
	}

	public void show() {
		System.out.println("This is Box1, data is " + data);
    }
	
		public static void main(String[] args) {
		Box1 b = new Box1();
		b.setData("Hello");
		b.show();
	}
}

