package dang.advance.job.callback;

public class Area implements Comparable<Area> {
	private int x;
	private int y;

	public Area(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "Location is x = " + x + ", y = " + y;
	}

	public int compareTo(Area other) {
		int area1 = x * y;
		int area2 = other.x * other.y;

		if (area1 == area2) {
			return 0;
		} else if (area1 > area2) {
			return 1;
		} else {
			return -1;
		}
	}
}
