import store from '@/store'

function checkPermission (value) {
  const authority = store.getters.authority
  if (value && value instanceof Array) {
    return authority.some(item => value.includes(item))
  } else {
    throw new Error(`使用方式： v-permission="['admin','editor']"`)
  }
}

const permission = {
  inserted: function (el, binding) {
    const { value } = binding // 获取到 v-permission的值
    if (permission) {
      const hasPermission = checkPermission(value)
      if (!hasPermission) {
        // 没有权限 移除Dom元素
        el.parentNode && el.parentNode.removeChild(el)
      }
    }
  }
}

export default permission
