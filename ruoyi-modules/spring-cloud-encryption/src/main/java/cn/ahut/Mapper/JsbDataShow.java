package cn.ahut.Mapper;


import cn.ahut.entity.DictInfo;
import cn.ahut.entity.FileDemo;
import cn.ahut.entity.Psychosispersoninfop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wzw on 2020/10/29.
 */

@Service
@Mapper
public interface JsbDataShow {
    /**
     * 获取所有精神病患者数据
     * @param psychosispersoninfop
     * @return
     */
    List<Psychosispersoninfop> selectAllJsb(Psychosispersoninfop psychosispersoninfop);

    /**
     * 按照患者编号删除患者信息
     * @return
     */
    int deleteJsbBySno(String[] sno);

    /**
     * 按照编号获取患者信息
     * @param sno
     * @return
     */
    Psychosispersoninfop selectOneBySno(String sno);

    /**
     * 根据代码获取信息
     * @param code
     * @return
     */
    DictInfo selectByIhitCode(int code); //肇事

    DictInfo selectByZzCode(int code); //重症

    DictInfo selectByJsbCode(int code);//精神病

    DictInfo selectByZlCode(int code);//治疗情况


    /**
     * 获取文件信息
     */
    List<FileDemo> selectAllFile();
}
