package advance.job.object;

public class ObjectDemo {
	public static void main(String[] args) {
		ObjectUtil o1 = new ObjectUtil("test");
		ObjectUtil o2 = o1;
		System.out.println(o1.toString());
		System.out.println(o2.toString());
		System.out.println("o1 hashcode " + o1.hashCode());
		System.out.println("o2 hashcode " + o2.hashCode());
		System.out.println("o1 name hashcode " + o1.name.hashCode());
		System.out.println("o2 name hashcode " + o2.name.hashCode());
	}
}
