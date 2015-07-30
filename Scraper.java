
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Scraper {

	public static void extractall(String symbol, String path, WebDriver driver)
			throws InterruptedException, IOException {
		// extract pages into folders according to symbols

		// insider
		new File(path + "/insider-transaction").mkdir();
		insider.extractinsider(symbol, path + "/insider-transaction", driver);
		// profile
		new File(path + "/pofile").mkdir();
		profile.extractprofile(symbol, path + "/pofile", driver);
		// short-sales
		new File(path + "/short-sales").mkdir();
		shortsales.extractsales(symbol, path + "/short-sales", driver);
		// news
		new File(path + "/news").mkdir();
		news.extractnews(symbol, path + "/news", driver);
		// rest of the links: do it in a different crawl

	}

	
	
	
	
	public static void main(String[] args) throws InterruptedException,
			IOException {

		
		

		WebDriver driver = new FirefoxDriver();

		ArrayList<String> list = new ArrayList<String>();
		String path = args[0];
		
		//create the base directory if does not exist
		File basedir = new File(path);
		if (!basedir.exists()) {
			basedir.mkdir();
		}
		
		//list of stocks where problems occured while fetching
		File doagain = new File(path + "/stcokstodoagain.txt");
		if (!doagain.exists()) {
			doagain.createNewFile();
		}
		FileWriter fw = new FileWriter(doagain);
		
		//completed stocks list
		File done = new File(path +"/stcoksdone.txt");
		if (!done.exists()) {
			done.createNewFile();
		}
		FileWriter fw1 = new FileWriter(done);
		
		//to - do initial list of stocks to fetch
		FileReader fileReader = new FileReader(
				args[1]);

		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = "";

		while ((line = bufferedReader.readLine()) != null) {

			list.add(line);

		}

		bufferedReader.close();
		fileReader.close();

		for (String symbol : list) {
			try {
			System.out.println(symbol);
			if (new File(path+"/" + symbol).exists()) {
				new File(path+"/" + symbol).delete();
			}
			new File(path+"/" + symbol).mkdir();

			
				extractall(symbol, path +"/"+ symbol, driver);
				fw1.write(symbol);
				fw1.write(String.format("%n"));
				fw1.flush();
			} catch (Exception e) {
				fw.write(symbol);
				fw.write(String.format("%n"));
				fw.flush();
				// delete the directory
				new File(path+ "/"+ symbol).delete();
			}
		}

		fw.close();
		fw1.close();
		driver.quit();

	}

}
