package ui.admin;

import data.savedata.ProvinceData;
import org.jdesktop.swingx.JXDatePicker;
import util.DateUtil;
import util.FileUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by 江婷婷 on 2018/1/4.
 */
public class AddCity {
    public AddCity() {
        JFrame jf = new JFrame();
        jf.setTitle("添加城市");//标题
        jf.setSize(600, 140);//大小
        jf.setLocationRelativeTo(null);//居中
//        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jp = new JPanel();
        jp.setLayout(null);
        placeComponents(jf, jp);
        jf.setVisible(true);
    }

    private static void placeComponents(JFrame jf, JPanel jp) {

        JLabel userLabel = new JLabel("省份：");
        userLabel.setBounds(20, 20, 100, 25);
        jp.add(userLabel);
        JTextField userText = new JTextField(20);
        userText.setBounds(60, 20, 165, 25);
        jp.add(userText);

        JLabel passwordLabel = new JLabel("市：");
        passwordLabel.setBounds(260, 20, 100, 25);
        jp.add(passwordLabel);
        JTextField passwordText = new JTextField(20);
        passwordText.setBounds(300, 20, 165, 25);
        jp.add(passwordText);

        JButton registerButton = new JButton("添加");
        registerButton.setBounds(480, 20, 80, 25);
        jp.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProvinceData.addCityAlways(userText.getText(), passwordText.getText());
                FileUtil.writeProvinceData();
            }
        });
        jf.add(jp);
    }
}
