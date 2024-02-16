package academy.testComponents;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalTime;

import org.bouncycastle.crypto.RuntimeCryptoException;

import net.bytebuddy.asm.Advice.Local;

public class DockerManager {
	private static final String ROOT = System.getProperty("user.dir");
	private static final String LOG_FILE = "logs.txt";
	private static final Path PATH = Path.of(ROOT + "\\" + LOG_FILE);
	
	private static final String STARTED_MSG = " Node has been added";
	private static final String STOPPED_MSG = "selenium-hub exited with code 0";
	
	public DockerManager() {
		deletelogfile();
	}
	

	private void deletelogfile() {
		if(Files.exists(PATH)) {
			try {
				Files.delete(PATH);
				System.out.println("File deleted");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	
	public void actionDocker(String action) {
		Runtime runtime = Runtime.getRuntime();
		
		switch (action) {
		case "START":
			try {
				runtime.exec("cmd /c start startDocker.bat");
				System.out.println("Grid is up");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			waitTillLogFileCreated();
			try {
				waitTill(STARTED_MSG);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			break;
		case "STOP":
			try {
				runtime.exec("cmd /c start stopDocker.bat");
				System.out.println("Grid is down");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				waitTill(STOPPED_MSG);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			break;
		default:
			break;
		}
	}

	private void waitTill(String signal) throws IOException {
		LocalTime waitTill = LocalTime.now().plus(Duration.ofSeconds(40));
		String fileContent = "";
		Boolean started = false;
		
		while(LocalTime.now().compareTo(waitTill)<0) {
			fileContent = Files.readString(PATH);
			if(fileContent.contains(signal)) {
				started = true;
				break;
			}
		}
		
		if(!started) {
			throw new RuntimeException("Grid not started");
		}
		
	}

	private void waitTillLogFileCreated() {
		Boolean flag = false;
		while(!flag) {
			flag = Files.exists(PATH);			
		}
		System.out.println("Log file is created");
	}
	
	
	
	
	
}
