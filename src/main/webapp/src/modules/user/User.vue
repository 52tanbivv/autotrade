<template>
  <div>
    <del-user-confirm v-ref:DelUserconfirm></del-user-confirm>

    <div class="container-fluid">
      <div class="content">
        <div class="flash-container">
        </div>
        <div class="clearfix">
          <div class="row">
            <a class="show-aside" href="#aside"><i class="fa fa-angle-left"></i>
            </a>
            <user-list></user-list>
            <page :page-inx="queryParam.pageIndex" :page-count="queryParam.pageTotal" :callback="switchPage"></page>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {showAlert} from '../../action.js'
import { callApi } from '../../api/api.js'
import {refreshUser, refreshUserDeveloper, hideAlert, changeParam} from './user-action.js'
import {Modal, Icon, Page} from '@wxt/control'
import UserList from './UserList'
import NewUserDialog from './NewUser'
import DelUserConfirm from './DelUserConfirm'

export default {
  data () {
    return {
      curuserid: ''
    }
  },
  methods: {
    showNewDialog () {
      this.$refs.newuserdialog.open()
    },
    switchPage (n) {
      this.changeParam('pageInx', n)
      this.refreshUser()
    },
    delconfirm () {
      this.showdelconfirm = true
    },
    delUser () {
      callApi('user_delete', {id: this.curuserid}, (data) => {
        if (data.includes('error')) {
          this.showAlert('danger', data)
        } else {
          this.showAlert('success', data)
          this.refreshUser()
        }
        this.showdelconfirm = false
      }, false)
    }
  },
  components: {
    Modal,
    Page,
    Icon,
    UserList,
    NewUserDialog,
    DelUserConfirm
  },
  vuex: {
    actions: {
      refreshUser,
      refreshUserDeveloper,
      hideAlert,
      changeParam,
      showAlert
    },
    getters: {
      queryParam: ({userstore}) => userstore.queryParam
    }
  },
  route: {
    activate: function (transition) {
      this.refreshUser()
      this.refreshUserDeveloper()
      transition.next()
    }
  },
  events: {
    // 打开修改user对话框的事件，从后台获取数据后，打开对话框
    'edit-user-open': function (userid) {
      callApi('user_findbyid', {userid: userid}, (data) => {
        this.$refs.edituseraside.open(data)
      })
    },
     // 删除user事件，初始化参数后，弹出确认删除框
    'remove-user': function (userid, username) {
      this.$refs.deluserconfirm.open(userid, username)
    }
  }
}
</script>
