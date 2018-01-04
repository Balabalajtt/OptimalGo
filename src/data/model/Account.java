package data.model;

import java.io.Serializable;

/**
 * Created by 江婷婷 on 2018/1/3.
 */
public class Account implements Serializable {
    private String name;
    private String password;
    private int type;

    public Account(String name, String password, int type) {
        this.name = name;
        this.password = password;
        this.type = type;//0用户 1管理员
    }

    public Account(String account, String password) {
        this.name = account;
        this.password = password;
        this.type = 0;//0用户
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
