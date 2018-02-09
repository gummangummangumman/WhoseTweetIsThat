package hotboys69.dat153.whosetweetisthatappthing;

import java.util.ArrayList;

/**
 * Created by GuMMaN on 07.02.2018.
 */

public class Data {

    public static ArrayList<String> users = init();

    public static ArrayList<String> musicians = musicians();



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

        //Neil deGrasse Tyson
        arrayList.add("neiltyson");

        //Elon Musk
        arrayList.add("elonmusk");

        //Rihanna
        arrayList.add("rihanna");


        return arrayList;
    }

    public static ArrayList<String> musicians()
    {
        ArrayList<String> arrayList = new ArrayList<String>();

        //Jaden Smith
        arrayList.add("officialjaden");

        //Rihanna
        arrayList.add("rihanna");

        //Snoop Dogg
        arrayList.add("SnoopDogg");

        //Pitbull
        arrayList.add("pitbull");

        return arrayList;
    }

}
