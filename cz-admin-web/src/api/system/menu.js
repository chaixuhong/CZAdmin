import request from '@/utils/request'
const model_url = '/system/menus'
export { model_url }

export function getMenus (data) {
  return request.get(`${model_url}`, { params: data })
}

export function getChild (data) {
  return request.get(`${model_url}/child`, { params: data })
}

export function getIdsByRoleId (data) {
  return request.get(`${model_url}/idsByRoleId`, { params: data })
}

export function add (data) {
  return request.post(`${model_url}`, data)
}

export function edit (data) {
  return request.put(`${model_url}`, data)
}

export function del (ids) {
  return request.delete(`${model_url}`, { data: ids })
}
