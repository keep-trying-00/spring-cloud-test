package demo.com.test.java8;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		File[] hiddenFiles = new File("C:\\").listFiles(File::isHidden);
		for (File file : hiddenFiles) {
			System.out.println(file.getAbsolutePath()+"--"+file.getName());
		}
		
		List<String> test = new ArrayList<String>();
		test.add("11");
		test.add("123");
		test.add("523");
		test.add("72323");
		Collections.sort(test);
		
		for (String string : test) {
			System.out.println(string);
		}

	}

}
