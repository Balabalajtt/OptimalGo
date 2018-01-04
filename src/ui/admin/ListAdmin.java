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
    jf.setSize(300, 220);//大小
    jf.setLocationRelativeTo(null);//居中
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    JPanel jp = new JPanel();
    jp.setLayout(null);
    placeComponents(jf, jp);
    jf.setVisible(true);
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
        sp.setBounds(20, 20, 50, 200);
        jp.add(sp);

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
