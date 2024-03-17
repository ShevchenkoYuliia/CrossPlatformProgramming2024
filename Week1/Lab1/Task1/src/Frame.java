import java.lang.reflect.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Frame {
    private JFrame frame = new JFrame("Class Analyzer");
    private JPanel panel = new JPanel();
    private Dimension screenSize;
    private JPanel inputPanel = new JPanel();
    private JLabel label;
    private JTextField input;
    private JPanel textPanel = new JPanel();
    private JTextArea text;
    private JPanel buttonsPanel = new JPanel();
    private JButton analyse;
    private JButton clear;
    private JButton end;
    public static void main(String[] args) {
        new Frame();
    }

    public Frame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        screenSize = kit.getScreenSize();
        panel.setPreferredSize(new Dimension(screenSize.width/4 , screenSize.height/4));
        panel.setLayout(new BorderLayout());
        frame.setResizable(false);

        label = new JLabel("Сlass name(for example, java.lang.String):");
        input = new JTextField(10);
        input.addActionListener(new TextWritten());
        inputPanel.add(label);
        inputPanel.add(input);
        panel.add(inputPanel, BorderLayout.NORTH);

        text = new JTextArea(12, 40);
        textPanel.add(new JScrollPane(text));
        panel.add(textPanel, BorderLayout.CENTER);

        analyse = new JButton("Analyze");
        clear = new JButton("Clear");
        end = new JButton("Exit");

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText(null);
                input.setText(null);
            }
        });

        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonsPanel.add(analyse);
        buttonsPanel.add(clear);
        buttonsPanel.add(end);
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        analyse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = input.getText().trim();
                try {
                    String classInfo = analyzeClass(className);
                    text.setText(classInfo);
                } catch (ClassNotFoundException ex) {
                    text.setText("Class " + className + " not found!");
                }
            }
        });
    }

    private class TextWritten implements ActionListener {

    public void actionPerformed(ActionEvent e) {
            String className = input.getText().trim();
            try {
                String classInfo = analyzeClass(className);
                text.setText(classInfo);
            } catch (ClassNotFoundException ex) {
                text.setText("Class " + className + " not found!");
            }
        }
    }

    private String analyzeClass(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        return analyzeClass(clazz);
    }

    private String analyzeClass(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();

        Package pkg = clazz.getPackage();
        if (pkg != null) {
            sb.append("Package: ").append(pkg.getName()).append(";\n");
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
        sb.append("\n\nFields:\n");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            sb.append("    ");
            sb.append(Modifier.toString(field.getModifiers())).append(" ");
            sb.append(field.getType().getSimpleName()).append(" ");
            sb.append(field.getName()).append(";\n");
        }
        sb.append("\n\nConstructors:\n");

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            sb.append("    ");
            sb.append(Modifier.toString(constructor.getModifiers())).append(" ");
            sb.append(constructor.getName()).append("(");
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            sb.append(getParameterTypeNames(parameterTypes));
            sb.append(");\n");
        }
        sb.append("\nMethods:\n");
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

    private String getParameterTypeNames(Class<?>[] parameterTypes) {
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
}