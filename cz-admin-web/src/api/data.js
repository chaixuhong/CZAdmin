import request from '@/utils/request'
import qs from 'qs'

export function initData (url, params) {
  return request.get(`${url}?${qs.stringify(params, { indices: false })}`)
}

export function download (url, params) {
  return request.get(`${url}?${qs.stringify(params, { indices: false })}`, { responseType: 'blob' })
}
