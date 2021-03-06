package ui;

import data.model.*;
import data.savedata.AccountData;
import data.savedata.ProvinceData;
import data.savedata.TransportData;
import util.DateUtil;
import util.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by 江婷婷 on 2018/1/3.
 */
public class Main {
    public static void main(String[] args) {

//        AccountData.accountList.add(new Account("admin", "password", 1));
//        FileUtil.writeAccountData();

        FileUtil.readProvinceData();
        FileUtil.readTransportData();
        FileUtil.readAccountData();


//        print();

        new Thread(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("登录");
                frame.setSize(300, 200);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                JPanel panel = new JPanel();
                frame.add(panel);
                placeComponents(frame, panel);
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
            }
        }).start();

    }

    private static void placeComponents(JFrame frame, JPanel panel) {

        panel.setLayout(null);

        JLabel userLabel = new JLabel("账户：");
        userLabel.setBounds(30,20,80,25);
        panel.add(userLabel);
        JTextField userText = new JTextField(20);
        userText.setBounds(80,20,165,25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setBounds(30,60,80,25);
        panel.add(passwordLabel);
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(80,60,165,25);
        panel.add(passwordText);

        JButton loginButton = new JButton("登录");
        loginButton.setBounds(50, 120, 80, 25);
        panel.add(loginButton);
        JButton registerButton = new JButton("注册");
        registerButton.setBounds(140, 120, 80, 25);
        panel.add(registerButton);

        JLabel errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        errorLabel.setBounds(90,90,120,25);
        panel.add(errorLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = userText.getText();
                String password = passwordText.getText();
//                System.out.println(account + " " + password);
                int as = judgeAccount(account, password);
                if (as == 0) {
                    errorLabel.setText("登录成功");
                    panel.updateUI();
                    frame.dispose();
                    new Query();
                } else if (as == 2) {
                    errorLabel.setText("账户或密码有误");
                    panel.updateUI();
                } else {
                    errorLabel.setText("登录成功");
                    panel.updateUI();
                    frame.dispose();
                    new AdminMenu(account);
                }

            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                frame.setVisible(false);// 本窗口隐藏,
                new Register();// 新窗口显示
//                frame.dispose();
            }
        });


    }

    /**
     * 判断账户密码是否有效
     * @param account
     * @param password 0用户 1管理员 2失败
     * @return
     */
    private static int judgeAccount(String account, String password) {
        for (Account a : AccountData.accountList) {
            if(a.getName().equals(account) && a.getPassword().equals(password)) {
//                System.out.println("登录成功");
                return a.getType();
            }
        }
//        System.out.println("登录失败");
        return 2;
    }

    public static void print() {
        if (ProvinceData.provinces.size() == 0) {
            System.out.println("无省份");
        }
        for (Province p : ProvinceData.provinces) {
            System.out.print(p.getProvinceName() + " | ");
            for (City c: p.getCityList()) {
                System.out.print(c.getCityName() + " ");
            }
            System.out.println();
        }
        if (TransportData.transports.size() == 0) {
            System.out.println("无工具");
        }
        for (Transport t : TransportData.transports) {
            System.out.println(t.getId());
            for (Route r : t.getRoutes()) {
                System.out.println(r.getStartStation().getCityName() + "-" + r.getEndStation().getCityName() + "  "
                        + DateUtil.transferDay(r.getStartTime()) + " " + DateUtil.transferDay(r.getEndTime()) + " " + r.getPrice());
            }
        }
    }

}
