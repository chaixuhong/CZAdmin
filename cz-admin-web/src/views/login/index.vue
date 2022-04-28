<template>
  <div class="login" :style="'background-image:url(' + Background + ');'">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" label-position="left" label-width="0px"
             class="login-form">
      <h3 class="title">CZ-ADMIN 后台管理系统</h3>
      <el-form-item prop="username">
        <el-input v-model="loginForm.username" type="text" auto-complete="off" placeholder="账号">
          <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="loginForm.password" type="password" auto-complete="off" placeholder="密码"
                  @keyup.enter.native="handleLogin">
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>
      <el-form-item prop="code">
        <el-input v-model="loginForm.code" auto-complete="off" placeholder="验证码" style="width: 63%;"
                  @keyup.enter.native="handleLogin">
          <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
        </el-input>
        <div class="login-code">
          <img :src="codeUrl" @click="getCode">
        </div>
      </el-form-item>
      <!-- <el-checkbox v-model="loginForm.rememberMe" style="margin: 0 0 25px 0;">
        记住我
      </el-checkbox> -->
      <el-form-item style="width: 100%;">
        <el-button :loading="loading" size="medium" type="primary" style="width: 100%;"
                   @click.native.prevent="handleLogin">
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
      </el-form-item>
    </el-form>
    <!--  底部  -->
    <div v-if="$store.state.settings.showFooter" id="el-login-footer">
      <span v-html="$store.state.settings.footerTxt" />
      <span> ⋅ </span>
      <a href="https://beian.miit.gov.cn/#/Integrated/index" target="_blank">{{ $store.state.settings.caseNumber }}</a>
    </div>
  </div>
</template>

<script>
import { getCodeImg } from '@/api/login'
import qs from 'qs'
import Background from '@/assets/background.jpg'
import { encrypt } from '@/utils/rsaEncrypt'
export default {
  name: 'Login',
  data () {
    return {
      Background: Background,
      codeUrl: '',
      loginForm: {
        username: 'admin',
        password: '123456',
        rememberMe: false,
        code: '',
        uuid: ''
      },
      loginRules: {
        username: [
          {
            required: true,
            trigger: 'blur',
            message: '用户名不能为空'
          }
        ],
        password: [
          {
            required: true,
            trigger: 'blur',
            message: '密码不能为空'
          }
        ],
        code: [
          {
            required: true,
            trigger: 'change',
            message: '验证码不能为空'
          }
        ]
      },
      loading: false,
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function (route) {
        const data = route.query
        if (data && data.redirect) {
          this.redirect = data.redirect
          delete data.redirect
          if (JSON.stringify(data) !== '{}') {
            this.redirect =
              this.redirect + '&' + qs.stringify(data, { indices: false })
          }
        }
      },
      immediate: true
    }
  },
  created () {
    // 获取验证码
    this.getCode()
    this.point()
  },
  methods: {
    getCode () {
      getCodeImg().then((res) => {
        const { data } = res
        this.codeUrl = data.img
        this.loginForm.uuid = data.uuid
      })
    },
    handleLogin () {
      this.$refs.loginForm.validate((valid) => {
        const user = {
          username: this.loginForm.username,
          password: encrypt(this.loginForm.password),
          rememberMe: this.loginForm.rememberMe,
          code: this.loginForm.code,
          uuid: this.loginForm.uuid
        }
        if (valid) {
          this.loading = true
          this.$store
            .dispatch('user/login', user)
            .then(() => {
              this.loading = false
              this.$router.push({ path: this.redirect || '/' })
            })
            .catch((err) => {
              console.log(err)
              this.loading = false
              this.getCode()
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    point () {
      const point = sessionStorage.getItem('responseCode')
      if (point) {
        this.$notify({
          title: '提示',
          message: '当前登录状态已过期，请重新登录！',
          type: 'warning',
          duration: 5000
        })
        sessionStorage.removeItem('responseCode')
      }
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-size: cover;
}
.title {
  margin: 0 auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 385px;
  padding: 25px 25px 5px 25px;
  .el-input {
    height: 38px;
    input {
      height: 38px;
    }
  }
  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}
.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
.login-code {
  width: 33%;
  display: inline-block;
  height: 38px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}
</style>
