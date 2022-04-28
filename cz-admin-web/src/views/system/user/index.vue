<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--侧边部门数据-->
      <el-col :xs="24" :sm="6" :md="5" :lg="4" :xl="4">
        <div class="head-container">
          <el-input v-model="deptName" clearable size="small" placeholder="输入部门名称搜索" prefix-icon="el-icon-search"
                    class="filter-item" @input="getDeptDatas" />
        </div>
        <el-tree v-loading="loading" :data="deptDatas" :props="defaultProps" :expand-on-click-node="false"
                 @node-click="handleNodeClick" />
      </el-col>
      <!--用户数据-->
      <el-col :xs="24" :sm="18" :md="19" :lg="20" :xl="20">
        <!--工具栏-->
        <div class="head-container">
          <div v-if="crud.props.searchToggle">
            <!-- 搜索 -->
            <el-input v-model="query.blurry" clearable size="small" placeholder="输入名称或者邮箱搜索" style="width: 200px;"
                      class="filter-item" @keyup.enter.native="crud.toQuery" />
            <date-range-picker v-model="query.dataRange" class="date-item" />
            <el-select v-model="query.enabled" clearable size="small" placeholder="状态" class="filter-item"
                       style="width: 90px">
              <el-option v-for="item in statusOptions" :key="item.key" :label="item.label" :value="item.value" />
            </el-select>
            <rrOperation />
          </div>
          <crudOperation :permission="permission" :hidden-columns="['createBy']" />
        </div>
        <!--表单渲染-->
        <el-dialog append-to-body :close-on-click-modal="false" :before-close="crud.cancelCU"
                   :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="570px">
          <el-form ref="form" :inline="true" :model="form" :rules="rules" size="small" label-width="66px">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="form.username" />
            </el-form-item>
            <el-form-item label="电话" prop="phone">
              <el-input v-model.number="form.phone" />
            </el-form-item>
            <el-form-item label="昵称" prop="nickName">
              <el-input v-model="form.nickName" />
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" />
            </el-form-item>
            <el-form-item label="部门" prop="deptId">
              <treeselect v-model="form.deptId" :options="deptDatas" :normalizer="normalizer" style="width: 178px"
                          show-count placeholder="选择部门" />
            </el-form-item>
            <el-form-item label="岗位" prop="jobId">
              <el-select v-model="form.jobId" style="width: 178px" filterable default-first-option placeholder="请选择岗位">
                <el-option v-for="item in jobDatas" :key="item.jobId" :label="item.name" :value="item.jobId" />
              </el-select>
            </el-form-item>
            <el-form-item label="性别">
              <el-radio-group v-model="form.gender" style="width: 178px">
                <el-radio label="男">男</el-radio>
                <el-radio label="女">女</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="状态">
              <el-radio-group v-model="form.enabled" :disabled="form.userId === user.userId">
                <el-radio v-for="(item,index) in statusOptions" :key="index" :label="item.value">
                  {{ item.label }}
                </el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item style="margin-bottom: 0;" label="角色" prop="roles">
              <el-select v-model="form.roles" style="width: 437px" multiple placeholder="请选择角色">
                <el-option v-for="item in roles" :key="item.name" :disabled="level !== 1 && item.level <= level"
                           :label="item.name" :value="item.roleId" />
              </el-select>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="text" @click="crud.cancelCU">取消</el-button>
            <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
          </div>
        </el-dialog>
        <!--表格渲染-->
        <el-table ref="table" v-loading="crud.loading" :data="crud.data" :height="windowHeight-280"
                  @selection-change="crud.selectionChangeHandler">
          <el-table-column :selectable="checkboxT" type="selection" width="55" />
          <el-table-column :show-overflow-tooltip="true" prop="username" label="用户名" />
          <el-table-column :show-overflow-tooltip="true" prop="nickName" label="昵称" />
          <el-table-column prop="gender" label="性别" />
          <el-table-column :show-overflow-tooltip="true" prop="phone" width="100" label="电话" />
          <el-table-column :show-overflow-tooltip="true" width="135" prop="email" label="邮箱" />
          <el-table-column prop="jobName" label="岗位" />
          <el-table-column prop="deptName" label="部门" />
          <el-table-column :show-overflow-tooltip="true" prop="deptPath" label="部门路径" />
          <el-table-column label="账号状态" align="center" prop="enabled">
            <template slot-scope="scope">
              <span v-if="scope.row.enabled">正常</span>
              <span v-else>锁定</span>
            </template>
          </el-table-column>
          <el-table-column :show-overflow-tooltip="true" prop="createTime" width="135" label="创建日期" />
          <el-table-column :show-overflow-tooltip="true" prop="createBy" width="135" label="创建人" />

          <el-table-column label="操作" width="120" align="center" fixed="right">
            <template slot-scope="scope">
              <udOperation :data="scope.row" :permission="permission"
                           :disabled-dle="scope.row.userId === user.userId" />
            </template>
          </el-table-column>

        </el-table>
        <!--分页组件-->
        <pagination />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import DateRangePicker from '@/components/DateRangePicker'
