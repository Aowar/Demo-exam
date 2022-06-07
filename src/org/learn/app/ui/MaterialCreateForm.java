package org.learn.app.ui;

import org.learn.app.entity.MaterialEntity;
import org.learn.app.manager.MaterialEntityManager;
import org.learn.app.util.BaseForm;
import org.learn.app.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class MaterialCreateForm extends BaseForm {
    private JTextField titleField;
    private JPanel mainPanel;
    private JTextField unitField;
    private JTextField descField;
    private JTextField imagePathField;
    private JTextField materialTypeField;
    private JButton saveButton;
    private JButton cancelButton;
    private JSpinner countInPackSpinner;
    private JSpinner countInStockSpinner;
    private JSpinner minCountSpinner;
    private JSpinner costSpinner;

    public MaterialCreateForm() {
        super(800, 600);
        setContentPane(mainPanel);

        initButtons();

        setVisible(true);
    }

    private void initButtons() {
        cancelButton.addActionListener(e -> {
            dispose();
            new MaterialTableForm();
        });

        saveButton.addActionListener(e -> {
            String title = titleField.getText();
            if(title.isEmpty() || title.length() > 100){
                DialogUtil.showInfo(this, "Наименования отсутствует или слишком большое");
                return;
            }
            String unit = unitField.getText();
            String desc = descField.getText();
            String imagePath = imagePathField.getText();
            String materialType = materialTypeField.getText();
            int countInPack = (int) countInPackSpinner.getValue();
            int countInStock = (int) countInStockSpinner.getValue();
            int minCount = (int) minCountSpinner.getValue();
            int cost = (int) costSpinner.getValue();

            MaterialEntity materialEntity = new MaterialEntity(title, countInPack, unit, countInStock, minCount, desc, cost, imagePath, materialType);

            try {
                MaterialEntityManager.insert(materialEntity);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                DialogUtil.showError(this, "Ошибка при добавлении нового материала" + throwables.getMessage());
            }
            dispose();
            new MaterialTableForm();
        });
    }
}
