package myApplication;
import beans.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame {
    private Button readButton, saveButton, clearButton, exitButton;
    private DataSheetTable dataSheetTable = null;
    private DataSheetGraph dataSheetGraph = null;
    private Panel panel;
    private DataSheet dataSheet;
    private final FileDialog fileDialog = new FileDialog(this);

    public Test() throws HeadlessException {
        setupUI();
        fileDialog.setDirectory(".");
        readButton.addActionListener(e -> {
            fileDialog.setVisible(true);
            String fileName = fileDialog.getFile();
            if (fileName != null) {
                dataSheet = XMLRead.XMLReadData(fileName);
                dataSheetTable.getTableModel().setDataSheet(dataSheet);
                dataSheetTable.revalidate();
                dataSheetGraph.setDataSheet(dataSheet);
            }
        });
        saveButton.addActionListener(e -> {
            fileDialog.setMode(FileDialog.SAVE);
            fileDialog.setVisible(true);
            String fileName = fileDialog.getFile();
            if (fileName != null) {
                DataSheetToXML.saveXMLDoc(DataSheetToXML.createDataSheetDOM(dataSheet), fileName);
                JOptionPane.showMessageDialog(null, "File " + fileName.trim()
                        + " saved!", "Results saved", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        clearButton.addActionListener(e -> {
            dataSheet = new DataSheet();
            dataSheet.addDataItem(new Data());
            dataSheetTable.getTableModel().setDataSheet(dataSheet);
            dataSheetTable.revalidate();
            dataSheetGraph.setDataSheet(dataSheet);
        });
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        setTitle("JavaBeans");
        setPreferredSize(new Dimension(812, 600));
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    public static void main(String[] args) {
        JFrame test = new Test();
    }

    private void setupUI() {
        createUIComponents();
        panel.setLayout(new BorderLayout(0, 0));
        panel.setMinimumSize(new Dimension(800, 600));
        panel.setPreferredSize(new Dimension(800, 600));
        final Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        buttonPanel.setMinimumSize(new Dimension(800, 60));
        buttonPanel.setPreferredSize(new Dimension(800, 60));
        panel.add(buttonPanel, BorderLayout.SOUTH);
        readButton = new Button("Open");
        readButton.setForeground(Color.black);
        readButton.setPreferredSize(new Dimension(80, 40));
        readButton.setFont(new Font("Arial", Font.BOLD, 16));
        buttonPanel.add(readButton);
        saveButton = new Button("Save");
        saveButton.setFont(new Font("Arial", Font.BOLD, 16));
        saveButton.setPreferredSize(new Dimension(80, 40));
        buttonPanel.add(saveButton);
        clearButton = new Button("Clear");
        clearButton.setForeground(Color.black);
        clearButton.setPreferredSize(new Dimension(80, 40));
        clearButton.setFont(new Font("Arial", Font.BOLD, 16));
        buttonPanel.add(clearButton);
        exitButton = new Button("Exit");
        exitButton.setForeground(Color.black);
        exitButton.setPreferredSize(new Dimension(80, 40));
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        buttonPanel.add(exitButton);
        panel.add(dataSheetTable.getRootComponent(), BorderLayout.WEST);
        dataSheetGraph = new DataSheetGraph();
        panel.add(dataSheetGraph, BorderLayout.EAST);
        add(panel);
    }

    private void createUIComponents() {
        panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.setMinimumSize(new Dimension(800, 600));
        panel.setPreferredSize(new Dimension(800, 600));

        dataSheet = new DataSheet();
        dataSheet.addDataItem(new Data());

        dataSheetTable = new DataSheetTable();
        dataSheetTable.getTableModel().setDataSheet(dataSheet);
        dataSheetTable.getTableModel().addDataSheetChangeListener(e -> {
            dataSheetGraph.revalidate();
            dataSheetGraph.repaint();
        });
        panel.add(dataSheetTable, BorderLayout.WEST);
    }
}
