<template>
  <div class="app-container">

    <el-row :gutter="10" class="mb8">

      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >解密</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>

      <el-col :span="1.5">
      <!--  <el-button
          type="warning"
          size="mini"
          @click="showimage"
        >统计</el-button>-->
        <el-dropdown>
          <el-button type="warning">
            按条件统计数据<i class="el-icon-arrow-down el-icon--right"></i>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <el-form :model="queryParams" ref="queryForm" v-show="showSearch" :inline="true">

                <el-form-item label="肇事次数" prop="sguardianname">
                  <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择起始日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
                  </el-col>
                  <el-col class="line" :span="2">-</el-col>
                  <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择终止日期" v-model="form.date2" style="width: 100%;"></el-date-picker>
                  </el-col>
                </el-form-item>
                <el-form-item>
                  <el-button type="cyan" icon="el-icon-search" size="mini" @click="ihitshow">数据统计</el-button>
                </el-form-item>
              </el-form>
            </el-dropdown-item>
            <el-dropdown-item>
              <el-form :model="queryParams" ref="queryForm" v-show="showSearch" :inline="true">

                <el-form-item label="自伤次数" prop="sguardianname">
                  <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择起始日期" v-model="form.date3" style="width: 100%;"></el-date-picker>
                  </el-col>
                  <el-col class="line" :span="2">-</el-col>
                  <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择终止日期" v-model="form.date4" style="width: 100%;"></el-date-picker>
                  </el-col>
                </el-form-item>
                <el-form-item>
                  <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">数据统计</el-button>
                </el-form-item>
              </el-form>
            </el-dropdown-item>
            <el-dropdown-item>
              <el-form :model="queryParams" ref="queryForm" v-show="showSearch" :inline="true">

                <el-form-item label="自杀次数" prop="sguardianname">
                  <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择起始日期" v-model="form.date5" style="width: 100%;"></el-date-picker>
                  </el-col>
                  <el-col class="line" :span="2">-</el-col>
                  <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择终止日期" v-model="form.date6" style="width: 100%;"></el-date-picker>
                  </el-col>
                </el-form-item>
                <el-form-item>
                  <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">数据统计</el-button>
                </el-form-item>
              </el-form>
            </el-dropdown-item>
            <el-dropdown-item>
              <el-form :model="queryParams" ref="queryForm" v-show="showSearch" :inline="true">

                <el-form-item label="住院次数" prop="sguardianname">
                  <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择起始日期" v-model="form.date7" style="width: 100%;"></el-date-picker>
                  </el-col>
                  <el-col class="line" :span="2">-</el-col>
                  <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择终止日期" v-model="form.date8" style="width: 100%;"></el-date-picker>
                  </el-col>
                </el-form-item>
                <el-form-item>
                  <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">数据统计</el-button>
                </el-form-item>
              </el-form>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-col>



      <!--按条件搜索-->
      <el-col :span="1.5">
        <el-dropdown>
          <el-button type="primary">
            按条件搜索<i class="el-icon-arrow-down el-icon--right"></i>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <el-form :model="queryParams" ref="queryForm" v-show="showSearch" :inline="true">

                <el-form-item label="角色名称" prop="sguardianname">
                  <el-input
                    v-model="queryParams.sguardianname"
                    placeholder="请输入角色名称"
                    clearable
                    size="small"
                    style="width: 240px"
                    @keyup.enter.native="handleQuery"
                  />
                </el-form-item>
                <el-form-item>
                  <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                </el-form-item>
              </el-form>
            </el-dropdown-item>
            <el-dropdown-item>
              <el-form :model="queryParams" ref="queryForm" v-show="showSearch" :inline="true">

                <el-form-item label="肇事次数" prop="sguardianname">
                  <el-input
                    v-model="queryParams.sguardianname"
                    placeholder="请输入肇事次数"
                    clearable
                    size="small"
                    style="width: 240px"
                    @keyup.enter.native="handleQuery"
                  />
                </el-form-item>
                <el-form-item>
                  <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                </el-form-item>
              </el-form>
            </el-dropdown-item>
            <el-dropdown-item>
              <el-form :model="queryParams" ref="queryForm" v-show="showSearch" :inline="true">

                <el-form-item label="精神病症状" prop="sguardianname">
                  <el-input
                    v-model="queryParams.sguardianname"
                    placeholder="请输入精神病症状"
                    clearable
                    size="small"
                    style="width: 240px"
                    @keyup.enter.native="handleQuery"
                  />
                </el-form-item>
                <el-form-item>
                  <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                </el-form-item>
              </el-form>
            </el-dropdown-item>
            <el-dropdown-item>
              <el-form :model="queryParams" ref="queryForm" v-show="showSearch" :inline="true">

                <el-form-item label="重症精神病" prop="sguardianname">
                  <el-input
                    v-model="queryParams.sguardianname"
                    placeholder="请输入重症精神病症状"
                    clearable
                    size="small"
                    style="width: 240px"
                    @keyup.enter.native="handleQuery"
                  />
                </el-form-item>
                <el-form-item>
                  <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                </el-form-item>
              </el-form>
            </el-dropdown-item>
            <el-dropdown-item>
              <el-form :model="queryParams" ref="queryForm" v-show="showSearch" :inline="true">

                <el-form-item label="地区" prop="sguardianname">
                  <el-input
                    v-model="queryParams.sguardianname"
                    placeholder="地区"
                    clearable
                    size="small"
                    style="width: 240px"
                    @keyup.enter.native="handleQuery"
                  />
                </el-form-item>
                <el-form-item>
                  <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                </el-form-item>
              </el-form>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-col>


      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <div style="font-size: 14px;color: #A8A8A8 ">
      查询到47条数据，耗时约2s
    </div>

     <el-table v-loading="loading" :data="jsbList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <!--<el-table-column label="患者编号" prop="sno" :show-overflow-tooltip="true"  />-->
        <el-table-column label="患者姓名" prop="sguardianname" :show-overflow-tooltip="true"  />
        <el-table-column label="患者地址(省)" prop="sguardianprovince" :show-overflow-tooltip="true" width="150" />
        <el-table-column label="患者地址(市)" prop="sguardiancity" :show-overflow-tooltip="true" width="150" />
        <el-table-column label="患者地址(县)" prop="sguardiancountry" :show-overflow-tooltip="true" width="150" />
        <el-table-column label="监护人电话" prop="sguardiantelephone" :show-overflow-tooltip="true" width="150" />
        <el-table-column label="患者电话" prop="spatienttelephone" :show-overflow-tooltip="true" width="150" />
        <el-table-column label="责任医生" prop="schargephysician" :show-overflow-tooltip="true" width="150" />
        <el-table-column label="精神病症状" prop="ssymptomscode" :show-overflow-tooltip="true" width="150" />
        <el-table-column label="重症精神病症状" prop="spsychosiscode" :show-overflow-tooltip="true" width="150" />
        <el-table-column label="既往治疗效果" prop="treatmenteffectcode" :show-overflow-tooltip="true" width="150"/>
        <el-table-column label="肇事次数" prop="ihit" :show-overflow-tooltip="true" width="150" />
      </el-table>


    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改角色配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="130px">
        <el-form-item label="患者姓名" prop="sguardianname">
          <el-input v-model="form.sguardianname" placeholder="请输入患者名称" />
        </el-form-item>
        <el-form-item label="患者编号" prop="sno">
          <el-input v-model="form.sno" placeholder="请输入患者编号" />
        </el-form-item>
        <el-form-item label="患者电话" prop="spatienttelephone">
          <el-input v-model="form.spatienttelephone"  />
        </el-form-item>
        <el-form-item label="监护人电话" prop="sguardiantelephone">
          <el-input v-model="form.sguardiantelephone" />
        </el-form-item>
        <el-form-item label="精神病症状代码" prop="ssymptomscode">
          <el-input v-model="form.ssymptomscode"  />
        </el-form-item>
        <el-form-item label="重症精神病代码" prop="spsychosiscode">
          <el-input v-model="form.spsychosiscode"  />
        </el-form-item>
        <el-form-item label="肇事次数" prop="ihit">
          <el-input v-model="form.ihit" />
        </el-form-item>
        <el-form-item label="责任医生" prop="schargephysician">
          <el-input v-model="form.schargephysician" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 肇事次数统计图 -->
    <el-dialog :title="title" :visible.sync="ihitimage" width="500px" append-to-body>
      <div class="block">
        <span class="demonstration">统计数据柱状图</span>
        <el-image :src="testimage"></el-image>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">统计发布</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 分配角色数据权限对话框 -->
    <el-dialog :title="title" :visible.sync="openDataScope" width="500px" append-to-body>
      <el-form :model="form" label-width="80px">
        <el-form-item label="角色名称">
          <el-input v-model="form.roleName" :disabled="true" />
        </el-form-item>
        <el-form-item label="权限字符">
          <el-input v-model="form.roleKey" :disabled="true" />
        </el-form-item>
        <el-form-item label="权限范围">
          <el-select v-model="form.dataScope">
            <el-option
              v-for="item in dataScopeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="数据权限" v-show="form.dataScope == 2">
          <el-checkbox v-model="deptExpand" @change="handleCheckedTreeExpand($event, 'dept')">展开/折叠</el-checkbox>
          <el-checkbox v-model="deptNodeAll" @change="handleCheckedTreeNodeAll($event, 'dept')">全选/全不选</el-checkbox>
          <el-checkbox v-model="form.deptCheckStrictly" @change="handleCheckedTreeConnect($event, 'dept')">父子联动</el-checkbox>
          <el-tree
            class="tree-border"
            :data="deptOptions"
            show-checkbox
            default-expand-all
            ref="dept"
            node-key="id"
            :check-strictly="!form.deptCheckStrictly"
            empty-text="加载中，请稍后"
            :props="defaultProps"
          ></el-tree>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitDataScope">确 定</el-button>
        <el-button @click="cancelDataScope">取 消</el-button>
      </div>
    </el-dialog>

    <div>
      <div id="report" style="width: 1000px;height: 400px;"></div>
    </div>
  </div>
