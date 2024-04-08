package task4;

import org.w3c.dom.Document;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        String filePath = "Popular_Baby_Names_NY.xml";
        String outputFilePath = "Popular_Baby_Names2.xml";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of ethnicity:");
        String ethnicity = scanner.nextLine();
        System.out.println("Enter number of names:");
        int num = scanner.nextInt();
        Document doc = XMLParser.parseXML(filePath);
        List<BabyInfo> popularNames = DataProcessor.getPopularNames(doc, ethnicity, num);
        Collections.sort(popularNames, Comparator.comparingInt(BabyInfo::getRating));
        XMLWriter.writeXML(popularNames, outputFilePath);
    }
}
