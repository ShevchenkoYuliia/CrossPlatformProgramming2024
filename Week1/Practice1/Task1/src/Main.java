import java.lang.reflect.*;
import java.util.Scanner;

public class Main {
    public static String analyzeClass(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        return analyzeClass(clazz);
    }

    public static String analyzeClass(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();

        Package pkg = clazz.getPackage();
        if (pkg != null) {
            sb.append("package ").append(pkg.getName()).append(";").append("\n");
        }

        int modifiers = clazz.getModifiers();
        sb.append(Modifier.toString(modifiers)).append(" class ").append(clazz.getSimpleName());

        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null && superclass != Object.class) {
            sb.append(" extends ").append(superclass.getSimpleName());
        }

        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces.length > 0) {
            sb.append(" implements ");
            for (int i = 0; i < interfaces.length; i++) {
                sb.append(interfaces[i].getSimpleName());
                if (i < interfaces.length - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append(" {\n");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            sb.append("    ");
            sb.append(Modifier.toString(field.getModifiers())).append(" ");
            sb.append(field.getType().getSimpleName()).append(" ");
            sb.append(field.getName()).append(";\n");
        }

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            sb.append("    ");
            sb.append(Modifier.toString(constructor.getModifiers())).append(" ");
            sb.append(constructor.getName()).append("(");
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            sb.append(getParameterTypeNames(parameterTypes));
            sb.append(");\n");
        }

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            sb.append("    ");
            sb.append(Modifier.toString(method.getModifiers())).append(" ");
            sb.append(method.getReturnType().getSimpleName()).append(" ");
            sb.append(method.getName()).append("(");
            Class<?>[] parameterTypes = method.getParameterTypes();
            sb.append(getParameterTypeNames(parameterTypes));
            sb.append(");\n");
        }

        sb.append("}");

        return sb.toString();
    }

    private static String getParameterTypeNames(Class<?>[] parameterTypes) {
        if (parameterTypes.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameterTypes.length; i++) {
            sb.append(parameterTypes[i].getSimpleName());
            if (i < parameterTypes.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type Java class full name (for example java.util.Date)\n->");
        String className = scanner.nextLine();
        scanner.close();
        try {
            String classInfo = analyzeClass(className);
            System.out.println(classInfo);
        } catch (ClassNotFoundException e) {
            System.out.println("Class " + className + " not found! ");
        }
    }
}
