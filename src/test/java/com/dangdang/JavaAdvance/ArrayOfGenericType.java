package com.dangdang.JavaAdvance;

import java.util.Arrays;

public class ArrayOfGenericType<T> {
	T[] ts;
	@SuppressWarnings("unchecked")
	public ArrayOfGenericType(int size){
		ts = (T[]) new Object[size];
		Object[] objects = {};
		Arrays.sort(objects);
		//ts = new T[size];//Cannot create a generic array of T
	}
}
