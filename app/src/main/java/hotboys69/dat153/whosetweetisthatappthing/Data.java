package hotboys69.dat153.whosetweetisthat;

import java.util.ArrayList;

/**
 * Created by GuMMaN on 07.02.2018.
 */

public class Data {

    public static ArrayList<String> users = init();

    public static ArrayList<String> init()
    {
        ArrayList<String> arrayList = new ArrayList<String>();

        //Pope Francis
        arrayList.add("Pontifex");

        //Malik Obama
        arrayList.add("ObamaMalik");

        //Donald J. Trump
        arrayList.add("realDonaldTrump");

        //Jaden Smith
        arrayList.add("officialjaden");

        //Dalai Lama
        arrayList.add("DalaiLama");

        return arrayList;
    }

}