</template>

<script>
  import {changeRoleStatus, dataScope, getRole} from "@/api/system/role";
  import {roleMenuTreeselect, treeselect as menuTreeselect} from "@/api/system/menu";
  import {roleDeptTreeselect, treeselect as deptTreeselect} from "@/api/system/dept";
  import {delJsb, jmJsb, listJsb} from "@/api/ttjm/ttjm";

  export default {
  name: "Jsb",
  data() {
    return {

      testimage: require('@/assets/image/image.jpg'),
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 角色表格数据
      jsbList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      ihitimage: false,
      // 是否显示弹出层（数据权限）
      openDataScope: false,
	  menuExpand: false,
      menuNodeAll: false,
      deptExpand: true,
      deptNodeAll: false,
      // 日期范围
      dateRange: [],
      // 状态数据字典
      statusOptions: [],
      // 数据范围选项
      dataScopeOptions: [
        {
          value: "1",
          label: "全部数据权限"
        },
        {
          value: "2",
          label: "自定数据权限"
        },
        {
          value: "3",
          label: "本部门数据权限"
        },
        {
          value: "4",
          label: "本部门及以下数据权限"
        },
        {
          value: "5",
          label: "仅本人数据权限"
        }
      ],
      // 菜单列表
      menuOptions: [],
      // 部门列表
      deptOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 20,
        Name: undefined,
      },
      // 表单参数
      form: {
        sguardianname: '',
        sno: '',
        spatienttelephone: '',
        sguardiantelephone: '',
        ssymptomscode: '',
        spsychosiscode: '',
        ihit: '',
        schargephysician: ''
      },


      defaultProps: {
        children: "children",
        label: "label"
      },


      // 表单校验
      rules: {
        sguardianname: [
          { required: true, message: "患者姓名不能为空", trigger: "blur" }
        ],
        sno: [
          { required: true, message: "患者不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  /*  this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });*/
  },
  methods: {


    /** 查询菜单树结构 */
    getMenuTreeselect() {
      menuTreeselect().then(response => {
        this.menuOptions = response.data;
      });
    },
    /** 查询部门树结构 */
    getDeptTreeselect() {
      deptTreeselect().then(response => {
        this.deptOptions = response.data;
      });
    },


    // 所有菜单节点数据
    getMenuAllCheckedKeys() {
      // 目前被选中的菜单节点
      let checkedKeys = this.$refs.menu.getHalfCheckedKeys();
      // 半选中的菜单节点
      let halfCheckedKeys = this.$refs.menu.getCheckedKeys();
      checkedKeys.unshift.apply(checkedKeys, halfCheckedKeys);
      return checkedKeys;
    },
    // 所有部门节点数据
    getDeptAllCheckedKeys() {
      // 目前被选中的部门节点
      let checkedKeys = this.$refs.dept.getHalfCheckedKeys();
      // 半选中的部门节点
      let halfCheckedKeys = this.$refs.dept.getCheckedKeys();
      checkedKeys.unshift.apply(checkedKeys, halfCheckedKeys);
      return checkedKeys;
    },
    /** 根据角色ID查询菜单树结构 */
    getRoleMenuTreeselect(roleId) {
      return roleMenuTreeselect(roleId).then(response => {
        this.menuOptions = response.menus;
        return response;
      });
    },
    /** 根据角色ID查询部门树结构 */
    getRoleDeptTreeselect(roleId) {
      return roleDeptTreeselect(roleId).then(response => {
        this.deptOptions = response.depts;
        return response;
      });
    },
    // 角色状态修改
    handleStatusChange(row) {
      let text = row.status === "0" ? "启用" : "停用";
      this.$confirm('确认要"' + text + '""' + row.roleName + '"角色吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return changeRoleStatus(row.roleId, row.status);
        }).then(() => {
          this.msgSuccess(text + "成功");
        }).catch(function() {
          row.status = row.status === "0" ? "1" : "0";
        });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.ihitimage=false;
      this.reset();
    },
    // 取消按钮（数据权限）
    cancelDataScope() {
      this.openDataScope = false;
      this.reset();
    },
    // 表单重置
    reset() {
      if (this.$refs.menu != undefined) {
        this.$refs.menu.setCheckedKeys([]);
      }
	  this.menuExpand = false,
      this.menuNodeAll = false,
      this.deptExpand = true,
      this.deptNodeAll = false,
      this.form = {
        name: undefined,
        idCardNo: undefined,
        bmi: undefined,
        bmi_Suggest: undefined,
        arterial: undefined,
        fastingGlucose: undefined,
        drugName1: undefined,
        doctor: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.sno = selection.map(item => item.sno)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
	// 树权限（展开/折叠）
    handleCheckedTreeExpand(value, type) {
      if (type == 'menu') {
        let treeList = this.menuOptions;
        for (let i = 0; i < treeList.length; i++) {
          this.$refs.menu.store.nodesMap[treeList[i].id].expanded = value;
        }
      } else if (type == 'dept') {
        let treeList = this.deptOptions;
        for (let i = 0; i < treeList.length; i++) {
          this.$refs.dept.store.nodesMap[treeList[i].id].expanded = value;
        }
      }
    },
    // 树权限（全选/全不选）
    handleCheckedTreeNodeAll(value, type) {
      if (type == 'menu') {
        this.$refs.menu.setCheckedNodes(value ? this.menuOptions: []);
      } else if (type == 'dept') {
        this.$refs.dept.setCheckedNodes(value ? this.deptOptions: []);
      }
    },
    // 树权限（父子联动）
    handleCheckedTreeConnect(value, type) {
      if (type == 'menu') {
        this.form.menuCheckStrictly = value ? true: false;
      } else if (type == 'dept') {
        this.form.deptCheckStrictly = value ? true: false;
      }
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      //this.getMenuTreeselect();
      this.open = true;
      this.title = "添加患者信息";
    },
    /** 分配数据权限操作 */
    handleDataScope(row) {
      this.reset();
      const roleDeptTreeselect = this.getRoleDeptTreeselect(row.roleId);
      getRole(row.roleId).then(response => {
        this.form = response.data;
        this.openDataScope = true;
        this.$nextTick(() => {
          roleDeptTreeselect.then(res => {
            this.$refs.dept.setCheckedKeys(res.checkedKeys);
          });
        });
        this.title = "分配数据权限";
      });
    },

    submitForm: function() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          setTimeout(() =>{
            this.msgSuccess("添加患者"+this.form.sguardianname+"成功");
            this.open = false;
            this.getList();
          },1050);
        } else {
          this.msgSuccess("添加患者"+this.form.sguardianname+"成功");
        }
      });
    },


    /** 提交按钮（数据权限） */
    submitDataScope: function() {
      if (this.form.roleId != undefined) {
        this.form.deptIds = this.getDeptAllCheckedKeys();
        dataScope(this.form).then(response => {
          if (response.code === 200) {
            this.msgSuccess("修改成功");
            this.openDataScope = false;
            this.getList();
          }
        });
      }
    },


    handleDelete() {
      const sno=this.sno;
      this.$confirm('是否确认删除身份证号为"' + sno + '"的数据项?', "警告", {
        distinguishCancelAndClose: true,
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: "warning"
      })
        .then(() => {
          return delJsb(sno);
        })
        .then(()=>{
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(()=>{

        });
    },
    /** 解密按钮操作 */
    handleUpdate() {
      const sno=this.sno;
      jmJsb(sno).then(
        response => {
          //debugger
          this.jsbList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },

    showimage(){

    },

    ihitshow(){
      this.reset();
      //this.getMenuTreeselect();
      this.ihitimage = true;
      this.loading = false;
    },

    /** 查询角色列表 */
    getList() {
      this.loading = true;
      listJsb(this.addDateRange(this.queryParams, this.dateRange)).then(
        response => {
          //debugger
          this.jsbList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
  }
};
</script>


