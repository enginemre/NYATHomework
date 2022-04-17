package com.nyat.proje.AgArayuzu;

import com.nyat.proje.Eyleyici.IEyleyici;
import com.nyat.proje.SicaklikAlgilayici.ISicaklikAlgilayici;

public interface IAgArayuzu {
    void sogutucuAc(IEyleyici eyleyici) throws InterruptedException;
    void sogutucuKapat(IEyleyici eyleyici) throws InterruptedException;
    void sicaklikGonder(ISicaklikAlgilayici sicaklikAlgilayici) throws InterruptedException;
}
