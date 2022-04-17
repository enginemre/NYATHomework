package com.nyat.proje.VeriTabani;

import java.sql.Connection;
import java.sql.ResultSet;

public interface IVeriTabaniRepository {
    Connection baglan();
    ResultSet sorguCalistir(String sql);
    void baglantiSonlandir(Connection con);
    boolean yetkilimi(String kullaniciAdi);
    boolean sifreDogrula(String sifre);
    boolean kullaniciAdiDogrula(String kullaniciAdi);
    Kullanici kullaniciGetir(String kullaniciAdi);

}
