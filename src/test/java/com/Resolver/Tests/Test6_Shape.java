package com.Resolver.Tests;

public class Test6_Shape {

	int width;
	int height;

	//Constructor
	public Test6_Shape(int width, int height) {

		this.width = width;
		this.height = height;
	}

	public static void main(String[] args) {

		Triangle triangle_Obj = new Triangle(20, 30, 10);
		Rectangle rectangle_Obj = new Rectangle(20, 20, 12);

		int triangle = triangle_Obj.area();
		int rectangle = rectangle_Obj.area();

		System.out.println("Area of the Triangle: " + triangle);
		System.out.println("Area of the Rectangle: " + rectangle);
	}
}

class Triangle extends Test6_Shape {

	int base;

	public Triangle(int width, int height, int base) {
		super(width, height);
		this.base = base;
	}

	public int area() {

		int triangle_Area = 0;
		triangle_Area = (height * base) / 2;

		return triangle_Area;
	}

}

class Rectangle extends Test6_Shape {

	int length;

	public Rectangle(int width, int height, int length) {
		super(width, height);
		this.length = length;
	}

	public int area() {
		int rectangle_Area = 0;
		rectangle_Area = width * length;

		return rectangle_Area;
	}
}
