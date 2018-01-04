package ui.admin;

import data.model.City;
import data.model.Route;
import data.model.Transport;
import data.savedata.ProvinceData;
import data.savedata.TransportData;
import org.jdesktop.swingx.JXDatePicker;
import util.DateUtil;
import util.FileUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 添加一列车或一个航班 transport
 * Created by 江婷婷 on 2018/1/3.
 */
public class AddTransport {

    private List<Route> routeList = new ArrayList<>();
    private Date startDate;
    private Date endDate;
    private List<Date> dateList = new ArrayList<>();

    private JTextArea routeListText;
    private String routesString = "   出发城市 / 目的城市 / 出发时间 / 到达时间 / 价钱\n";
    JRadioButton week;
    JTextField daText;
    JTextField nameText;
    JRadioButton train;
    JRadioButton airplane;


    public AddTransport() {
        JFrame jf = new JFrame();
        jf.setTitle("添加交通");//标题
        jf.setSize(560, 560);//大小
        jf.setLocationRelativeTo(null);//居中
        JPanel jp = new JPanel();
        jp.setLayout(null);
        placeComponents(jf, jp);
        jf.setVisible(true);
        jf.setResizable(false);
    }

    private void placeComponents(JFrame jf, JPanel jp) {

        train = new JRadioButton("列车");
        train.setBounds(16, 20, 60, 25);
        airplane = new JRadioButton("飞机");
        airplane.setBounds(80, 20, 60, 25);
        ButtonGroup bg = new ButtonGroup();
        bg.add(train);
        bg.add(airplane);
        jp.add(train);
        jp.add(airplane);

        JLabel nameLabel = new JLabel("名字");
        nameLabel.setBounds(150, 20, 500, 25);
        jp.add(nameLabel);
        nameText = new JTextField(20);
        nameText.setBounds(180, 20, 120, 25);
        jp.add(nameText);

        JLabel routeLabel = new JLabel("添加路线");
        routeLabel.setBounds(20, 70, 500, 25);
        jp.add(routeLabel);
        JLabel cityLabel = new JLabel("城市");
        cityLabel.setBounds(20, 100, 500, 25);
        jp.add(cityLabel);
        JTextField city1Text = new JTextField(12);
        city1Text.setBounds(50, 100, 60, 25);
        jp.add(city1Text);
        JTextField city2Text = new JTextField(12);
        city2Text.setBounds(110, 100, 60, 25);
        jp.add(city2Text);


        JLabel timeLabel = new JLabel("时刻");
        timeLabel.setBounds(185, 100, 500, 25);
        jp.add(timeLabel);
        JTextField startTimeText = new JTextField(12);
        startTimeText.setBounds(215, 100, 60, 25);
        jp.add(startTimeText);
        JTextField endTimeText = new JTextField(12);
        endTimeText.setBounds(275, 100, 60, 25);
        jp.add(endTimeText);

        JLabel moneyLabel = new JLabel("价格");
        moneyLabel.setBounds(350, 100, 500, 25);
        jp.add(moneyLabel);
        JTextField moneyText = new JTextField(12);
        moneyText.setBounds(380, 100, 60, 25);
        jp.add(moneyText);

        JButton addRouteButton = new JButton("添加");
        addRouteButton.setBounds(460, 100, 60, 25);
        jp.add(addRouteButton);
        addRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] startText = startTimeText.getText().split(" ");
                String[] endText = endTimeText.getText().split(" ");
                long startTime = Integer.parseInt(startText[0]) * 24 * 60 * 60 * 1000 +
                        Integer.parseInt(startText[1]) * 60 * 60 * 1000 +
                        Integer.parseInt(startText[2]) * 60 * 1000;
                long endTime = Integer.parseInt(endText[0]) * 24 * 60 * 60 * 1000 +
                        Integer.parseInt(endText[1]) * 60 * 60 * 1000 +
                        Integer.parseInt(endText[2]) * 60 * 1000;
                float money = Float.parseFloat(moneyText.getText());
                City startCity = ProvinceData.getCity(city1Text.getText());
                City endCity = ProvinceData.getCity(city2Text.getText());

