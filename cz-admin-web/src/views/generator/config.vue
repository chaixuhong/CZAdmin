<template>
  <div class="app-container">
    <el-row :gutter="15">
      <el-col style="margin-bottom: 10px">
        <el-card class="box-card" shadow="never">
          <div slot="header" class="clearfix">
            <span class="role-span">字段配置：{{ tableName }}</span>
            <el-button :loading="columnLoading" icon="el-icon-check" size="mini" style="float: right; padding: 6px 9px;"
                       type="primary" @click="saveColumnConfig">保存</el-button>
            <el-tooltip class="item" effect="dark" content="数据库中表字段变动时使用该功能" placement="top-start">
              <el-button :loading="syncLoading" icon="el-icon-refresh" size="mini"
                         style="float: right; padding: 6px 9px;margin-right: 9px;" type="info" @click="sync">同步
              </el-button>
            </el-tooltip>
          </div>
          <el-form size="small" label-width="90px">
            <el-table v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;margin-bottom: 15px">
              <el-table-column prop="columnName" label="字段名称" />
              <el-table-column prop="columnType" label="字段类型" />
              <el-table-column prop="remark" label="字段描述">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.remark" size="mini" class="edit-input" />
                </template>
              </el-table-column>
              <el-table-column align="center" label="必填" width="70px">
                <template slot-scope="scope">
                  <el-checkbox v-model="scope.row.notNull" />
                </template>
              </el-table-column>
              <el-table-column align="center" label="列表" width="70px">
                <template slot-scope="scope">
                  <el-checkbox v-model="scope.row.listShow" />
                </template>
              </el-table-column>
              <el-table-column align="center" label="表单" width="70px">
                <template slot-scope="scope">
                  <el-checkbox v-model="scope.row.formShow" />
                </template>
              </el-table-column>
              <el-table-column label="表单类型">
                <template slot-scope="scope">
                  <el-select v-model="scope.row.formType" filterable class="edit-input" clearable size="mini"
                             placeholder="请选择">
                    <el-option label="文本框" value="Input" />
                    <el-option label="文本域" value="Textarea" />
                    <el-option label="单选框" value="Radio" />
                    <el-option label="下拉框" value="Select" />
                    <el-option label="日期框" value="Date" />
                  </el-select>
                </template>
              </el-table-column>
              <el-table-column label="查询方式">
                <template slot-scope="scope">
                  <el-select v-model="scope.row.queryType" filterable class="edit-input" clearable size="mini"
                             placeholder="请选择">
                    <el-option label="=" value="=" />
                    <el-option label="!=" value="!=" />
                    <el-option label=">=" value=">=" />
                    <el-option label="<=" value="<=" />
                    <el-option label="Like" value="Like" />
                    <el-option label="NotNull" value="NotNull" />
                    <el-option label="BetWeen" value="BetWeen" />
                  </el-select>
                </template>
              </el-table-column>
              <el-table-column label="关联字典">
                <template slot-scope="scope">
                  <el-select v-model="scope.row.dictName" filterable class="edit-input" clearable size="mini"
                             placeholder="请选择"
                             :disabled="scope.row.columnType === 'date' || scope.row.columnType === 'datetime' || scope.row.columnType === 'timestamp' ">
                    <el-option v-for="item in dicts" :key="item.dictId"
                               :label="item.description === '' ? item.name : item.description" :value="item.name" />
                  </el-select>
                </template>
              </el-table-column>
            </el-table>
          </el-form>
        </el-card>
      </el-col>
      <el-col>
        <el-card class="box-card" shadow="never">
          <div slot="header" class="clearfix">
            <span class="role-span">生成配置</span>
            <el-button :loading="configLoading" icon="el-icon-check" size="mini" style="float: right; padding: 6px 9px"
                       type="primary" @click="saveConfig">保存</el-button>
          </div>
          <el-form ref="form" :model="form" :rules="rules" size="small" label-width="78px">
            <el-form-item label="作者名称" prop="author">
              <el-input v-model="form.author" style="width: 40%" />
              <span style="color: #C0C0C0;margin-left: 10px;">类上面的作者名称</span>
            </el-form-item>
            <el-form-item label="模块名称" prop="moduleName">
              <el-input v-model="form.moduleName" style="width: 40%" />
              <span style="color: #C0C0C0;margin-left: 10px;">模块的名称，请选择项目中已存在的模块</span>
            </el-form-item>
            <el-form-item label="至于包下" prop="pack">
              <el-input v-model="form.pack" style="width: 40%" />
              <span style="color: #C0C0C0;margin-left: 10px;">项目包的名称，生成的代码放到哪个包里面</span>
            </el-form-item>
            <el-form-item label="接口名称" prop="apiAlias">
              <el-input v-model="form.apiAlias" style="width: 40%" />
              <span style="color: #C0C0C0;margin-left: 10px;">接口的名称，用于控制器与接口文档中</span>
            </el-form-item>
            <el-form-item label="前端路径" prop="path">
              <el-input v-model="form.path" style="width: 40%" />
              <span style="color: #C0C0C0;margin-left: 10px;">输入views文件夹下的目录，不存在即创建</span>
            </el-form-item>
            <el-form-item label="去表前缀" prop="prefix">
              <el-input v-model="form.prefix" placeholder="默认不去除表前缀" style="width: 40%" />
              <span style="color: #C0C0C0;margin-left: 10px;">默认不去除表前缀，可自定义</span>
            </el-form-item>
            <el-form-item label="是否覆盖" prop="cover">
              <el-radio-group v-model="form.cover" size="mini" style="width: 40%">
                <el-radio-button label="true">是</el-radio-button>
                <el-radio-button label="false">否</el-radio-button>
              </el-radio-group>
              <span style="color: #C0C0C0;margin-left: 10px;">谨防误操作，请慎重选择</span>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import * as crudGenConfig from '@/api/generator/genConfig'
