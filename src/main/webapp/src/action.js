import { callApi } from './api/api.js'

// 查询菜单
export function getAllMenus (store, router) {
  let success = (data) => {
    store.dispatch('INIT_MENU', data)
  }

  console.log('store.state.menus.length ==' + store.state.menus.length)
  if (store.state.menus.length === 0) {
    // 取后台查询菜单数据
    callApi('menu_list', {}, success)
  }
}

// 查询项目列表
export function getAllProjects (store) {
  let success = (data) => {
    store.dispatch('REFRESH_PROJECT', data)
    if (data.length > 0) {
      let projectid = data[0].id
      store.dispatch('CHANGE_PROJECT', projectid)
    }
  }

  callApi('project_list', {}, success)
}

// 查询项目列表
export function changeProject (store, projectid) {
  store.dispatch('CHANGE_PROJECT', projectid)

  let success = (data) => {
    console.log('user_setCurrentProject result is ' + data)
  }
  callApi('user_setCurrentProject', {projectid: projectid}, success)
}

// 点击菜单
export function clickMenu ({dispatch}, menuid, url, router) {
  router.go(url)
}

export function chageBarState ({dispatch}) {
  dispatch('CHANGE_BAR_STATE')
}

// 获取当前用户
export function getCurrentUser ({dispatch}) {
  let success = (data) => {
    dispatch('QUERY_USER', data)
  }

  callApi('user_findcurrentuser', {}, success)
}

// 隐藏alert框
export function hideAlert ({dispatch}) {
  dispatch('HIDE_ALERT')
}

// 显示alert框
export function showAlert ({dispatch}, type, msg) {
  console.log('IN SHOW ALERT STORE ,msg.length= ' + msg)
  dispatch('SHOW_ALERT', type, msg)
}
