package com.nyat.proje.VeriTabani;

public class Kullanici {
    String ad;
    String soyad;
    String kullaniciAdi;


    public Kullanici(String ad, String soyad, String kullaniciAdi) {
        this.ad = ad;
        this.soyad = soyad;
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }
}
