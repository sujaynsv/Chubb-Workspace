package performance;

public class Worker implements Runnable{
	
	@Override
	public void run() {
		add(1,2);
	}
	static int add(int a, int b) {
		add(1,2);
		return a+b;
	}

}
