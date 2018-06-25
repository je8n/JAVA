/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appclass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author serkan.ozdemir
 */
public class VtClass {

    String Sonuc;
    String Adres;
    String Kullanici_Adi;
    String sifre;
    String hata;

    public String getHata() {
        return hata;
    }

    public void setHata(String hata) {
        this.hata = hata;
    }

    public String getSonuc() {
        return Sonuc;
    }

    public void setSonuc(String Sonuc) {
        this.Sonuc = Sonuc;
    }

    public String getAdres() {
        return Adres;
    }

    public void setAdres(String Adres) {
        this.Adres = Adres;
    }

    public String getKullanici_Adi() {
        return Kullanici_Adi;
    }

    public void setKullanici_Adi(String Kullanici_Adi) {
        this.Kullanici_Adi = Kullanici_Adi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
    Connection connection = null;

    public void icerikal(String sorgu) throws SQLException {
        try{
            PreparedStatement ps;
            ps = this.connection.prepareStatement(sorgu);
            ResultSet rs = ps.executeQuery();
            JSONArray jsons = new JSONArray();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                int numColumns = rsmd.getColumnCount();
                JSONObject obj = new JSONObject();
                for (int i = 1; i <= numColumns; i++) {
                    String column_name = rsmd.getColumnName(i);
                    obj.put(column_name, rs.getObject(column_name));
                }
                jsons.add(obj);
            }
            this.Sonuc = jsons.toJSONString();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void connect() throws ClassNotFoundException {
        this.setHata("100");
        this.setSonuc("Veritabanı Kütüphanesi Etkinleştirildi!");
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@" + this.Adres, this.Kullanici_Adi, this.sifre);
        } catch (SQLException e) {
            this.setHata("404");
            this.setSonuc("Veritabanına bağlanılamadı: " + e.getMessage());
        }

        if (connection != null) {
            this.setHata("200");
            this.setSonuc("Veritabanına Bağlanıldı!");
        }
    }
}
