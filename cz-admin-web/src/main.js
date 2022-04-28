import Vue from 'vue'

import 'normalize.css/normalize.css'

import Element from 'element-ui'
import './styles/element-variables.scss'

import '@/styles/index.scss'
// 自定义指令
import directives from '@/directives'
// 数据字典
import dict from './components/Dict'
// 代码高亮
import VueHighlightJS from 'vue-highlightjs'
import 'highlight.js/styles/rainbow.css'
// markdown编辑器
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
// 复制插件
import VueClipboard from 'vue-clipboard2'

import App from './App'
import store from './store'
import router from './router'

import i18n from './lang'
import './assets/icons'
import './router/router'
import * as filters from './filters'

Vue.use(Element, {
  size: localStorage.getItem('size') || 'mini',
  i18n: (key, value) => i18n.t(key, value)
})
Vue.use(directives)
Vue.use(dict)
Vue.use(VueHighlightJS)
Vue.use(mavonEditor)
Vue.use(VueClipboard)
// 注册全局过滤器
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  i18n,
  render: h => h(App)
})
