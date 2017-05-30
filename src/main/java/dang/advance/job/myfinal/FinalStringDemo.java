package dang.advance.job.myfinal;

class MyString {
	private String content;

	public MyString(String content) {
		// TODO Auto-generated constructor stub
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	void printInfo() {
		System.out.println("content = " + content);
	}

}

public class FinalStringDemo {
	public static void main(String[] args) {
		String param = new String("abc");
		MyString myString = new MyString(param);
		myString.printInfo();

		String content = myString.getContent();
		
		content.toUpperCase();
		
		myString.printInfo();
	}
}
