import User from './modules/user/User'
import NewUser from './modules/user/NewUser'
import DelUserConfirm from './modules/user/DelUserConfirm'
import Icondemo from './Icondemo'

export function configRouter (router) {
  router.map({
    // 用户管理相关URL
    '/user': {
      component: User
    },
    '/user/newuser': {
      name: 'newuser',
      component: NewUser
    },
    '/user/edituser/:userid': {
      name: 'edituser',
      component: NewUser
    },
    '/user/deluserconfirm/:userid': {
      name: 'deluserconfirm',
      component: DelUserConfirm
    },
    // 用户管理相关URL
    '/icon': {
      component: Icondemo
    }
  })
}
