package com.nyat.proje;

import com.nyat.proje.VeriTabani.IVeriTabaniRepository;
import com.nyat.proje.VeriTabani.Kullanici;
import com.nyat.proje.VeriTabani.PostrgreSQLSurucu;

public class AkilliCihazBilgiSistemi {
    private IVeriTabaniRepository veriTabani;

    public AkilliCihazBilgiSistemi() {
        this.veriTabani = new PostrgreSQLSurucu();
    }

    // Veri tabanı ile kullanici doğrulama işlemi yapılıyor.
    public boolean kullaniciAdiDogrula(String kullaniciAdi){
       return veriTabani.kullaniciAdiDogrula(kullaniciAdi);
    }
    // Veri tabanı ile şifre doğrulama işlemi yapılıyor.
    public boolean sifreDogrula(String sifre){
        return veriTabani.sifreDogrula(sifre);
    }
    // Veri tabanı ile kullanıcının yetkili olup olmadığı kontrol ediliyor.
    public boolean yetkilimi(String kullaniciAdi){
        return veriTabani.yetkilimi(kullaniciAdi);
    }

    // Veri tabanından kullanici adı verilen Kullanıcının verileri getiriliyor.
    public Kullanici kullaniciGetir(String kullaniciAdi){
        return veriTabani.kullaniciGetir(kullaniciAdi);
    }
}
