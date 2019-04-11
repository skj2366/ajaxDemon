package test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import service.FileService;
import service.impl.FIleServiceImpl;

public class ReadFIle {

	public static void main(String[] args) {
		FileService fs = new FIleServiceImpl();
		String path = "D:\\study\\addr\\input";
		String targetPath = "D:\\study\\addr\\input\\ok";
		File tagerFolder = new File(targetPath);
		File root = new File(path);
		File[] files = root.listFiles();
		for(File file : files) {
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
}
