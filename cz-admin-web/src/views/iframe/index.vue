<template>
  <div class="app-container">
    <div class="filter-container">
      <div v-loading="loading">
        <iframe class="myFrame" width="100%" src="https://cn.bing.com/?ensearch=1" style="height:87vh" frameborder="0"
                marginheight="0" marginwidth="0" @load="handleLoad" />
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'Iframe',
  data () {
    return {
      loading: true
    }
  },
  methods: {
    handleLoad () {
      this.loading = false
    }
  }
}
</script>
<style scoped="Iframe">
</style>
