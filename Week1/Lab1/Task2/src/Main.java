import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;
class Check {
    private double x;
    private double y;
    public Check(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double Dist() {
        return Math.sqrt(x * x + y * y);
    }
    public void setRandomData() {
        this.x = Math.random() * 10;
        this.y = Math.random() * 10;
    }
    public void setData(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public String toString() {
        return "x=" + x + ", y=" + y ;
    }
}

public class Main {
    public static void main(String[] args) {
        Check obj = new Check(3.0, 4.0);
        System.out.println("Створення об'єкту...");
        printObjectState(obj);
        System.out.println("Виклик методу...");
        printAvailableMethods(obj);
        int length = obj.getClass().getDeclaredMethods().length;
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Введіть порядковий номер методу [1 , " + length + "]: ");
        int number = scanner.nextInt();
        if (number >= 1 && number <= length) {
            try {
                Method selectedMethod = Check.class.getDeclaredMethods()[number - 1];
                if (selectedMethod.getParameterCount() == 0 && selectedMethod.getReturnType() == void.class) {
                    selectedMethod.invoke(obj);
                    System.out.println("Метод успішно виконано");
                } else if (selectedMethod.getParameterCount() == 2 && selectedMethod.getReturnType() == void.class) {
                    Scanner dataScanner = new Scanner(System.in);
                    System.out.print("Введіть значення x: ");
                    double x = dataScanner.nextDouble();
                    System.out.print("Введіть значення y: ");
                    double y = dataScanner.nextDouble();
                    selectedMethod.invoke(obj, x, y);
                    System.out.println("Метод успішно виконано");
                } else {
                    Object result = selectedMethod.invoke(obj);
                    System.out.println("Результат виклику методу: " + result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Невірний номер методу");
        }
    }
    private static void printObjectState(Object obj) {
        System.out.println("Стан об'єкту:");
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                System.out.println(field.getType().getSimpleName() + " " + field.getName() + " = " + field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    private static void printAvailableMethods(Object obj) {
        Class<?> clazz = Check.class;
        Method[] methods = clazz.getDeclaredMethods();
        System.out.println("Список відкритих методів:");
        int index = 1;
        for (Method method : methods) {
            System.out.println(index + "). " + method.toString());
            index++;
        }
    }
}
