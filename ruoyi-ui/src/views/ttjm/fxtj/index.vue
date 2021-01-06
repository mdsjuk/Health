<template>
  <div class="app-container">


    <el-row :gutter="10" class="mb8">

      <el-col :span="1.5">
        <!--  <el-button
            type="warning"
            size="mini"
            @click="showimage"
          >统计</el-button>-->
        <el-select v-model="DataBase" placeholder="请选择" @change="changeSelect">
          <el-option
            v-for="(item,index) in DataBaseOptions"
            :key="index"
            :label="item"
            :value="item"
          />
        </el-select>
      </el-col>
      <el-col :span="1.5">
        <el-select v-model="DataBaseBrandSecond" placeholder="请选择" >
          <el-option
            v-for="(item,index) in DropDownTitle"
            :key="index"
            :label="item"
            :value="item"
          />
        </el-select>
        <el-date-picker
              v-model="value1"
              type="year"
              placeholder="选择年">
         </el-date-picker>
                  -
         <el-date-picker
              v-model="value2"
              type="year"
              placeholder="选择年">
           </el-date-picker>
                 
      </el-col>

        <el-button type="cyan" icon="el-icon-search" size="mini" @click="ihitshow">数据统计</el-button>

      <!--按条件搜索-->
    </el-row>
    <!-- 分配角色数据权限对话框 -->
        <div id="main1" class="pie-class" :style="{width: '100%', height: '500px' } " ></div>



  </div>
</template>
<script>
  import {dataGet} from "@/api/ttjm/ttjm";


  export  function daterules(v1,v2) {
    if(v1>v2){
      return false;
    }else if(v1<2010||v2>2019){
      return false;
    }
    return true;
  }
  export default {
    data() {
      return {
       DataBaseOptions: ['精神病数据统计', '居民体检数据统计', '高血压数据统计'],
       DropDownOptions: { '精神病数据统计': ['肇事次数', '自伤次数', '自杀次数','住院次数'],
                          '居民体检数据统计': ['感冒次数', '扭伤次数', '摔伤次数','健康次数'],
                          '高血压数据统计': ['昏倒次数', '头晕次数', '其他次数','另外次数']
                        },

        //testimage: require('@/assets/image/image.jpg'),
        // 遮罩层


        // 非多个禁用

        showSearch: false,
        // 总条数

        // 日期范围
        dateRange: [],
        value1: "2012-01-01",
        value2:"2018-01-01",
        // 状态数据字典
        statusOptions: [],
        // 数据范围选项
        DataBase:"精神病数据统计",
        DataBaseBrandSecond:"肇事次数",
        DropDownTitle: ['肇事次数', '自伤次数', '自杀次数','住院次数'],
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
        //图表参数
        echartstitle: "",
        echartparam:[],
        echartparamx:[],
        echartparamy:[],

        defaultProps: {
          children: "children",
          label: "label"
        },
        //
        arraytj:[],


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

    mounted() {
      // value1: new Date(nowDate.getTime() - 365*10 * 24 * 3600 * 1000);
      // value2:new Date(nowDate.getTime() - 365*5 * 24 * 3600 * 1000);
      this.ihitshow();


    },

    methods:{
      changeSelect(value){
        this.showSearch=true
        this.DropDownTitle = this.DropDownOptions[value]
      },
      ihitshow(){
        let date1= new Date(this.value1);
        let year1=date1.getFullYear();
        let month1=date1.getMonth()+1;
        let day1=date1.getDate()+1;
        this.value1=year1+ '-' + month1+ '-' + day1;
        let date2= new Date(this.value2);
        let year2=date2.getFullYear();
        let month2=date2.getMonth()+1;
        let day2=date2.getDate();
        this.value2=year2+ '-' + month2+ '-' + day2;

        // if(this.daterules(year1,year2)==false){
        //
        // }else{
          dataGet({
            c1:this.DataBase,
            c2:this.DataBaseBrandSecond,
            y1:this.value1,
            y2:this.value2,}).then(

            response => {
              this.echartparamx=[];
              this.echartparamy=[];

              this.echartparam=response.data;
              for (let key in this.echartparam){
                this.echartparamx.push(key.toString());
                this.echartparamy.push(this.echartparam[key])
              }
              this.echartstitle="年人均"+this.DataBaseBrandSecond +"统计"
              let myChart1 =this.$echarts.init(document.getElementById('main1'));
              let option1 = {
                title: {
                  text: this.echartstitle
                },
                tooltip: {},
                legend: {
                  data: this.DataBaseBrandSecond
                },
                xAxis: {
                  data: this.echartparamx
                },
                yAxis: {},
                series: [{
                  name: this.DataBaseBrandSecond,
                  type: 'line',
                  label: {
                    normal: {
                      show: true,
                      position:'top',
                      formatter: '{c}'//百分比显示，模板变量有 {a}、{b}、{c}、{d}，分别表示系列名，数据名，数据值，百分比。{d}数据会根据value值计算百分比
                    }
                  },
                  data:this.echartparamy
                }]
              }
              this.$nextTick(() => {
                myChart1.setOption(option1);

              })
            }
          );


        }



      },
      handleQuery(){}

  }


</script>
