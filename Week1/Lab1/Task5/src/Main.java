import java.lang.reflect.*;
import java.util.Scanner;
interface Evaluatable {
    double evalf(double x, int num);
}
class NumFunction implements Evaluatable {
    public double evalf(double x, int num) {
        if(num == 1){
            return Math.exp(-Math.abs(2.5) * x) * Math.sin(x);
        }
        else {
            return x * x;
        }
    }
}
class MyProxy implements InvocationHandler {
    private Object obj;
    private double x;
    private int number;
    public MyProxy(Object obj, double x, int number) {
        this.obj = obj;
        this.x = x;
        this.number = number;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        double start = System.nanoTime();
        Object res = method.invoke(obj, args);
        double finish = System.nanoTime();

        if (number == 1 ) {
            System.out.println("[Exp(-|2.5| * x) * sin(x)]." + method.getName() + " took " + (finish - start) + " ns");
            System.out.println("[Exp(-|2.5| * x) * sin(x)]." + method.getName() + "(" + x + ")" + " = " + res);
        } else if (number == 2 ) {
            System.out.println("[x * x]." + method.getName() + " took " + (finish - start) + " ns");
            System.out.println("[x * x]." + method.getName() + "(" + x + ")" + " = " + res);
        }
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner s1 = new Scanner(System.in);
        System.out.printf("Enter x: ");
        double x = s1.nextDouble();
        NumFunction fun = new NumFunction();


        Class<?> proxy = Proxy.getProxyClass(fun.getClass().getClassLoader(),
                fun.getClass().getInterfaces());
        try {
            System.out.println("F1: " + fun.evalf(x, 1));
            Evaluatable e1 = (Evaluatable) proxy.getConstructor(InvocationHandler.class)
                    .newInstance(new MyProxy(fun, x, 1));
            e1.evalf(x, 1);
            System.out.println("F2: " + fun.evalf(x, 2));
            Evaluatable e2 = (Evaluatable) proxy.getConstructor(InvocationHandler.class)
                    .newInstance(new MyProxy(fun, x, 2));
            e2.evalf(x, 2);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}