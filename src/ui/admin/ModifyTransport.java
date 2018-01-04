package ui.admin;

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
import java.text.DecimalFormat;

/**
 * Created by 江婷婷 on 2018/1/4.
 */
public class ModifyTransport {
    public ModifyTransport() {
        JFrame jf = new JFrame();
        jf.setTitle("修改交通");//标题
        jf.setSize(850, 320);//大小
        jf.setLocationRelativeTo(null);//居中
//        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jp = new JPanel();
        jp.setLayout(null);
        placeComponents(jf, jp);
        jf.setVisible(true);
        jf.setResizable(false);
    }

    private static void placeComponents(JFrame jf, JPanel jp) {

        JLabel userLabel = new JLabel("编号");
        userLabel.setBounds(50,10,100,25);
        jp.add(userLabel);
        JTextField userText = new JTextField(20);
        userText.setBounds(80,10,80,25);
        jp.add(userText);
        JButton transportButton = new JButton("确定");
        transportButton.setBounds(170, 10, 80, 25);
        jp.add(transportButton);
        JTextArea transportListText = new JTextArea();
        for (int i = 0; i < TransportData.transports.size(); i++) {
            transportListText.append(i + " " + TransportData.transports.get(i).getId() + "\n");
        }
        transportListText.setEditable(false);
        JScrollPane sp = new JScrollPane(transportListText);
        sp.setBounds(50, 50, 200, 200);
        jp.add(sp);



        JLabel indexLabel = new JLabel("编号");
        indexLabel.setBounds(300,10,100,25);
        jp.add(indexLabel);
        JTextField indexText = new JTextField(20);
        indexText.setBounds(330,10,60,25);
        jp.add(indexText);

        JLabel cityLabel = new JLabel("出发");
        cityLabel.setBounds(400,10,100,25);
        jp.add(cityLabel);
        JTextField cityText = new JTextField(20);
        cityText.setBounds(430,10,60,25);
        jp.add(cityText);

        JLabel timeLabel = new JLabel("到达");
        timeLabel.setBounds(500,10,100,25);
        jp.add(timeLabel);
        JTextField timeText = new JTextField(20);
        timeText.setBounds(530,10,60,25);
        jp.add(timeText);

        JLabel priceLabel = new JLabel("价格");
        priceLabel.setBounds(600,10,100,25);
        jp.add(priceLabel);
        JTextField priceText = new JTextField(20);
        priceText.setBounds(630,10,60,25);
        jp.add(priceText);


        JButton modifyButton = new JButton("修改");
        modifyButton.setBounds(700, 10, 80, 25);
        jp.add(modifyButton);
        JTextArea routeListText = new JTextArea();
//        for (int i = 0; i < TransportData.transports.size(); i++) {
//            transportListText.append(i + " " + TransportData.transports.get(i).getId() + "\n");
//        }
        routeListText.setEditable(false);
        JScrollPane spr = new JScrollPane(routeListText);
        spr.setBounds(300, 50, 480, 200);
        jp.add(spr);
        transportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                routeListText.setText("");
                int i = Integer.parseInt(userText.getText());
                int count = 0;
                for (Route r : TransportData.transports.get(i).getRoutes()) {
                    routeListText.append(count + "：" + r.getStartStation().getCityName() + "->" + r.getEndStation().getCityName()
                     + " " + DateUtil.transferDay(r.getStartTime()) + "->" + DateUtil.transferDay(r.getEndTime())
                     + " " + new DecimalFormat(".0").format(r.getPrice()) + "\n");
                    count++;
                }
            }
        });
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ti = Integer.parseInt(userText.getText());
                int i = Integer.parseInt(indexText.getText());
                String[] start = cityText.getText().split(" ");
                String[] end = timeText.getText().split(" ");
                long startTime = Integer.parseInt(start[0]) * 24 * 60 * 60 * 1000 +
                        Integer.parseInt(start[1]) * 60 * 60 * 1000 +
                        Integer.parseInt(start[2]) * 60 * 1000;
                long endTime = Integer.parseInt(end[0]) * 24 * 60 * 60 * 1000 +
                        Integer.parseInt(end[1]) * 60 * 60 * 1000 +
                        Integer.parseInt(end[2]) * 60 * 1000;
                float price = Float.parseFloat(priceText.getText());
                TransportData.transports.get(ti).getRoutes().get(i).setStartTime(startTime);
                TransportData.transports.get(ti).getRoutes().get(i).setEndTime(endTime);
                TransportData.transports.get(ti).getRoutes().get(i).setPrice(price);

                boolean flag = false;
                int cc;
                int pi;
                int ci;
                for (pi = 0; pi < ProvinceData.provinces.size(); pi++) {
                    for (ci = 0; ci < ProvinceData.provinces.get(pi).getCityList().size(); ci++) {
                        for (cc = 0; cc < ProvinceData.provinces.get(pi).getCityList().get(ci).getRouteList().size(); cc++) {
                            if (ProvinceData.provinces.get(pi).getCityList().get(ci).getRouteList().get(cc).getTransport().getId().equals(TransportData.transports.get(ti).getId())) {
                                flag = true;
                                break;
                            }
                        }
                        if (flag) {
                            ProvinceData.provinces.get(pi).getCityList().get(ci).getRouteList().get(cc).setStartTime(startTime);
                            ProvinceData.provinces.get(pi).getCityList().get(ci).getRouteList().get(cc).setEndTime(endTime);
                            ProvinceData.provinces.get(pi).getCityList().get(ci).getRouteList().get(cc).setPrice(price);
                            break;
                        }
                    }
                    if (flag) {
                        break;
                    }
                }

                routeListText.setText("");
                int count = 0;
                for (Route r : TransportData.transports.get(ti).getRoutes()) {
                    routeListText.append(count + "：" + r.getStartStation().getCityName() + "->" + r.getEndStation().getCityName()
                            + " " + DateUtil.transferDay(r.getStartTime()) + "->" + DateUtil.transferDay(r.getEndTime())
                            + " " + new DecimalFormat(".0").format(r.getPrice()) + "\n");
                    count++;
                }
                FileUtil.writeTransportData();
                FileUtil.writeProvinceData();

            }
        });

        jf.add(jp);
    }

}
