package scanners;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Main2 {
	public static void main(String[] args) throws InterruptedException {
		String []paths = {
				"/home/max/javaedu/.metadata/.mylyn",
				"/home/max/javaedu",
				"/home/max/javaedu/FolderScanner",
				"/home/max/javaedu/RemoteSystemsTempFiles",
				"/home/max/javaedu/.metadata",
		};
		Scanner.stop = new CountDownLatch(paths.length);
		Scanner.start = new CountDownLatch(paths.length);
		List<Scanner> scanners = new ArrayList<>();
		for(int j=0;j<paths.length;j++){
			Scanner scanner = new Scanner(paths[j]);
			scanners.add(scanner);
			scanner.start();
		}
		for(int i =0 ; i< scanners.size() ;i++){
			for(int j = 0 ;j< scanners.size() ;j++){
				if(isParentDirectory(scanners.get(i).getPath(),scanners.get(j).getPath()) && i!=j){
					scanners.get(i).addToExchange(scanners.get(j).getExchanger());
					scanners.get(i).addToIgnore(scanners.get(j).getPath());
				}
			}
			Scanner.start.countDown();
		}
		long tic = System.currentTimeMillis();
		Scanner.stop.await();
		long toc = System.currentTimeMillis();
		System.out.println((toc-tic));
		for(Scanner scan: scanners){
			scan.print();
		}
	}
	
	private static boolean isParentDirectory(String parent, String child){
		return new File(child).getParent().toString().compareTo(parent)==0;
	}
}
