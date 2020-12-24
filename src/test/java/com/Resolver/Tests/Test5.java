package com.Resolver.Tests;

public class Test5 {

	public static void main(String[] args) {
		print_Function(100);
	}

	public static void print_Function(int nums) {

		// using conditional statements:
//		for (int i = 1; i <= nums; i++) {  // For loop
//			if (i % 3 == 0 && i % 5 == 0) {
//				System.out.println("Resolver");
//				continue;
//			} else if (i % 3 == 0) {
//				System.out.println("MThree");
//				continue;
//			} else if (i % 5 == 0) {
//				System.out.println("MFive");
//				continue;
//			} else {
//				System.out.println(i);
//				continue;
//			}
//		}

		// using Ternary operators:
		int j = 1;
		while (j <= nums) { // While loop
			String result = ((j % 3 == 0) && (j % 5 == 0)) ? "Resolver"
					: (j % 3 == 0) ? "MThree" : (j % 5 == 0) ? "MFive" : j + "";
			System.out.println(result);
			j++;
		}

	}

}
