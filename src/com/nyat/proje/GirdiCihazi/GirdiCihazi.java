package com.nyat.proje.GirdiCihazi;

import java.util.Scanner;

public class GirdiCihazi implements IGirdiCihazi{
    @Override
    public String stringVeriAl() {
        // String olarak veri alınıyor
        Scanner girdi = new Scanner(System.in);
        return girdi.nextLine();
    }

    @Override
    public int intVeriAl() {
        // integer olarak veri alınıyor
        Scanner girdi = new Scanner(System.in);
        return girdi.nextInt();
    }
}
