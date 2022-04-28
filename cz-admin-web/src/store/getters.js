const getters = {
  // 系统设置
  language: state => state.app.language,
  sidebar: state => state.app.sidebar,
  size: state => state.app.size,
  device: state => state.app.device,
  uniqueOpened: state => state.settings.uniqueOpened,
  theme: state => state.settings.theme,
  // 路由
  sidebarRouters: state => state.router.sidebarRouters,
  requestStatus: state => state.router.requestStatus,
  // 用户信息
  avatar: state => state.user.userInfo.user.avatarName ? '/api/avatar/' + state.user.userInfo.user.avatarName : require('@/assets/images/head.gif'), // state.user.userInfo.user.avatarName
  name: state => state.user.userInfo.user.nickName,
  token: state => state.user.token,
  roles: state => state.user.userInfo.roles,
  authority: state => state.user.userInfo.authority,
  dept: state => state.user.userInfo.dept,
  job: state => state.user.userInfo.job,
  user: state => state.user.userInfo.user
}
export default getters
