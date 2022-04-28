import { isvalidPhone } from './validate.js'
// 验证手机号
export function validPhone (rule, value, callback) {
  if (!value) {
    callback(new Error('请输入电话号码'))
  } else if (!isvalidPhone(value)) {
    callback(new Error('请输入正确的11位手机号码'))
  } else {
    callback()
  }
}