import * as crudJob from '@/api/system/job'
import * as crudUser from '@/api/system/user'
import { validPhone } from '@/utils/form-rules'
import { list, getLevel, getRoleIds } from '@/api/system/roles'
import { getDepts } from '@/api/system/dept'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'
import { mapGetters } from 'vuex'
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
const defaultForm = { userId: null, username: null, nickName: null, jobId: '', gender: '男', email: null, enabled: true, roles: [], deptId: null, phone: null }
export default {
  name: 'User',
  components: { crudOperation, rrOperation, udOperation, pagination, DateRangePicker, Treeselect },
  cruds () {
    return CRUD({ title: '用户', url: 'system/user', idField: 'userId', sort: 'userId,asc', crudMethod: { ...crudUser } })
  },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  data () {
    return {
      deptName: '',
      deptDatas: [],
      jobDatas: [],
      level: 3,
      roles: [],
      loading: false,
      timer: '',
      defaultProps: { children: 'children', label: 'name' },
      permission: {
        add: ['user:add'],
        edit: ['user:edit'],
        del: ['user:del']
      },
      statusOptions: [
        { value: true, label: '激活' },
        { value: false, label: '锁定' }
      ],
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ],
        nickName: [
          { required: true, message: '请输入用户昵称', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }
        ],
        phone: [
          { required: true, trigger: 'blur', validator: validPhone }
        ]
      },
      normalizer (node) {
        return {
          id: node.deptId,
          label: node.name,
          children: node.children.length > 0 ? node.children : undefined
        }
      }
    }
  },
  computed: {
    ...mapGetters(['user'])
  },
  mounted () {
    this.getDeptDatas()
    this.getRoleLevel()
  },
  methods: {
    getDeptDatas (name) {
      clearTimeout(this.timer)
      let delay = 200 // 防抖输入
      this.loading = true
      const params = {}
      if (name) {
        params['name'] = name
        delay = 800
      }
      this.timer = setTimeout(() => {
        getDepts(params).then(res => {
          this.loading = false
          clearTimeout(this.timer)
          this.deptDatas = res.data
        }).catch(() => {
          this.loading = false
        })
      }, delay)
    },
    // 切换部门请求列表数据
    handleNodeClick (data) {
      this.query.deptPath = data.path
      this.crud.toQuery()
    },
    // 弹窗前
    [CRUD.HOOK.beforeToCU] (crud, form) {
      this.getAllJob()
      this.getRoles()
    },
    // 初始化编辑时候的角色与岗位
    [CRUD.HOOK.beforeToEdit] (crud, form) {
      const params = {
        userId: this.form.userId
      }
      getRoleIds(params).then(res => {
        this.form.roles = res.data
      })
    },
    // 提交前做的操作
    [CRUD.HOOK.afterValidateCU] (crud) {
      if (this.form.roles.length === 0) {
        this.$message({
          message: '请选择角色',
          type: 'warning'
        })
        return false
      }
      if (!crud.form.deptId) {
        this.$message({
          message: '部门不能为空',
          type: 'warning'
        })
        return false
      }
    },
    // 获取弹窗内角色数据
    getRoles () {
      if (this.roles.length === 0) {
        list({ page: 1, size: 9999 }).then(res => {
          this.roles = res.data.records
        }).catch((e) => {
          console.warn(e)
        })
      }
    },
    // 获取权限级别
    getRoleLevel () {
      getLevel().then(res => {
        this.level = res.data.level
      })
    },
    getAllJob () {
      if (this.jobDatas.length === 0) {
        crudJob.all().then(res => {
          this.jobDatas = res.data
        }).catch(_ => { })
      }
    },
    checkboxT (row, rowIndex) {
      return row.userId !== this.user.userId
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
::v-deep .vue-treeselect__control,
::v-deep .vue-treeselect__placeholder,
::v-deep .vue-treeselect__single-value {
  height: 30px;
  line-height: 30px;
}
</style>
