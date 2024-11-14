package advance.job.polymorp.special;

public class StaticSon extends StaticFather {
	public static void staticShow() {
		System.out.println("Static Son method.");
	}

	public void nonStaticShow() {
		System.out.println("Non-static Son method. ");
	}
}
