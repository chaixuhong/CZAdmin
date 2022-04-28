import zh from './zh'
// 从中文中获取key作为英文
const en = {}
function zhToEn (z, e) {
  Object.keys(z).forEach(item => {
    if (z[item] instanceof Object) {
      e[item] = {}
      zhToEn(z[item], e[item])
    } else {
      e[item] = item
    }
  })
}
zhToEn(zh, en)
export default en
