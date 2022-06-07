package org.learn.app.ui;

import org.learn.app.entity.MaterialEntity;
import org.learn.app.manager.MaterialEntityManager;
import org.learn.app.util.BaseForm;
import org.learn.app.util.CustomTableModel;
import org.learn.app.util.DialogUtil;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class MaterialTableForm extends BaseForm {
    private JPanel mainPanel;
    private JTable table;
    private JButton createButton;

    private CustomTableModel<MaterialEntity> model;

    public MaterialTableForm() {
        super(1200, 800);
        setContentPane(mainPanel);

        initTable();
        initButtons();

        setVisible(true);
    }

    private void initTable() {
        table.setRowHeight(50);
        try {
            model = new CustomTableModel<>(
                    MaterialEntity.class,
                    new String[]{"ID", "Название материала", "Количество в упаковке", "Единица измерения", "Количество на складе", "Минимальное количество", "Описание", "Цена", "Путь к изображению", "Тип материала", "Изображение"},
                    MaterialEntityManager.selectAll()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            DialogUtil.showError(this, "Ошибка вывода таблицы" + e.getMessage());
        }
        table.setModel(model);

        TableColumn column1 = table.getColumn("ID");
        column1.setMaxWidth(0);
        column1.setMinWidth(0);
        column1.setPreferredWidth(0);

        TableColumn column2 = table.getColumn("Путь к изображению");
        column2.setMaxWidth(0);
        column2.setMinWidth(0);
        column2.setPreferredWidth(0);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON2) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row != -1) {
                        dispose();
                        new MaterialUpdateForm(model.getRows().get(row));
                    }
                }
            }
        });
    }

    private void initButtons() {
        createButton.addActionListener(e -> {
            dispose();
            new MaterialCreateForm();
        });
    }
}
