<template>
  <el-tabs v-model="activeName" type="card" class="mytab">
    <el-tab-pane v-for="item in data" :key="item.name" :lazy="true" :label="item.name" :name="item.name">
      <el-button type="primary" class="copy_b" @click.stop="copyCode(item.content)">复制</el-button>
      <div v-if="item.name === 'index' || item.name === 'api'">
        <pre v-highlightjs="item.content">
        <code :style="{height:height}" class="javascript" theme="idea" />
      </pre>
      </div>
      <div v-else>
        <pre v-highlightjs="item.content">
        <code :style="{height:height}" class="java" />
      </pre>
      </div>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import { previewCode } from '@/api/generator/generator'
export default {
  name: 'Preview',
  data () {
    return {
      data: [],
      height: '',
      activeName: 'Entity'
    }
  },
  created () {
    this.height = document.documentElement.clientHeight - 160 + 'px'
    const params = { tableName: this.$route.params.tableName }
    previewCode(params).then(res => {
      this.data = res.data
    }).catch((e) => {
      this.$notify(e)
    })
  },
  methods: {
    copyCode (text) {
      this.$copyText(text).then(_ => {
        this.$notify.success({ title: '已成功复制到剪切板', type: 'success' })
      }).catch(_ => {
        this.$notify.success({ title: '复制失败', type: 'error' })
      })
    }
  }
}
</script>
<style scoped>
.mytab >>> .el-tabs__header {
  margin-bottom: 0;
}
.mytab >>> pre {
  display: flex;
}

.hljs {
  width: 100%;
}

.copy_b {
  position: absolute;
  top: 0;
  right: 0;
}
</style>
