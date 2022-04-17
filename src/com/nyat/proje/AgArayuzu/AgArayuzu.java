package com.nyat.proje.AgArayuzu;

import com.nyat.proje.Eyleyici.IEyleyici;
import com.nyat.proje.SicaklikAlgilayici.ISicaklikAlgilayici;

public class AgArayuzu implements IAgArayuzu{

    @Override
    public void sogutucuAc(IEyleyici eyleyici) throws InterruptedException {
        eyleyici.sogutucuAc();
    }

    @Override
    public void sogutucuKapat(IEyleyici eyleyici) throws InterruptedException {
        eyleyici.sogutucuKapat();
    }

    @Override
    public void sicaklikGonder(ISicaklikAlgilayici sicaklikAlgilayici) throws InterruptedException {
        sicaklikAlgilayici.sicaklikOku();
    }

    @Override
    public String toString() {
        return "Ag Arayuzu kullanıcıdan aldığı istekleri cevrebirimlere iletmekle sorumludur.";
    }
}
