package com.company;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowOne extends JFrame {

    private JPanel contentPane;
    private JTabbedPane tabbedPane1;
    private JPanel shops;
    private JPanel storage;
    private JPanel file;
    private JPanel help;
    private JButton b1;
    private JButton приёмТовараВМагазинButton;
    private JButton продажаТовараМагазиномButton;
    private JButton отпускТовараСоСкладаButton;
    private JButton получениеСправкиОНаличииButton;
    private JButton получениеСправкиОНаличииButton2;
    private JButton другиеСправкиButton;
    private JButton формированиеЗаказаМагазинаНаButton;
    private JTextField textField1;

    public WindowOne() {
        setContentPane(contentPane);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String data[][]={ {"101","Amit","670000"},
//                        {"102","Jai","780000"},
//                        {"101","Sachin","700000"}};
//                String column[]={"ID","NAME","SALARY"};
//                table1.setBounds(30,40,200,300);
//                table1 = new JTable(data,column);
//                JScrollPane sp=new JScrollPane(table1);
//                shops.add(table1);
                String data[][]={ {"101","Amit","670000"},
                        {"102","Jai","780000"},
                        {"101","Sachin","700000"}};
                String column[]={"ID","NAME","SALARY"};
                JTable jt=new JTable(data,column);
                jt.setBounds(30,40,200,300);
                JScrollPane sp=new JScrollPane(jt);
                shops.add(sp);

            }
        });
    }
    public static void main(String[] args) {
        WindowOne w1 = new WindowOne();
        w1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // выход из приложения при закрытии окна
        w1.pack();
        w1.setVisible(true);
    }
}
