package com.nyat.proje.Observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher  implements ISubject{

    private List<IObserver> subscribers = new ArrayList<>();

    @Override
    public void attach(IObserver o) {
        subscribers.add(o);
    }

    @Override
    public void detach(IObserver o) {
        subscribers.add(o);
    }

    @Override
    public void notify(String m) {
        for(IObserver o: subscribers) {
            o.update(m);
        }
    }
}
