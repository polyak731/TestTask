package scanners;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Executor;
/**
 * 
 * Simple optimization
 * 
 * @author max
 *
 */
public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		String []paths = {
				"/home/max/javaedu",
				"/home/max/javaedu/FolderScanner",
				"/home/max/javaedu/RemoteSystemsTempFiles",
				"/home/max/javaedu/.metadata",
		};
		Scanner.stop = new CountDownLatch(paths.length);
		List<Scanner> scanners = new ArrayList<>();
		for(int j=0;j<paths.length;j++){
			Scanner scanner = new Scanner(paths[j]);
			for(int i=0;i<paths.length;i++){
				if(paths[i].contains(paths[j]) && i!=j){
					scanner.addToIgnore(paths[i]);
				}
			}
			scanners.add(scanner);
			scanner.start();
		}
		Scanner.stop.await();
		for(Scanner scan: scanners){
			for(int i = 0; i < scanners.size() ; i++){
				for(String path : scan.getIgnoreList()){
					if(path.compareTo(scanners.get(i).getPath()) == 0){
						scan.addToCounter(scanners.get(i).getCounter());
					}
				}
			}
		}
		for(Scanner scan: scanners){
			scan.print();
		}
		System.out.println("++++++++++++++++++++++");
		for(String path: paths){
			Scanner scanner = new Scanner(path);
			scanner.start();
		}
	}
}
