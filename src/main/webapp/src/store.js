import Vue from 'vue'
import Vuex from 'vuex'
import userstore from './modules/user/user-store'

Vue.use(Vuex)

const state = {
  user: {},
  projects: [],
  menus: [],
   // 成功或者失败的提示信息框
  alert: {
    show: false,
    type: 'success',
    msg: ''
  },
  projectid: '',
  batState: 'closed'
}

const mutations = {
  REFRESH_PROJECT (state, projects) {
    state.projects = projects
  },
  CHANGE_PROJECT (state, projectid) {
    state.projectid = projectid
  },
  INIT_MENU (state, menus) {
    state.menus = menus
  },
  QUERY_USER (state, user) {
    console.log('user is ' + JSON.stringify(user))
    state.user = user
  },
  SHOW_ALERT (state, type, msg) {
    state.alert.show = true
    state.alert.type = type
    state.alert.msg = msg
  },
  HIDE_ALERT (state) {
    state.alert.show = false
  },
  CHANGE_BAR_STATE (state) {
    if (state.batState === 'expanded') {
      state.batState = 'closed'
    } else {
      state.batState = 'expanded'
    }
  }
}

export default new Vuex.Store({
  state,
  mutations,
  modules: {
    userstore
  }
})
