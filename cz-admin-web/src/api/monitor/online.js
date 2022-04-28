import request from '@/utils/request'
const model_url = '/monitor/online'

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
 * 删
 * @param {*} data
 * @returns
 */
export function del (ids) {
  return request.delete(model_url, { data: ids })
}
