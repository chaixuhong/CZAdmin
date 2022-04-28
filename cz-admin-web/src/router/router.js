import router from '.'
import store from '../store'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import getPageTitle from '@/utils/get-page-title'
import { getToken } from '@/utils/auth'

NProgress.configure({ showSpinner: false })

const whiteList = ['/login', '/404', '/401'] // 白名单，直接进入
// 路由守卫，每次路由都检查登录状态
router.beforeEach(async (to, from, next) => {
  NProgress.start()
  // 动态设置页面title
  document.title = getPageTitle(to.meta.title)
  if (getToken()) {
    if (to.path === '/login') {
      next({ path: '/' })
    } else {
      const hasRoles = store.getters.roles && store.getters.roles.length > 0
      if (!hasRoles && !store.getters.requestStatus) {
        try {
          await store.dispatch('user/getInfo')
          generateDynamicRouter(to, next)
        } catch (error) {
          await store.dispatch('user/resetToken')
          next(`/login?redirect=${to.path}`)
        }
      } else if (!store.getters.requestStatus) {
        // 登录后获取路由
        generateDynamicRouter(to, next)
      } else {
        next()
      }
    }
  } else if (whiteList.indexOf(to.path) !== -1) {
    next()
  } else {
    next(`/login?redirect=${to.path}`)
  }
})

/**
 * 加载动态路由
 * @param {*} to
 * @param {*} next
 */
async function generateDynamicRouter (to, next) {
  const dynamicRouter = await store.dispatch('router/generateRoutes')
  if (dynamicRouter.length > 0) router.addRoutes(dynamicRouter)
  next({ ...to, replace: true })
}

router.afterEach(() => {
  NProgress.done()
})
