package com.ruoyi.system.api.domain;

/**
 * Created by wzw on 2020/10/29.
 */

/**
 * 高血压实体类
 */
public class SysGXY {
    private String Name;
    private String idCardNo;
    private String PresentSymptoms;
    private String SystolicPressure;
    private String AssertPressure;
    private String BMI;
    private String BMI_Suggest;
    private String MedicationAdherence;
    private String DrugName1;
    private String Usage_Day1;
    private String Doctor;

    public SysGXY() {
    }

    public SysGXY(String name, String idCardNo, String presentSymptoms, String systolicPressure, String assertPressure, String BMI, String BMI_Suggest, String medicationAdherence, String drugName1, String usage_Day1, String doctor) {
        Name = name;
        this.idCardNo = idCardNo;
        PresentSymptoms = presentSymptoms;
        SystolicPressure = systolicPressure;
        AssertPressure = assertPressure;
        this.BMI = BMI;
        this.BMI_Suggest = BMI_Suggest;
        MedicationAdherence = medicationAdherence;
        DrugName1 = drugName1;
        Usage_Day1 = usage_Day1;
        Doctor = doctor;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getPresentSymptoms() {
        return PresentSymptoms;
    }

    public void setPresentSymptoms(String presentSymptoms) {
        PresentSymptoms = presentSymptoms;
    }

    public String getSystolicPressure() {
        return SystolicPressure;
    }

    public void setSystolicPressure(String systolicPressure) {
        SystolicPressure = systolicPressure;
    }

    public String getAssertPressure() {
        return AssertPressure;
    }

    public void setAssertPressure(String assertPressure) {
        AssertPressure = assertPressure;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public String getBMI_Suggest() {
        return BMI_Suggest;
    }

    public void setBMI_Suggest(String BMI_Suggest) {
        this.BMI_Suggest = BMI_Suggest;
    }

    public String getMedicationAdherence() {
        return MedicationAdherence;
    }

    public void setMedicationAdherence(String medicationAdherence) {
        MedicationAdherence = medicationAdherence;
    }

    public String getDrugName1() {
        return DrugName1;
    }

    public void setDrugName1(String drugName1) {
        DrugName1 = drugName1;
    }

    public String getUsage_Day1() {
        return Usage_Day1;
    }

    public void setUsage_Day1(String usage_Day1) {
        Usage_Day1 = usage_Day1;
    }

    public String getDoctor() {
        return Doctor;
    }

    public void setDoctor(String doctor) {
        Doctor = doctor;
    }

    @Override
    public String toString() {
        return "SysGXY{" +
                "Name='" + Name + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", PresentSymptoms='" + PresentSymptoms + '\'' +
                ", SystolicPressure='" + SystolicPressure + '\'' +
                ", AssertPressure='" + AssertPressure + '\'' +
                ", BMI='" + BMI + '\'' +
                ", BMI_Suggest='" + BMI_Suggest + '\'' +
                ", MedicationAdherence='" + MedicationAdherence + '\'' +
                ", DrugName1='" + DrugName1 + '\'' +
                ", Usage_Day1='" + Usage_Day1 + '\'' +
                ", Doctor='" + Doctor + '\'' +
                '}';
    }
}
