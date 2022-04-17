package com.nyat.proje;



public class Uygulama {

    public static void main(String[] args) throws InterruptedException {
        // Ana islem platfomu olusturulup cihaz başlatılıyor.
        AnaIslemPlatformu anaIslemPlatformu = new AnaIslemPlatformuFactory().factoryMethod();
        anaIslemPlatformu.baslat();
    }
}
