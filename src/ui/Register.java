package ui;

import data.model.Account;
import data.savedata.AccountData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 江婷婷 on 2018/1/3.
 */
public class Register {

    public Register() {
        JFrame jf = new JFrame();
        jf.setTitle("用户注册");//标题
        jf.setSize(300, 220);//大小
        jf.setLocationRelativeTo(null);//居中
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jp = new JPanel();
        jp.setLayout(null);
        placeComponents(jf, jp);
        jf.setVisible(true);
    }

    private static void placeComponents(JFrame jf, JPanel jp) {
        JLabel jl1 = new JLabel("请输入信息注册账户");
        jp.add(jl1);

        JLabel userLabel = new JLabel("账户：");
        userLabel.setBounds(20,20,100,25);
        jp.add(userLabel);
        JTextField userText = new JTextField(20);
        userText.setBounds(80,20,165,25);
        jp.add(userText);

        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setBounds(20,50,100,25);
        jp.add(passwordLabel);
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(80,50,165,25);
        jp.add(passwordText);

        JLabel password2Label = new JLabel("确认密码：");
        password2Label.setBounds(10,80,100,25);
        jp.add(password2Label);
        JPasswordField password2Text = new JPasswordField(20);
        password2Text.setBounds(80,80,165,25);
        jp.add(password2Text);

        JLabel errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        errorLabel.setBounds(90,100,120,25);
        jp.add(errorLabel);

        JButton registerButton = new JButton("注册");
        registerButton.setBounds(100, 130, 80, 25);
        jp.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = userText.getText();
                String password = passwordText.getText();
                String password2 = password2Text.getText();
                if (judgeAccountRegister(account, password, password2)) {
                    errorLabel.setText("注册成功");
                    jp.updateUI();
                    addAccount(new Account(account, password));
                    jf.dispose();

                } else {
                    errorLabel.setText("输入信息有误");
                    jp.updateUI();
                }
            }
        });
        jf.add(jp);
    }

    private static void addAccount(Account account) {
        AccountData.accountList.add(account);
    }

    private static boolean judgeAccountRegister(String account, String password, String password2) {
        for(Account a : AccountData.accountList) {
            if (account.equals(a.getName())) {
                return false;
            }
        }
        if (!password.equals(password2)) {
            return false;
        }
        return true;

    }

}
