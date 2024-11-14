package advance.job.link;

public class LinkNode {
	int value;
	LinkNode next;

	public LinkNode(int value) {
		// TODO Auto-generated constructor stub
		this.value = value;
		next = null;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the next
	 */
	public LinkNode getNext() {
		return next;
	}

	/**
	 * @param next
	 *            the next to set
	 */
	public void setNext(LinkNode next) {
		this.next = next;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " " + value + " ";
	}

}
