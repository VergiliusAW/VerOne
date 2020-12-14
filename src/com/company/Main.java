package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class Main {

    private static Connection connection;
    private static String url = "jdbc:mysql://localhost/DB_kursach?useUnicode=true&serverTimezone=UTC&useSSL=false";
    private static String user = "root";
    private static String password = "Vbif2017";
    private Statement stmt;
    private JFrame f;

    private DefaultTableModel modelShops;
    private JTable jtShops;

    private DefaultTableModel modelStorage;
    private JTable jtStorage;

    private JScrollPane shops;
    private JScrollPane storage;
    private JPanel file;
    private JPanel help;

    public void initConnection() {
        try {
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void createGUI() throws SQLException {
        initConnection();

        stmt = connection.createStatement();
        f=new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jtShops = new JTable(new DefaultTableModel());
        jtStorage = new JTable(new DefaultTableModel());
        shops = new JScrollPane(jtShops);
        storage = new JScrollPane(jtStorage);
        file = new JPanel();
        help = new JPanel();

        JTabbedPane tp = new JTabbedPane();
        tp.addTab("Магазины",shops);
        tp.addTab("Склад",storage);
        tp.addTab("Файл",file);
        tp.addTab("Помощь",help);
        f.add(tp);

        f.setSize(800,300);
        f.setVisible(true);

    }

    /**
     * Инициализация таблицы shops из базы данных
     * @throws SQLException
     */
    public void initShops() throws SQLException {
        modelShops = new DefaultTableModel();
        ResultSet rs = stmt.executeQuery("SELECT * FROM shops");
        ResultSetMetaData rsmd = rs.getMetaData();
        int col = rsmd.getColumnCount();
        for (int i=0;i<col;i++) {
            modelShops.addColumn(rsmd.getColumnLabel (i + 1));
        }
        int j=0;
        while (rs.next()) {
            Vector v = new Vector();
            for (int i=0;i<col;i++) {
                v.add(rs.getObject(i+1));
            }
            modelShops.insertRow(j,v);
            j++;
        }
        jtShops.setModel(modelShops);
    }
    /**
     * Инициализация таблицы storage из базы данных
     * @throws SQLException
     */
    public void initStorage() throws SQLException {
        modelStorage = new DefaultTableModel();
        ResultSet rs = stmt.executeQuery("SELECT idStorage,idProduct,name,city,price FROM storage,products WHERE idProduct=products_idProduct GROUP BY idStorage;");
        ResultSetMetaData rsmd = rs.getMetaData();
        int col = rsmd.getColumnCount();
        for (int i=0;i<col;i++) {
            modelStorage.addColumn(rsmd.getColumnLabel (i + 1));
        }
        int j=0;
        while (rs.next()) {
            Vector v = new Vector();
            for (int i=0;i<col;i++) {
                v.add(rs.getObject(i+1));
            }
            modelStorage.insertRow(j,v);
            j++;
        }
        jtStorage.setModel(modelStorage);
    }

    public void initFile() {
        Button b1 = new Button("Приём товара на склад");
        ActionListener b1l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame b1lf = new JFrame("Приём товара на склад");
                b1lf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel pane = new JPanel(new GridBagLayout());
                b1lf.add(pane);

                GridBagConstraints constr = new GridBagConstraints();
                constr.insets = new Insets(5, 5, 5, 5);
                constr.anchor = GridBagConstraints.WEST;

                constr.gridx=0;
                constr.gridy=0;

                Label l1 = new Label("Название товара: ");
                Label l2 = new Label("Город производства товара: ");
                Label l3 = new Label("Цена: ");

                TextField t1 = new TextField(10);
                TextField t2 = new TextField(10);
                TextField t3 = new TextField(10);

                pane.add(l1,constr);
                constr.gridy=1;
                pane.add(l2,constr);
                constr.gridy=2;
                pane.add(l3,constr);

                constr.gridx=1;
                constr.gridy=0;
                pane.add(t1,constr);
                constr.gridy=1;
                pane.add(t2,constr);
                constr.gridy=2;
                pane.add(t3,constr);

                Button add = new Button("Принять товар");
                ActionListener actionListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name=t1.getText(),city=t2.getText(),price=t3.getText();
                        try {
                            ResultSet rs = stmt.executeQuery("INSERT ");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                };
                add.addActionListener(actionListener);
                constr.gridx=0;
                constr.gridy=3;
                pane.add(add,constr);

                b1lf.setSize(300,200);
                b1lf.setVisible(true);
            }
        } ;
        b1.addActionListener(b1l);
        Button b2 = new Button("Приём товара в магазин");
        ActionListener b2l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame b2lf = new JFrame("Приём товара в магазин");
                b2lf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                b2lf.setSize(300,200);
                b2lf.setVisible(true);
            }
        };
        b2.addActionListener(b2l);
        Button b3 = new Button("Продажа товара магазином");
        ActionListener b3l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame b3lf = new JFrame("Продажа товара магазином");
                b3lf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                b3lf.setSize(300,200);
                b3lf.setVisible(true);
            }
        };
        b3.addActionListener(b3l);
        Button b4 = new Button("Отпуск товара со склада магазину");
        ActionListener b4l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame b4lf = new JFrame("Отпуск товара со склада магазину");
                b4lf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                b4lf.setSize(300,200);
                b4lf.setVisible(true);
            }
        };
        b4.addActionListener(b4l);
        Button b5 = new Button("Получение справки о наличии товара на складе");
        ActionListener b5l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame b5lf = new JFrame("Получение справки о наличии товара на складе");
                b5lf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Container container = b5lf.getContentPane();
                container
                        .setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

                Label pr = new Label("Введите название товара");
                TextField tpr = new TextField(10);
                Label check = new Label();
                Button bpr = new Button("Проверить наличие");
                ActionListener bprl = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String pname = tpr.getText();
                        try {
                            ResultSet rs = stmt.executeQuery("SELECT EXISTS(SELECT idStorage FROM storage WHERE products_idProduct=(SELECT idProduct FROM products WHERE name=\""+pname+"\"))");
                            rs.next();
                            ResultSetMetaData rsmd = rs.getMetaData();
                            if (rs.getInt(1)==0) {
                                check.setText("Товара с таким названием на складе нет");
                            } else {
                                check.setText("Товар есть на складе");
                            }
                           //
                            System.out.println(rsmd.getColumnName(1));
                        } catch (Exception throwables) {
                            throwables.printStackTrace();
                        }
                    }
                };
                bpr.addActionListener(bprl);

                b5lf.add(pr,container);
                b5lf.add(tpr,container);
                b5lf.add(bpr,container);
                b5lf.add(check,container);

                b5lf.setSize(400,200);
                b5lf.setVisible(true);
            }
        };
        b5.addActionListener(b5l);
        Button b6 = new Button("Получение справки о наличии товара в магазине или во всей торговой сети");
        ActionListener b6l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame b6lf = new JFrame("Получение справки о наличии товара в магазине или во всей торговой сети");
                b6lf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                Container container = b6lf.getContentPane();
                container
                        .setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

                JPanel pane = new JPanel();
                b6lf.add(pane,container);
                Label pr = new Label("Введите название товара");
                pr.setSize(100,20);
                TextField tpr = new TextField(10);
                tpr.setSize(100,20);
                Button bpr = new Button("Проверить наличие");
                bpr.setSize(100,20);
                Label c = new Label("Номера магазинов в которых есть данный товар:");
                Vector<Label> v = new Vector();
                ActionListener bprl = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < v.size(); i++) {
                            b6lf.remove(v.get(i));
                        }
                        v.clear();
                        String pname = tpr.getText();
                        try {
                            ResultSet rs = stmt.executeQuery("SELECT shops_idShop FROM shops_has_products WHERE products_idProduct=(SELECT idProduct FROM products WHERE name=\"" + pname + "\")");
                            ResultSetMetaData rsmd = rs.getMetaData();
                            int col = rsmd.getColumnCount();
                            int j = 0;

                            while (rs.next()) {
                                v.add(new Label(rs.getString(1)));
                                j++;
                            }
                            c.setVisible(true);
                            for (int i = 0; i < v.size(); i++) {
                                b6lf.add(v.get(i),container);
                                System.out.println(v.get(i).getText() + " " + j);
                            }
                           // b6lf.add(p,);
                            b6lf.pack();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                };
                bpr.addActionListener(bprl);

                pane.add(pr,container);
                pane.add(tpr,container);
                pane.add(bpr,container);
                c.setVisible(false);
                b6lf.add(c,container);


                b6lf.setSize(300,200);
                b6lf.setVisible(true);
            }
        };
        b6.addActionListener(b6l);
        Button b7 = new Button("Формирование заказа магазина на поставку товара со склада");
        ActionListener b7l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame b7lf = new JFrame("Формирование заказа магазина на поставку товара со склада");
                b7lf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                b7lf.setSize(300,200);
                b7lf.setVisible(true);
            }
        };
        b7.addActionListener(b7l);
        //
        file.add(b1);
        file.add(b2);
        file.add(b3);
        file.add(b4);
        file.add(b5);
        file.add(b6);
        file.add(b7);
    }

    public void initHelp() {

    }

    Main() throws SQLException {
        //Инициализация GUI
        createGUI();
        //Вкладка "Магазины"
        initShops();
        //Вкладка "Склад"
        initStorage();
        //Вкладка "Файл"
        initFile();
        //Вкладка "Помощь"
        initHelp();
    }

    public static void main(String[] args) throws SQLException {
	// write your code here
        new Main();
    }
}
