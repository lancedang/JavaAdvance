package advance.job.myfinal;

class AppleTag {
	private String name;
	private String age;

	public String getName() {
		return name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AppleTag(String name, String age) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.age = age;
	}

}

class Apple {

	// 本意是一旦 初始化
	// appleTag后即不能对其进行任何修改（其状态），故设计成final类型（但单纯final类型不能完全保证状态不修改，final仅保证引用地址不变）
	private final AppleTag appleTag;

	public Apple(AppleTag appleTag) {
		// TODO Auto-generated constructor stub
		this.appleTag = appleTag;
	}

	public AppleTag getAppleTag() {
		return appleTag;
	}

	public void printAppleInfo() {
		System.out.println("name = " + appleTag.getName() + "; age = " + appleTag.getAge());
	}
}

class Apple2 {
	private final AppleTag appleTag;

	public Apple2(AppleTag appleTag) {
		// the constructor is interesting in that it creates a new instance
		// rather than transferring the param's reference directly, which
		// results the separation of Apple2's variable and outside-instance
		this.appleTag = new AppleTag(appleTag.getName(), appleTag.getAge());
	}

	public AppleTag getAppleTag() {
		// return a new instance which has same state(attribute) with the class
		// variable so the outside can only read the class variable but can do
		// nothing to change it
		return new AppleTag(appleTag.getName(), appleTag.getAge());
	}

	public void printAppleInfo() {
		System.out.println("name = " + appleTag.getName() + "; " + appleTag.getAge());
	}

}

class AppleDemo {

	public static void main(String[] args) {
		AppleTag appleTag = new AppleTag("tag1", "1");

		Apple apple = new Apple(appleTag); // Apple实例需要AppleTag对象实例化，且appletag对象不可变
		// apple.printAppleInfo();
		apple.printAppleInfo();		
		
		appleTag = new AppleTag("ddddd", "dddd");
		//appleTag.setName("dasf");
		apple.printAppleInfo();

		AppleTag appleTag2 = apple.getAppleTag();

		appleTag2.setName(",,,,");
		apple.printAppleInfo();

	}
}