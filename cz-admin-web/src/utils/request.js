import { Message } from 'element-ui'
import axios from 'axios'
import store from '../store'
import { getToken } from '@/utils/auth'
// 创建axios实例
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // api 的 base_url
  timeout: 60000 // 请求超时时间
})

// 请求拦截器
service.interceptors.request.use(
  req => {
    if (getToken()) {
      req.headers['Authorization'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    return req
  },
  error => {
    console.log(error)
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  response => {
    return response.data
  },
  error => {
    const { code, errMsg } = error.response.data
    if (code === 1000) {
      store.dispatch('user/logout').then(() => {
        sessionStorage.setItem('responseCode', 1000)
        location.reload()
      })
    } else if (code && errMsg) {
      Message({
        message: errMsg,
        type: 'error',
        duration: 5 * 1000
      })
    } else if (error.response.data instanceof Blob) {
      const reader = new FileReader()
      reader.readAsText(error.response.data, 'utf-8')
      reader.onload = () => {
        Message({
          message: JSON.parse(reader.result).errMsg,
          type: 'error',
          duration: 5 * 1000
        })
      }
    } else {
      // 服务器异常
      Message({
        message: '服务器崩溃了~~',
        type: 'error',
        duration: 5 * 1000
      })
    }
    return Promise.reject(error)
  }
)

export default service
