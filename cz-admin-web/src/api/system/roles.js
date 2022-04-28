import request from '@/utils/request'
const model_url = '/system/roles'

export { model_url }
/**
 * 查询角色列表
 * @param {*} data
 * @returns
 */
export function list (data) {
  return request.get(model_url, { params: data })
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

export function menu (data) {
  return request.post(`${model_url}/menu`, data)
}

export function getLevel () {
  return request.get(`${model_url}/level`)
}

export function getRoleIds (data) {
  return request.get(`${model_url}/roleIds`, { params: data })
}

export function getDeptIds (data) {
  return request.get(`${model_url}/deptIds`, { params: data })
}

