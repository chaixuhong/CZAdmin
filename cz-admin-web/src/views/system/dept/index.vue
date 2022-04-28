<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <el-input v-model="query.name" clearable size="small" placeholder="输入部门名称搜索" style="width: 200px;"
                  class="filter-item" @keyup.enter.native="crud.toQuery" />
        <rrOperation />
      </div>
      <crudOperation :permission="permission" />
    </div>
    <!--表单组件-->
    <el-dialog append-to-body :close-on-click-modal="false" :before-close="crud.cancelCU"
               :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
      <el-form ref="form" inline :model="form" :rules="rules" size="small" label-width="80px">
        <el-form-item label="部门名称" prop="name">
          <el-input v-model="form.name" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="部门排序" prop="deptSort">
          <el-input-number v-model.number="form.deptSort" :min="0" :max="999" controls-position="right"
                           style="width: 370px;" />
        </el-form-item>
        <el-form-item label="上级部门" prop="pid">
          <treeselect v-model="form.pid" :options="crud.data" :normalizer="normalizer" style="width: 370px" show-count
                      placeholder="选择上级类目，不选默认为顶级" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="text" @click="crud.cancelCU">取消</el-button>
        <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
      </div>
    </el-dialog>
    <!--表格渲染-->
    <el-table v-loading="crud.loading" :data="tableDate" row-key="deptId" highlight-current-row
              @selection-change="crud.selectionChangeHandler">
      <el-table-column type="selection" width="55" />
      <el-table-column label="部门名称" prop="name" />
      <el-table-column label="部门路径" prop="path" />
      <el-table-column label="排序" prop="deptSort" />
      <el-table-column prop="createTime" label="创建日期" />
      <el-table-column label="操作" width="130px" align="center" fixed="right">
        <template slot-scope="scope">
          <udOperation :data="scope.row" :permission="permission" msg="确定删除吗,如果存在下级节点则一并删除，此操作不能撤销！" />
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import * as crudDept from '@/api/system/dept'
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'

const defaultForm = { deptId: null, name: null, pid: null, deptSort: 999 }
export default {
  name: 'Dept',
  components: { Treeselect, crudOperation, rrOperation, udOperation },
  cruds () {
    return CRUD({ title: '部门', url: crudDept.model_url, idField: 'deptId', crudMethod: { ...crudDept } })
  },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  // 设置数据字典
  data () {
    return {
      rules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ],
        deptSort: [
          { required: true, message: '请输入序号', trigger: 'blur', type: 'number' }
        ]
      },
      permission: {
        add: ['dept:add'],
        edit: ['dept:edit'],
        del: ['dept:del']
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
    tableDate () {
      this.crud.data.forEach(item => {
        if (item.pid === 0) {
          item.pid = null
        }
      })
      return this.crud.data
    }
  },
  created () {
    this.crud.optShow.del = false
  },
  methods: {
    [CRUD.HOOK.beforeSubmit] () {
      if (this.form.pid === this.form.deptId) {
        this.$message({
          message: '上级部门不能是当前部门',
          type: 'warning'
        })
        return false
      }
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
<style rel="stylesheet/scss" lang="scss" scoped>
::v-deep .el-input-number .el-input__inner {
  text-align: left;
}
</style>
