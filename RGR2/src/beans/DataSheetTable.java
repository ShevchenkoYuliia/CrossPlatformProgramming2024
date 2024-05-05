package beans;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataSheetTable extends JPanel {
    private JTable table;
    private DataSheetTableModel tableModel;
    private Button addButton;
    private Button delButton;
    private Panel panel1;
    private Panel panelButtons;

    public DataSheetTable(){
        setupUI();
        table.setModel(tableModel);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(tableModel.getRowCount()+1);
                tableModel.getDataSheet().addDataItem(new Data());
                table.revalidate();
                tableModel.fireDataSheetChange();
            }
        });
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tableModel.getRowCount() > 1) {
                    tableModel.setRowCount(tableModel.getRowCount() - 1);
                    tableModel.getDataSheet().removeDataItem(
                            tableModel.getDataSheet().size()-1);
                    table.revalidate();
                    tableModel.fireDataSheetChange();
                } else {
                    tableModel.getDataSheet().getDataItem(0).setDate("");
                    tableModel.getDataSheet().getDataItem(0).setX(0);
                    tableModel.getDataSheet().getDataItem(0).setY(0);
                    table.revalidate();
                    table.repaint();
                    tableModel.fireDataSheetChange();
                }
            }
        });
    }

    public DataSheetTableModel getTableModel() {
        return tableModel;
    }
    public void setTableModel(DataSheetTableModel tableModel) {
        this.tableModel = tableModel;
        table.revalidate();
    }
    public void revalidate() {
        if (table != null) table.revalidate();
    }
    public Component getRootComponent() {
        return panel1;
    }

    private void setupUI() {
        createUIComponents();
        panel1 = new Panel();
        panel1.setLayout(new BorderLayout( 0, 0));
       panel1.setForeground(Color.black);
        panel1.setMinimumSize(new Dimension(400, 600));
        panel1.setPreferredSize(new Dimension(400, 600));
        panelButtons = new Panel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));
        panelButtons.setForeground(Color.black);
        panel1.add(panelButtons, BorderLayout.SOUTH);
        addButton = new Button("Add(+)");
        addButton.setForeground(Color.BLACK);
        addButton.setPreferredSize(new Dimension(100, 40));
        addButton.setFont(new Font("Arial", Font.BOLD, 16));
        panelButtons.add(addButton);
        delButton = new Button("Del(-)");
        delButton.setForeground(Color.BLACK);
        delButton.setFont(new Font("Arial", Font.BOLD, 16));
        delButton.setPreferredSize(new Dimension(100, 40));
        panelButtons.add(delButton);
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setForeground(Color.black);
        panel1.add(scrollPane1, BorderLayout.CENTER);
        table.setForeground(Color.black);
        table.setSelectionForeground(Color.BLACK);
        scrollPane1.setViewportView(table);
    }

    private void createUIComponents() {
        table = new JTable();
        tableModel = new DataSheetTableModel();
        table.setModel(tableModel);
        table.getTableHeader().setForeground(Color.black);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
    }
}
