
<template>
  <div class="app-container">
    <el-row :gutter="10">
      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
        <div class="edit_box">
          <Toolbar style="border-bottom: 1px solid #ccc" :editor="editor" :default-config="toolbarConfig"
                   :mode="mode" />
          <Editor v-model="editorContent" style="height: 78vh; z-index:1002;" :default-config="editorConfig"
                  :mode="mode" @onCreated="onCreated" @onBlur="onBlur" />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
        <el-button type="primary" @click="dialog = true">获取html</el-button>
        <div class="preview_box" v-html="editorContent" />
      </el-col>
    </el-row>
    <el-dialog title="提示" :visible.sync="dialog">
      <span>{{ editorContent }}</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialog = false">关 闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import '@wangeditor/editor/dist/css/style.css'
import Prism from 'prismjs'
import { model_url } from '@/api/tools/storage'
import { getToken } from '@/utils/auth'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
export default {
  name: 'Editors',
  components: { Editor, Toolbar },
  data () {
    return {
      dialog: false,
      editor: null,
      editorContent: '',
      toolbarConfig: {},
      editorConfig: {
        placeholder: '请输入内容...'
      },
      mode: 'default' // or 'simple'
    }
  },
  beforeDestroy () {
    const editor = this.editor
    if (editor == null) return
    editor.destroy() // 组件销毁时，及时销毁编辑器
  },
  created () {
    const editorConfig = { MENU_CONF: {} }
    const imageConfig = {}
    const videoConfig = {}
    const config = {
      fieldName: 'files',
      timeout: 50 * 1000,
      server: '/api/' + model_url,
      headers: {
        Authorization: getToken()
      },
      customInsert: (res, cb) => {
        cb('/api/file/' + res.data.fileUrl)
      }
    }
    Object.assign(imageConfig, config)
    Object.assign(videoConfig, config)
    imageConfig.maxFileSize = 20 * 1024 * 1024
    videoConfig.maxFileSize = 50 * 1024 * 1024
    imageConfig.meta = {
      fileName: '富文本编辑器',
      appCode: 'system',
      pathName: 'edit/image'
    }
    videoConfig.meta = {
      fileName: '富文本编辑器',
      appCode: 'system',
      pathName: 'edit/video'
    }
    imageConfig.onError = (file, err, res) => {
      if (err.message.indexOf('maximum') > -1) this.$notify({ title: '图片超出限制，最大20M', type: 'error' })
    }
    videoConfig.onError = (file, err, res) => {
      if (err.message.indexOf('maximum') > -1) this.$notify({ title: '视频超出限制，最大50M', type: 'error' })
    }
    editorConfig.MENU_CONF['uploadImage'] = imageConfig
    editorConfig.MENU_CONF['uploadVideo'] = videoConfig
    this.editorConfig = editorConfig
  },
  methods: {
    onCreated (editor) {
      this.editor = Object.seal(editor)
    },
    onBlur (editor) {
      Prism.highlightAll()
    }
  }
}
</script>

<style scoped>
::v-deep .edit_box .w-e-text-container p {
  margin: 10px 0;
}

.edit_box {
  border: 1px solid #ccc;
}
/* ul ol 样式 */
::v-deep .edit_box .w-e-scroll ol {
  margin: 10px 0 10px 22px;
  list-style-type: decimal;
}
::v-deep .edit_box .w-e-scroll ul {
  margin: 10px 0 10px 22px;
  list-style-type: disc;
}
/* ul ol 样式 */
::v-deep .preview_box ol {
  margin: 10px 0 10px 22px;
  list-style-type: decimal;
}
::v-deep .preview_box ul {
  margin: 10px 0 10px 22px;
  list-style-type: disc;
}

.w-e-full-screen-container {
  z-index: 2000;
}

.preview_box {
  height: 82.7vh;
  overflow: auto;
  border: 1px solid #ccc;
  padding: 10px;
}
.el-button {
  margin-bottom: 5px;
}

::v-deep .preview_box img {
  width: 100%;
}

::v-deep .preview_box p {
  margin: 10px 0;
}
::v-deep .preview_box p,
::v-deep .preview_box li {
  white-space: pre-wrap; /* 保留空格 */
}

::v-deep .preview_box blockquote {
  border-left: 8px solid #d0e5f2;
  padding: 10px 10px;
  margin: 10px 0;
  background-color: #f1f1f1;
}

::v-deep .preview_box code {
  font-family: monospace;
  background-color: #eee;
  padding: 3px;
  border-radius: 3px;
}
::v-deep .preview_box pre > code {
  display: block;
  padding: 10px;
}

::v-deep .preview_box table {
  border-collapse: collapse;
}

::v-deep .preview_box table tr {
  box-sizing: border-box;
  margin: 0;
  outline: none;
  padding: 0;
}
::v-deep .preview_box td,
::v-deep .preview_box th {
  border: 1px solid #ccc;
  min-width: 50px;
  height: 20px;
  line-height: 1.5;
  padding: 3px 5px;
}
::v-deep .preview_box th {
  background-color: #f5f2f0;
}

::v-deep .preview_box ul,
::v-deep .preview_box ol {
  padding-left: 20px;
}

::v-deep .preview_box input[type='checkbox'] {
  margin-right: 5px;
}
</style>
