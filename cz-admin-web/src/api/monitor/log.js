import request from '@/utils/request'
const model_url = '/logs'

export { model_url }

export function getException (logId) {
  return request.get(`${model_url}/error/${logId}`)
}

export function delAllInfo () {
  return request.delete(`${model_url}/info`)
}

export function delAllError () {
  return request.delete(`${model_url}/error`)
}
