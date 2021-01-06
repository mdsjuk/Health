package cn.ahut.Mapper;

import cn.ahut.entity.PersonInfo;
import cn.ahut.entity.code_id.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper

public interface personMapper {

     @Select("select * from psychosispersoninfo")
     List<PersonInfo> selectAll();
     void  addPerson(PersonInfo person);
     List<PersonInfo> selectihz(int m);

     List<FirstAge> selectfa();

     @Select( "insert into FIRSTDISEASEAGE values (#{id},#{age},#{pid,jdbcType=BLOB})")
     void insertFristage(FirstAge fa);

     @Insert("insert into SYMCODE values(#{id},#{code},#{pid,jdbcType=BLOB})")
     void insertSysmcode(SysmCode sysmcode);

     @Select("select * from SYMCODE order by 'id' asc")
     List<SysmCode> selectsysmcode();
     @Select("select * from IHIT order by 'id' asc")
     List<Ihit> selectIHIT();


     @Insert("insert  into HOSPITALIZATIONS values(#{id},#{hosps},#{pid,jdbcType=BLOB})")
     void inserthosp(Hosp hosp);

     //@Select("select * from HOSPITALIZATIONS order by 'id' asc")
     List<Hosp> selecthosp();

     @Insert("insert into SYSCHCODE values(#{id},#{sychcode},#{pid,jdbcType=BLOB})")
     void insertsyschcode(Syschcode syschcode);


     List<Syschcode> selectsyscode();

     @Insert("insert into TREAT values(#{id},#{treat},#{pid,jdbcType=BLOB})")
     void inserttreat(Treat trea);

     List<Treat> selecttreat();
     @Insert("insert into IHIT values(#{id},#{hit},#{pid,jdbcType=BLOB})")
     void inserthit(Ihit ihit);

     @Insert("insert into ISUICIDE values(#{id},#{sucide},#{pid,jdbcType=BLOB})")
     void insertisuicide(Isuicide isuicide);

     @Insert("insert into IATTEMPTE values(#{id},#{attempt},#{pid,jdbcType=BLOB})")
     void insertiattempt(Iattempt iattempt);

     List<Ihit> selectihit();
     List<Isuicide> selectsucide();
     List<Iattempt> selectattempt();



     void updateFristage_code(FirstAge fa);
     void updateSysmcode(SysmCode sc);

     void updatehosp(Hosp hosp);

     void updatesyschcode(Syschcode syschcode);

     void updatetreat(Treat trea);

     void updatehit(Ihit ihit);
     void updatesucide(Isuicide isuicide);
     void updateattempt(Iattempt iattempt);


}
