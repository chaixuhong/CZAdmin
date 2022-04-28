<template>
  <div class="app-container">
    <mavon-editor ref="md" v-model="content" :style="'height:' + height" :image-filter="imageFilter" @imgAdd="imgAdd"
                  @save="save" />
  </div>
</template>

<script>
import { upload } from '@/api/tools/storage'
export default {
  name: 'Markdown',
  data () {
    return {
      height: document.documentElement.clientHeight - 200 + 'px',
      content: ''
    }
  },
  mounted () {
    const that = this
    window.onresize = function temp () {
      that.height = document.documentElement.clientHeight - 200 + 'px'
    }
  },
  methods: {
    // 图片上传
    imgAdd (pos, $file) {
      upload($file, 'MarkDown编辑器', 'system', 'markdown/image').then(res => {
        this.$refs.md.$img2Url(pos, '/api/file/' + res.data.fileUrl)
      })
    },
    // 图片上传前校验
    imageFilter (file) {
      if (file.size > 20 * 1024 * 1024) {
        this.$notify({ title: '图片超出限制，最大20M', type: 'error' })
        return false
      }
      return true
    },
    // 保存
    save (value, render) {
      console.log(value)
      console.log(render)
    }
  }
}
</script>

<style scoped>
.v-note-wrapper.shadow {
  z-index: 1002;
}

::v-deep .markdown-body ol {
  margin: 10px 0 10px 22px;
  list-style-type: decimal;
}
::v-deep .markdown-body ul {
  margin: 10px 0 10px 22px;
  list-style-type: disc;
}
</style>
