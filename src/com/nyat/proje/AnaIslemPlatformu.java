package com.nyat.proje;

import com.nyat.proje.AgArayuzu.IAgArayuzu;
import com.nyat.proje.Araclar.Durum;
import com.nyat.proje.Ekran.Ekran;
import com.nyat.proje.Ekran.IEkran;
import com.nyat.proje.Eyleyici.IEyleyici;
import com.nyat.proje.Factories.AgArayuzuFactory;
import com.nyat.proje.Factories.EyleyiciFactory;
import com.nyat.proje.Factories.SicaklikAlgilayiciFactory;
import com.nyat.proje.GirdiCihazi.GirdiCihazi;
import com.nyat.proje.GirdiCihazi.IGirdiCihazi;
import com.nyat.proje.Observer.IObserver;
import com.nyat.proje.Observer.ISubject;
import com.nyat.proje.Observer.Publisher;
import com.nyat.proje.SicaklikAlgilayici.ISicaklikAlgilayici;
import com.nyat.proje.VeriTabani.Kullanici;

import java.util.Locale;

import static java.lang.System.exit;

public class AnaIslemPlatformu implements IObserver {
    private IEkran ekran;
    private IGirdiCihazi girdiCihazi;
    private ISicaklikAlgilayici sicaklikAlgilayici;
    private IEyleyici eyleyici;
    private IAgArayuzu agArayuzu;
    private AkilliCihazBilgiSistemi akilliCihazBilgiSistemi;
    private final int SICAKLIK_GORUNTULE = 1;
    private final int SOGUTUCU_KAPAT = 2;
    private final int SOGUTUCU_AC = 3;
    private final int CIKIS = 4;
    public static Durum durum = Durum.KAPALI;



    public AnaIslemPlatformu() {
        this.ekran = new Ekran();
        this.girdiCihazi = new GirdiCihazi();
        this.akilliCihazBilgiSistemi = new AkilliCihazBilgiSistemi();
        ISubject publisher = new Publisher();
        publisher.attach(this);
        agArayuzu =  new AgArayuzuFactory().factoryMethod();
        eyleyici  =  new EyleyiciFactory().factoryMethod(publisher);
        sicaklikAlgilayici =  new SicaklikAlgilayiciFactory().factoryMethod(publisher);
    }


    // Kullanıcı doğrulanıyor ardından kullanıcı menuye aktarılıyor.
    public void baslat() throws InterruptedException {
        durum = Durum.ACILIS_TESTI;
        boolean girisBasarisiz = true;
        String kullaniciAdi;
        ekran.mesajGoruntule("Vazgecmek icin Q ya basiniz");
        do {
            ekran.mesajGoruntule("Kullanici Adi : ");
            kullaniciAdi = girdiCihazi.stringVeriAl();
            if (kullaniciAdi.toLowerCase(Locale.ROOT).equals("q"))
                exit(1);
            boolean kullaniciAdiDogrula = akilliCihazBilgiSistemi.kullaniciAdiDogrula(kullaniciAdi);
            if(kullaniciAdiDogrula){
                ekran.mesajGoruntule("Sifre : ");
                String sifre = girdiCihazi.stringVeriAl();
                if (sifre.toLowerCase(Locale.ROOT).equals("q"))
                    exit(1);
                boolean sifreDogrula = akilliCihazBilgiSistemi.sifreDogrula(sifre);
                if(sifreDogrula){
                    girisBasarisiz = false;
                    Kullanici kullanici = akilliCihazBilgiSistemi.kullaniciGetir(kullaniciAdi);
                    if(kullanici != null)
                        ekran.hosGeldinMesaj(kullanici.getAd(),kullanici.getSoyad());
                    else
                        ekran.mesajGoruntule("Hoş Geldiniz");
                }else{
                    continue;
                }
            }else
                continue;

        }while(girisBasarisiz);
        menuIslemleri(kullaniciAdi);
    }

    // Menu de seceneklerin seçilmesi ve secilen seçeneklere göre işlemeler yaptırılıyor.
    private void menuIslemleri(String kullaniciAdi) throws InterruptedException {
        int secenek;
        do {
            ekran.menuGoruntule();
            secenek = girdiCihazi.intVeriAl();
            switch (secenek){
                case SICAKLIK_GORUNTULE -> {
                    durumKontrol();
                    boolean yetkilimi = yetkiKontrol(kullaniciAdi);
                    if(yetkilimi)
                    agArayuzu.sicaklikGonder(sicaklikAlgilayici);

                }
                case SOGUTUCU_KAPAT -> {
                    durumKontrol();
                    boolean yetkilimi = yetkiKontrol(kullaniciAdi);
                    if(yetkilimi)
                    agArayuzu.sogutucuKapat(eyleyici);
                }
                case SOGUTUCU_AC -> {
                    durumKontrol();
                    boolean yetkilimi = yetkiKontrol(kullaniciAdi);
                    if(yetkilimi)
                    agArayuzu.sogutucuAc(eyleyici);
                }
                case CIKIS ->
                    ekran.mesajGoruntule("Uygulamadan Cikiliyor");

                default -> ekran.mesajGoruntule("Lutfen dogru secenegi sectiginizden emin olunuz");
            }
        }while(secenek != CIKIS);
    }

    // Akıllı cihaz durumu kontrol ediliyor
    private void durumKontrol(){
        if(durum == Durum.KAPALI ||durum == Durum.SERVIS_DISI){
            ekran.mesajGoruntule("Akilli cihaz durumu : " + durum + " isleme devam edilemiyor.");
            exit(1);
        }
    }

    // Kullanıcının istenilen işlemlere yetkisi olup olmadığı kontrol ediliyor.
    private boolean yetkiKontrol(String kullaniciAdi) throws InterruptedException {
        if(!akilliCihazBilgiSistemi.yetkilimi(kullaniciAdi)){
            ekran.mesajGoruntule("Kullanici yetkili degil lutfen yetkili bir kullanici ile giris yapiniz");
            return false;
        }
        return true;
    }

    // Çevre birimlerinin yayınladıkları mesajlar ana işlem platformu tarafından alınıp ekrana yazdırılıyor.
    @Override
    public void update(String message) {
        ekran.mesajGoruntule(message);
    }
}
