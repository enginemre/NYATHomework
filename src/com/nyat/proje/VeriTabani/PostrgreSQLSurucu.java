package com.nyat.proje.VeriTabani;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostrgreSQLSurucu implements IVeriTabaniRepository{


    @Override
    public boolean kullaniciAdiDogrula(String kullaniciAdi) {
        try{
            Connection  connection = baglan();
            String sql = "SELECT kullanici_adi FROM kullanicilar";
            ResultSet rs =  sorguCalistir(sql);
            String dbKullaniciAdi;
            while (rs.next()){
                dbKullaniciAdi = rs.getString("kullanici_adi");
                // Girilen kullanici adıyla veri tabanındaki uyuşup uyuşmadığı kontrol ediliyor.
                if(dbKullaniciAdi.equals(kullaniciAdi))
                    return  true;
            }
            rs.close();
            baglantiSonlandir(connection);
            return  false;
        }catch (Exception e){
            System.out.println("Kullanici Adi sorgulanırken hata oluştu");
            return false;
        }



    }


    @Override
    public Kullanici kullaniciGetir(String kullaniciAdi) {
        try{
            // Kullanici adiyla eşleşen kullanıcı bilgileri getiriliyor.
            Connection  connection = baglan();
            String sql = "SELECT * FROM kullanicilar WHERE \"kullanici_adi\" = '" + kullaniciAdi + "'";
            ResultSet rs =  sorguCalistir(sql);
            if(rs != null){
                String ad ,soyad;
                while (rs.next()){
                    ad = rs.getString("ad");
                    soyad = rs.getString("soyad");
                    return new Kullanici(ad,soyad,kullaniciAdi);
                }
            }
            rs.close();
            baglantiSonlandir(connection);
            return  null;
        }catch (Exception e){
            System.out.println("Sifre sorgulanırken hata oluştu");
            return null;
        }
    }

    @Override
    public boolean sifreDogrula(String sifre) {
        try{
            Connection  connection = baglan();
            String sql = "SELECT sifre FROM kullanicilar";
            ResultSet rs =  sorguCalistir(sql);
            if(rs != null){
                String dbSifre;
                while (rs.next()){
                    // Girilen şifreyle veri tabanındaki uyuşup uyuşmadığı kontrol ediliyor.
                    dbSifre = rs.getString("sifre");
                    if(dbSifre.equals(sifre))
                        return  true;
                }
            }
            rs.close();
            baglantiSonlandir(connection);
            return  false;
        }catch (Exception e){
            System.out.println("Sifre sorgulanırken hata oluştu");
            return false;
        }
    }

    @Override
    public boolean yetkilimi(String kullaniciAdi) {
        try{
            Connection  connection = baglan();
            String sql = "SELECT yetki FROM kullanicilar WHERE \"kullanici_adi\" = '" + kullaniciAdi + "'";
            ResultSet rs =  sorguCalistir(sql);
            if(rs != null){
                int yetki;
                while (rs.next()){
                    // kullanicinin yetkisi olup olmadığı kontrol ediliyor.
                    yetki = rs.getInt("yetki");
                    if(yetki == 1)
                        return  true;
                }
            }
            rs.close();
            baglantiSonlandir(connection);
            return  false;
        }catch (Exception e){
            System.out.println("Yetki sorgulanırken hata oluştu");
            return false;
        }

    }

    @Override
    public Connection baglan() {
        Connection conn = null;
        // Veri tabanı ile bağlantı kuruluyor
        try{
            conn =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "1234");
        }catch (Exception e ){
            System.out.println(e.toString());
        }
        return conn;
    }

    @Override
    public ResultSet sorguCalistir(String sql) {
        // Girilen string sorgu çalıştırılıp sonuç tablosu dönülüyor.
       Connection connection = baglan();
        ResultSet rs;
        try{
            Statement stmt = connection.createStatement();
            rs= stmt.executeQuery(sql);
        }catch (Exception e){
            System.out.println(e.toString());
            rs = null;
        }
        baglantiSonlandir(connection);
        return  rs;

    }

    @Override
    public void baglantiSonlandir(Connection conn) {
        try {
            if(conn != null){
                conn.close();
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
