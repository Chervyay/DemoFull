package ru.chervy.demoexam.ui;

import ru.chervy.demoexam.entity.ProductEntity;
import ru.chervy.demoexam.manager.ProductEntityManager;
import ru.chervy.demoexam.util.BaseForm;
import ru.chervy.demoexam.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class ProductCreateForm extends BaseForm
{
    private JPanel mainPanel;
    private JTextField titleField;
    private JTextField typeField;
    private JTextField articleField;
    private JTextField imagePathField;
    private JTextField personCountField;
    private JTextField workshopField;
    private JTextField minAgentCostField;
    private JTextField descField;
    private JButton backButton;
    private JButton saveButton;


    public ProductCreateForm() {
        super(400, 600);
        setContentPane(mainPanel);

        initButtons();

        setVisible(true);

    }

    private void initButtons()
    {
        backButton.addActionListener(e->
        {
            dispose();
            new ProductEntityForm();

        });
        saveButton.addActionListener(e->
        {
            String title = titleField.getText();
            if(title.isEmpty() || title.length() > 50)
            {
                DialogUtil.showError(this, "Неверно введено наименование");
                return;
            }
            String productType = typeField.getText();
            String article = articleField.getText();
            String description = descField.getText();
            String imagePath = imagePathField.getText();
            int personCount = Integer.parseInt(personCountField.getText());
            int workshop = Integer.parseInt(workshopField.getText());
            float minCostForAgent = Float.parseFloat(minAgentCostField.getText());

            ProductEntity product = new ProductEntity(title,productType,article,description,imagePath,personCount,workshop,minCostForAgent);

            try {
                ProductEntityManager.insert(product);
            } catch (SQLException ex) {
                ex.printStackTrace();
                DialogUtil.showError(this, "Ошибка записи в базу данных");
            }
            DialogUtil.showInfo(this, "Продукт успешно добавлен");
        });
    }



}
