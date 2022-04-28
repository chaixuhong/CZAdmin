import request from '@/utils/request'
const model_url = '/tools/files'

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
 * 上传文件
 * @returns
 */
export function upload (file, fileName, appCode, pathName) {
  var data = new FormData()
  data.append('files', file)
  data.append('fileName', fileName)
  data.append('appCode', appCode)
  data.append('pathName', pathName)
  return request.post(`${model_url}`, data, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}
