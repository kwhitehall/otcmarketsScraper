
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class quote {
	public static void extractquotes(String symbol,String path,WebDriver driver) throws InterruptedException, IOException{
	
	      

	      driver.get("http://www.otcmarkets.com/stock/"+symbol+"/quote");

	      // Wait for the page to load, timeout after 3 seconds
	      Thread.sleep(3000);

	      String innerHtml = driver.findElement(By.tagName("body")).getAttribute("innerHTML");

	      File file=new File(path+"/"+driver.getCurrentUrl().replaceAll("/", "_"));
	        if(!file.exists()) {
	            file.createNewFile();
	        }
	        FileWriter    fw = new FileWriter(file);
	        fw.write(innerHtml);
	        fw.flush();
	        fw.close();
	}
}
