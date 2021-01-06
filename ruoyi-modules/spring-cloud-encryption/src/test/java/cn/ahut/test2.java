package cn.ahut;


import cn.ahut.handleSQL.JudgeSql;
import cn.ahut.untils.BGNOp.DoAdd;
import cn.ahut.untils.CutSQL;
import it.unisa.dia.gas.jpbc.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={NscrlV1Application.class})
public class test2 {
    @Autowired
    private JudgeSql judgeSql;
    @Autowired
    private CutSQL cts;
    @Test
    public void test() throws Exception{
       // String sql="[select] * from @PSYCHOSISPERSONINFO@ where <IHIT>=3";
     //   double t1=System.currentTimeMillis();
        //judgeSql.handleAndRe(sql);
       // double t2= System.currentTimeMillis();
       // System.out.println(t2-t1);
        int i=3;

        System.out.println(i+1+"");
       Element[] count = new Element[1];
        int[] sum = new int[1];


    }
}
