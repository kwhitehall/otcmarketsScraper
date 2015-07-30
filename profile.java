
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class profile {

	public static void extractprofile(String symbol,String path,WebDriver driver) throws InterruptedException, IOException{
		
		
		
	    driver.get("http://www.otcmarkets.com/stock/"+symbol+"/profile");

	    // Wait for the page to load, timeout after 3 seconds
	    Thread.sleep(3000);
	   
	    WebElement businessContent=null;
	    try{
	    	 businessContent = driver.findElement(By.id("businessContent"));
	    }
	   catch(Exception e){
		  return ;
	   }
	  
	    try{ 
	    	
	    	 WebElement  atag = businessContent.findElement(By.tagName("a"));
	     if(atag.getAttribute("href").equals("javascript:void(null)")){  
		        atag.click(); 
		        Thread.sleep(3);
		      }
	     
	    }
	    catch(Exception e){
	    	
	    }
	      
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
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
    	
    	
	    
		
	
	
   
}
	
}
