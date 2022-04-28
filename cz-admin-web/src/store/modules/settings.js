import variables from '@/styles/element-variables.scss'
import defaultSettings from '@/settings'

const state = {
  theme: variables.theme,
  showSettings: false,
  tagsView: defaultSettings.tagsView,
  fixedHeader: defaultSettings.fixedHeader,
  sidebarLogo: defaultSettings.sidebarLogo,
  supportPinyinSearch: defaultSettings.supportPinyinSearch,
  uniqueOpened: defaultSettings.uniqueOpened,
  showFooter: defaultSettings.showFooter,
  footerTxt: defaultSettings.footerTxt,
  caseNumber: defaultSettings.caseNumber
}

const mutations = {
  CHANGE_SETTING: (state, { key, value }) => {
    if (Object.prototype.hasOwnProperty.call(state, key)) {
      state[key] = value
    }
  }
}

const actions = {
  changeSetting ({ commit }, data) {
    commit('CHANGE_SETTING', data)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

