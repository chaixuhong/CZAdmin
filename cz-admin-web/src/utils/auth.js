import store from '@/store'
import { tokenKey } from '@/settings'

// token存入localStorage
export function setToken (token) {
  localStorage.setItem(tokenKey, token)
}
// 获取token
export function getToken () {
  // 尽量减少从浏览器读取token。增加一级缓存
  const token = store.getters.token
  if (token) return token
  return localStorage.getItem(tokenKey)
}
// 清除token
export function removeToken () {
  return localStorage.removeItem(tokenKey)
}
