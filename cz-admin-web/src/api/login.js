import request from '@/utils/request'

/**
 * 登录
 * @param {*} data
 * @returns
 */
export function login (data) {
  return request.post('/login', data)
}

/**
 * 登出
 * @param {*} data
 * @returns
 */
export function logout () {
  return request.post('/logout')
}

/**
 * 获取登录图片验证码
 * @returns
 */
export function getCodeImg () {
  return request.get('/auth/code')
}

/**
 * 获取登录用户信息
 * @returns
 */
export function getInfo () {
  return request.get('/auth/user')
}

/**
 * 获取登录用户左侧菜单
 * @returns
 */
export function getMenus () {
  return request.get('/auth/menus')
}

/**
 * 登录用户修改个人信息
 * @returns
 */
export function updateInfo (data) {
  return request.post('/auth/updateInfo', data)
}

/**
 * 登录用户修改密码
 * @returns
 */
export function updatePass (data) {
  return request.post('/auth/updatePass', data)
}