                Route route = new Route(startCity, endCity, startTime, endTime, money);
                routeList.add(route);
                routeListText.append("   " + startCity.getCityName() + "-->" + endCity.getCityName() + "   " +
                        DateUtil.transferDay(startTime) + "-->" + DateUtil.transferDay(endTime) + "   " + money + "元\n");

            }
        });

        routeListText = new JTextArea();
        routeListText.append(routesString);
        routeListText.setEditable(false);

        JScrollPane sp = new JScrollPane(routeListText);
        sp.setBounds(20, 150, 500, 200);
        jp.add(sp);
        JLabel zhouqiLabel = new JLabel("添加周期");
        zhouqiLabel.setBounds(20, 375, 500, 25);
        jp.add(zhouqiLabel);
        week = new JRadioButton("星期");
        week.setBounds(16, 402, 60, 25);
        JRadioButton interval = new JRadioButton("间隔天数");
        interval.setBounds(80, 402, 80, 25);
        ButtonGroup b = new ButtonGroup();
        b.add(week);
        b.add(interval);
        jp.add(week);
        jp.add(interval);

        /**
         * 选择星期 输入： 1 3 5空格间隔
         * 选择间隔 输入一个数字： 2
         */
        daText = new JTextField(12);
        daText.setBounds(160, 405, 120, 25);
        jp.add(daText);


        final JXDatePicker datepick1 = new JXDatePicker();
        datepick1.setDate(new Date());
        datepick1.setBounds(310, 405, 100, 24);
        jp.add(datepick1);

        final JXDatePicker datepick2 = new JXDatePicker();
        datepick2.setDate(new Date());
        datepick2.setBounds(420, 405, 100, 24);
        jp.add(datepick2);
        datepick1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startDate = datepick2.getDate();
            }
        });
        datepick2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endDate = datepick2.getDate();
            }
        });


        JButton confirmButton = new JButton("确定");
        confirmButton.setBounds(140, 460, 120, 25);
        jp.add(confirmButton);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transport t = newTransport();

                TransportData.transports.add(t);
                FileUtil.writeTransportData();
                FileUtil.writeProvinceData();
                jf.dispose();
            }
        });

        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(280, 460, 120, 25);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
            }
        });
        jp.add(cancelButton);
        jf.add(jp);

    }


    private Transport newTransport() {
        String id;
        int type;//1:飞机 2:火车
        id = nameText.getText();
        if (train.isSelected()) {
            type = 1;
        } else {
            type = 2;
        }
        initDateList();
        return new Transport(id, type, routeList, dateList);

    }


    private void initDateList() {
        if (week.isSelected()) {
            String[] s = daText.getText().split(" ");
            int[] w = new int[s.length];
            for (int i = 0; i < s.length; i++) {
                w[i] = Integer.parseInt(s[i]);
            }
            Calendar c = Calendar.getInstance();
            c.setTime(startDate);
            Calendar end = Calendar.getInstance();
            end.setTime(endDate);
            while (!c.after(end)) {
                for (int aW : w) {
                    if (dayForWeek(c) == aW) {
                        dateList.add(c.getTime());
                        System.out.println("****");
                        System.out.println(DateUtil.transfer(c.getTime()));
                        break;
                    }
                }
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            }
        } else {
            int d = Integer.parseInt(daText.getText());
            Calendar c = Calendar.getInstance();
            c.setTime(startDate);
            Calendar end = Calendar.getInstance();
            end.setTime(endDate);
            while (!c.after(end)) {
                dateList.add(c.getTime());
                c.add(Calendar.DAY_OF_MONTH, d);// 今天+1天
            }
        }
    }


    private static int dayForWeek(Calendar c) {
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

}
