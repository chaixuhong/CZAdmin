<template>
  <el-card class="box-card">
    <div slot="header">
      <span>个人信息</span>
    </div>
    <div>
      <div class="avatar">
        <img :src="avatar" alt="点击上传头像" @click="toggleShow">
        <myUpload ref="myUpload" v-model="show" field="avatar" url="/api/auth/updateAvatar" :headers="headers"
                  :no-rotate="false" @crop-upload-success="cropUploadSuccess" />
      </div>
      <ul class="user-info">
        <li>
          <div>
            <svg-icon icon-class="login" />
            <span>登录账号</span>
          </div>
          <div class="user-right">{{ user.username }}</div>
        </li>
        <li>
          <div>
            <svg-icon icon-class="user1" />
            <span>用户昵称</span>
          </div>
          <div class="user-right">{{ user.nickName }}</div>
        </li>
        <li>
          <div>
            <svg-icon icon-class="email" />
            <span>用户角色</span>
          </div>
          <div class="user-right">{{ roleName }}</div>
        </li>
        <li>
          <div>
            <svg-icon icon-class="Steve-Jobs" />
            <span>所属部门</span>
          </div>
          <div class="user-right"> {{ dept.name }}</div>
        </li>
        <li>
          <div>
            <svg-icon icon-class="dept" />
            <span>所在岗位</span>
          </div>
          <div class="user-right"> {{ jobName }}</div>
        </li>
        <li>
          <div>
            <svg-icon icon-class="peoples" />
            <span>用户性别</span>
          </div>
          <div class="user-right"> {{ user.gender }}</div>
        </li>
        <li>
          <div>
            <svg-icon icon-class="phone" />
            <span>手机号码</span>
          </div>
          <div class="user-right">{{ user.phone }}</div>
        </li>
        <li>
          <div>
            <svg-icon icon-class="email" />
            <span>用户邮箱</span>
          </div>
          <div class="user-right">{{ user.email }}</div>
        </li>
      </ul>
    </div>
  </el-card>
</template>

<script>
import myUpload from 'vue-image-crop-upload'
import { getToken } from '@/utils/auth'
import tipsMixins from '@/mixins/tipsMixins'
export default {
  name: 'InfoCard',
  components: {
    myUpload
  },
  mixins: [tipsMixins],
  props: {
    user: {
      type: Object,
      required: true
    },
    roles: {
      type: Array,
      required: true
    },
    dept: {
      type: Object,
      required: true
    },
    jobName: {
      type: String,
      default: ''
    },
    avatar: {
      type: String,
      required: true
    }
  },
  data () {
    return {
      show: false,
      headers: {
        'Authorization': getToken()
      }
    }
  },
  computed: {
    roleName () {
      return this.roles ? this.roles.map(item => item.name)[0] : '未设置'
    }
  },
  methods: {
    toggleShow () {
      this.show = !this.show
    },
    cropUploadSuccess (jsonData, field) {
      this.$store.dispatch('user/getInfo')
      this.show = false
      this.editSuccessNotify()
      // 调用其组件的方法，将其视图跳转到第一步
      this.$refs.myUpload.setStep(1)
    }
  }
}
</script>

<style lang='scss' scoped='InfoCard'>
.avatar {
  text-align: center;
  img {
    width: 120px;
    height: 120px;
    border-radius: 50%;
    display: inline-block;
  }
}
.user-info {
  li {
    border-bottom: 1px solid #f0f3f4;
    line-height: 40px;
    font-size: 13px;
    display: flex;
    justify-content: space-between;
    div {
      span {
        margin-left: 5px;
      }
    }
  }
}
</style>
