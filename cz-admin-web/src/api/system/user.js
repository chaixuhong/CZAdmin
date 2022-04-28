import request from '@/utils/request'
const model_url = '/system/user'

export { model_url }

export function add (data) {
  return request.post(model_url, data)
}
export function edit (data) {
  return request.put(model_url, data)
}
export function del (ids) {
  return request.delete(model_url, { data: ids })
}
