import request from '@/utils/request'
const model_url = '/system/dept'

export { model_url }
/**
 * 获取组织
 * @param {*} data
 * @returns
 */
export function getDepts (data) {
  return request.get(`${model_url}`, { params: data })
}
/**
 * 增
 * @param {*} data
 * @returns
 */
export function add (data) {
  return request.post(model_url, data)
}

/**
 * 改
 * @param {*} data
 * @returns
 */
export function edit (data) {
  return request.put(model_url, data)
}

/**
 * 删
 * @param {*} data
 * @returns
 */
export function del (ids) {
  return request.delete(model_url, { data: ids })
}
