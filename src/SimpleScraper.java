import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class SimpleScraper {

    public static void main(String[] args) {
        // URLs to scrape
        String page1 = "https://www.zigzag.am/am/tv-audio-video.html?cat=86&product_list_mode=list";
        String page2 = "https://www.zigzag.am/am/tv-audio-video.html?cat=86&p=2&product_list_mode=list";

        System.out.println("Scraping data and saving to CSV file...");

        // Output CSV file path
        String outputFilePath = "output.csv";

        try {
            // Create FileWriter to write to the CSV file
            FileWriter writer = new FileWriter(outputFilePath);

            // Add CSV header
            writer.write("Name,Description,Price,Product URL\n");

            // Process both pages
            scrapePage(page1, writer);
            scrapePage(page2, writer);

            // Close FileWriter
            writer.close();

            System.out.println("Scraping completed. Data saved to " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Error saving data to CSV file.");
            e.printStackTrace();
        }
    }

    // Scraper for a single page
    public static void scrapePage(String url, FileWriter writer) {
        try {
            // Connect to the URL and parse the HTML
            Document document = Jsoup.connect(url).get();

            // Select all product containers
            Elements products = document.select("[data-container='product-list']");

            // Iterate over the products and scrape the data
            for (Element product : products) {
                // Extract details using respective selectors
                String name = product.select(".info_block > a:first-of-type").text();
                String price = product.select("[data-price-type='finalPrice']").text().replaceAll("[^0-9]", "");
                String description = product.select("ul").text();
                String productUrl = product.select(".info_block > a:first-of-type").attr("abs:href");

                // Write the data to the CSV file
                writer.write(name + "," + description + "," + price + "," + productUrl + "\n");
            }
        } catch (IOException e) {
            System.out.println("Failed to scrape data from: " + url);
        }
    }
}