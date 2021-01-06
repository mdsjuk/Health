package com.ruoyi.system.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.system.api.domain.SysGXY;
import com.ruoyi.system.api.domain.SysTNB;
import com.ruoyi.system.mapper.SysYLSJMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门信息
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/ylsj")
public class SysYlsjController extends BaseController
{
    @Autowired
    private SysYLSJMapper sysYLSJMapper;

    //@PreAuthorize(hasPermi = "system:role:list")
    @GetMapping("/tnb/list")
    public TableDataInfo list(SysTNB sysTNB)
    {
        startPage();
        List<SysTNB> list = sysYLSJMapper.selectAllPeople(sysTNB);
        return getDataTable(list);
    }

    /**
     * 新增糖尿病患者
     */
    @PostMapping("/tnb")
    public AjaxResult add(@Validated @RequestBody SysTNB sysTNB)
    {
        if(sysYLSJMapper.selectById(sysTNB.getIdCardNo()).size()==0){
         return toAjax(sysYLSJMapper.insertTnb(sysTNB));
        }
        return AjaxResult.error("新增患者'" + sysTNB.getName() + "'失败，患者身份信息已存在");
    }

    /**
     * 删除糖尿病患者
     */
    @DeleteMapping("/tnb/{idCardNo}")
    public AjaxResult remove(@PathVariable String[] idCardNo)
    {
        return toAjax(sysYLSJMapper.deleteTnbById(idCardNo));
    }

    /**
     * 查询高血压角色
     * @return
     */
    @GetMapping("/gxy/list")
    public TableDataInfo list(SysGXY sysGXY)
    {
        startPage();
        List<SysGXY> list = sysYLSJMapper.selectAllGXY(sysGXY);
        return getDataTable(list);
    }


    /**
     * 新增高血压患者
     */
    @PostMapping("/gxy")
    public AjaxResult add(@Validated @RequestBody SysGXY sysGXY)
    {
        if(sysYLSJMapper.selectByGxyId(sysGXY.getIdCardNo()).size()==0){
            return toAjax(sysYLSJMapper.insertGxy(sysGXY));
        }
        return AjaxResult.error("新增患者'" + sysGXY.getName() + "'失败，患者身份信息已存在");
    }

    /**
     * 删除高血压患者
     */
    @DeleteMapping("/gxy/{idCardNo}")
    public AjaxResult removeGxy(@PathVariable String[] idCardNo)
    {
        return toAjax(sysYLSJMapper.deleteGxyById(idCardNo));
    }
}
