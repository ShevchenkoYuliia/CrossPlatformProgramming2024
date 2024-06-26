package consoleTasks;
import java.io.*;
import java.util.*;
import static java.lang.Math.*;
public class FileTreeSetInterpolation extends TreeSetInterpolation{
    public FileTreeSetInterpolation() {
        super();
    }
    public void readFromFile(String fileName) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String s = in.readLine();
        clear();
        while ((s = in.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s);
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            addPoint(new Point2D(x, y));
        }
        in.close();
    }
    public void writeToFile(String fileName) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));
        out.printf("%9s%25s\n", "x", "y");
        for (int i = 0; i < numPoints(); i++) {
            out.println(getPoint(i).getX() + "\t" + getPoint(i).getY());
        }
        out.close();
    }
    public static void main(String[] args){
        FileTreeSetInterpolation fun = new FileTreeSetInterpolation();
        int num;
        double x;
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("Кількість точок: ");
            num = in.nextInt();
        } while (num <= 0);
        for (int i = 0; i < num; i++) {
            x = 1.0 + (5.0 - 1.0) * random();
            fun.addPoint(new Point2D(x, sin(x)));
        }
        System.out.println("Інтерполяція по: " + fun.numPoints() + " точкам");
        System.out.println("Несортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++) {
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
        }
        fun.sort();
        System.out.println("Відсортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++) {
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
        }
        System.out.println("Мінімальне значення х: " + fun.getPoint(0).getX());
        System.out.println("Максимальне значення х: " + fun.getPoint(fun.numPoints()-1).getX());

        System.out.println("Зберігаємо у файл");
        try {
            fun.writeToFile("data.dat");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println("Зчитування з файлу");
        fun.clear();
        try {
            fun.readFromFile("data.dat");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println("Дані з файлу:");
        fun.sort();
        for (int i = 0; i < fun.numPoints(); i++) {
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
        }
        System.out.println("Мінімальне значення х: " + fun.getPoint(0).getX());
        System.out.println("Максимальне значення х: " + fun.getPoint(fun.numPoints()-1).getX());
        x = 0.5*(fun.getPoint(0).getX() + fun.getPoint(fun.numPoints() - 1).getX());
        System.out.println("Значення інтерполяції fun(" + x + ") = " + fun.evalf(x));
        System.out.println("Точне значення sin(" + x + ") = " + sin(x));
        System.out.println("Абсолютна помилка = " + abs(fun.evalf(x) - sin(x)));
        System.out.println("Готуємо дані для підрахунку");
        fun.clear();
        for (x = 1.0; x <= 7.0; x += 0.1) {
            fun.addPoint(new Point2D(x, sin(x)));
        }
        try {
            fun.writeToFile("TblFunc.dat");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}