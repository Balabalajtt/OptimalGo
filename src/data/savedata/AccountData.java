package data.savedata;

import data.model.Account;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 江婷婷 on 2018/1/3.
 */
public class AccountData {
    public static List<Account> accountList = new ArrayList<>();

    public static boolean addAdminAcount(String account, String password) {
        for (Account a : accountList) {
            if (a.getName().equals(account)) {
                return false;
            }
        }
        accountList.add(new Account(account, password, 1));
        FileUtil.writeAccountData();
        return true;
    }
}
