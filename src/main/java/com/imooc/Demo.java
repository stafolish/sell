package com.imooc;


import com.mysql.cj.api.mysqla.result.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by: xmg
 * 2018/3/27 18:25
 */

public class Demo {

    static String url = "jdbc:mysql://127.0.0.1:3306/sell?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false";
    static String userName = "root";
    static String pass = "root";
    static String driver = "com.mysql.cj.jdbc.Driver";

    public static Connection getConn() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, userName, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        Connection conn = getConn();
        //System.out.println(conn);

        String sql = "desc order_master";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        List<String> column_names = new ArrayList<>();
        List<String> column_types = new ArrayList<>();
        while (rs.next()) {
            column_names.add(rs.getString(1));
            column_types.add(rs.getString(2));
        }

        Function<String, String> strUpper = (String s) -> {
            String[] strs = s.split("_");
            String newStr = "";
            for (int i = 1; i < strs.length; i++) {
                newStr = strs[0];
                char[] cs = strs[i].toCharArray();
                cs[0] -= 32;
                newStr += String.valueOf(cs);
            }
            return newStr;
        };
        List<String> columnNames = column_names.stream().map(strUpper)
                .collect(Collectors.toList());
        System.out.println(column_names);
        System.out.println(column_types);
        System.out.println(columnNames);
        System.out.println("hehehe");

        conn.close();


    }
}
