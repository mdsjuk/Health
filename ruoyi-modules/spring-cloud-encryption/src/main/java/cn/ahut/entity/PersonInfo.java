package cn.ahut.entity;


import java.math.BigDecimal;
import java.util.Date;


public class PersonInfo {
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
    private byte[] ifirstdiseaseage;

    private byte[] ssymptomscode;

    private byte[] ihospitalizations;

    private byte[] spsychosiscode;

    private byte[] treatmenteffectcode;

    private byte[] ihit;
    private byte[] isuicide ;
    private byte[] iattemptedsuicide;

    public byte[] getIsuicide() {
        return isuicide;
    }

    public void setIsuicide(byte[] isuicide) {
        this.isuicide = isuicide;
    }

    public byte[] getIattemptedsuicide() {
        return iattemptedsuicide;
    }

    public void setIattemptedsuicide(byte[] iattemptedsuicide) {
        this.iattemptedsuicide = iattemptedsuicide;
    }

    public byte[] getIfirstdiseaseage() {
        return ifirstdiseaseage;
    }

    public void setIfirstdiseaseage(byte[] ifirstdiseaseage) {
        this.ifirstdiseaseage = ifirstdiseaseage;
    }

    public byte[] getSsymptomscode() {
        return ssymptomscode;
    }

    public void setSsymptomscode(byte[] ssymptomscode) {
        this.ssymptomscode = ssymptomscode;
    }

    public byte[] getIhospitalizations() {
        return ihospitalizations;
    }

    public void setIhospitalizations(byte[] ihospitalizations) {
        this.ihospitalizations = ihospitalizations;
    }

    public byte[] getSpsychosiscode() {
        return spsychosiscode;
    }

    public void setSpsychosiscode(byte[] spsychosiscode) {
        this.spsychosiscode = spsychosiscode;
    }

    public byte[] getTreatmenteffectcode() {
        return treatmenteffectcode;
    }

    public void setTreatmenteffectcode(byte[] treatmenteffectcode) {
        this.treatmenteffectcode = treatmenteffectcode;
    }

    public byte[] getIhit() {
        return ihit;
    }

    public void setIhit(byte[] ihit) {
        this.ihit = ihit;
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
        this.sno = sno == null ? null : sno.trim();
    }

    public String getSguardianname() {
        return sguardianname;
    }

    public void setSguardianname(String sguardianname) {
        this.sguardianname = sguardianname == null ? null : sguardianname.trim();
    }

    public String getSguardianprovince() {
        return sguardianprovince;
    }

    public void setSguardianprovince(String sguardianprovince) {
        this.sguardianprovince = sguardianprovince == null ? null : sguardianprovince.trim();
    }

    public String getSguardiancity() {
        return sguardiancity;
    }

    public void setSguardiancity(String sguardiancity) {
        this.sguardiancity = sguardiancity == null ? null : sguardiancity.trim();
    }

    public String getSguardiancountry() {
        return sguardiancountry;
    }

    public void setSguardiancountry(String sguardiancountry) {
        this.sguardiancountry = sguardiancountry == null ? null : sguardiancountry.trim();
    }

    public String getSguardianvillage() {
        return sguardianvillage;
    }

    public void setSguardianvillage(String sguardianvillage) {
        this.sguardianvillage = sguardianvillage == null ? null : sguardianvillage.trim();
    }

    public String getSguardianhouseno() {
        return sguardianhouseno;
    }

    public void setSguardianhouseno(String sguardianhouseno) {
        this.sguardianhouseno = sguardianhouseno == null ? null : sguardianhouseno.trim();
    }

    public String getSguardiantelephone() {
        return sguardiantelephone;
    }

    public void setSguardiantelephone(String sguardiantelephone) {
        this.sguardiantelephone = sguardiantelephone == null ? null : sguardiantelephone.trim();
    }

    public String getSpatienttelephone() {
        return spatienttelephone;
    }

    public void setSpatienttelephone(String spatienttelephone) {
        this.spatienttelephone = spatienttelephone == null ? null : spatienttelephone.trim();
    }

    public String getSchargephysician() {
        return schargephysician;
    }

    public void setSchargephysician(String schargephysician) {
        this.schargephysician = schargephysician == null ? null : schargephysician.trim();
    }

    public String getSfirstdiseasetime() {
        return sfirstdiseasetime;
    }

    public void setSfirstdiseasetime(String sfirstdiseasetime) {
        this.sfirstdiseasetime = sfirstdiseasetime == null ? null : sfirstdiseasetime.trim();
    }

    public String getSdoctoradvice() {
        return sdoctoradvice;
    }

    public void setSdoctoradvice(String sdoctoradvice) {
        this.sdoctoradvice = sdoctoradvice == null ? null : sdoctoradvice.trim();
    }

    public Date getDfilltime() {
        return dfilltime;
    }

    public void setDfilltime(Date dfilltime) {
        this.dfilltime = dfilltime;
    }
}