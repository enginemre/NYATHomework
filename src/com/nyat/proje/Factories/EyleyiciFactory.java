package com.nyat.proje.Factories;

import com.nyat.proje.Eyleyici.Eyleyici;
import com.nyat.proje.Eyleyici.IEyleyici;
import com.nyat.proje.Observer.ISubject;

public class EyleyiciFactory{
    public IEyleyici factoryMethod(ISubject publisher) {
        return new Eyleyici(publisher);
    }
}
