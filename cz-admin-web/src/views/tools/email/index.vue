<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">发件人邮箱</label>
        <el-input v-model="query.fromUser" clearable placeholder="发件人邮箱" style="width: 185px;" class="filter-item"
                  @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">发件人用户名</label>
        <el-input v-model="query.user" clearable placeholder="发件人用户名" style="width: 185px;" class="filter-item"
                  @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">SMTP地址</label>
        <el-input v-model="query.host" clearable placeholder="邮件服务器SMTP地址" style="width: 185px;" class="filter-item"
                  @keyup.enter.native="crud.toQuery" />
        <rrOperation :crud="crud" />
      </div>
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog append-to-body :close-on-click-modal="false" :before-close="crud.cancelCU"
                 :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="600px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="120px">
          <el-form-item label="发件人邮箱" prop="fromUser">
            <el-input v-model="form.fromUser" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="发件人用户名" prop="user">
            <el-input v-model="form.user" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="邮箱密码" prop="pass">
            <el-input v-model="form.pass" style="width: 370px;" type="password" />
          </el-form-item>
          <el-form-item label="SMTP地址" prop="host">
            <el-input v-model="form.host" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="SMTP端口" prop="port">
            <el-input-number v-model=" form.port" style="width: 150px;" controls-position="right" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;"
                :height="windowHeight-280" @selection-change="crud.selectionChangeHandler">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="user" label="发件人用户名" />
        <el-table-column prop="fromUser" label="发件人邮箱" />
        <el-table-column prop="host" label="SMTP地址" />
        <el-table-column prop="port" label="端口" />
        <el-table-column prop="createTime" label="创建时间" />
        <el-table-column prop="createBy" label="创建人" />
        <el-table-column v-if="checkPer(['sysEmailConfig:send'])" label="操作" width="100px" align="center">
          <template slot-scope="{row}">
            <el-button size="mini" type="text">
              <router-link :to="'/sys-tools/email/sendEmail/' + row.emailId +'?fromUser='+ row.fromUser">
                发送邮件
              </router-link>
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import * as crudSysEmailConfig from '@/api/tools/email'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import pagination from '@crud/Pagination'
import { validEmail } from '@/utils/validate'
import { encrypt } from '@/utils/rsaEncrypt'
const defaultForm = { emailId: null, fromUser: null, host: null, pass: null, port: null, user: null, createTime: null, createBy: null, updateTime: null, updateBy: null }
export default {
  name: 'SysEmailConfig',
  components: { pagination, crudOperation, rrOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds () {
    return CRUD({ title: '邮箱配置', url: crudSysEmailConfig.model_url, idField: 'emailId', sort: 'emailId,desc', crudMethod: { ...crudSysEmailConfig } })
  },
  data () {
    const validateEmail = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入发件人邮箱'))
      } else if (!validEmail(value)) {
        callback(new Error('请输入正确的邮箱格式'))
      } else {
        callback()
      }
    }
    return {
      permission: {
        add: ['sysEmailConfig:add'],
        edit: ['sysEmailConfig:edit'],
        del: ['sysEmailConfig:del']
      },
      rules: {
        fromUser: [
          { required: true, validator: validateEmail, trigger: 'blur' }
        ],
        host: [
          { required: true, message: '邮件服务器SMTP地址不能为空', trigger: 'blur' }
        ],
        pass: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ],
        port: [
          { required: true, message: '端口不能为空', trigger: 'blur' }
        ],
        user: [
          { required: true, message: '发件人用户名不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    [CRUD.HOOK.beforeSubmit] () {
      this.form.pass = encrypt(this.form.pass)
    }
  }
}
</script>

<style scoped>
</style>
