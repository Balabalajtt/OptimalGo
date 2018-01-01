package util;

import data.model.Province;
import data.model.Transport;
import data.savedata.ProvinceData;
import data.savedata.TransportData;

import java.io.*;
import java.util.List;

/**
 * Created by 江婷婷 on 2017/12/30.
 */
public class FileUtil {

    /**
     * 改成小单个对象存储
     * @return
     */
    public static boolean writeProvinceData() {
        return writeData(ProvinceData.provinces, "province.dat");
    }

    public static void readProvinceData() {
        ProvinceData.provinces = (List<Province>) readData("province.dat");
    }

    public static boolean writeTransportData() {
        return writeData(TransportData.transports, "transport.dat");
    }

    public static void readTransportData() {
        TransportData.transports = (List<Transport>) readData("transport.dat");
    }

    private static boolean writeData(Object o, String fileName) {
        File file = new File(fileName);
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(o);
            objOut.flush();
            objOut.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static Object readData(String fileName) {
        Object temp = null;
        File file = new File(fileName);
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(in);
            temp = objIn.readObject();
            objIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }

}
