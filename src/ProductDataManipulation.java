import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ProductDataManipulation {
    public static void main(String[] args) throws IOException {
        Queue queue = new Queue();

        Path outputPath = Paths.get("src", "output.csv");

        loadCsvDataToQueue(outputPath.toString(), queue);

        System.out.println("\nQueue after loading from CSV:");
        queue.printAll();

        System.out.println("\nSearch for 'Samsung QE65QN90DAUXRU':");
        queue.search("Samsung QE65QN90DAUXRU");

        System.out.println("\nRemoving an element:");
        queue.remove();

        System.out.println("\nQueue after removal:");
        queue.printAll();

        System.out.println("\nSorting the queue...");
        queue = Queue.quickSort(queue);

        System.out.println("\nQueue after sorting:");
        queue.printAll();
    }

    public static void loadCsvDataToQueue(String filePath, Queue queue) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        boolean skipHeader = true;

        while ((line = reader.readLine()) != null) {
            if (skipHeader) {
                skipHeader = false;
                continue;
            }

            String productName = line.split(",")[0];
            queue.add(productName);
        }
        System.out.println("CSV data loaded into the queue.");
        queue.printAll();
    }
}