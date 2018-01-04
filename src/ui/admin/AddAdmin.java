package ui.admin;

import data.model.Account;
import data.savedata.AccountData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 江婷婷 on 2018/1/4.
 */
public class AddAdmin {
    public AddAdmin() {
        JFrame jf = new JFrame();
        jf.setTitle("添加管理员账号");
        jf.setSize(300, 220);
        jf.setLocationRelativeTo(null);
        JPanel jp = new JPanel();
        jp.setLayout(null);
        placeComponents(jf, jp);
        jf.setVisible(true);
        jf.setResizable(false);
    }

    private static void placeComponents(JFrame jf, JPanel jp) {

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

        JButton registerButton = new JButton("添加");
        registerButton.setBounds(50, 130, 80, 25);
        jp.add(registerButton);
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(150, 130, 80, 25);
        jp.add(cancelButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = userText.getText();
                String password1 = passwordText.getText();
                String password2 = password2Text.getText();
                if (!password1.equals(password2) || !AccountData.addAdminAcount(account, password1)) {
                    errorLabel.setText("添加失败");
                } else {
                    errorLabel.setText("添加成功");
                    jf.dispose();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
            }
        });



        jf.add(jp);
    }


}

