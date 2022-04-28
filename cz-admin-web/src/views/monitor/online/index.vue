<template>
  <div class="app-container">
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <el-input v-model="query.filter" clearable size="small" placeholder="全表模糊搜索" style="width: 200px;"
                  class="filter-item" @keyup.enter.native="crud.toQuery" />
        <rrOperation />
      </div>
      <crudOperation>
        <el-button slot="left" :loading="delLoading" class="filter-item" type="danger" icon="el-icon-delete"
                   :disabled="crud.selections.length === 0" @click="doDelete(crud.selections)">强退</el-button>
      </crudOperation>
    </div>
    <!--表格渲染-->
    <el-table ref="table" v-loading="crud.loading" :data="crud.data" :height="windowHeight-280"
              @selection-change="crud.selectionChangeHandler">
      <el-table-column type="selection" width="55" />
      <el-table-column prop="username" label="用户名" />
      <el-table-column prop="nickName" label="用户昵称" />
      <el-table-column prop="deptName" label="部门" />
      <el-table-column prop="ip" label="登录IP" />
      <el-table-column :show-overflow-tooltip="true" prop="address" label="登录地点" />
      <el-table-column prop="browser" label="浏览器" />
      <el-table-column prop="loginTime" label="登录时间" />
      <el-table-column v-if="checkPer(['online:del'])" label="操作" width="70px" fixed="right">
        <template slot-scope="scope">
          <el-popover :ref="scope.$index" placement="top" width="180">
            <p>确定强制退出该用户吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="$refs[scope.$index].doClose()">取消</el-button>
              <el-button :loading="delLoading" type="primary" size="mini" @click="delMethod(scope.row, scope.$index)">确定
              </el-button>
            </div>
            <el-button slot="reference" size="mini" type="text">强退</el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
    <!--分页组件-->
    <pagination />
  </div>
</template>

<script>
import * as crudOnline from '@/api/monitor/online'
import CRUD, { presenter, header, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import pagination from '@crud/Pagination'

export default {
  name: 'OnlineUser',
  components: { pagination, crudOperation, rrOperation },
  cruds () {
    return CRUD({ url: crudOnline.model_url, title: '在线用户', optShow: { download: false } })
  },
  mixins: [presenter(), header(), crud()],
  data () {
    return {
      delLoading: false
    }
  },
  created () {
    this.crud.msg.del = '强退成功！'
  },
  methods: {
    doDelete (datas) {
      this.$confirm(`确认强退选中的${datas.length}个用户?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.delMethod(datas)
      }).catch(() => { })
    },
    // 踢出用户
    delMethod (key, index) {
      const paramList = []
      if (key instanceof Array) {
        key.forEach(val => {
          const params = {
            userId: val.userId,
            loginTime: val.loginTime
          }
          paramList.push(params)
        })
      } else {
        const params = {
          userId: key.userId,
          loginTime: key.loginTime
        }
        paramList.push(params)
      }
      this.delLoading = true
      crudOnline.del(paramList).then(() => {
        this.delLoading = false
        if (this.$refs[index]) {
          this.$refs[index].doClose()
        }
        this.crud.dleChangePage(1)
        this.crud.delSuccessNotify()
        this.crud.toQuery()
      }).catch(() => {
        this.delLoading = false
        if (this.$refs[index]) {
          this.$refs[index].doClose()
        }
      })
    }
  }
}
</script>
