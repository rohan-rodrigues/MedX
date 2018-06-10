package com.vipul.bit_hotels.utils;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class CSVData {
    private ArrayList<MatchForScout> data;
    private boolean fileExists = false;

    public CSVData(ArrayList<MatchForScout> list) {
        this.data = list;
    }

    public void writeDataToCSV(Context context, String competition) throws IOException {
      //  String str = listToString(data);
       // String str = listToString(team);

        fileExists = false;

        String FILE_PATH = "Scouting_Data_" + competition + ".csv";
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), FILE_PATH);

//        if (!file.exists()) {
//            FileWriter fw = new FileWriter(file);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(str);
//            bw.close();
//        }
//
//        else {
//            FileOutputStream fileinput = new FileOutputStream(file, true);
//            PrintStream printstream = new PrintStream(fileinput);
//            printstream.print("\n" + str);
//            fileinput.close();
//            fileExists = true;
//        }
    }

    public void writeDataToExternalDrive(Context context, String competition) throws IOException {
        if (!isExternalStorageAvailable())
            return;

     //   String str = listToString(data);

        fileExists = false;


        String FILE_PATH = "Scouting_Data_" + competition + ".csv";
        File file = new File(context.getExternalFilesDir(
                "files"), FILE_PATH);

//        if (!file.exists()) {
//            FileWriter fw = new FileWriter(file);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(str);
//            bw.close();
//        }
//
//        else {
//            FileOutputStream fileinput = new FileOutputStream(file, true);
//            PrintStream printstream = new PrintStream(fileinput);
//            printstream.print("\n" + str);
//            fileinput.close();
//            fileExists = true;
//        }
    }

//    public static void overrideCSVFile(String fileName, ArrayList<MatchForScout> teamForScouts, Context context) throws IOException {
//        String str = listToString(teamForScouts);
//
//        File file = new File(context.getExternalFilesDir(
//                Environment.DIRECTORY_DOCUMENTS), fileName);
//
//            FileWriter fw = new FileWriter(file);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(str);
//            bw.close();
//    }

//    public static String listToString(ArrayList<MatchForScout> teamForScout) {
//        StringBuilder str = new StringBuilder();
//
//     /*   for (int i = 0; i < teamForScout.get(0).getPropertyList().size(); i++) {
//            str += teamForScout.get(0).getPropertyList().get(i) + ",";
//        } */
//
//        // TO USE WHEN ENTERING MULTIPLE TEAMS INTO THE CSV FILE
//        for (int i = 0; i < teamForScout.size(); i++) {
//         //   ArrayList<String> propertyList = createPropertyListForScout(teamForScout.get(i));
//            for (int j = 0; j < propertyList.size(); j++) {
//                if (j < propertyList.size()-1)
//                    str.append(propertyList.get(j)).append(",");
//                else
//                    str.append(propertyList.get(j));
//            }
//
//            if (i < teamForScout.size()-1)
//                str.append("\n");
//        }
//        return str.toString();
//    }

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

//    private static ArrayList<String> createPropertyListForScout(MatchForScout match) {
//        ArrayList<String> propertyList = new ArrayList<>();
//        PatientByStudent team = match.getPatientByStudentWatched();
//
//        propertyList.add(match.getStudentName() + "");
//        propertyList.add(match.getScout() + "");
//        propertyList.add(team.getPatientName() + "");
//        propertyList.add(match.getPatientByStudentWatched().isCrossedBaseline() + "");
//        propertyList.add(team.getNumCubesSwitchAuton() +"");
//        propertyList.add(team.getNumCubesScaleAuton() +"");
//        propertyList.add(team.getNumCubesSwitchTeleop() +"");
//        propertyList.add(team.getNumCubesScaleTeleop() +"");
//        propertyList.add(team.getNumExchangeTeleop() +"");
//        propertyList.add(team.getFouls() +"");
//        propertyList.add(team.getNumCubesInOpponentsSwitch() + "");
//        return propertyList;
//    }

}