package com.jawnho.douyuspringboot.hive;

public class Test {
    public static void main(String[] argus) {
        HiveJDBC.showDatabases().forEach(a->{System.out.println(a);});
    }

}
