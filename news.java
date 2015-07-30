
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class news {
public static void extractnews(String symbol,String path,WebDriver driver)throws InterruptedException, IOException{
	 	      
	      String newsTextFileName = symbol+"_news.txt";
	      File outputNews = new File(path+"/"+newsTextFileName);
	      //System.out.println(newsTextFileName);
	      if(!outputNews.exists()) {
	    	  outputNews.createNewFile();
	        }
	      FileWriter newsText = new FileWriter(outputNews);
	      String newsUrlsFileName = symbol+"_news_urls.txt";
	      File outputUrls = new File(path+"/"+newsUrlsFileName);
	      if(!outputUrls.exists()) {
	    	  outputUrls.createNewFile();
	        }
	      FileWriter newsUrls = new FileWriter(outputUrls);
	      
	      driver.get("http://www.otcmarkets.com/stock/"+symbol+"/news");

	      // Wait for the page to load, timeout after 3 seconds
	      Thread.sleep(3000);
	      //get the raw page
	        
	        boolean next=true;
	      
	        while(next){
	        	next=false;
	        	WebElement table=null;
	        	List<WebElement> tbodies=null;
	        			
	        try{

	        	 table = driver.findElement(By.id("outsideNewsTable"));
	            
	             tbodies= table.findElements( By.tagName("tbody"));
	        }
	        catch(Exception e){
	        	outputUrls.delete();
	        	outputNews.delete();
	        	
	        	newsText.close();
	        	newsUrls.close();
	        	
	        	
	        	return;
	        }
	            
	        for(int i=0;i<tbodies.size();i++){
	        	
	        	try{
	        		tbodies.get(i).findElement(By.tagName("a"));
	        	}
	        	catch(Exception e){
	        		continue;
	        	}
	        	
	        	List<WebElement> trs= tbodies.get(i).findElements( By.tagName("tr"));
	        	
	        	
	        	for(int j=0;j<trs.size();j++){
	        		
	        		
	            	WebElement atag=trs.get(j).findElement(By.tagName("a"));
	            	if(atag.getAttribute("href").equals("javascript:void(null);")){
	            
	            	
	            	atag.click();
	            	
	            	 Thread.sleep(3000);
	            	
	            	// System.out.println(driver.getPageSource());
	            	WebElement newscontainer = driver.findElement(By.className("newsContainer"));
	            	//System.out.println(newscontainer.getText());
	            	// print all the output to a file
	            	newsText.write(String.format("%n"));
	            	newsText.write("*******************************************************");
	                newsText.write(String.format("%n"));
	                newsText.write(String.format("%n"));
	                newsText.write(newscontainer.getText());
	                newsText.flush();
	            	WebElement newsback = driver.findElement(By.className("newsBack"));
	            	WebElement back =newsback.findElement(By.tagName("a"));
	            	back.click();
	            	 Thread.sleep(3000);    
	            	 
	            	 //refreshing the holders as the page was messed with
	            	 
	            	  table = driver.findElement(By.id("outsideNewsTable"));
	                 
	                  tbodies= table.findElements( By.tagName("tbody"));
	                  
	                  trs= tbodies.get(i).findElements( By.tagName("tr"));
	                  
	                  //refresh ends
	            	}
	            	else{
	            		
	            			newsUrls.write(atag.getAttribute("href"));
	            			 newsUrls.write(String.format("%n"));
	            			newsUrls.flush();
	            	}
	            	
	        	}
	        		
	        	
	        	
	       }
	        
	        //check for the next button, if found then click set the flag to true
	        List<WebElement> paginationli=null;
	        try{
	        WebElement pagination=driver.findElement(By.id("outsideNewsTable")).findElement(By.className("pageList"));
	         paginationli=pagination.findElements(By.tagName("li"));
	        }
	        catch(Exception e){
	        	newsText.close();
	        	newsUrls.close();
	        	return;	
	        }
	        
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
	      
	      newsText.close();
	      newsUrls.close();
	      
	    }



public static void main(String[] args) throws InterruptedException, IOException {
	
	
    
	



}
	    }

