package com.ruoyi.system.mapper;

import com.ruoyi.system.api.domain.SysGXY;
import com.ruoyi.system.api.domain.SysTNB;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wzw on 2020/10/27.
 */
@Service
public interface SysYLSJMapper {
    /**
     * 查询所有糖尿病角色
     *
     * @return 角色列表
     */
    List<SysTNB> selectAllPeople(SysTNB sysTNB);

    /**
     * 查看糖尿病患者是否存在
     * @param idCardNo
     * @return
     */
    List<SysTNB> selectById(String idCardNo);

    /**
     * 新增糖尿病患者
     * @return 结果
     */
     int insertTnb(SysTNB sysTNB);

    /**
     * 按照身份证号批量删除糖尿病患者信息
     * @return
     */
     int deleteTnbById(String[] idCardNo);


    /**
     * 查询所有高血压角色
     *
     * @return 角色列表
     */
    List<SysGXY> selectAllGXY(SysGXY sysGXY);

    /**
     * 查看高血压患者是否存在
     * @param idCardNo
     * @return
     */
    List<SysGXY> selectByGxyId(String idCardNo);

    /**
     * 新增高血压患者
     * @return 结果
     */
    int insertGxy(SysGXY sysGXY);

    /**
     * 按照身份证号批量删除高血压患者信息
     * @return
     */
    int deleteGxyById(String[] idCardNo);
}
