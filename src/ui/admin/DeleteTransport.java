package ui.admin;

import data.model.City;
import data.model.Province;
import data.model.Route;
import data.model.Transport;
import data.savedata.AccountData;
import data.savedata.ProvinceData;
import data.savedata.TransportData;
import util.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 * Created by 江婷婷 on 2018/1/4.
 */
public class DeleteTransport {
    static JTextArea transportListText;
    public DeleteTransport() {
//        print();
        JFrame jf = new JFrame();
        jf.setTitle("删除列车或飞机");//标题
        jf.setSize(260, 320);//大小
        jf.setLocationRelativeTo(null);//居中
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jp = new JPanel();
        jp.setLayout(null);
        placeComponents(jf, jp);
        jf.setVisible(true);
        jf.setResizable(false);
    }

    private static void placeComponents(JFrame jf, JPanel jp) {

        JLabel userLabel = new JLabel("编号");
        userLabel.setBounds(20, 10, 120, 25);
        jp.add(userLabel);
        JTextField userText = new JTextField(20);
        userText.setBounds(50, 10, 70, 25);
        jp.add(userText);

        JButton registerButton = new JButton("删除");
        registerButton.setBounds(140, 10, 80, 25);
        jp.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int transportIndex = Integer.parseInt(userText.getText());

                for (Province p : ProvinceData.provinces) {
                    for (City c : p.getCityList()) {
                        int i;
                        boolean f = false;
                        for (i = 0; i < c.getRouteList().size(); i++) {
                            if (c.getRouteList().get(i).getTransport().getId().
                                    equals(TransportData.transports.get(transportIndex).getId())) {
                                f = true;
                                break;
                            }
                        }
                        if (f) {
                            c.getRouteList().remove(i);
                        }
                    }
                }
                TransportData.transports.remove(transportIndex);
                transportListText.setText("");
                for (int i = 0; i < TransportData.transports.size(); i++) {
                    transportListText.append(i + "：" + TransportData.transports.get(i).getId() + "\n");
                }
                FileUtil.writeProvinceData();
                FileUtil.writeTransportData();
//                print();
            }
        });


        transportListText = new JTextArea();
        for (int i = 0; i < TransportData.transports.size(); i++) {
            transportListText.append(i + "：" + TransportData.transports.get(i).getId() + "\n");
        }
        transportListText.setEditable(false);

        JScrollPane sp = new JScrollPane(transportListText);
        sp.setBounds(20, 50, 200, 200);
        jp.add(sp);
        jf.add(jp);
    }

    public static void print() {
//        if (ProvinceData.provinces.size() == 0) {
//            System.out.println("无省份");
//        }
        for (Province p : ProvinceData.provinces) {
//            System.out.print(p.getProvinceName() + " | ");
            for (City c: p.getCityList()) {
                System.out.print(c.getCityName() + " ");
                for (Route r : c.getRouteList()) {
                    System.out.println(r.getStartStation().getCityName() + " " + r.getEndStation().getCityName() + " " + r.getTransport().getId());
                }
            }
            System.out.println();
        }
    }
}
