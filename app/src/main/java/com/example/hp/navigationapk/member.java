package com.example.hp.navigationapk;

/**
 * Created by HP on 3/13/2018.
 */

public class member {
    String name;
    String add;
    String num;
    String est;

    /*public member(String name, String add) {
        this.name = name;
        this.add = add;
    }*/

    public member(String name, String add, String num, String est) {
        this.name = name;
        this.add = add;
        this.num = num;
        this.est = est;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
    }
}
