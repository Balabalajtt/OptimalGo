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
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by 江婷婷 on 2018/1/3.
 */
public class Query {
    private JTextArea plansText;
    private JXDatePicker datepick;
    public Query() {
        JFrame jf = new JFrame();
        jf.setTitle("交通查询");//标题
        jf.setSize(660, 420);
        jf.setLocationRelativeTo(null);
        JPanel jp = new JPanel();
        jp.setLayout(null);
        placeComponents(jf, jp);
        jf.setVisible(true);
        jf.setResizable(false);
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

        datepick = new JXDatePicker();
        datepick.setDate(new Date());
        datepick.setBounds(320, 20, 177, 24);
        jp.add(datepick);

        datepick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(DateUtil.transfer(datepick.getDate()));
            }
        });

        JButton moneyButton = new JButton("费用优先");
        moneyButton.setBounds(60, 80, 100, 25);
        moneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plansText.setText("");
                Algorithm.sortPlansByMoney(ProvinceData.getCity(userText.getText()), ProvinceData.getCity(passwordText.getText()), datepick.getDate());
                output();
            }
        });
        jp.add(moneyButton);
        jf.add(jp);

        JButton timeButton = new JButton("时间优先");
        timeButton.setBounds(180, 80, 100, 25);
        timeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plansText.setText("");
                Algorithm.sortPlansByTime(ProvinceData.getCity(userText.getText()), ProvinceData.getCity(passwordText.getText()), datepick.getDate());
                output();
            }
        });
        jp.add(timeButton);
        jf.add(jp);

        JButton zhuanchengButton = new JButton("转乘优先");
        zhuanchengButton.setBounds(300, 80, 100, 25);
        zhuanchengButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plansText.setText("");
                Algorithm.sortPlansByZhuancheng(ProvinceData.getCity(userText.getText()), ProvinceData.getCity(passwordText.getText()), datepick.getDate());
                output();
            }
        });
        jp.add(zhuanchengButton);
        jf.add(jp);


        JButton registerButton = new JButton("查询");
        registerButton.setBounds(520, 20, 80, 25);
        jp.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plansText.setText("");
                Algorithm.sortPlansByStartTime(ProvinceData.getCity(userText.getText()), ProvinceData.getCity(passwordText.getText()), datepick.getDate());
                output();

            }
        });

        plansText = new JTextArea();
        plansText.setEditable(false);
        JScrollPane sp = new JScrollPane(plansText);
        sp.setBounds(20, 120, 600, 220);
        jp.add(sp);


        jf.add(jp);
    }

    private void output() {
        System.out.println(Algorithm.plans.size());
        if (Algorithm.plans.size() == 0) {
            plansText.append("暂无方案");
        }
        int k = 0;
        for (TotalPlan t : Algorithm.plans) {
            Date sd = new Date();
            Date ed = new Date();
            sd.setTime(t.getStartTime());
            ed.setTime(t.getEndTime());
            System.out.println(t.getEndTime());
            k++;
            plansText.append("方案" + k + "：" + DateUtil.transfer(sd) + "--" + DateUtil.transfer(ed) + "\n               ");
            for (int i = 0; i < t.getRouteList().size(); i++) {
                Route r = t.getRouteList().get(i);
                //起始或转乘
                if (i == 0 || !r.getTransport().getId().equals(t.getRouteList().get(i - 1).getTransport().getId())) {
                    if(i != 0) {
                        plansText.append(")\n               --转乘--  \n               ");
                    }
                    plansText.append(r.getTransport().getId() + " (");
                    plansText.append(r.getStartStation().getCityName() + " " +
                            r.getEndStation().getCityName() + " ");
                } else {
                    plansText.append(r.getEndStation().getCityName() + " ");
                }
            }
            plansText.append(")\n               总价" + new DecimalFormat(".0").format(t.getTotalPrice()) + "元       耗时");
            plansText.append(DateUtil.transferDDay(t.getDuration()) + "       " + t.getTransferNumber() + "次转乘\n");
            plansText.append("\n");
        }
    }

}