package ru.chervy.demoexam.ui;

import ru.chervy.demoexam.Application;
import ru.chervy.demoexam.entity.ProductEntity;
import ru.chervy.demoexam.manager.ProductEntityManager;
import ru.chervy.demoexam.util.BaseForm;
import ru.chervy.demoexam.util.CustomTableModel;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class ProductEntityForm extends BaseForm
{
    private CustomTableModel<ProductEntity> model;

    private JPanel mainPanel;
    private JButton addButton;
    private JTable table;

    public ProductEntityForm() {
        super(1200, 800);
        setContentPane(mainPanel);
        initTable();
        initButton();
        setVisible(true);
    }

    private void initTable()
    {
        try {
            model = new CustomTableModel<>(
                            ProductEntity.class,
                            new String[]{
                                "ID", "Наименование", "Тип", "Артикль","Описание",
                                "Путь до изображения", "Количество людей для производства",
                                "Номер цеха производства", "Минимальная цена для агента",
                                "Изображение"},
                            ProductEntityManager.selectAll());
            table.setModel(model);
            table.getTableHeader().setReorderingAllowed(false);
            TableColumn column = table.getColumn("Путь до изображения");
            column.setMaxWidth(0);
            column.setMinWidth(0);
            column.setPreferredWidth(0);
            table.setRowHeight(50);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(Application.ADMIN_MODE)
        {
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getClickCount() == 2)
                    {
                        int row =table.rowAtPoint(e.getPoint());
                        if(row != -1)
                        {
                            dispose();
                            new ProductEditForm(model.getRows().get(row));
                        }
                    }
                }


            });
        }
    }

    private void initButton()
    {
        addButton.addActionListener(e -> {
            dispose();
            new ProductCreateForm();
        });
    }
}
