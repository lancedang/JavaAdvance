package dang.JavaAdvance.job.polymorp.special;

public class FieldSon extends FieldFather {
	public String field = "Son same name String field.";

	public String getField() {
		return field;
	}

	public String getFatherField() {
		return super.getField();
	}
}
