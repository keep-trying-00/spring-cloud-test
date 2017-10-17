package demo.com.test;

class Singleton {
	private byte[] a = new byte[6 * 1024 * 1024];
	private static Singleton singleton = new Singleton();

	private Singleton() {
	}

	public static Singleton getInstance() {
		return singleton;
	}
}

class Obj {
	private byte[] a = new byte[3 * 1024 * 1024];
}

public class testXX {
	public static void main(String[] args) throws Exception {
		Singleton.getInstance();
		while (true) {
			new Obj();
		}
	}
}
