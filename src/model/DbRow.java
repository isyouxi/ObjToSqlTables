package model;

import java.lang.reflect.Type;

/**
 * Created by youxi on 2017/1/5.
 */
public class DbRow {
    /**
     * 名稱
     **/
    public String name;
    /**
     * 是否是主鍵
     **/
    public boolean isPrimary;
    /**
     * 類型
     **/
    public Type type;
    /**
     * 默認值
     **/
    public String o_default;
    /**
     * 描述
     **/
    public String decs;

    public DbRow(String name, boolean isPrimary, Type type, String o_default, String decs) {
        this.name = name;
        this.isPrimary = isPrimary;
        this.type = type;
        this.o_default = o_default;
        this.decs = decs;
    }

    public DbRow(String name, Type type, String o_default, String decs) {
        this.name = name;
        this.isPrimary = false;
        this.type = type;
        this.o_default = o_default;
        this.decs = decs;
    }

    public DbRow(String name, Type type, String decs) {
        this.name = name;
        this.isPrimary = false;
        this.type = type;
        this.o_default = null;
        this.decs = decs;
    }

    public DbRow(String name, Type type) {
        this.name = name;
        this.isPrimary = false;
        this.type = type;
        this.o_default = null;
        this.decs = null;
    }
}
