<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <el-input v-model="query.fileName" clearable size="small" placeholder="输入名称进行搜索" style="width: 200px;"
                  class="filter-item" @keyup.enter.native="crud.toQuery" />
        <date-range-picker v-model="query.createTime" class="date-item" style="margin-right:5px;" />
        <rrOperation />
      </div>
      <crudOperation :permission="permission">
        <!-- 新增 -->
        <el-button slot="left" v-permission="['storage:add']" class="filter-item" size="mini" type="primary"
                   icon="el-icon-upload" @click="crud.toAdd">上传
        </el-button>
      </crudOperation>
    </div>
    <!--表单组件-->
    <el-dialog append-to-body :close-on-click-modal="false" :before-close="crud.cancelCU"
               :visible.sync="crud.status.cu > 0" title="文件上传" width="600px">
      <el-form ref="form" :model="form" size="small" label-width="100px">
        <el-form-item label="名称">
          <el-input v-model="form.fileName" style="width: 370px;" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="业务">
          <el-select v-model="form.appCode" filterable placeholder="请选择业务" style="width: 370px;">
            <el-option v-for="(item,index) in dict.app_name" :key="index" :label="item.label" :value="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="路径">
          <el-select v-model="form.pathName" filterable placeholder="请选择路径" style="width: 370px;">
            <el-option v-for="(item,index) in dict.file_path" :key="index" :label="item.label" :value="item.value" />
          </el-select>
        </el-form-item>

        <!--   上传文件   -->
        <el-form-item v-if="crud.status.add" label="上传">
          <el-upload ref="upload" :limit="1" :before-upload="beforeUpload" :auto-upload="false" :headers="headers"
                     :data="{fileName:form.fileName ,appCode: form.appCode ,pathName: form.pathName}"
                     :on-success="handleSuccess" :on-error="handleError" :action="'/api' + model_url" name="files">
            <div class="eladmin-upload"><i class="el-icon-upload" /> 添加文件</div>
            <div slot="tip" class="el-upload__tip">可上传任意格式文件，且不超过100M</div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="text" @click="crud.cancelCU">取消</el-button>
        <el-button :loading="loading" type="primary" @click="upload">确认</el-button>
      </div>
    </el-dialog>
    <!--表格渲染-->
    <el-table ref="table" v-loading="crud.loading" :data="crud.data" style="width: 100%;" :height="windowHeight-280"
              @selection-change="crud.selectionChangeHandler">
      <el-table-column type="selection" width="55" />
      <el-table-column label="名称">
        <template slot-scope="{row}">
          <a class="el-button el-button--text el-button--mini" :href="'/api/file/' + row.fileUrl"
             target="_blank">{{ row.fileName }}</a>
        </template>
      </el-table-column>
      <el-table-column prop="fileOriginalName" label="原文件名" />
      <el-table-column prop="path" label="缩略图">
        <template slot-scope="{row}">
          <el-image v-if="row.fileType==='图片'" :src="'/api/file/' + row.fileUrlThumb"
                    :preview-src-list="['/api/file/' + row.fileUrl]" fit="contain" lazy class="el-avatar">
            <div slot="error">
              <i class="el-icon-document" />
            </div>
          </el-image>
          <el-image v-else-if="row.fileType==='word'" :src="require('@/assets/images/word.png')" fit="contain"
                    class="el-avatar cz-image" />
          <el-image v-else-if="row.fileType==='excel'" :src="require('@/assets/images/excel.png')" fit="contain"
                    class="el-avatar cz-image" />
          <el-image v-else-if="row.fileType==='ppt'" :src="require('@/assets/images/ppt.png')" fit="contain"
                    class="el-avatar cz-image" />
          <el-image v-else-if="row.fileType==='pdf'" :src="require('@/assets/images/pdf.png')" fit="contain"
                    class="el-avatar cz-image" />
          <el-image v-else-if="row.fileType==='视频'" :src="require('@/assets/images/video.png')" fit="contain"
                    class="el-avatar cz-image" />
          <el-image v-else-if="row.fileType==='音乐'" :src="require('@/assets/images/mp3.png')" fit="contain"
                    class="el-avatar cz-image" />
          <el-image v-else-if="row.fileType==='文档'" :src="require('@/assets/images/txt.png')" fit="contain"
                    class="el-avatar cz-image" />
          <el-image v-else :src="require('@/assets/images/file.png')" fit="contain" class="el-avatar cz-image" />
        </template>
      </el-table-column>
      <el-table-column prop="fileType" label="文件类型" />
      <el-table-column label="类型">
        <template slot-scope="{row}">
          {{ getFileType(row.fileOriginalName) }}
        </template>
      </el-table-column>
      <el-table-column prop="fileSize" label="大小" />
      <el-table-column prop="createBy" label="操作人" />
      <el-table-column prop="createTime" label="创建日期" />
    </el-table>
    <!--分页组件-->
    <pagination />
  </div>
</template>

<script>
import { getToken } from '@/utils/auth'
import * as crudFile from '@/api/tools/storage'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import pagination from '@crud/Pagination'
import DateRangePicker from '@/components/DateRangePicker'

const defaultForm = { fileName: '', appCode: '', pathName: '', files: null }
export default {
  name: 'Storage',
  components: { pagination, crudOperation, rrOperation, DateRangePicker },
  cruds () {
    return CRUD({ title: '文件', url: crudFile.model_url, crudMethod: { ...crudFile }, idField: 'fileId', optShow: { del: true, reset: true, download: true }, sort: 'createTime,desc' })
  },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  dicts: ['app_name', 'file_path'],
  data () {
    return {
      delAllLoading: false,
      loading: false,
      headers: { 'Authorization': getToken() },
      permission: {
        edit: ['storage:edit'],
        del: ['storage:del']
      },
      model_url: ''
    }
  },
  created () {
    this.model_url = crudFile.model_url
  },
  methods: {
    getFileType (name) {
      if (name.indexOf('.') > -1) {
        const split = name.split('.')
        return split[split.length - 1]
      }
      return '-'
    },
    // 上传文件
    upload () {
      this.$refs.upload.submit()
    },
    beforeUpload (file) {
      let isLt2M = true
      isLt2M = file.size / 1024 / 1024 < 100
      if (!isLt2M) {
        this.loading = false
        this.$message.error('上传文件大小不能超过 100MB!')
      }
      this.form.name = file.name
      return isLt2M
    },
    handleSuccess (response, file, fileList) {
      this.crud.notify('上传成功', CRUD.NOTIFICATION_TYPE.SUCCESS)
      this.$refs.upload.clearFiles()
      this.crud.status.add = CRUD.STATUS.NORMAL
      this.crud.resetForm()
      this.crud.toQuery()
    },
    // 监听上传失败
    handleError (e, file, fileList) {
      const msg = JSON.parse(e.message)
      this.$notify({
        title: msg.message,
        type: 'error',
        duration: 2500
      })
      this.loading = false
    }
  }
}
</script>

<style scoped>
::v-deep .el-image__error,
.el-image__placeholder {
  background: none;
}
::v-deep .el-image-viewer__wrapper {
  top: 55px;
}
.cz-image {
  border-radius: 5px;
  background: #ffffff;
}
</style>
