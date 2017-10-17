package demo.com.test.java8;

import java.util.Comparator;

import demo.com.test.vo.Apple;

public class AppleTest {
	public static void main(String[] args) {
		Comparator<Apple> byWeight =
				(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
				
		Apple a = new Apple();
		a.setWeight("20");
		
		Apple b = new Apple();
		b.setWeight("25");
		
		System.out.println(byWeight.compare(a, b));
		
		Runnable r1 = () -> System.out.println("Hello World 1");
		
		r1.run();
		
	}
		
}
