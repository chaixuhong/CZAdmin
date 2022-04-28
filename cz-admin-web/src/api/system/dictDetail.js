import request from '@/utils/request'
const model_url = '/system/dict/detail'

export { model_url }

/**
 * 查
 * @param {*} data
 * @returns
 */
export function get (dictName) {
  const params = {
    dictName
  }
  return request.get(`${model_url}/listByName`, { params: params })
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
