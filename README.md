# otcmarketsScrapper


The information extracted by this code is as follows:

INSIDER DISCLOSURE: extracts the 'SEC Transactions Last 2 Years' table completely by going to each successive 'next' page

PROFILE: extracs the 'business description' by also clicking 'more' on the page and then extracting the whole description

SHORT SALES: extracts the whole ajax loaded page

QUOTE: extracts the whole page

NEWS: extracts the 'News and Analysis' table by clicking each entry: saving the ajax loaded content otherwise if it's an external link, the link will be saved into a file. Then it goes to all the 'next' pages in the table. (Saves each news with ********************************** seperated lines.)
    
RUN::

javac -cp "path/with/selenium/jars/*:." Scraper.java

java -cp "path/with/selenium/jars/*:." Scraper otc seeds.txt


NOTE: otc folder will be created on its own in the current directory. Make sure the seeds.txt path is correct.

The code produces one folder for each stock with subfolders for each category insider, news etc.
It also produces two other files called: stcokstodoagain.txt (These were the stocks that faced 
                                        problems while fetching so have to refetch them)
                                        and stcoksdone.txt (names of successfully fetched stocks)



