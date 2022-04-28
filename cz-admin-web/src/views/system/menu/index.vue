<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <crudOperation :permission="permission" />
    </div>
    <!--表单渲染-->
    <el-dialog append-to-body :close-on-click-modal="false" :before-close="crud.cancelCU"
               :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="580px">
      <el-form ref="form" :inline="true" :model="form" :rules="rules" size="small" label-width="80px">
        <el-form-item label="菜单类型" prop="menuType">
          <el-radio-group v-model="form.menuType" size="mini" style="width: 178px">
            <el-radio-button :label="0">目录</el-radio-button>
            <el-radio-button :label="1">菜单</el-radio-button>
            <el-radio-button :label="2">按钮</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-show="form.menuType !== 2" label="菜单图标" prop="icon">
          <el-popover placement="bottom-start" width="450" trigger="click" @show="$refs['iconSelect'].reset()">
            <IconSelect ref="iconSelect" @selected="selected" />
            <el-input slot="reference" v-model="form.icon" style="width: 450px;" placeholder="点击选择图标" readonly>
              <svg-icon v-if="form.icon" slot="prefix" :icon-class="form.icon" class="el-input__icon"
                        style="height: 32px;width: 16px;" />
              <i v-else slot="prefix" class="el-icon-search el-input__icon" />
            </el-input>
          </el-popover>
        </el-form-item>
        <el-form-item v-show="form.menuType !== 2" label="外链菜单" prop="iFrame">
          <el-radio-group v-model="form.iFrame" size="mini">
            <el-radio-button :label="true">是</el-radio-button>
            <el-radio-button :label="false">否</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-show="form.menuType === 1" label="菜单缓存" prop="isCache">
          <el-radio-group v-model="form.isCache" size="mini">
            <el-radio-button :label="true">是</el-radio-button>
            <el-radio-button :label="false">否</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-show="form.menuType !== 2" label="菜单可见" prop="hidden">
          <el-radio-group v-model="form.hidden" size="mini">
            <el-radio-button :label="false">是</el-radio-button>
            <el-radio-button :label="true">否</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-if="form.menuType !== 2" label="菜单标题" prop="title">
          <el-input v-model="form.title" :style=" form.menuType === 0 ? 'width: 450px' : 'width: 178px'"
                    placeholder="菜单标题" />
        </el-form-item>
        <el-form-item v-if="form.menuType === 2" label="按钮名称" prop="title">
          <el-input v-model="form.title" placeholder="按钮名称" style="width: 178px;" />
        </el-form-item>
        <el-form-item v-show="form.menuType !== 0" label="权限标识" prop="permission">
          <el-input v-model="form.permission" :disabled="form.iFrame === true" placeholder="权限标识"
                    style="width: 178px;" />
        </el-form-item>
        <el-form-item v-if="form.menuType !== 2" label="路由地址" prop="path">
          <el-input v-model="form.path" placeholder="路由地址" style="width: 178px;" />
        </el-form-item>
        <el-form-item label="菜单排序" prop="menuSort">
          <el-input-number v-model.number="form.menuSort" :min="0" :max="999" controls-position="right"
                           style="width: 178px;" />
        </el-form-item>
        <el-form-item v-show="form.iFrame !== true && form.menuType === 1" label="组件名称" prop="name">
          <el-input v-model="form.name" style="width: 178px;" placeholder="匹配组件内Name字段" />
        </el-form-item>
        <el-form-item v-show="form.iFrame !== true && form.menuType === 1" label="组件路径" prop="component">
          <el-input v-model="form.component" style="width: 178px;" placeholder="组件路径" />
        </el-form-item>
        <el-form-item label="上级类目" prop="pid">
          <treeselect v-model="form.pid" :options="menus" :load-options="loadMenus" style="width: 450px;"
                      :normalizer="normalizer" placeholder="选择上级类目，不选默认为顶级" />
        </el-form-item>
      </el-form>
      <div style="text-align: right; color:#F56C6C; font-size:12px;">提示：对菜单的操作会造成整个页面的刷新</div>
      <div slot="footer" class="dialog-footer">
        <el-button type="text" @click="crud.cancelCU">取消</el-button>
        <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
      </div>
    </el-dialog>
    <!--表格渲染-->
    <el-table ref="table" v-loading="crud.loading" lazy :load="getMenus" :data="crud.data"
              :tree-props="{children: 'children', hasChildren: 'hasChildren'}" row-key="menuId"
              @select="crud.selectChange" @select-all="crud.selectAllChange"
              @selection-change="crud.selectionChangeHandler">
      <el-table-column type="selection" width="55" />
      <el-table-column :show-overflow-tooltip="true" label="菜单标题" prop="title" />
      <el-table-column prop="icon" label="图标" align="center" width="60px">
        <template slot-scope="scope">
          <svg-icon :icon-class="scope.row.icon ? scope.row.icon : ''" />
        </template>
      </el-table-column>
      <el-table-column prop="menuSort" align="center" label="排序">
        <template slot-scope="scope">
          {{ scope.row.menuSort }}
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" prop="permission" label="权限标识" />
      <el-table-column :show-overflow-tooltip="true" prop="component" label="组件路径" />
      <el-table-column :show-overflow-tooltip="true" prop="name" label="组件名称" />
      <el-table-column prop="iFrame" label="外链" width="75px">
        <template slot-scope="scope">
          <span v-if="scope.row.iFrame">是</span>
          <span v-else>否</span>
        </template>
      </el-table-column>
      <el-table-column prop="isCache" label="缓存" width="75px">
        <template slot-scope="scope">
          <span v-if="scope.row.isCache">是</span>
          <span v-else>否</span>
        </template>
      </el-table-column>
      <el-table-column prop="hidden" label="可见" width="75px">
        <template slot-scope="scope">
          <span v-if="scope.row.hidden">否</span>
          <span v-else>是</span>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建日期" width="135px" />
      <el-table-column label="操作" width="130px" align="center" fixed="right">
        <template slot-scope="scope">
          <udOperation :data="scope.row" :permission="permission" msg="确定删除吗,如果存在下级节点则一并删除，此操作不能撤销！" />
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import * as crudMenu from '@/api/system/menu'
import IconSelect from '@/components/IconSelect'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
// crud交由presenter持有
const defaultForm = { menuId: null, title: null, menuSort: 999, path: null, component: null, name: null, iFrame: false, pid: 0, icon: null, isCache: false, hidden: false, menuType: 0, permission: null }
export default {
  name: 'Menu',
  components: { IconSelect, crudOperation, udOperation, Treeselect },
  cruds () {
    return CRUD({ title: '菜单', url: crudMenu.model_url, idField: 'menuId', crudMethod: { ...crudMenu } })
  },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  data () {
    return {
      menus: [],
      permission: {
        add: ['menu:add'],
        edit: ['menu:edit'],
        del: ['menu:del']
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' }
        ],
        path: [
          { required: true, message: '请输入地址', trigger: 'blur' }
        ]
      },
      normalizer (node) {
        return {
          id: node.menuId,
          label: node.title,
          children: node.children
        }
      }
    }
  },
  created () {
    this.crud.optShow.download = false
  },
  methods: {
    loadMenus ({ action, parentNode, callback }) {
      const params = { pid: parentNode.menuId, sort: 'menuSort,asc' }
      setTimeout(() => {
        crudMenu.getMenus(params).then(res => {
          res.data.forEach(item => {
            if (item.hasChildren) {
              item.children = null
            }
          })
          parentNode.children = res.data
          callback()
        })
      }, 100)
    },
    getMenus (tree, treeNode, resolve) {
      const params = { pid: tree.menuId, sort: 'menuSort,asc' }
      setTimeout(() => {
        crudMenu.getMenus(params).then(res => {
          this.menus = [{
            menuId: 0,
            pid: 0,
            title: '顶级菜单',
            children: JSON.parse(JSON.stringify(this.crud.data))
          }]
          resolve(res.data)
        })
      }, 100)
    },
    // 选中图标
    selected (name) {
      this.form.icon = name
    },
    [CRUD.HOOK.afterRefresh] (crud, form) {
      crud.data.forEach(item => {
        if (item.hasChildren) {
          item.children = null
        }
      })
      this.menus = [{
        menuId: 0,
        pid: 0,
        title: '顶级菜单',
        children: JSON.parse(JSON.stringify(crud.data))
      }]
    },
    [CRUD.HOOK.beforeSubmit] () {
      if (this.form.pid === undefined) this.form.pid = 0
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
::v-deep .el-input-number .el-input__inner {
  text-align: left;
}
::v-deep .vue-treeselect__control,
::v-deep .vue-treeselect__placeholder,
::v-deep .vue-treeselect__single-value {
  height: 30px;
  line-height: 30px;
}
</style>
