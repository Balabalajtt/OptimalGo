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
public class ListAdmin {
    public ListAdmin() {
        JFrame jf = new JFrame();
        jf.setTitle("查看账号");//标题
        jf.setSize(260, 280);//大小
        jf.setLocationRelativeTo(null);//居中
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jp = new JPanel();
        jp.setLayout(null);
        placeComponents(jf, jp);
        jf.setVisible(true);
        jf.setResizable(false);
    }

    private static void placeComponents(JFrame jf, JPanel jp) {

        JTextArea accountListText = new JTextArea();
        for (Account a : AccountData.accountList) {
            String type = "";
            if (a.getType() == 0) {
                type = "普通用户";
            } else {
                type = "管理员";
            }
            accountListText.append(a.getName() + "（" + type + ")\n");
        }
        accountListText.setEditable(false);

        JScrollPane sp = new JScrollPane(accountListText);
        sp.setBounds(20, 20, 200, 200);
        jp.add(sp);

        jf.add(jp);
    }


}
