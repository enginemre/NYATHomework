package com.nyat.proje.Factories;

import com.nyat.proje.AgArayuzu.AgArayuzu;
import com.nyat.proje.AgArayuzu.IAgArayuzu;
import com.nyat.proje.Observer.ISubject;

public class AgArayuzuFactory{
    public IAgArayuzu factoryMethod() {
        return new AgArayuzu();
    }
}
