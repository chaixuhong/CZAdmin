import store from '@/store'
const modulesFiles = require.context('./modules', true, /\.js$/)

export default {
  install (Vue) {
    modulesFiles.keys().reduce((modules, modulePath) => {
      const moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, '$1')
      const value = modulesFiles(modulePath)
      Vue.directive(moduleName, value.default)
    }, {})
    Vue.prototype.checkPer = (value) => {
      const authority = store.getters.authority
      if (value && value instanceof Array) {
        return authority.some(item => value.includes(item))
      } else {
        throw new Error(`使用方式： v-if="checkPer(['admin','editor'])"`)
      }
    }
    Vue.prototype.checkResult = (value) => {
      if (!value || value.indexOf('{') < 0) return false
      const result = JSON.parse(value)
      if (!result.data || (result.data instanceof Array && result.data.length === 0)) {
        return false
      }
      return true
    }
  }
}
