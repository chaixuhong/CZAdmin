import request from '@/utils/request'
const model_url = '/system/job'

export { model_url }
/**
 * 获取所有岗位
 * @returns
 */
export function all () {
  return request.get(`${model_url}/all`)
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
