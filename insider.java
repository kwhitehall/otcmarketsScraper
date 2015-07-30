
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;





public class insider  {
	
	
	public static void extractinsider(String symbol,String path,WebDriver driver ) throws InterruptedException, IOException{
		 

        
        driver.get("http://www.otcmarkets.com/stock/"+symbol+"/insider-transactions");
        
        Thread.sleep(3000);
        
        boolean next=true;
        int count=-1;
      
        while(next){
        	next=false;
        count++;

        	
            
           //grab the whole page and the link
       
       File file=new File(path+"/"+driver.getCurrentUrl().replaceAll("/", "_")+'_'+count);
        if(!file.exists()) {
            file.createNewFile();
        }
        FileWriter    fw = new FileWriter(file);
        fw.write(driver.getPageSource());
        fw.flush();
        fw.close();
        
        
        
        
        
        
        	System.out.println(driver.getCurrentUrl()+'/'+count);
        	//System.out.println(driver.getPageSource());
        	
        	
        //check for the next button, if found then click set the flag to true
        	WebElement pagination=null;
        try{
         pagination=driver.findElement(By.id("shortInterestTable")).findElement(By.className("pageList"));
        }
        catch(Exception e){
        	continue;
        }
        List<WebElement> paginationli=pagination.findElements(By.tagName("li"));
      
        
        for(WebElement ele:paginationli){
        	
        	
        	try{
        	ele.findElement(By.tagName("a")).getText();
        	}
        	catch(Exception e){
        		continue;
        	}
        	
        	if(ele.findElement(By.tagName("a")).getText().equals("next >")){
        		//System.out.println("HI");
        		WebElement nextpage= ele.findElement(By.tagName("a"));
        		nextpage.click();
        		next=true;
        		Thread.sleep(3000);
        		
        	}
        	
        }
        
              } 
        


        
	}
	
	
	
	
    public static void main(String[] args) throws InterruptedException, IOException {
    	
    	
    
    		
    	
    	
       
    }
}