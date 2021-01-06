package com.ruoyi.system.api.domain;

/**
 * Created by wzw on 2020/10/19.
 */
public class SysTNB {
    private String Name;
    private String idCardNo;
    private String BMI;
    private String BMI_Suggest;
    private String Arterial;
    private String FastingGlucose;
    private String DrugName1;
    private String Usage_Day1;
    private String Usage_Time1;
    private String Insulin1;
    private String Doctor;

    public SysTNB() {
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

    public String getArterial() {
        return Arterial;
    }

    public void setArterial(String arterial) {
        Arterial = arterial;
    }

    public String getFastingGlucose() {
        return FastingGlucose;
    }

    public void setFastingGlucose(String fastingGlucose) {
        FastingGlucose = fastingGlucose;
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

    public String getUsage_Time1() {
        return Usage_Time1;
    }

    public void setUsage_Time1(String usage_Time1) {
        Usage_Time1 = usage_Time1;
    }

    public String getInsulin1() {
        return Insulin1;
    }

    public void setInsulin1(String insulin1) {
        Insulin1 = insulin1;
    }

    public String getDoctor() {
        return Doctor;
    }

    public void setDoctor(String doctor) {
        Doctor = doctor;
    }

    public SysTNB(String name, String idCardNo, String BMI, String BMI_Suggest, String arterial, String fastingGlucose, String drugName1, String usage_Day1, String usage_Time1, String insulin1, String doctor) {
        Name = name;
        this.idCardNo = idCardNo;
        this.BMI = BMI;
        this.BMI_Suggest = BMI_Suggest;
        Arterial = arterial;
        FastingGlucose = fastingGlucose;
        DrugName1 = drugName1;
        Usage_Day1 = usage_Day1;
        Usage_Time1 = usage_Time1;
        Insulin1 = insulin1;
        Doctor = doctor;
    }

    @Override
    public String toString() {
        return "SysTNB{" +
                "Name='" + Name + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", BMI='" + BMI + '\'' +
                ", BMI_Suggest='" + BMI_Suggest + '\'' +
                ", Arterial='" + Arterial + '\'' +
                ", FastingGlucose='" + FastingGlucose + '\'' +
                ", DrugName1='" + DrugName1 + '\'' +
                ", Usage_Day1='" + Usage_Day1 + '\'' +
                ", Usage_Time1='" + Usage_Time1 + '\'' +
                ", Insulin1='" + Insulin1 + '\'' +
                ", Doctor='" + Doctor + '\'' +
                '}';
    }
}
