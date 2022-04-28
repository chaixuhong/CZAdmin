import request from '@/utils/request'
const model_url = '/generator/config'

export { model_url }

/**
 * 获取字段配置
 * @param {*} data
 * @returns
 */
export function getColumns (data) {
  return request.get(`${model_url}/columns`, { params: data })
}

/**
 * 保存字段配置
 * @param {*} data
 * @returns
 */
export function saveColumns (data) {
  return request.put(`${model_url}/columns`, data)
}

/**
 * 同步字段配置
 * @param {*} data
 * @returns
 */
export function syncColumns (data) {
  return request.post(`${model_url}/columns`, data)
}

/**
 * 获取包配置
 * @param {*} data
 * @returns
 */
export function getConfig (data) {
  return request.get(`${model_url}`, { params: data })
}

/**
 * 保存包配置
 * @param {*} data
 * @returns
 */
export function saveConfig (data) {
  return request.put(`${model_url}`, data)
}

