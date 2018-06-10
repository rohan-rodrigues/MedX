package com.vipul.bit_hotels.utils;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;


public class CSVSuperData {
    private ArrayList<MatchForSuperScout> data;
    private boolean fileExists = false;

    public CSVSuperData(ArrayList<MatchForSuperScout> list) {
        this.data = list;
    }

    public void writeDataToCSV(Context context, String competition) throws IOException {
        String str = listToString(data);
        // String str = listToString(team);

        fileExists = false;

        String FILE_PATH = "Scouting_Data_" + competition + ".csv";
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), FILE_PATH);

        if (!file.exists()) {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str);
            bw.close();
        }

        else {
            FileOutputStream fileinput = new FileOutputStream(file, true);
            PrintStream printstream = new PrintStream(fileinput);
            printstream.print("\n" + str);
            fileinput.close();
            fileExists = true;
        }
    }

    public void writeDataToExternalDrive(Context context, String competition) throws IOException {
        if (!isExternalStorageAvailable())
            return;

        String str = listToString(data);

        fileExists = false;


        String FILE_PATH = "Scouting_Data_" + competition + ".csv";
        File file = new File(context.getExternalFilesDir(
                "files"), FILE_PATH);

        if (!file.exists()) {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str);
            bw.close();
        }

        else {
            FileOutputStream fileinput = new FileOutputStream(file, true);
            PrintStream printstream = new PrintStream(fileinput);
            printstream.print("\n" + str);
            fileinput.close();
            fileExists = true;
        }
    }

    public static void overrideCSVFile(String fileName, ArrayList<MatchForSuperScout> teamForScouts, Context context) throws IOException {
        String str = listToString(teamForScouts);

        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), fileName);

        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(str);
        bw.close();
    }

    public static String listToString(ArrayList<MatchForSuperScout> teamForScout) {
        StringBuilder str = new StringBuilder();

     /*   for (int i = 0; i < teamForScout.get(0).getPropertyList().size(); i++) {
            str += teamForScout.get(0).getPropertyList().get(i) + ",";
        } */

        // TO USE WHEN ENTERING MULTIPLE TEAMS INTO THE CSV FILE
        for (int i = 0; i < teamForScout.size(); i++) {
            ArrayList<TeamForSuperScout> teams = teamForScout.get(i).getAlliance().getTeamList();

            for (int team = 0; team < 3; team++) {
                HashMap<String, String> propertyList = teamForScout.get(i).generatePropertyList(teams.get(team).getTeamNumber());

                for (String key : propertyList.keySet()) {
                    str.append(propertyList.get(key)).append(",");
                }

                if (i < teamForScout.size()-1)
                    str.append("\n");
            }
        }
        return str.toString();
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(extStorageState);
    }

    public boolean doesFileExist() {
        return fileExists;
    }


    public static void clearCSVFile(String fileName, Context context) throws IOException {
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), fileName);

        if (!file.exists()) {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");
            bw.close();
        }
    }

}