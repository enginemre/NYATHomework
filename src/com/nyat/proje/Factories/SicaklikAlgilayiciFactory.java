package com.nyat.proje.Factories;

import com.nyat.proje.Observer.ISubject;
import com.nyat.proje.SicaklikAlgilayici.ISicaklikAlgilayici;
import com.nyat.proje.SicaklikAlgilayici.SicaklikAlgilayici;

public class SicaklikAlgilayiciFactory {

    public ISicaklikAlgilayici factoryMethod(ISubject publisher) {
        return new SicaklikAlgilayici(publisher);
    }
}
