package ru.chervy.demoexam.ui;

import ru.chervy.demoexam.entity.ProductEntity;
import ru.chervy.demoexam.manager.ProductEntityManager;
import ru.chervy.demoexam.util.BaseForm;
import ru.chervy.demoexam.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

import static javax.swing.JOptionPane.*;

public class ProductEditForm extends BaseForm
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
    private JButton deleteButton;
    private ProductEntity product;


    public ProductEditForm(ProductEntity product) {
        super(400, 600);
        this.product = product;
        setContentPane(mainPanel);

        initButtons();
        initFields();

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



            try {
                ProductEntityManager.update(product);
            } catch (SQLException ex) {
                ex.printStackTrace();
                DialogUtil.showError(this, "Ошибка записи в базу данных");
                return;
            }
            DialogUtil.showInfo(this, "Продукт успешно сохранён");

        });

        deleteButton.addActionListener(e->
        {
            if(DialogUtil.showConfirm(this, "Вы точно хотите удалить данный продукт?") == JOptionPane.YES_OPTION)
            {
                try {
                    ProductEntityManager.delete(product);
                    DialogUtil.showInfo(this, "Запись успешно удалена");
                    dispose();
                    new ProductEntityForm();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    DialogUtil.showError(this, "Ошибка при удалении" + ex.getMessage());
                }

            }
        });
    }

    private void initFields()
    {
        titleField.setText(product.getTitle());
        typeField.setText(product.getProductType());
        articleField.setText(product.getArticleNumber());
        descField.setText(product.getDescription());
        imagePathField.setText(product.getImagePath());
        personCountField.setText(String.valueOf(product.getPersonCount()));
        workshopField.setText(String.valueOf(product.getWorkshopNumber()));
        minAgentCostField.setText(String.valueOf(product.getMinimalCostForAgent()));


    }



}
