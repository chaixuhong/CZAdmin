<template>
  <el-form ref="form" :model="form" :rules="rules" style="margin-top: 10px;" size="small" label-width="65px">
    <el-form-item label="昵称" prop="nickName">
      <el-input v-model="form.nickName" style="width: 35%" suffix-icon="el-icon-user-solid" />
      <span style="color: #C0C0C0;margin-left: 10px;">用户昵称不作为登录使用</span>
    </el-form-item>
    <el-form-item label="手机号" prop="phone">
      <el-input v-model="form.phone" style="width: 35%;" maxlength="11" suffix-icon="el-icon-phone" />
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="form.email" style="width: 35%;" suffix-icon="el-icon-message" />
    </el-form-item>
    <el-form-item label="性别">
      <el-radio-group v-model="form.gender" style="width: 178px">
        <el-radio label="男">男</el-radio>
        <el-radio label="女">女</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item>
      <el-button :loading="saveLoading" size="mini" type="primary" @click="doSubmit">保存配置</el-button>
    </el-form-item>
  </el-form>

</template>
<script>
import { validPhone } from '@/utils/form-rules'
import { updateInfo } from '@/api/login'
import tipsMixins from '@/mixins/tipsMixins'
export default {
  components: {},
  mixins: [tipsMixins],
  props: {
    user: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      saveLoading: false,
      form: {},
      rules: {
        nickName: [
          { required: true, message: '请输入用户昵称', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ],
        phone: [
          { required: true, trigger: 'blur', validator: validPhone }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {},
  created () {
    this.form = { nickName: this.user.nickName, gender: this.user.gender, phone: this.user.phone, email: this.user.email }
  },
  methods: {
    doSubmit () {
      if (this.$refs['form']) {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            this.saveLoading = true
            console.log(this.form)
            updateInfo(this.form).then(() => {
              return this.$store.dispatch('user/getInfo')
            }).then(() => {
              this.saveLoading = false
              this.editSuccessNotify()
            }).catch(() => {
              this.saveLoading = false
            })
          }
        })
      }
    }
  }
}
</script>
<style scoped>
</style>
