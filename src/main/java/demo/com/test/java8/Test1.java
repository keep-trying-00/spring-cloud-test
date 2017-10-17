package demo.com.test.java8;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
	static String  base = "string";
	public static void main(String[] args) {
		    String str2 = new String("str")+new String("01");
	        str2.intern();
	        String str1 = "str01";
	       // System.out.println(str2==str1);//#7
		    /*String str1 = "str01";
	        String str2 = new String("str")+new String("01");
	        str2.intern();
	        System.out.println(str2 == str1);//#8
	        System.out.println(str1.hashCode());
	        System.out.println(str2);*/
	        
	        String s = new String("abc");
	        String s1 = "abc";
	        String s2 = new String("abc");
	        
	        System.out.println(s == s1.intern());
	        System.out.println(s == s2.intern());
	        System.out.println(s1 == s2.intern());
	}

}
