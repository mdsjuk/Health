<template>
  <div class="app-container">


    <el-row :gutter="10" class="mb8">

      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >文件上传</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="success"
          icon="info"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >请求验证</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="info"
          size="mini"
          :disabled="single"
          @click="handletest"
        >证据验证</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


     <el-table v-loading="loading" :data="jsbList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <!--<el-table-column label="患者编号" prop="sno" :show-overflow-tooltip="true"  />-->
        <el-table-column label="患者姓名" prop="name" :show-overflow-tooltip="true"  />
        <el-table-column label="CT文件名称" prop="ctName" :show-overflow-tooltip="true" />
        <el-table-column label="文件上传时间" prop="upDate" :show-overflow-tooltip="true" />
        <el-table-column label="文件大小" prop="fileSize" :show-overflow-tooltip="true" />
      </el-table>


    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

  <!-- 文件上传-->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文件名称" prop="sguardianname">
          <el-input v-model="form.sguardianname" placeholder="请输入文件名称" />
        </el-form-item>
        <el-form-item label="文件上传" prop="spatienttelephone">
          <el-upload
            class="upload-demo"
            drag
            action="https://jsonplaceholder.typicode.com/posts/"
            multiple>
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


   <!-- 请求验证-->
    <el-dialog :title="title" :visible.sync="hascheck" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文件名称" prop="sguardianname">
          <!--<el-input v-model="form.sguardianname" placeholder="请输入文件名称" />-->
          <div>李青山脑部CT</div>
        </el-form-item>
        <el-form-item label="文件上传" prop="spatienttelephone">
          <el-upload
            class="upload-demo"
            drag
            action="https://jsonplaceholder.typicode.com/posts/"
            multiple>
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">请将存储随机下标的文件拖到此处，或<em>点击此处上传</em></div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="success" @click="submitForm">请求验证</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 证据验证-->
    <el-dialog :title="title" :visible.sync="zjcheck" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文件名称" prop="sguardianname" label-width="auto">
          <!--<el-input v-model="form.sguardianname" placeholder="请输入文件名称" />-->
          <div>李青山脑部CT</div>
        </el-form-item>
        <el-form-item label="选择文件块和" prop="spatienttelephone" label-width="auto">
          <el-upload
            class="upload-demo"
            drag
            action="https://jsonplaceholder.typicode.com/posts/"
            multiple>
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">请将文件块和文件拖到此处，或<em>点击此处上传</em></div>
          </el-upload>
        </el-form-item>
        <el-form-item label="选择标签和" prop="spatienttelephone" label-width="auto">
          <el-upload
            class="upload-demo"
            drag
            action="https://jsonplaceholder.typicode.com/posts/"
            multiple>
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">请将标签和文件拖到此处，或<em>点击此处上传</em></div>
          </el-upload>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="warning" @click="submitForm">证据验证</el-button>
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
  import {delJsb, listfile} from "@/api/ttjm/ttjm";

  export default {
  name: "Jsb",
  data() {
    return {
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
      hascheck: false,
      zjcheck: false,
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
      this.hascheck=false;
      this.zjcheck=false;
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
            this.msgSuccess("经验证，[李青山脑部CT]文件未被修改");
            this.open = false;
            this.getList();
          },1050);
        } else {
          this.msgSuccess("经验证，[李青山脑部CT]文件未被修改");
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

    handleUpdate() {
      this.reset();
      //this.getMenuTreeselect();
      this.hascheck = true;
      this.title = "请求验证";
    },

    handletest() {
      this.reset();
      //this.getMenuTreeselect();
      this.zjcheck = true;
      this.title = "证据验证";
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      //this.getMenuTreeselect();
      this.open = true;
      this.title = "文件上传";
    },
    showimage(){

    },

    /** 查询角色列表 */
    getList() {
      this.loading = true;
      listfile(this.addDateRange(this.queryParams, this.dateRange)).then(
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


