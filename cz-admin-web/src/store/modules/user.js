import { login, getInfo, logout } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'
const state = {
  userInfo: '',
  token: ''
}

const mutations = {
  SET_USER_INFO: (state, data) => {
    state.userInfo = data
  },
  SET_TOKEN: (state, data) => {
    state.token = data
  }
}

const actions = {
  // 用户登录
  login ({ commit }, userInfo) {
    userInfo.username = userInfo.username.trim()
    return new Promise((resolve, reject) => {
      login(userInfo).then(result => {
        const { data } = result
        setToken(data.token)
        commit('SET_USER_INFO', data.userInfo)
        commit('SET_TOKEN', data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  // 获取用户信息
  getInfo ({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo().then(result => {
        const { data } = result
        commit('SET_USER_INFO', data)
        commit('SET_TOKEN', getToken())
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 退出登录
  logout ({ commit, state, dispatch }) {
    return new Promise((resolve, reject) => {
      logout().then(res => {
        // 重置浏览过的页面，删除页签缓存
        dispatch('tagsView/delAllViews', null, { root: true })
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 清除token
  resetToken ({ commit }) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      removeToken()
      resolve()
    })
  }

}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
