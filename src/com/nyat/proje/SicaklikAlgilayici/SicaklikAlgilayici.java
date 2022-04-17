package com.nyat.proje.SicaklikAlgilayici;

import com.nyat.proje.AkilliCihazBilgiSistemi;
import com.nyat.proje.AnaIslemPlatformu;
import com.nyat.proje.Araclar.Durum;
import com.nyat.proje.Observer.ISubject;

import java.text.DecimalFormat;
import java.util.Random;

public class SicaklikAlgilayici implements ISicaklikAlgilayici{
    private  ISubject publisher;

    public SicaklikAlgilayici(ISubject publisher) {
        this.publisher = publisher;
    }

    @Override
    public float sicaklikOku() throws InterruptedException{
        // Sicaklık random olarak oluşturulup Fahrenhayt ve Celcius olarak renkli bir şekilde ekrana yazdırılıyor
        Random random = new Random();
        AnaIslemPlatformu.durum = Durum.ALGILAMA;
        Thread.sleep(2000);
        AnaIslemPlatformu.durum = Durum.BEKLEME;
        float okunanDeger = random.nextFloat(30)+10;
        double fah = okunanDeger * 1.8 + 32;
        DecimalFormat df = new DecimalFormat("#.##");
        publisher.notify("Sicaklik : "+"\u001B[31m" +df.format(okunanDeger) + "  Celcius " + df.format(fah) + "  Fahrenhayt" + "\u001B[0m");
        return okunanDeger;

    }

    @Override
    public String toString() {
        return "Sicaklik algılayici ortamdaki sicaklığı ölçmekten sorumlu çevrebirimidir.";
    }
}
