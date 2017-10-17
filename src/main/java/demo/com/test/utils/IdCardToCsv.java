package demo.com.test.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IdCardToCsv {
	public static void main(String[] args) {
		File fp = new File("D:\\idcardNums.csv");
		FileWriter pfp = null;
		try {
			pfp = new FileWriter(fp, true);
			for (int i = 1; i <= 1000; i++) {
				IdCardGenerator g = new IdCardGenerator();
				pfp.write(g.generate() + "\r\n");
			}
			pfp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
