import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import { configRouter } from './route-config'
import { getAllMenus, getAllProjects } from './action'
import VueValidator from 'vue-validator'
import Vueditor from './directives/ueditor'

Vue.config.debug = true

// install router
Vue.use(VueRouter)
Vue.use(VueValidator)
Vue.directive('ueditor', Vueditor)
console.log('in kindeditor init')
// create router
const router = new VueRouter()
console.log('window.url==' + window.location.href)
// configure router
configRouter(router)

// boostrap the app
const RootApp = Vue.extend(App)

router.start(RootApp, '#app')

router.beforeEach(function () {
  window.scrollTo(0, 0)
})
getAllProjects(App.store)

getAllMenus(App.store, router)
if (window.location.href.indexOf('#!') === -1 || window.location.href.lastIndexOf('#!/') === window.location.href.length - 3) {
  router.go('/user')
}

// just for debugging
window.router = router
