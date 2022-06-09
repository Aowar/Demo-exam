package org.leaarn_school.app.ui;

import com.mysql.cj.xdevapi.Column;
import com.mysql.cj.xdevapi.UpdateParams;
import org.leaarn_school.app.App;
import org.leaarn_school.app.entity.ServiceEntity;
import org.leaarn_school.app.manager.ServiceEntityManager;
import org.leaarn_school.app.util.BaseForm;
import org.leaarn_school.app.util.CustomTableModel;
import org.leaarn_school.app.util.DialogUtil;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class ServiceTableForm extends BaseForm {
    private JTable table;
    private JPanel mainPanel;
    private JComboBox comboBox;
    private JButton sortButton;
    private JLabel amountLabel;
    private JButton createButton;
    private CustomTableModel<ServiceEntity> model;
    private boolean sortFlag = false;


    public ServiceTableForm() {
        super(800, 600);
        setContentPane(mainPanel);
        initTable();
        initButtons();
        initBoxes();

        amountLabel.setText("Отображено 100/100");

        setVisible(true);
        if(App.isAdmin) {
            DialogUtil.ShowInfo(null, "Вы вошли как админ");
        }
    }

    private void initBoxes() {
        comboBox.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                try {
                    List<ServiceEntity> list = ServiceEntityManager.selectALl();
                    switch (comboBox.getSelectedIndex()) {
                        case 1 -> list.removeIf(serviceEntity -> serviceEntity.getDiscount() > 5);
                        case 2 -> list.removeIf(serviceEntity -> serviceEntity.getDiscount() <= 5 || serviceEntity.getDiscount() > 15);
                        case 3 -> list.removeIf(serviceEntity -> serviceEntity.getDiscount() <= 15 || serviceEntity.getDiscount() > 30);
                        case 4 -> list.removeIf(serviceEntity -> serviceEntity.getDiscount() <= 30 || serviceEntity.getDiscount() > 75);
                        case 5 -> list.removeIf(serviceEntity -> serviceEntity.getDiscount() <= 70);
                    }
                    model.setRows(list);
                    model.fireTableDataChanged();
                    amountLabel.setText("Отображено " + list.size() + "/100");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    private void initButtons() {
        createButton.addActionListener(e -> {
            dispose();
            new ServiceCreateForm();
        });

        sortButton.addActionListener(e -> {
            if(sortFlag) {
                model.getRows().sort(new Comparator<ServiceEntity>() {
                    @Override
                    public int compare(ServiceEntity o1, ServiceEntity o2) {
                        return Double.compare(o1.getCost(), o2.getCost());
                    }
                });
            } else {
                model.getRows().sort(new Comparator<ServiceEntity>() {
                    @Override
                    public int compare(ServiceEntity o1, ServiceEntity o2) {
                        return Double.compare(o2.getCost(), o1.getCost());
                    }
                });
            }
            sortFlag = !sortFlag;
            model.fireTableDataChanged();
        });
    }

    private void initTable() {
        try {
            model = new CustomTableModel<>(
                    ServiceEntity.class,
                    new String[]{"ID", "Наименование услуги", "Стоимость", "Длительность", "Описание", "Скидка", "Дата", "Путь к картинке", "Картинка"},
                    ServiceEntityManager.selectALl()
            );
            table.setRowHeight(50);
            table.setModel(model);

            TableColumn column = table.getColumn("ID");
            column.setPreferredWidth(0);
            column.setMinWidth(0);
            column.setMaxWidth(0);

            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getButton() == MouseEvent.BUTTON2) {
                        int row = table.rowAtPoint(e.getPoint());
                        if(row != -1) {
                            dispose();
                            new ServiceUpdateForm(model.getRows().get(row));
                        }
                    }
                }
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

