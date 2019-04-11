package test;

class Test implements Runnable{  // 쓰레드 상속 받는 대신 러너블을 구현 받으면(다중 상속 불가) 
	public void run() {			// 상속을 받을수 있기 때문에 Runnable를 사용 한다.
		System.out.println("실행!!");
	}
}

public class RunnableTest {
	public static void main(String[] args) {
		Runnable r = new Test();
		Thread t = new Thread(r);
		t.start();
	}
}