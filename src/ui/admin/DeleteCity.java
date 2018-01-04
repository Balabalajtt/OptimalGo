package ui.admin;

import data.model.*;
import data.savedata.AccountData;
import data.savedata.ProvinceData;
import data.savedata.TransportData;
import util.DateUtil;
import util.FileUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;


/**
 * Created by 江婷婷 on 2018/1/4.
 */
public class DeleteCity {
    static JTextArea cityListText;
    public DeleteCity() {
//        print();
        JFrame jf = new JFrame();
        jf.setTitle("删除城市");//标题
        jf.setSize(280, 320);//大小
        jf.setLocationRelativeTo(null);//居中
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jp = new JPanel();
        jp.setLayout(null);
        placeComponents(jf, jp);
        jf.setVisible(true);
        jf.setResizable(false);
    }

    private static void placeComponents(JFrame jf, JPanel jp) {

        JLabel userLabel = new JLabel("省号");
        userLabel.setBounds(20, 10, 100, 25);
        jp.add(userLabel);
        JTextField userText = new JTextField(20);
        userText.setBounds(50, 10, 40, 25);
        jp.add(userText);

        JLabel passwordLabel = new JLabel("市号");
        passwordLabel.setBounds(100, 10, 100, 25);
        jp.add(passwordLabel);
        JTextField passwordText = new JTextField(20);
        passwordText.setBounds(130, 10, 40, 25);
        jp.add(passwordText);

        JButton registerButton = new JButton("删除");
        registerButton.setBounds(180, 10, 60, 25);
        jp.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int provinceIndex = Integer.parseInt(userText.getText());
                int cityIndex = Integer.parseInt(passwordText.getText());

                City city = ProvinceData.provinces.get(provinceIndex).getCityList().get(cityIndex);
                //删含有该城市的Transport
                Iterator<Transport> it = TransportData.transports.iterator();
                while(it.hasNext()){
                    Transport t = it.next();
                    boolean flag = false;
                    //判断该t是否经过city
                    for (Route r : t.getRoutes()) {
                        //此城市
                        if (r.getStartStation().getCityName().equals(city.getCityName())) {
                            flag = true;
                            break;
                        }
                    }
                    //t经过
                    if (flag) {
                        for (Province p : ProvinceData.provinces) {
                            for (City c : p.getCityList()) {
                                int i;
                                boolean f = false;
                                for (i = 0; i < c.getRouteList().size(); i++) {
                                    if (c.getRouteList().get(i).getTransport().getId().equals(t.getId())) {
                                        f = true;
                                        break;
                                    }
                                }
                                if (f) {
                                    c.getRouteList().remove(i);
                                }

                            }
                        }
                        it.remove();
                    }
                }

                //删城市
                ProvinceData.provinces.get(provinceIndex).getCityList().remove(cityIndex);
                if (ProvinceData.provinces.get(provinceIndex).getCityList().size() == 0) {
                    ProvinceData.provinces.remove(provinceIndex);
                }

//                print();

                cityListText.setText("");
                for (int i = 0; i < ProvinceData.provinces.size(); i++) {
                    cityListText.append(i + "：" + ProvinceData.provinces.get(i).getProvinceName() + "\n");
                    for (int j = 0; j < ProvinceData.provinces.get(i).getCityList().size(); j++) {
                        cityListText.append("      " + j + "：" + ProvinceData.provinces.get(i).getCityList().get(j).getCityName() + "\n");
                    }

                }
                FileUtil.writeProvinceData();
                FileUtil.writeTransportData();

            }
        });


        cityListText = new JTextArea();
        for (int i = 0; i < ProvinceData.provinces.size(); i++) {
            cityListText.append(i + "：" + ProvinceData.provinces.get(i).getProvinceName() + "\n");
            for (int j = 0; j < ProvinceData.provinces.get(i).getCityList().size(); j++) {
                cityListText.append("      " + j + "：" + ProvinceData.provinces.get(i).getCityList().get(j).getCityName() + "\n");
            }

        }
        cityListText.setEditable(false);

        JScrollPane sp = new JScrollPane(cityListText);
        sp.setBounds(20, 50, 220, 200);
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
