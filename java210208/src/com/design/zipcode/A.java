package com.design.zipcode;

import java.util.ArrayList;

public class A {

	public static void main(String[] args) {
		ArrayList f1 = new ArrayList();
		f1.add("수박");
		f1.add("딸기");
		f1.add("키위");
		String my = (String)f1.get(0);
		Object obj = f1.get(2);
		System.out.println(my);
		System.out.println(obj);
		System.out.println("==========================================");
		ArrayList<String> f2 = new ArrayList<String>();
		f2.add("수박");
		f2.add("딸기");
		f2.add("키위");
		f2.add(1, "바나나");
		for(String str:f2) {
			System.out.println(str);
		}
		String my2 = f2.get(0);
		Object obj2 = f2.get(2);
		System.out.println(my2);
		System.out.println(obj2);
	}

}