import { getDicts } from '@/api/system/dict'
const defaultForm = { 'apiAlias': '', 'apiPath': '', 'author': '', 'configId': null, 'cover': false, 'moduleName': '', 'pack': '', 'path': '', 'prefix': '', 'tableName': '' }
export default {
  name: 'GeneratorConfig',
  components: {},
  cruds () {
    return CRUD({ url: `${crudGenConfig.model_url}/columns`, queryOnPresenterCreated: false })
  },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  data () {
    return {
      activeName: 'first',
      tableName: '',
      columnLoading: false,
      configLoading: false,
      dicts: [],
      syncLoading: false,
      genLoading: false,
      rules: {
        author: [
          { required: true, message: '作者不能为空', trigger: 'blur' }
        ],
        pack: [
          { required: true, message: '包路径不能为空', trigger: 'blur' }
        ],
        moduleName: [
          { required: true, message: '包路径不能为空', trigger: 'blur' }
        ],
        path: [
          { required: true, message: '前端路径不能为空', trigger: 'blur' }
        ],
        apiAlias: [
          { required: true, message: '接口名称不能为空', trigger: 'blur' }
        ],
        cover: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    this.tableName = this.$route.params.tableName
    this.crud.query = { tableName: this.tableName }
    this.crud.toQuery()// 获取字段信息
    this.getColumns()
    this.$nextTick(() => {
      this.getDicts()
    })
  },
  methods: {
    // 获取所有字典
    getDicts () {
      getDicts().then(res => {
        this.dicts = res.data
      })
    },
    // 获取包配置信息
    getColumns () {
      const params = { tableName: this.tableName }
      crudGenConfig.getConfig(params).then(res => {
        this.form = res.data
      })
    },
    // 保存字段配置
    saveColumnConfig () {
      this.columnLoading = true
      crudGenConfig.saveColumns(this.crud.data).then(res => {
        this.crud.notify('保存成功', 'success')
        this.crud.data = res.data
        this.columnLoading = false
      }).catch(err => {
        this.columnLoading = false
        console.log(err)
      })
    },
    // 保存包配置
    saveConfig () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.configLoading = true
          crudGenConfig.saveConfig(this.form).then(res => {
            this.crud.notify('保存成功', 'success')
            this.form = res.data
            this.configLoading = false
          }).catch(err => {
            this.configLoading = false
            console.log(err.response.data.message)
          })
        }
      })
    },
    sync () {
      this.syncLoading = true
      crudGenConfig.syncColumns([this.tableName]).then(() => {
        this.crud.notify('同步成功', 'success')
        this.crud.toQuery()
        this.syncLoading = false
      }).then(() => {
        this.syncLoading = false
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
.edit-input {
  .el-input__inner {
    border: 1px solid #e5e6e7;
  }
}
</style>

<style scoped>
::v-deep .input-with-select .el-input-group__prepend {
  background-color: #fff;
}
</style>
