package consoleTasks;
import java.util.*;
import static java.lang.Math.*;

public class TreeMapInterpolation extends Interpolator {
    private SortedMap<Double, Double> data = null;
    public TreeMapInterpolation() {
        data = new TreeMap<Double, Double>();
    }
    public TreeMapInterpolation(SortedMap<Double, Double> data) {
        this.data = data;
    }
    public TreeMapInterpolation(Point2D[] data) {
        this();
        for (Point2D pt :
                data) {
            this.data.put(pt.getX(), pt.getY());
        }
    }
    public void clear() {
        data.clear();
    }
    public int numPoints() {
        return data.size();
    }
    public void addPoint(Point2D pt) {
        data.put(pt.getX(), pt.getY());
    }
    public Point2D getPoint(int i) {
        List<Point2D> tmp2 = new ArrayList<Point2D>();
        Set<Double> tmp = data.keySet();
        for (Double x:
                tmp) {
            tmp2.add(new Point2D(x, data.get(x)));
        }
        return tmp2.get(i);
    }
    public void setPoint(int i, Point2D pt) {
        List<Point2D> tmp2 = new ArrayList<Point2D>();
        Set<Double> tmp = data.keySet();
        TreeMap<Double, Double> tmp3 = new TreeMap<>();
        for (Double x:
                tmp) {
            tmp2.add(new Point2D(x, data.get(x)));
        }
        tmp2.set(i, pt);
        for (Point2D p:
                tmp2) {
            tmp3.put(p.getX(), p.getY());
        }
        data = tmp3;
    }
    public void removeLastPoint() {
        data.remove(data.lastKey());
    }
    public void sort() {
    }
    public static void main(String[] args) {
        TreeMapInterpolation fun = new TreeMapInterpolation();
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
        System.out.println("Набір: ");
        for (int i = 0; i < fun.numPoints(); i++) {
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
        }
        System.out.println("Мінімальне значення х: " + fun.getPoint(0).getX());
        System.out.println("Максимальне значення х: " + fun.getPoint(fun.numPoints() - 1).getX());
        x = 0.5 * (fun.getPoint(0).getX() + fun.getPoint(fun.numPoints() - 1).getX());
        System.out.println("Значення інтерполяції fun(" + x + ") = " + fun.evalf(x));
        System.out.println("Точне значення sin(" + x + ") = " + sin(x));
        System.out.println("Абсолютна помилка = " + abs(fun.evalf(x) - sin(x)));
        System.out.println("Видалення точки.");
        fun.removeLastPoint();
        System.out.println("Перезапис першої точки.");
        fun.setPoint(0, new Point2D(0, sin(0)));
        System.out.println("Інтерполяція по: " + fun.numPoints() + " точкам");
        System.out.println("Набір: ");
        for (int i = 0; i < fun.numPoints(); i++) {
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
        }
        System.out.println("Мінімальне значення х: " + fun.getPoint(0).getX());
        System.out.println("Максимальне значення х: " + fun.getPoint(fun.numPoints() - 1).getX());
        x = 0.5 * (fun.getPoint(0).getX() + fun.getPoint(fun.numPoints() - 1).getX());
        System.out.println("Значення інтерполяції fun(" + x + ") = " + fun.evalf(x));
        System.out.println("Точне значення sin(" + x + ") = " + sin(x));
        System.out.println("Абсолютна помилка = " + abs(fun.evalf(x) - sin(x)));
    }
}