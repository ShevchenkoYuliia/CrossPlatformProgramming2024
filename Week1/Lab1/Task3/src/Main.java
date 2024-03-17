import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
class FunctionNotFoundException extends Exception {
    public FunctionNotFoundException(String message) {
        super(message);
    }
}
public class Main {
    public static void callMethod(Object object, String methodName, Object... parameters) throws FunctionNotFoundException {
        Class<?> clazz = object.getClass();
        Class<?>[] parameterTypes = new Class[parameters.length];
        StringBuilder parameterInfo = new StringBuilder("Типи: [");
        StringBuilder parameterValues = new StringBuilder("значення: [");

        for (int i = 0; i < parameters.length; i++) {
            parameterTypes[i] = parameters[i].getClass();
            parameterInfo.append(parameterTypes[i].getSimpleName());
            parameterValues.append(parameters[i]);
            if (i < parameters.length - 1) {
                parameterInfo.append(", ");
                parameterValues.append(", ");
            }
        }
        parameterInfo.append("]");
        parameterValues.append("]");

        try {
            Method method = null;
            try {
                method = clazz.getMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {
                Class<?>[] primitiveParameterTypes = new Class[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    primitiveParameterTypes[i] = convertToPrimitive(parameters[i].getClass());
                }
                method = clazz.getMethod(methodName, primitiveParameterTypes);
                Object[] primitiveParameters = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    primitiveParameters[i] = convertToPrimitive(parameters[i]);
                }
                parameters = primitiveParameters;
            }

            Object result = method.invoke(object, parameters);
            System.out.println(parameterInfo + ", " + parameterValues);
            System.out.println("Результат виклику: " + result);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new FunctionNotFoundException("Метод " + methodName + " не знайдено або виникла помилка під час виклику");
        }
    }

    private static Class<?> convertToPrimitive(Class<?> wrapperClass) {
        if (wrapperClass == Double.class) {
            return double.class;
        } else if (wrapperClass == Integer.class) {
            return int.class;
        }
        return wrapperClass;
    }

    private static Object convertToPrimitive(Object obj) {
        if (obj instanceof Double) {
            return (double) obj;
        } else if (obj instanceof Integer) {
            return (int) obj;
        }
        return obj;
    }
    public static void main(String[] args) {
        double a = 1.0;
        TestClass test = new TestClass(a);
        try {
            System.out.println("TestClass [a = " + a + ", exp(-abs(a) * x) * sin(x)]");
            callMethod(test, "calculate", 1.0);
            callMethod(test, "calculate", 1.0, 1);
        } catch (FunctionNotFoundException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
class TestClass {
    private double a;
    public TestClass(double a) {
        this.a = a;
    }
    public double calculate(double x) {
        return Math.exp(-Math.abs(a) * x) * Math.sin(x);
    }
    public double calculate(double x, int y) {
        return Math.exp(-Math.abs(a) * x) * Math.sin(x);
    }
}