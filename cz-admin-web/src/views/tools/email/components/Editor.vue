
<template>
  <div class="edit_box">
    <Toolbar style="border-bottom: 1px solid #ccc" :editor="editor" :default-config="toolbarConfig" />
    <Editor v-model="editorContent" style="height: 65vh; z-index:1002;" :default-config="editorConfig"
            @onCreated="onCreated" />
  </div>
</template>

<script>
import '@wangeditor/editor/dist/css/style.css'
import { model_url } from '@/api/tools/storage'
import { getToken } from '@/utils/auth'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import { DomEditor } from '@wangeditor/editor'
export default {
  name: 'EmailEditor',
  components: { Editor, Toolbar },
  props: {
    content: {
      type: String,
      default: ''
    }
  },
  data () {
    return {
      editor: null,
      editorContent: this.content,
      editorConfig: {
        placeholder: '请输入内容...'
      },
      toolbarConfig: {
        excludeKeys: [
          'group-video', // 排除视频上传
          'todo'
        ]
      }
    }
  },
  beforeDestroy () {
    const editor = this.editor
    if (editor == null) return
    editor.destroy()
  },
  created () {
    const editorConfig = { MENU_CONF: {} }
    editorConfig.MENU_CONF['uploadImage'] = {
      fieldName: 'files',
      timeout: 50 * 1000,
      server: '/api/' + model_url,
      headers: {
        Authorization: getToken()
      },
      maxFileSize: 5 * 1024 * 1024,
      customInsert: (res, cb) => {
        cb('/api/file/' + res.data.fileUrl)
      },
      meta: {
        fileName: '富文本编辑器',
        appCode: 'system',
        pathName: 'edit/image'
      },
      onError: (file, err, res) => {
        if (err.message.indexOf('maximum') > -1) this.$notify({ title: '图片超出限制，最大5M', type: 'error' })
      },
      base64LimitSize: 5 * 1024 * 1024 // 邮件使用base64发送，否则需要https域名
    }
    this.editorConfig = editorConfig
  },
  methods: {
    onCreated (editor) {
      this.editor = Object.seal(editor)
      setTimeout(() => {
        console.log(DomEditor.getToolbar(editor))
      }, 2000)
    }
  }
}
</script>

<style scoped>
::v-deep .w-e-text-container p {
  margin: 10px 0;
}

.edit_box {
  border: 1px solid #ccc;
  margin-bottom: 50px;
}
/* ul ol 样式 */
::v-deep .w-e-scroll ol {
  margin: 10px 0 10px 22px;
  list-style-type: decimal;
}
::v-deep .w-e-scroll ul {
  margin: 10px 0 10px 22px;
  list-style-type: disc;
}
/* ul ol 样式 */
.w-e-full-screen-container {
  z-index: 1002;
}
</style>
