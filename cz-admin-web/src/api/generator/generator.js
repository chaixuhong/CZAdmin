import request from '@/utils/request'
const model_url = '/generator'

export function previewCode (data) {
  return request.get(`${model_url}`, { params: data })
}

export function generatorCode (data) {
  return request.put(`${model_url}`, data)
}

export function downloadCode (data) {
  return request.post(`${model_url}`, data, { responseType: 'blob' })
}

export { model_url }

