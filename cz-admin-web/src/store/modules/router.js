import { constantRoutes } from '@/router'
import { getMenus } from '@/api/login'
import Layout from '@/layout/index'
import ParentView from '@/components/ParentView'

const state = {
  routes: [],
  addRouters: [],
  requestStatus: false,
  sidebarRouters: []
}

const mutations = {
  SET_ROUTES: (state, data) => {
    state.addRouters = data
    state.routes = constantRoutes.concat(data)
  },
  SET_REQUEST_STATUS: (state, data) => {
    state.requestStatus = data
  },
  SET_SIDERBAR_ROUTERS: (state, data) => {
    state.sidebarRouters = constantRoutes.concat(data)
  }
}

const actions = {
  generateRoutes ({ commit }) {
    return new Promise((resolve, reject) => {
      getMenus().then(response => {
        const { data } = response
        const sidebarRouters = dealRouters(JSON.parse(JSON.stringify(data)))
        const accessedRoutes = dealRouters(JSON.parse(JSON.stringify(data)), true)
        accessedRoutes.push({ path: '*', redirect: '/404', hidden: true })
        commit('SET_ROUTES', accessedRoutes)
        commit('SET_SIDERBAR_ROUTERS', sidebarRouters)
        commit('SET_REQUEST_STATUS', true)
        resolve(accessedRoutes)
      }).catch(error => {
        reject(error)
      })
    })
  }
}

/**
 * 将服务端返回的字符串组件替换为前端component
 * 处理路由参数
 * @param {*} requestRouters
 * @param {*} type
 * @returns
 */
function dealRouters (requestRouters, type = false) {
  const menuArr = []
  requestRouters.forEach(item => {
    if (type && item.children) {
      item.children = filterChildren(item.children)
    }
    const menu = {
      name: item.name || item.title,
      hidden: item.hidden,
      path: item.pid ? item.path : `/${item.path}`, // 一级目录需要加斜杠，不然会报警告
      meta: {
        title: item.title,
        icon: item.icon,
        noCache: !item.isCache
      }
    }

    if (!item.iFrame) {
      if (!item.pid) {
        menu.component = Layout
      } else if (item.menuType === 0) {
        menu.component = ParentView
      } else {
        menu.component = loadView(item.component)
      }
    }
    if (item.children && item.children.length > 0) {
      menu.alwaysShow = true
      menu.redirect = 'noRedirect'
      menu.children = dealRouters(item.children)
    } else if (!item.pid) {
      // 处理是一级菜单并且没有子菜单的情况
      const menuTmp = {}
      menuTmp.meta = menu.meta
      // 非外链
      if (!item.iFrame) {
        menuTmp.path = 'index'
        menuTmp.name = menu.name
        if (item.component) {
          menuTmp.component = loadView(item.component)
        }
      } else {
        menuTmp.path = item.path
      }
      menu.name = ''
      menu.meta = ''
      menu.children = [menuTmp]
    }
    menuArr.push(menu)
  })
  return menuArr
}

/**
 * 处理多级缓存，由于多层router-view嵌套，只有顶层做了keep-live
 * 所以如果想要都支持缓存机制，需要将所有子级的router，处理为平级。
 * 以下就是处理方法
 * @param {*} childrenMap
 * @param {*} lastRouter
 * @returns
 */
function filterChildren (childrenMap, lastRouter = false) {
  var children = []
  childrenMap.forEach((el, index) => {
    if (el.children && el.children.length) {
      if (el.pid && el.menuType === 0) {
        el.children.forEach(c => {
          c.path = el.path + '/' + c.path
          if (c.children && c.children.length) {
            children = children.concat(filterChildren(c.children, c))
            return
          }
          children.push(c)
        })
        return
      }
    }
    if (lastRouter) {
      el.path = lastRouter.path + '/' + el.path
    }
    children = children.concat(el)
  })
  return children
}

const loadView = (view) => {
  return (resolve) => require([`@/views/${view}`], resolve)
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
