package cn.ahut.entity;


import java.math.BigDecimal;
import java.util.Date;


public class Jmpsychosispersoninfop {
    private BigDecimal skey;

    private String sno;

    private String sguardianname;

    private String sguardianprovince;

    private String sguardiancity;

    private String sguardiancountry;

    private String sguardianvillage;

    private String sguardianhouseno;

    private String sguardiantelephone;

    private String spatienttelephone;

    private String schargephysician;

    private String sfirstdiseasetime;

    private String sdoctoradvice;

    private Date dfilltime;
    private String ifirstdiseaseage;

    private String ssymptomscode;

    private String ihospitalizations;

    private String spsychosiscode;

    private String treatmenteffectcode;

    private int ihit;

    public Jmpsychosispersoninfop() {
    }

    public Jmpsychosispersoninfop(BigDecimal skey, String sno, String sguardianname, String sguardianprovince, String sguardiancity, String sguardiancountry, String sguardianvillage, String sguardianhouseno, String sguardiantelephone, String spatienttelephone, String schargephysician, String sfirstdiseasetime, String sdoctoradvice, Date dfilltime, String ifirstdiseaseage, String ssymptomscode, String ihospitalizations, String spsychosiscode, String treatmenteffectcode, int ihit) {
        this.skey = skey;
        this.sno = sno;
        this.sguardianname = sguardianname;
        this.sguardianprovince = sguardianprovince;
        this.sguardiancity = sguardiancity;
        this.sguardiancountry = sguardiancountry;
        this.sguardianvillage = sguardianvillage;
        this.sguardianhouseno = sguardianhouseno;
        this.sguardiantelephone = sguardiantelephone;
        this.spatienttelephone = spatienttelephone;
        this.schargephysician = schargephysician;
        this.sfirstdiseasetime = sfirstdiseasetime;
        this.sdoctoradvice = sdoctoradvice;
        this.dfilltime = dfilltime;
        this.ifirstdiseaseage = ifirstdiseaseage;
        this.ssymptomscode = ssymptomscode;
        this.ihospitalizations = ihospitalizations;
        this.spsychosiscode = spsychosiscode;
        this.treatmenteffectcode = treatmenteffectcode;
        this.ihit = ihit;
    }

    @Override
    public String toString() {
        return "Jmpsychosispersoninfop{" +
                "skey=" + skey +
                ", sno='" + sno + '\'' +
                ", sguardianname='" + sguardianname + '\'' +
                ", sguardianprovince='" + sguardianprovince + '\'' +
                ", sguardiancity='" + sguardiancity + '\'' +
                ", sguardiancountry='" + sguardiancountry + '\'' +
                ", sguardianvillage='" + sguardianvillage + '\'' +
                ", sguardianhouseno='" + sguardianhouseno + '\'' +
                ", sguardiantelephone='" + sguardiantelephone + '\'' +
                ", spatienttelephone='" + spatienttelephone + '\'' +
                ", schargephysician='" + schargephysician + '\'' +
                ", sfirstdiseasetime='" + sfirstdiseasetime + '\'' +
                ", sdoctoradvice='" + sdoctoradvice + '\'' +
                ", dfilltime=" + dfilltime +
                ", ifirstdiseaseage='" + ifirstdiseaseage + '\'' +
                ", ssymptomscode='" + ssymptomscode + '\'' +
                ", ihospitalizations='" + ihospitalizations + '\'' +
                ", spsychosiscode='" + spsychosiscode + '\'' +
                ", treatmenteffectcode='" + treatmenteffectcode + '\'' +
                ", ihit='" + ihit + '\'' +
                '}';
    }

    public BigDecimal getSkey() {
        return skey;
    }

    public void setSkey(BigDecimal skey) {
        this.skey = skey;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSguardianname() {
        return sguardianname;
    }

    public void setSguardianname(String sguardianname) {
        this.sguardianname = sguardianname;
    }

    public String getSguardianprovince() {
        return sguardianprovince;
    }

    public void setSguardianprovince(String sguardianprovince) {
        this.sguardianprovince = sguardianprovince;
    }

    public String getSguardiancity() {
        return sguardiancity;
    }

    public void setSguardiancity(String sguardiancity) {
        this.sguardiancity = sguardiancity;
    }

    public String getSguardiancountry() {
        return sguardiancountry;
    }

    public void setSguardiancountry(String sguardiancountry) {
        this.sguardiancountry = sguardiancountry;
    }

    public String getSguardianvillage() {
        return sguardianvillage;
    }

    public void setSguardianvillage(String sguardianvillage) {
        this.sguardianvillage = sguardianvillage;
    }

    public String getSguardianhouseno() {
        return sguardianhouseno;
    }

    public void setSguardianhouseno(String sguardianhouseno) {
        this.sguardianhouseno = sguardianhouseno;
    }

    public String getSguardiantelephone() {
        return sguardiantelephone;
    }

    public void setSguardiantelephone(String sguardiantelephone) {
        this.sguardiantelephone = sguardiantelephone;
    }

    public String getSpatienttelephone() {
        return spatienttelephone;
    }

    public void setSpatienttelephone(String spatienttelephone) {
        this.spatienttelephone = spatienttelephone;
    }

    public String getSchargephysician() {
        return schargephysician;
    }

    public void setSchargephysician(String schargephysician) {
        this.schargephysician = schargephysician;
    }

    public String getSfirstdiseasetime() {
        return sfirstdiseasetime;
    }

    public void setSfirstdiseasetime(String sfirstdiseasetime) {
        this.sfirstdiseasetime = sfirstdiseasetime;
    }

    public String getSdoctoradvice() {
        return sdoctoradvice;
    }

    public void setSdoctoradvice(String sdoctoradvice) {
        this.sdoctoradvice = sdoctoradvice;
    }

    public Date getDfilltime() {
        return dfilltime;
    }

    public void setDfilltime(Date dfilltime) {
        this.dfilltime = dfilltime;
    }

    public String getIfirstdiseaseage() {
        return ifirstdiseaseage;
    }

    public void setIfirstdiseaseage(String ifirstdiseaseage) {
        this.ifirstdiseaseage = ifirstdiseaseage;
    }

    public String getSsymptomscode() {
        return ssymptomscode;
    }

    public void setSsymptomscode(String ssymptomscode) {
        this.ssymptomscode = ssymptomscode;
    }

    public String getIhospitalizations() {
        return ihospitalizations;
    }

    public void setIhospitalizations(String ihospitalizations) {
        this.ihospitalizations = ihospitalizations;
    }

    public String getSpsychosiscode() {
        return spsychosiscode;
    }

    public void setSpsychosiscode(String spsychosiscode) {
        this.spsychosiscode = spsychosiscode;
    }

    public String getTreatmenteffectcode() {
        return treatmenteffectcode;
    }

    public void setTreatmenteffectcode(String treatmenteffectcode) {
        this.treatmenteffectcode = treatmenteffectcode;
    }

    public int getIhit() {
        return ihit;
    }

    public void setIhit(int ihit) {
        this.ihit = ihit;
    }
}