<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" style="margin-top: 6px;" size="small" label-width="100px" inline>
      <el-form-item label="邮件标题" prop="title">
        <el-input v-model="form.title" style="width: 394px" />
      </el-form-item>
      <el-form-item>
        <el-button :loading="loading" type="success" @click="doSubmit">发送邮件
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addDomain">添加邮箱
        </el-button>
      </el-form-item>
      <el-form-item>
        <span style="color:#909399;">发件人：{{ fromUser }}</span>
      </el-form-item>
      <br>
      <el-form-item v-for="(item, index) in form.tos" :key="index" :label="'收件邮箱' + (index === 0 ? '': index)">
        <el-input v-model="form.tos[index]" style="width: 300px" />
        <el-button style="margin-left:5px;" type="text" @click.prevent="removeDomain(index)">删除</el-button>
      </el-form-item>
    </el-form>
    <Editor ref="emailEditor" :content="form.content" />
  </div>
</template>

<script>
import { sendEmail } from '@/api/tools/email'
import { validEmail } from '@/utils/validate'
import Editor from './components/Editor'
export default {
  name: 'SendEmail',
  components: { Editor },
  data () {
    return {
      loading: false,
      form: { title: '', tos: [''], content: '' },
      rules: {
        title: [
          { required: true, message: '标题不能为空', trigger: 'blur' }
        ]
      },
      fromUser: ''
    }
  },
  created () {
    this.form.emailId = this.$route.params.emailId
    this.fromUser = this.$route.query.fromUser
  },
  methods: {
    removeDomain (index) {
      if (this.form.tos.length < 2) {
        this.$message({
          message: '请至少保留一位联系人',
          type: 'warning'
        })
        return
      }
      this.form.tos.splice(index, 1)
    },
    addDomain () {
      this.form.tos.push('')
    },
    doSubmit () {
      this.loading = true
      this.$refs['form'].validate((valid) => {
        if (valid) {
          let flag = true
          this.form.tos.forEach((item, index) => {
            if (!validEmail(item)) {
              this.$message({
                message: `收件箱${index}格式错误`,
                type: 'warning'
              })
              flag = false
            }
          })
          if (flag) {
            this.form.content = this.$refs.emailEditor.editorContent
            sendEmail(this.form).then(res => {
              this.$notify({
                title: '邮件发送成功！',
                type: 'success'
              })
              this.loading = false
            }).catch(err => {
              console.log(err)
              this.loading = false
            })
          }
        }
        this.loading = false
      })
    }
  }
}
</script>

<style scoped>
</style>
