package com.vipul.bit_hotels.utils;

import java.util.HashMap;

/**
 * Created by rohanrodrigues on 5/5/17.
 */

public class MatchForScout {
    private String studentName;
    private PatientByStudent mPatientByStudentWatched;
    private String location;

    public MatchForScout(PatientByStudent patientByStudentWatched, String studentName) {
        this.mPatientByStudentWatched = patientByStudentWatched;
        this.studentName = studentName;
    }

    public MatchForScout() {
        this.mPatientByStudentWatched = new PatientByStudent();
    }

    public PatientByStudent getPatientByStudentWatched() {
        return mPatientByStudentWatched;
    }

    public void setPatientByStudentWatched(PatientByStudent patientByStudentWatched) {
        this.mPatientByStudentWatched = patientByStudentWatched;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setScout(String scout) {
        mPatientByStudentWatched.setStudent(scout);
    }

    public String getScout() {
        return this.getPatientByStudentWatched().getStudent();
    }

    public void setIssue(String issue) {
        mPatientByStudentWatched.setMedicalIssue(issue);
    }

    public String getIssue() {
        return this.getPatientByStudentWatched().getMedicalIssue();
    }

    public HashMap<String, String> generatePropertyList() {
        HashMap<String, String> data = new HashMap<>();
        PatientByStudent t = getPatientByStudentWatched();
        data.put("student", getScout());
        data.put("patient", t.getPatientName() + "");
        data.put("medicalIssue", t.getMedicalIssue() + "");
        data.put("procedure", t.getSurgicalProcedure() + "");
        data.put("effect", t.getProcedureEffect() + "");
        data.put("review", t.getReview() + "");
        data.put("recommend", t.getRecommend() + "");
        data.put("age", t.getAge() + "");
        data.put("weight", t.getWeight() + "");
        data.put("height", t.getHeight() + "");
        return data;
    }

}
