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
    private String routesString = "城市 / 起始时间 / 到达时间\n";
    JRadioButton week;
    JTextField daText;
    JTextField nameText;
    JRadioButton train;
    JRadioButton airplane;


    public AddTransport() {
        JFrame jf = new JFrame();
        jf.setTitle("添加交通");//标题
        jf.setSize(620, 580);//大小
        jf.setLocationRelativeTo(null);//居中
//        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jp = new JPanel();
        jp.setLayout(null);
        placeComponents(jf, jp);
        jf.setVisible(true);
    }

    private void placeComponents(JFrame jf, JPanel jp) {

        train = new JRadioButton("列车");
        train.setBounds(16, 20, 60, 25);
        airplane = new JRadioButton("飞机");
        airplane.setBounds(100, 20, 60, 25);
        ButtonGroup bg = new ButtonGroup();
        bg.add(train);
        bg.add(airplane);
        jp.add(train);
        jp.add(airplane);

        JLabel nameLabel = new JLabel("名字:");
        nameLabel.setBounds(20, 50, 500, 25);
        jp.add(nameLabel);
        nameText = new JTextField(20);
        nameText.setBounds(60, 50, 165, 25);
        jp.add(nameText);

        JLabel routeLabel = new JLabel("路线:");
        routeLabel.setBounds(20, 80, 500, 25);
        jp.add(routeLabel);
        JLabel cityLabel = new JLabel("城市");
        cityLabel.setBounds(20, 100, 500, 25);
        jp.add(cityLabel);
        JTextField city1Text = new JTextField(12);
        city1Text.setBounds(50, 100, 120, 25);
        jp.add(city1Text);
        JTextField city2Text = new JTextField(12);
        city2Text.setBounds(200, 100, 120, 25);
        jp.add(city2Text);


        JLabel timeLabel = new JLabel("开始(天时分)");
        timeLabel.setBounds(20, 150, 500, 25);
        jp.add(timeLabel);
        JTextField dayText = new JTextField(12);
        dayText.setBounds(100, 150, 20, 25);
        jp.add(dayText);
        JTextField hourText = new JTextField(12);
        hourText.setBounds(120, 150, 20, 25);
        jp.add(hourText);
        JTextField minuteText = new JTextField(12);
        minuteText.setBounds(140, 150, 20, 25);
        jp.add(minuteText);
        JLabel durationLabel = new JLabel("结束时间(天时分)");
        durationLabel.setBounds(170, 150, 500, 25);
        jp.add(durationLabel);
        JTextField ddayText = new JTextField(12);
        ddayText.setBounds(270, 150, 20, 25);
        jp.add(ddayText);
        JTextField dhourText = new JTextField(12);
        dhourText.setBounds(290, 150, 20, 25);
        jp.add(dhourText);
        JTextField dminuteText = new JTextField(12);
        dminuteText.setBounds(310, 150, 20, 25);
        jp.add(dminuteText);

        JTextField moneyText = new JTextField(12);
        moneyText.setBounds(20, 180, 100, 25);
        jp.add(moneyText);

        JButton addRouteButton = new JButton("添加");
        addRouteButton.setBounds(320, 180, 60, 25);
        jp.add(addRouteButton);
        addRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long startTime = Integer.parseInt(dayText.getText()) * 24 * 60 * 60 * 1000 +
                        Integer.parseInt(hourText.getText()) * 60 * 60 * 1000 +
                        Integer.parseInt(minuteText.getText()) * 60 * 1000;
                long endTime = Integer.parseInt(ddayText.getText()) * 24 * 60 * 60 * 1000 +
                        Integer.parseInt(dhourText.getText()) * 60 * 60 * 1000 +
                        Integer.parseInt(dminuteText.getText()) * 60 * 1000;
                float money = Float.parseFloat(moneyText.getText());
                City startCity = ProvinceData.getCity(city1Text.getText());
                City endCity = ProvinceData.getCity(city2Text.getText());

                Route route = new Route(startCity, endCity, startTime, endTime, money);
                routeList.add(route);
                routeListText.append(startCity.getCityName() + " 到 " + endCity.getCityName() + " " +
                        DateUtil.transferDay(startTime) + " " + DateUtil.transferDay(endTime) + " " + money + "元\n");

            }
        });

        routeListText = new JTextArea();
        routeListText.append(routesString);
        routeListText.setEditable(false);

        JScrollPane sp = new JScrollPane(routeListText);
        sp.setBounds(20, 220, 500, 200);
        jp.add(sp);

        week = new JRadioButton("星期");
        week.setBounds(16, 420, 60, 25);
        JRadioButton interval = new JRadioButton("间隔天数");
        interval.setBounds(100, 420, 100, 25);
        ButtonGroup b = new ButtonGroup();
        b.add(week);
        b.add(interval);
        jp.add(week);
        jp.add(interval);


        final JXDatePicker datepick1 = new JXDatePicker();
        datepick1.setDate(new Date());
        datepick1.setBounds(20, 450, 100, 24);
        jp.add(datepick1);

        final JXDatePicker datepick2 = new JXDatePicker();
        datepick2.setDate(new Date());
        datepick2.setBounds(150, 450, 100, 24);
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

        /**
         * 选择星期 输入： 1 3 5空格间隔
         * 选择间隔 输入一个数字： 2
         */
        daText = new JTextField(12);
        daText.setBounds(280, 450, 180, 25);
        jp.add(daText);


        JButton confirmButton = new JButton("确定");
        confirmButton.setBounds(200, 500, 60, 25);
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
        cancelButton.setBounds(280, 500, 60, 25);
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
                for (int i = 0; i < w.length; i++) {
                    if (dayForWeek(c) == w[i]) {
                        dateList.add(c.getTime());
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
