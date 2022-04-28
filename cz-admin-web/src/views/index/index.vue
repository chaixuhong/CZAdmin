<template>
  <div class="app-container">欢迎 {{ name }} 登录</div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'Index',
  data () {
    return {
      data: {}
    }
  },
  computed: {
    ...mapGetters(['name'])
  }
}
</script>

<style lang="scss" scoped>
</style>
