package ui;

import data.model.Account;
import data.model.Route;
import data.model.TotalPlan;
import data.savedata.AccountData;
import data.savedata.ProvinceData;
import org.jdesktop.swingx.JXDatePicker;
import util.Algorithm;
import util.DateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by 江婷婷 on 2018/1/3.
 */
public class Query {
    private JTextArea plansText;
    public Query() {
        JFrame jf = new JFrame();
        jf.setTitle("交通查询");//标题
        jf.setSize(660, 800);//大小
        jf.setLocationRelativeTo(null);//居中
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jp = new JPanel();
        jp.setLayout(null);
        placeComponents(jf, jp);
        jf.setVisible(true);
    }

    private void placeComponents(JFrame jf, JPanel jp) {

        JLabel userLabel = new JLabel("起点：");
        userLabel.setBounds(20, 20, 100, 25);
        jp.add(userLabel);
        JTextField userText = new JTextField(20);
        userText.setBounds(60, 20, 80, 25);
        jp.add(userText);

        JLabel passwordLabel = new JLabel("终点：");
        passwordLabel.setBounds(160, 20, 100, 25);
        jp.add(passwordLabel);
        JTextField passwordText = new JTextField(20);
        passwordText.setBounds(200, 20, 80, 25);
        jp.add(passwordText);

        final JXDatePicker datepick = new JXDatePicker();
        datepick.setDate(new Date());
        datepick.setBounds(320, 20, 177, 24);
        jp.add(datepick);

        datepick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(DateUtil.transfer(datepick.getDate()));
            }
        });

        JButton registerButton = new JButton("查询");
        registerButton.setBounds(520, 20, 80, 25);
        jp.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Algorithm.sortPlansByZhuancheng(ProvinceData.getCity("安庆"), ProvinceData.getCity("西安"));
                System.out.println(Algorithm.plans.size());
                for (TotalPlan t : Algorithm.plans) {
                    plansText.append("1:");
                    for (Route r : t.getRouteList()) {
                        plansText.append(r.getTransport().getId() + " " + r.getStartStation().getCityName() + " " +
                                r.getEndStation().getCityName() + " ");
                    }
                    plansText.append("\n");
                }
            }
        });

        plansText = new JTextArea();

        plansText.setEditable(false);
        JScrollPane sp = new JScrollPane(plansText);
        sp.setBounds(20, 120, 600, 500);
        jp.add(sp);


        jf.add(jp);
    }

}