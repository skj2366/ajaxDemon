package listener;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.io.FileUtils;

import service.FileService;
import service.impl.FIleServiceImpl;

public class DaemonListener implements ServletContextListener, Runnable {

 
	private Thread t;
	private static final long BREAK_TIME = 1000 * 60;
	private static FileService fs = new FIleServiceImpl();

	public DaemonListener() {
		System.out.println("내가 제일 처음!");

	}

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("난 생성후 바로 실행 되지");
		if (t == null) {
			t = new Thread(this);
			t.setDaemon(true);
		}
		t.start();
	}

	private void readAddrFiles() {
		String path = "D:\\study\\addr\\input";
		String targetPath = "D:\\study\\addr\\input\\ok";
		File tagerFolder = new File(targetPath);
		File root = new File(path);
		File[] files = root.listFiles();
		for(File file : files) {
			System.out.println("총 파일 갯수 : " + (files.length-1));
			if(!file.isDirectory()){
				String name = file.getName();
				System.out.println(name + "파일 시작!");
				Map<String,String> rMap = fs.insertAddrFromFile(file);
				int targetCnt = Integer.parseInt(rMap.get("targetCnt"));
				int totalCnt = Integer.parseInt(rMap.get("totalCnt"));
				if(targetCnt==totalCnt) {
					try {
						FileUtils.moveFileToDirectory(file, tagerFolder, false);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("실행 시간: " + rMap.get("executTime"));
				System.out.println(name + "파일 종료!");
				return;				
			}
		}
	}
	@Override
	public void run() {
		  while (Thread.currentThread() == t) {
			try {
				Thread.sleep(BREAK_TIME);
				readAddrFiles();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  }
	}
	
}
