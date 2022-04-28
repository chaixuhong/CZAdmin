import request from '@/utils/request'
const model_url = '/${changeClassName}'
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
