import request from '@/utils/request'
const model_url = '/system/dict'

export { model_url }
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

/**
 * 获取所有顶级字典列表
 * @returns
 */
export function getDicts () {
  return request.get(`${model_url}/all`)
}

