
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;





public class financial  {
	
	
	public static void extractfinancials(String symbol,String path,WebDriver driver ) throws InterruptedException, IOException{
		 

        
       driver.get("http://www.otcmarkets.com/stock/"+symbol+"/financials");

        // Wait for the page to load, timeout after 3 seconds
        Thread.sleep(3000);
       
        WebElement businessContent=null;
        try{
            //businessContent = driver.findElement(By.id("incomeStatementTable"));
            businessContent = driver.findElement(By.id("financial-reports-div"));
            List<WebElement> subtags = businessContent.findElements(By.xpath("//div[@id='report-types-div']/ul/li/a"));
            List<WebElement> viewSubtags = businessContent.findElements(By.xpath("//div[@class='filterContent']/ul/li/a"));
             
            for(int i=0;i<subtags.size();i++){
                WebElement atag=subtags.get(i);//.findElements(By.tagName("a"));
                atag.click();
                for(int j=0;j<viewSubtags.size();j++){
                    WebElement viewtag=viewSubtags.get(j);//.findElements(By.tagName("a"));
                    viewtag.click();
                    Thread.sleep(3000);
                    //grab the whole page and the link
       
                    File file=new File(path+"/"+driver.getCurrentUrl().replaceAll("/", "_")+atag.getAttribute("id")+'_'+viewtag.getAttribute("id"));
                    if(!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter    fw = new FileWriter(file);
                    fw.write(driver.getPageSource());
                    fw.flush();
                    fw.close();

                }//end for j

            }//endfor i
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