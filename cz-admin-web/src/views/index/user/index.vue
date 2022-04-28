<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="10" :lg="7" :xl="5" style="margin-bottom: 10px">
        <info-card :user="user" :dept="dept" :avatar="avatar" :roles="roles" :job-name="job" />
      </el-col>
      <el-col :xs="24" :sm="24" :md="14" :lg="17" :xl="19">
        <el-card class="box-card">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <!--    用户资料    -->
            <el-tab-pane label="用户资料" name="userInfo">
              <update-info :user="user" />
            </el-tab-pane>
            <el-tab-pane label="修改密码" name="updatePassword">
              <update-pass :user="user" />
            </el-tab-pane>
            <!--    操作日志    -->
            <el-tab-pane label="操作日志" name="logs">
              <logs />
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>

import { mapGetters } from 'vuex'
import infoCard from './components/infoCard'
import updateInfo from './components/updateInfo'
import updatePass from './components/updatePass'
import logs from './components/logs.vue'
export default {
  name: 'UserCenter',
  components: {
    infoCard,
    updateInfo,
    updatePass,
    logs
  },
  data () {
    return {
      activeName: 'userInfo'
    }
  },
  computed: {
    ...mapGetters(['user', 'dept', 'avatar', 'theme', 'roles', 'job'])
  },
  methods: {
    handleClick (tab, event) {
      if (tab.name === 'second') {
        this.init()
      }
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
