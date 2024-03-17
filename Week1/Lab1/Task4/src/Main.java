import java.lang.reflect.Array;
public class Main {
    public static Object createArray(Class<?> type, int size) {
        return Array.newInstance(type, size);
    }
    public static Object resizeArray(Object array, int newSize) {
        Class<?> type = array.getClass().getComponentType();
        Object newArray = Array.newInstance(type, newSize);
        int length = Math.min(Array.getLength(array), newSize);
        System.arraycopy(array, 0, newArray, 0, length);
        return newArray;
    }
    public static Object createMatrix(Class<?> type, int rows, int cols) {
        return Array.newInstance(type, rows, cols);
    }
    public static Object resizeMatrix(Object matrix, int newRows, int newCols) {
        Class<?> type = matrix.getClass().getComponentType().getComponentType();
        Object newMatrix = Array.newInstance(type, newRows, newCols);
        int rows = Math.min(Array.getLength(matrix), newRows);
        for (int i = 0; i < rows; i++) {
            Object row = Array.get(matrix, i);
            int cols = Math.min(Array.getLength(row), newCols);
            Object newRow = Array.newInstance(type, newCols);
            System.arraycopy(row, 0, newRow, 0, cols);
            Array.set(newMatrix, i, newRow);
        }
        return newMatrix;
    }
    public static String arrayToString(Object array) {
        StringBuilder sb = new StringBuilder();
        if (array.getClass().isArray()) {
            if (array.getClass().getComponentType().isArray()) {
                int rows = Array.getLength(array);
                int cols = Array.getLength(Array.get(array, 0));
                sb.append(array.getClass().getComponentType().getComponentType().getName())
                        .append("[").append(rows).append("][").append(cols).append("] = {");
                for (int i = 0; i < rows; i++) {
                    if (i > 0) sb.append(", ");
                    sb.append("{");
                    for (int j = 0; j < cols; j++) {
                        if (j > 0) sb.append(", ");
                        sb.append(Array.get(Array.get(array, i), j));
                    }
                    sb.append("}");
                }
                sb.append("}");
            } else {
                int length = Array.getLength(array);
                sb.append(array.getClass().getComponentType().getName())
                        .append("[").append(length).append("] = {");
                for (int i = 0; i < length; i++) {
                    if (i > 0) sb.append(", ");
                    sb.append(Array.get(array, i));
                }
                sb.append("}");
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        int[] array = (int[]) createArray(int.class, 2);
        System.out.println(arrayToString(array));

        String[]  array2 = (String[]) createArray(String.class, 3);
        System.out.println(arrayToString(array2));

        Double[]  array3 = (Double[]) createArray(Double.class, 5);
        System.out.println(arrayToString(array3));

        int[][]  array4 = (int[][]) createMatrix(int.class, 3, 5);
        int[][] newValues = {{0, 1, 2, 3, 4}, {10, 11, 12, 13, 14}, {20, 21, 22, 23, 24}};
        for (int i = 0; i < newValues.length; i++) {
            for (int j = 0; j < newValues[i].length; j++) {
                Array.set(array4[i], j, newValues[i][j]);
            }
        }
        System.out.println(arrayToString(array4));

        array4 = (int[][]) resizeMatrix( array4, 4, 6);
        System.out.println(arrayToString( array4));

        array4 = (int[][]) resizeMatrix(array4, 3, 7);
        System.out.println(arrayToString(array4));

        int[][] array5 = {{0, 1}, {10, 11}};
        System.out.println(arrayToString(array5));
    }
}