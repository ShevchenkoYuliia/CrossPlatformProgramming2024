package consoleTasks;
import java.util.*;
public class ListInterpolation extends Interpolator {
    private List<Point2D> data = null;
    public ListInterpolation(List<Point2D> data) {
        this.data = data;
    }
    public ListInterpolation() {
        data = new ArrayList<Point2D>();
    }
    public ListInterpolation(Point2D[] data) {
        this();
        for (Point2D pt : data)
            this.data.add(pt);
    }
    public void clear() {
        data.clear();
    }
    public int numPoints() {
        return data.size();
    }
    public void addPoint(Point2D pt) {
        data.add(pt);
    }
    public Point2D getPoint(int i) {
        return data.get(i);
    }
    public void setPoint(int i, Point2D pt) {
        data.set(i, pt);
    }
    public void removeLastPoint() {
        data.remove(data.size()-1);
    }
    public void sort() {
        Collections.sort(data);
    }
    public static void main(String[] args) {
        ListInterpolation fun = new ListInterpolation();
        int num;
        double x;
        java.util.Scanner in = new java.util.Scanner(System.in);
        do {
            System.out.print("Кількість точок: ");
            num = in.nextInt();
        } while (num <= 0);
        for (int i = 0; i < num; i++)
        {
            x = 1.0 + (5.0 - 1.0)*Math.random();
            fun.addPoint(new Point2D(x, Math.sin(x)));
        }
        System.out.println("Інтерполяція по: " + fun.numPoints() + " точкам");
        System.out.println("Несортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i+1) + ": " + fun.getPoint(i));
        fun.sort();
        System.out.println("Відсортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i+1) + ": " + fun.getPoint(i));
        System.out.println("Мінімальне значення x: " + fun.getPoint(0).getX());
        System.out.println("Максимальне значення x: " +
                fun.getPoint(fun.numPoints()-1).getX());
        x = 0.5*(fun.getPoint(0).getX() +
                fun.getPoint(fun.numPoints()-1).getX());
        System.out.println("Значення інтерполяції fun(" + x + ") = " +
                fun.evalf(x));
        System.out.println("Точне значення sin(" + x + ") = " + Math.sin(x));
        System.out.println("Абсолютна помилка = " +
                Math.abs(fun.evalf(x)-Math.sin(x)));
    }
}
