package consoleTasks;

import java.io.*;
public class DerivativeApplication {
    public static void main(String[] args) throws IOException {
        Evaluatable functs[] = new Evaluatable[4];
        functs[0] = new FFunction(1);
        functs[1] = new FFunction(0.5);
        functs[2] = new FFunction(1.5);
        functs[3] = new FileListInterpolation();
        try {
            ((FileListInterpolation)functs[3]).readFromFile("TblFunc.dat");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        String fileName = "";
        for (Evaluatable f: functs) {
            System.out.printf("Функція: " + f.getClass().getSimpleName()) ;
            if (f instanceof FFunction) {
                FFunction function = (FFunction) f;
                System.out.println("  a = " + function.getA());
            } else {
                System.out.println();
            }
            fileName = f.getClass().getSimpleName() + ".dat";
            PrintWriter out = new PrintWriter(new FileWriter(fileName));
            for (double x = 1.5; x <= 6.5; x += 0.05) {
                System.out.println("x: " + x + "\tf: " + f.evalf(x) + "\tf': " +
                        NumMethods.der(x, 1.0e-4, f));
                out.printf("%16.6e%16.6e%16.6e\n", x, f.evalf(x),
                        NumMethods.der(x, 1.0e-4, f));
            }
            System.out.println("\n");
            out.close();
        }
    }
}

