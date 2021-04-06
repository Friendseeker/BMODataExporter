package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.opencsv.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
// Thank you Maven. Have to manually press Ctrl Shift O to reset

public class App {
    public static void main(String[] args) {
        try {
            // Scrape transactions from downloaded BMO online banking page and convert for CSV file
            // Useful when filling out tax forms

            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter Path to HTML file");

            String input = scanner.nextLine(); // Set the path
            File in = new File(input); // No escape issue, which I appreciate
            Document doc = Jsoup.parse(in, null);

            // Stage 1 Test, if this shows incorrect result then need fixes.
            // System.out.printf("Title: %s\n", doc.title());

            // Extract the table from HTML
            Element table = doc.select(".bmoTable").get(0);
            Elements rows = table.select("tr");

            // Open the CSV file
            FileWriter w = new FileWriter("yourTransactionHistory.csv");
            CSVWriter writer = new CSVWriter(w);
            // Feed in 1st row (headings)
            String[] entries = ("Date#Transaction Code#Detail#Outgoing Amount#Incoming Amount" +
                    "#Outstanding Balance").split("#");
            // Spilt is a powerful function
            writer.writeNext(entries); // Write 1st row

            // Write the remaining rows
            for (int i = 2; i < rows.size(); i++) {
                // first row removed as it has different size and contains non-useful info.
                Element row = rows.get(i);
                Elements cols = row.select("td");
                // System.out.println(customEachText(cols));
                // Nor using .eachText as it skips empty strings.
                writer.writeNext(customEachText(cols));
            }

            // Documentation for each column from scraped data
            // 0 is date
            // 1 is the two letter Transaction code
            // 2 full name
            // 3 outgoing amount
            // 4 incoming amount
            // 5 outstanding balance

            writer.close();

            System.out.println("File extracted to application folder!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // .eachText but do not skip empty strings.
    public static String[] customEachText(Elements s) {
        ArrayList<String> l = new ArrayList<>();
        for (Element e: s) {
            l.add(e.text());
        }
        return l.toArray(new String[0]);
    }
}
