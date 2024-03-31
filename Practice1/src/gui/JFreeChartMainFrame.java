package gui;

import consoleTasks.FFunction;
import edu.hws.jcm.data.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFreeChartMainFrame extends JFrame {
    private JPanel contentPane;
    private JTextField textFieldA;
    private XYSeries series;
    private XYSeries derSeries;
    private FFunction f;
    private JTextField startX;
    private JTextField stopX;
    private JTextField stepX;
    private JTextField param;
    private String function;
    private JLabel label;
    private JPanel northPanel = new JPanel(new BorderLayout());


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFreeChartMainFrame frame = new  JFreeChartMainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public  JFreeChartMainFrame() {
        setResizable(false);
        setTitle("Function");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelButtons = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
        flowLayout.setHgap(15);
        contentPane.add(panelButtons, BorderLayout.SOUTH);

        JButton btnNewButtonPlot = new JButton("Plot");
        panelButtons.add(btnNewButtonPlot);

        JButton btnNewButtonExit = new JButton("Exit");
        btnNewButtonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panelButtons.add(btnNewButtonExit);

        JLabel start = new JLabel("Start: ");
        panelButtons.add(start);
        startX = new JTextField("1.5");
        panelButtons.add(startX);
        JLabel stop = new JLabel("Stop: ");
        panelButtons.add(stop);
        stopX = new JTextField("6.5");
        panelButtons.add(stopX);
        JLabel step = new JLabel("Step: ");
        panelButtons.add(step);
        stepX = new JTextField("0.05");
        panelButtons.add(stepX);

        btnNewButtonPlot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double start = Double.parseDouble(startX.getText());
                double stop = Double.parseDouble(stopX.getText());
                double step = Double.parseDouble(stepX.getText());
                String paramText = param.getText().trim();
                double a = paramText.isEmpty() ? 1 : Double.parseDouble(paramText);

                Parser parser = new Parser(Parser.STANDARD_FUNCTIONS);
                Variable var = new Variable("x");
                parser.add(var);
                function = textFieldA.getText();

                Expression fun = parser.parse(function.replaceAll("a", String.valueOf(a)));
                Expression der = fun.derivative(var);

                series.clear();
                derSeries.clear();
                for (double x = start; x < stop; x += step) {
                    var.setVal(x);
                    series.add(x, fun.getVal());
                    derSeries.add(x, der.getVal());
                }
                label.setText("<html>y = " + fun.toString() + "<br>y' = " + der.toString() +"</html>");
            }
        });

        JPanel panelData = new JPanel();
        northPanel.add(panelData, BorderLayout.NORTH);
        label = new JLabel("<html>y = sin(x)<br>y' = cos(x)</html>", SwingConstants.CENTER);
        northPanel.add(label, BorderLayout.SOUTH);
        contentPane.add(northPanel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("f(x): ");
        panelData.add(lblNewLabel);
        textFieldA = new JTextField();
        textFieldA.setText("sin(x)");
        panelData.add(textFieldA);
        textFieldA.setColumns(10);

        JLabel paramLabel = new JLabel("a: ");
        panelData.add(paramLabel);
        param = new JTextField();
        param.setText("1.0");
        panelData.add(param);
        param.setColumns(10);

        JFreeChart chart = createChart();
        ChartPanel chartPanel = new ChartPanel(chart);
        contentPane.add(chartPanel, BorderLayout.CENTER);
    }

    private JFreeChart createChart() {
        series = new XYSeries("Function");
        derSeries = new XYSeries("Derivative");

        double start = Double.parseDouble(startX.getText());
        double stop = Double.parseDouble(stopX.getText());
        double step = Double.parseDouble(stepX.getText());

        Parser parser = new Parser(Parser.STANDARD_FUNCTIONS);
        Variable var = new Variable("x");
        parser.add(var);
        function = textFieldA.getText();
        Expression fun = parser.parse(function);
        Expression der = fun.derivative(var);


        for (double x = start; x < stop; x += step) {
            var.setVal(x);
            series.add(x, fun.getVal());
            derSeries.add(x, der.getVal());
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        dataset.addSeries(derSeries);


        JFreeChart chart = ChartFactory.createXYLineChart("Function", "X",
                "Y", dataset, PlotOrientation.VERTICAL, true, true, false);

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        return chart;
    }
}