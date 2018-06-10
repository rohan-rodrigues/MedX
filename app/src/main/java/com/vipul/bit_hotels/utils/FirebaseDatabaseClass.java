package com.vipul.bit_hotels.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rohanrodrigues on 2/13/18.
 */

public class FirebaseDatabaseClass {
    public static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public static void storeCompetition(Competition c) {
//        Map<String, Object> ab = new HashMap<>();
//        ArrayList<MatchForScout> matchForScouts = c.getMatchForScouts();
//
//        for (int i = 0; i < matchForScouts.size(); i++) {
//            ab.put(matchForScouts.get(i).getStudentName(), matchForScouts.get(i));
//        }
//        mDatabase.child(c.getCompName()).updateChildren(ab);



        ArrayList<MatchForScout> matchForScouts = c.getMatchForScouts();

        for (int i = 0; i < matchForScouts.size(); i++) {
            Map<String, Object> ab = new HashMap<>();
            ab.put(matchForScouts.get(i).getPatientByStudentWatched().getPatientName(), matchForScouts.get(i).getPatientByStudentWatched());
            mDatabase.child(c.getCompName()).child(matchForScouts.get(i).getStudentName()).child("teamsWatched").updateChildren(ab);
        }
    }

    public static void storeMultipleCompetitions(ArrayList<Competition> comps) {
        for (int i = 0; i < comps.size(); i++) {
            ArrayList<MatchForScout> matchForScouts = comps.get(i).getMatchForScouts();

            for (int j = 0; j < matchForScouts.size(); j++) {
                Map<String, Object> ab = new HashMap<>();
                ab.put(matchForScouts.get(j).getPatientByStudentWatched().getPatientName(), matchForScouts.get(j).getPatientByStudentWatched());
                mDatabase.child(comps.get(i).getCompName()).child(matchForScouts.get(j).getStudentName()).child("teamsWatched").updateChildren(ab);
               // ab.put(matchForScouts.get(j).getStudentName(), matchForScouts.get(j));
            }
          //  mDatabase.child(comps.get(i).getCompName()).updateChildren(ab);
        }

    }

    public static void storeMatchForScout(String competition, MatchForScout match) {
        Map<String, Object> ab = new HashMap<>();
//        Map<String, Object> scoutList = new HashMap<>();
//        Scout scout = new Scout(match.getStudentName(), match.getStudent());
//        scoutList.put(scout.getName(), scout);
        ab.put(match.getPatientByStudentWatched().getPatientName(), match.getPatientByStudentWatched());
        mDatabase.child(competition).child(match.getStudentName()).child("teamsWatched").updateChildren(ab);
       // mDatabase.child(competition).child(match.getStudentName()).child("scoutsWatched").updateChildren(scoutList);
    }

    public static void storeMatchForSuperScout(String competition, MatchForSuperScout match) {
        Map<String, Object> ab = new HashMap<>();

        // Loop through teams in match
        // For each team, call generate property list in match object
        // iterate through the teams in firebase (can use same loop from above) and save the property list to the team for the specific match
System.out.println(competition);

        ab.put(match.getAlliance().getTeamList().get(0).getTeamNumber(), match.getAlliance().getTeamList().get(0));
        ab.put(match.getAlliance().getTeamList().get(1).getTeamNumber(), match.getAlliance().getTeamList().get(1));
        ab.put(match.getAlliance().getTeamList().get(2).getTeamNumber(), match.getAlliance().getTeamList().get(2));

        String allianceColor = match.getAlliance().getColor().name();
        mDatabase.child(competition).child(match.getMatchNum()).child("alliancesWatched").child(allianceColor).updateChildren(ab);
      //  mDatabase.child(competition).child(match.getStudentName()).child("superScoutsWatched").updateChildren(scoutList);
    }

    public static void updatedStoreMatchForSuperScout(String competition, MatchForSuperScout m) throws NullPointerException{
        Map<String, Object> ab = new HashMap<>();

        System.out.println("SIZEEEE: " + m.getAlliance().getTeamList().size());

        for (int i = 0; i < m.getAlliance().getTeamList().size(); i++) {
            TeamForSuperScout t = m.getAlliance().getTeamList().get(i);
            HashMap<String, String> propertyList = m.generatePropertyList(t.getTeamNumber());

            for (HashMap.Entry<String, String> entry : propertyList.entrySet()) {
              //  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                ab.put(entry.getKey(), entry.getValue());
            }
            mDatabase.child(competition).child(t.getTeamNumber()).child(m.getMatchNum()).updateChildren(ab);
        }
    }

    public static void updatedStoreMatchForScout(String competition, MatchForScout m) throws NullPointerException {
        Map<String, Object> ab = new HashMap<>();

        HashMap<String, String> propertyList = m.generatePropertyList();

        for (HashMap.Entry<String, String> entry : propertyList.entrySet()) {
                //  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            ab.put(entry.getKey(), entry.getValue());
        }
        mDatabase.child(competition).child(m.getPatientByStudentWatched().getPatientName()).child(m.getStudentName()).updateChildren(ab);
    }


    public static String[][] createData(DataSnapshot dataSnapshot) {
        String[][] finalArr = new String[66][15];
        Object jsonData = dataSnapshot.getValue();

        assert ((String)jsonData) != null;
        String[] arr = ((String)jsonData).split("]");

        for (int i = 0; i < arr.length; i++) {
            String[] arr2 = arr[i].split(",");

            if (i == 0) {
                arr2 = removeBrackets(arr2, 0);
            }
            else {
                arr2 = removeBrackets(arr2, 1);
            }

            for (int j = 0; j < arr2.length; j++) {
                finalArr[i][j] = arr2[j];
            }
        }
        return finalArr;
    }

    public static String[] removeBrackets(String[] arr, int startPosition) {
        ArrayList<String> newList = new ArrayList<>();
        for (int i = startPosition; i < arr.length; i++) {
            if (!arr[i].equals("[") && !arr[i].equals("]") && !arr[i].equals("\\") && !arr[i].equals(":")) {
                newList.add(arr[i]);
            }
        }
        //  System.out.println(newList);
        return listToArray(newList);
    }

    public static String[] listToArray(ArrayList<String> list) {
        String[] arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
            //       System.out.println(arr[i]);
        }
        //    System.out.println(Arrays.toString(arr));
        return arr;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Bitmap bytesToImage(DataSnapshot dataSnapshot) throws IOException {
       // byte[] byteArray = (byte[])dataSnapshot.getValue();


        ArrayList<Byte> arrayList = (ArrayList<Byte>) dataSnapshot.getValue();
        byte[] byteList = new byte[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            byteList[i] = arrayList.get(i);
        }

        Bitmap bmp= BitmapFactory.decodeByteArray(byteList, 0, byteList.length);
        System.out.println("ASDASDSD   " + (bmp == null));
        return bmp;





        // WORKING CODE
//        String encodedString = (String)dataSnapshot.getValue();
//
//        byte[] bytes = encodedString.getBytes();
//        Bitmap bmp= BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//        System.out.println("ASDASDSD   " + (bmp == null));
//        return bmp;
//





//        ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
//        DataInputStream in = new DataInputStream(bais);
//        while (in.available() > 0) {
//            String element = in.readUTF();
//            System.out.println(element);
//        }
//        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

//        Base64.Decoder decoder = Base64.getDecoder();
//        byte[] decodedByteArray = decoder.decode(encodedString);
//
//        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }

}
