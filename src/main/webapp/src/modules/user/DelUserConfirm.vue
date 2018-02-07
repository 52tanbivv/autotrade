<template>
    <modal title="确认删除用户" :show.sync="show" effect="fade" :small="true" :callback="delUser">
      <div slot="modal-body" class="modal-body">
      <span>是否确认用户“{{username}}”?</span>  
      </div>
    </modal>
</template>
<script>
import { callApi } from '../../api/api.js'
import { Modal } from '@wxt/control'
import {refreshUser} from './user-action.js'
import {showAlert} from '../../action.js'

export default {
  data () {
    return {
      username: '',
      userid: '',
      show: false
    }
  },
  methods: {
    open (userid, username) {
      this.show = true
      this.userid = userid
      this.username = username
    },
    delUser () {
      callApi('user_delete', {id: this.userid}, (data) => {
        if (data.includes('error')) {
          this.showAlert('danger', data)
        } else {
          this.show = false
          this.showAlert('success', data)
          this.refreshUser()
        }
      }, false)
    }
  },
  vuex: {
    actions: {
      showAlert,
      refreshUser
    }
  },
  components: {
    Modal
  }
}
</script>

<style>
  select.form-control { 
    background-size: 13px!important;
    background-position: right center!important;
    background-repeat: no-repeat!important;
}
</style>
