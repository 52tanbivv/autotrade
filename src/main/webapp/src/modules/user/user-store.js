
// initial state
const state = {
  // user列表数据源
  users: [],
  // user状态列表数据源
  userStateCount: {},
  // user的开发人员列表
  userDevelopers: [],
  // 页面查询参数
  queryParam: {
    userState: 'ALL',
    userStr: '',
    developer: '',
    pageInx: 1,
    pageSize: 5,
    pageTotal: 0
  },
  // 成功或者失败的提示信息框
  alert: {
    show: false,
    type: 'success',
    msg: ''
  }
}

// mutations
const mutations = {
  REFRESH_USER (state, users) {
    state.users = users
    state.queryParam.pageTotal = Math.ceil(users.total / state.queryParam.pageSize)
    console.log('users=' + JSON.stringify(users))
  },
  REFRESH_USERDEVELOPER (state, userDevelopers) {
    state.userDevelopers = userDevelopers
  },
  CHANGE_PARAM (state, paramType, newParam) {
    state.queryParam[paramType] = newParam
    if (paramType !== 'pageInx') {
      state.queryParam.pageInx = 1
    }
  },
  RESET_PARAM (state) {
    state.queryParam.userState = 'ALL'
    state.queryParam.userStr = ''
    state.queryParam.developer = ''
    state.queryParam.pageInx = 1
    state.queryParam.pageSize = 5
  },
  SHOW_ALERT (state, type, msg) {
    state.alert.show = true
    state.alert.type = type
    state.alert.msg = msg
  },
  HIDE_ALERT (state) {
    state.alert.show = false
  }
}

export default {
  state,
  mutations
}
