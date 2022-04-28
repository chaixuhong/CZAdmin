<template>
  <el-form ref="form" :model="form" :rules="rules" style="margin-top: 10px;" size="small" label-width="78px">
    <el-form-item label="旧密码" prop="oldPass">
      <el-input v-model="form.oldPass" type="password" auto-complete="on" style="width: 35%" />
    </el-form-item>
    <el-form-item label="新密码" prop="newPass">
      <el-input v-model="form.newPass" type="password" auto-complete="on" style="width: 35%" />
    </el-form-item>
    <el-form-item label="确认密码" prop="confirmPass">
      <el-input v-model="form.confirmPass" type="password" auto-complete="on" style="width: 35%" />
    </el-form-item>
    <el-form-item>
      <el-button :loading="saveLoading" size="mini" type="primary" @click="doSubmit">保存配置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { updatePass } from '@/api/login'
import { encrypt } from '@/utils/rsaEncrypt'
import tipsMixins from '@/mixins/tipsMixins'
export default {
  mixins: [tipsMixins],
  data () {
    const confirmPass = (rule, value, callback) => {
      if (value) {
        if (this.form.newPass !== value) {
          callback(new Error('两次输入的密码不一致'))
        } else {
          callback()
        }
      } else {
        callback(new Error('请再次输入密码'))
      }
    }
    return {
      saveLoading: false,
      form: { oldPass: '', newPass: '', confirmPass: '' },
      rules: {
        oldPass: [
          { required: true, message: '请输入旧密码', trigger: 'blur' }
        ],
        newPass: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
        ],
        confirmPass: [
          { required: true, validator: confirmPass, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    doSubmit () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.saveLoading = true
          const params = {
            oldPass: encrypt(this.form.oldPass),
            newPass: encrypt(this.form.newPass)
          }
          updatePass(params).then(() => {
            this.resetForm()
            this.saveLoading = false
            this.editSuccessNotify()
          }).catch(err => {
            this.saveLoading = false
            console.log(err)
          })
        } else {
          return false
        }
      })
    },
    resetForm () {
      this.$refs['form'].resetFields()
    }
  }
}
</script>

<style scoped>
</style>
