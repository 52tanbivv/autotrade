import { callApi } from '../../api/api.js'

// 获取USER列表
export function refreshUser (store) {
  // 组装参数
  let param = store.state.userstore.queryParam
  let success = (data) => store.dispatch('REFRESH_USER', data)
  console.log('IN refreshApiDeveloper')
  callApi('user_list', param, success)
}

// 获取USER开发者列表
export function refreshUserDeveloper (store) {
  let success = (data) => store.dispatch('REFRESH_USERDEVELOPER', data)
  callApi('user_list', {}, success)
}

// 更新USER查询参数
export function changeParam ({dispatch}, paramType, paramValue) {
  dispatch('CHANGE_PARAM', paramType, paramValue)
}

// 重置USER查询参数
export function resetParam ({dispatch}, paramType, paramValue) {
  dispatch('RESET_PARAM', paramType, paramValue)
}

// 保存用户信息
export function saveUser (store, formdata) {
  let fail = (user, ret) => {
    showAlert(store, 'danger', ret.msg)
  }
  let success = (data) => {
    // 展示保存成功的alert
    showAlert(store, 'success', 'USER保存成功！')
    refreshUser(store)
    // this.$router.go('/user')
  }
  callApi('user_save', formdata, success, false, fail)
}

// 隐藏alert框
export function hideAlert ({dispatch}) {
  dispatch('HIDE_ALERT')
}

// 显示alert框
export function showAlert ({dispatch}, type, msg) {
  dispatch('SHOW_ALERT', type, msg)
}
