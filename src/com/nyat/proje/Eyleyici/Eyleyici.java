package com.nyat.proje.Eyleyici;

import com.nyat.proje.AnaIslemPlatformu;
import com.nyat.proje.Araclar.Durum;
import com.nyat.proje.Observer.ISubject;
import com.nyat.proje.Observer.Publisher;


public class Eyleyici implements IEyleyici {
    private boolean sogutucuAcikmi = false;
    private ISubject publisher;


    public Eyleyici(ISubject publisher) {
        this.publisher = publisher;
    }

    @Override
    public void sogutucuAc() throws InterruptedException {
        // Soğutucu açık değilse açılıyor ve abonelere bilgi yayımlanıyor.
        if(!sogutucuAcikmi){
            AnaIslemPlatformu.durum = Durum.ISLEM_YAPILIYOR;
            publisher.notify("Sogutucu 2 saniye sonra acilacaktir.");
            Thread.sleep(2000);
            sogutucuAcikmi = true;
            AnaIslemPlatformu.durum = Durum.BEKLEME;
            publisher.notify("\u001B[34m" +"Sogutucu Acildi"+ "\u001B[0m");
        }else
            publisher.notify("Sogutucu suanda zaten acik");


    }

    @Override
    public void sogutucuKapat() throws InterruptedException{
        // Soğutucu kapalı değilse kapatılıyor ve abonelere bilgi yayımlanıyor.
        if(sogutucuAcikmi){
            publisher.notify("Sogutucu 2 saniye sonra kapatilacaktir.");
            AnaIslemPlatformu.durum = Durum.ISLEM_YAPILIYOR;
            Thread.sleep(2000);
            sogutucuAcikmi = false;
            AnaIslemPlatformu.durum = Durum.BEKLEME;
            publisher.notify("\u001B[31m" +"Sogutucu Kapatildi"+ "\u001B[0m");
        }else
            publisher.notify("Sogutucu suanda zaten kapali");
    }

    @Override
    public String toString() {
        return "Eyleyici sogutucunun acilmasi ve kapanmasindan sorumlu cevre birimidir.";
    }
}
