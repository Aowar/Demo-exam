package org.learn.app.ui;

import org.learn.app.entity.MaterialEntity;
import org.learn.app.manager.MaterialEntityManager;
import org.learn.app.ui.MaterialTableForm;
import org.learn.app.util.BaseForm;
import org.learn.app.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class MaterialUpdateForm extends BaseForm {
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
    private JButton deleteButton;

    private MaterialEntity material;

    public MaterialUpdateForm(MaterialEntity material) {
        super(800, 600);
        this.material = material;

        setContentPane(mainPanel);

        initFields();
        initButtons();

        setVisible(true);
    }

    private void initFields() {
        titleField.setText(material.getTitle());
        unitField.setText(material.getUnit());
        descField.setText(material.getDescription());
        imagePathField.setText(material.getImagePath());
        materialTypeField.setText(material.getMaterialType());
        countInPackSpinner.setValue(material.getCountInPack());
        countInStockSpinner.setValue(material.getCountInStock());
        minCountSpinner.setValue(material.getMinCount());
        costSpinner.setValue(material.getCost());
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
            }
            String unit = unitField.getText();
            String desc = descField.getText();
            String imagePath = imagePathField.getText();
            String materialType = materialTypeField.getText();
            int countInPack = (int) countInPackSpinner.getValue();
            int countInStock = (int) countInStockSpinner.getValue();
            int minCount = (int) minCountSpinner.getValue();
            int cost = (int) costSpinner.getValue();

            material.setTitle(title);
            material.setUnit(unit);
            material.setDescription(desc);
            material.setImagePath(imagePath);
            material.setMaterialType(materialType);
            material.setCountInPack(countInPack);
            material.setCountInStock(countInStock);
            material.setMinCount(minCount);
            material.setCost(cost);

            try {
                MaterialEntityManager.update(material);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                DialogUtil.showError(this, "Ошибка при добавлении нового материала" + throwables.getMessage());
            }
            dispose();
            new MaterialTableForm();
        });
        deleteButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Вы точно хотите удалить запись?", "Удалить запись", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    MaterialEntityManager.delete(material);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    DialogUtil.showError(this, "Ошибка при удалении записи: " + throwables.getMessage());
                }
            }
        });
    }
}
