package ui;

import ui.admin.AddCity;
import ui.admin.AddTransport;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 江婷婷 on 2018/1/3.
 */
public class AdminMenu {
    private String name;

    public AdminMenu(String name) {
        this.name = name;
        JFrame jf = new JFrame();
        jf.setTitle("管理员菜单");//标题
        jf.setSize(250, 450);//大小
        jf.setLocationRelativeTo(null);//居中
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jp = new JPanel();
        jp.setLayout(null);
        placeComponents(jf, jp);
        jf.setVisible(true);
    }

    private void placeComponents(JFrame jf, JPanel jp) {

        JLabel helloLabel = new JLabel(name + "您好，请选择：");
        helloLabel.setBounds(30,10,500,25);
        jp.add(helloLabel);

        int x = 30;

        JLabel accountLabel = new JLabel("账号管理：");
        accountLabel.setBounds(x,50,100,25);
        jp.add(accountLabel);
        JButton addAccountButton = new JButton("添加管理员");
        addAccountButton.setBounds(x, 80, 160, 25);
        jp.add(addAccountButton);
        JButton deleteAccountButton = new JButton("删除管理员");
        deleteAccountButton.setBounds(x, 110, 160, 25);
        jp.add(deleteAccountButton);

        JLabel cityLabel = new JLabel("城市管理：");
        cityLabel.setBounds(x,150,100,25);
        jp.add(cityLabel);
        JButton addCityButton = new JButton("添加城市");
        addCityButton.setBounds(x, 180, 160, 25);
        jp.add(addCityButton);
        addCityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCity();
            }
        });
        JButton deleteCityButton = new JButton("删除城市");
        deleteCityButton.setBounds(x, 210, 160, 25);
        jp.add(deleteCityButton);

        JLabel transportLabel = new JLabel("交通管理：");
        transportLabel.setBounds(x,250,100,25);
        jp.add(transportLabel);
        JButton addTransportButton = new JButton("添加航班或列车");
        addTransportButton.setBounds(x, 280, 160, 25);
        jp.add(addTransportButton);
        JButton changeTransportButton = new JButton("修改航班或列车");
        changeTransportButton.setBounds(x, 310, 160, 25);
        jp.add(changeTransportButton);
        JButton deleteTransportButton = new JButton("删除航班或列车");
        deleteTransportButton.setBounds(x, 340, 160, 25);
        jp.add(deleteTransportButton);

        addTransportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTransport();
            }
        });


//        datepick.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(DateUtil.transfer(datepick.getDate()));
//            }
//        });
//
//        JButton registerButton = new JButton("查询");
//        registerButton.setBounds(680, 20, 80, 25);
//        jp.add(registerButton);
//        registerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
        jf.add(jp);
    }

}
