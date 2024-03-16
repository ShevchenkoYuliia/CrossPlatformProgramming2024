/*import java.lang.reflect.Field;
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

    @Override
    public String toString() {
        return "Check{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

public class Task2 {
    public static void main(String[] args) {
        System.out.println("Створення об'єкту...");
        Check obj = new Check(3.0, 4.0);
        printObjectState(obj);

        System.out.println("Виклик методу...");
        printOpenMethods(obj);
        int choice = getUserChoice();
        callMethod(obj, choice);
    }

    private static void printObjectState(Object obj) {
        System.out.println("Стан об'єкту:");
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                System.out.println(field.getType().getSimpleName() + " " + field.getName() + " = " + field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printOpenMethods(Object obj) {
        System.out.println("Список відкритих методів:");
        Method[] methods = obj.getClass().getDeclaredMethods();
        int count = 1;
        for (Method method : methods) {
            if (method.getParameterCount() == 0 && method.getReturnType() != void.class) {
                System.out.println(count + "). " + method.toString());
                count++;
            }
        }
    }

    private static int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть порядковий номер методу:");
        return scanner.nextInt();
    }

    private static void callMethod(Object obj, int choice) {
        Method[] methods = obj.getClass().getDeclaredMethods();
        int count = 1;
        for (Method method : methods) {
            if (method.getParameterCount() == 0 && method.getReturnType() != void.class) {
                if (count == choice) {
                    try {
                        System.out.println("Результат виклику методу: " + method.invoke(obj));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                count++;
            }
        }
    }
}*/
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

class Task2 {
    private double x;
    private double y;

    public Task2(double x, double y) {
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

    @Override
    public String toString() {
        return "x = " + x + "\ny = " + y;
    }

    public static void main(String[] args) {
        Task2 obj = new Task2(3.0, 4.0);
        System.out.println("Створення об'єкту...");
        printObjectState(obj);

        System.out.println("Виклик методу...");
        printPublicMethods(obj);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть порядковий номер методу [1 ,6]:");
        int methodIndex = scanner.nextInt();

        switch (methodIndex) {
            case 1:
                System.out.println("Результат виклику методу: " + obj.Dist());
                break;
            case 2:
                obj.setRandomData();
                System.out.println("Стан об'єкту після виклику методу setRandomData:");
                printObjectState(obj);
                break;
            case 3:
                System.out.println("Результат виклику toString(): " + obj.toString());
                break;
            case 4:
                System.out.println("Введіть нові значення для x та y:");
                double newX = scanner.nextDouble();
                double newY = scanner.nextDouble();
                obj.setData(newX, newY);
                System.out.println("Стан об'єкту після виклику методу setData:");
                printObjectState(obj);
                break;
            default:
                System.out.println("Невірний номер методу!");
        }
    }

    private static void printObjectState(Task2 obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                System.out.println(field.getName() + " = " + field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printPublicMethods(Task2 obj) {
        Method[] methods = obj.getClass().getDeclaredMethods();
        System.out.println("Список відкритих методів:");
        int index = 1;
        for (Method method : methods) {
            if (method.getParameterCount() == 0 && !method.getName().equals("main")) {
                System.out.println(index + "). " + method.toString());
                index++;
            }
        }
    }
}
