package com.nyat.proje.Ekran;

public class Ekran implements IEkran{


    @Override
    public void hosGeldinMesaj(String ad, String soyad) {
        System.out.println("*********** Akilli Cihaz ***********");
        System.out.println("Hos Geldin "+ ad + " " + soyad);
    }

    @Override
    public void mesajGoruntule(String mesaj) {
        System.out.println(mesaj);
    }

    @Override
    public void menuGoruntule() {
        System.out.println("*********** Islem Menusu ***********");
        System.out.println("1- Sicaklik Goruntule");
        System.out.println("2- Sogutucu Kapat");
        System.out.println("3- Sogutucu Ac");
        System.out.println("4- Cikis");
        System.out.println("*************************************");
    }
}
