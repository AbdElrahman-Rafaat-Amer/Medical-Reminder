package com.medication.medicalreminder;

import java.util.ArrayList;


public class Doses {
    public static ArrayList<Doses> DosesArrayList;
    private int id;
    private String name;

    public Doses(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void initDoses(){
        DosesArrayList = new ArrayList<>();
        Doses g = new Doses(0, "g");
        DosesArrayList.add(g);

        Doses IU = new Doses(1, "IU");
        DosesArrayList.add(IU);

        Doses mcg = new Doses(2, "mcg");
        DosesArrayList.add(mcg);

        Doses mEq = new Doses(3, "mEq");
        DosesArrayList.add(mEq);

        Doses mg = new Doses(4, "mg");
        DosesArrayList.add(mg);

    }

    public static ArrayList<Doses> getDosesArrayList() {
        return DosesArrayList;
    }

    public static String[] DosesNames(){
        String[] names = new String[DosesArrayList.size()];
        for (int i=0; i<DosesArrayList.size(); i++){
            names[i] = DosesArrayList.get(i).name;
        }
        return names;
    }
}
