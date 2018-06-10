package com.vipul.bit_hotels.utils;

/**
 * Created by rohanrodrigues on 2/11/17.
 */

public class PatientByStudent {
    private String patientName;
    private String medicalIssue;
    private int age, weight, height;
    private String surgicalProcedure;
    private int procedureEffect;
    private String review;
    private int recommend;

    private String student;


    public PatientByStudent(String patientName, String medicalIssue, int age, int weight, int height, String surgicalProcedure, int procedureEffect, String review, int recommend) {
        this.patientName = patientName;
        this.medicalIssue = medicalIssue;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.surgicalProcedure = surgicalProcedure;
        this.procedureEffect = procedureEffect;
        this.review = review;
        this.recommend = recommend;
    }

    public PatientByStudent() {

    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getMedicalIssue() {
        return medicalIssue;
    }

    public void setMedicalIssue(String medicalIssue) {
        this.medicalIssue = medicalIssue;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSurgicalProcedure() {
        return surgicalProcedure;
    }

    public void setSurgicalProcedure(String surgicalProcedure) {
        this.surgicalProcedure = surgicalProcedure;
    }

    public int getProcedureEffect() {
        return procedureEffect;
    }

    public void setProcedureEffect(int procedureEffect) {
        this.procedureEffect = procedureEffect;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

}
