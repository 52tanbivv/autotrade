<template>

<div class='container-fluid'>
  <div class='content'>
    <div class='flash-container'>
    </div>
    <validator name="validation">
      <div class='clearfix'>
        <h3 class='page-title'>{{ title }}</h3>
        <hr>
        <div class='user_new'>
          <form accept-charset="UTF-8" action="/admin/users" class="form-horizontal fieldset-form" enctype="multipart/form-data" id="new_user" method="post">
            <div style="display:none">
              <input name="utf8" type="hidden" value="&#x2713;" />
              <input name="authenticity_token" type="hidden" value="7IKiLSYGNh8h0g2LN18pL00xt40LGVWxlAKgaB0n3jM=" />
            </div>
            <fieldset>
              <legend>帐户</legend>
              <div class='form-group'>
                <label class="control-label" for="user_name">姓名</label>
                <div class='col-sm-10'>
                  <input autocomplete="off" class="form-control" type="text"
                  v-bind:class="{'validate_error': (this.showError && ($validation.name.required || $validation.name.maxlength))}"
                  v-model="formdata.name" v-validate:name="{ required: true, maxlength: 20 }" />
                  <span style="color:red" v-if="this.showError && $validation.name.required">请输入姓名</span>
                  <span style="color:red" v-if="this.showError && $validation.name.maxlength">姓名长度不得超过20</span>
                </div>
              </div>
              <div  class='form-group'>
                <label class="control-label" for="user_username">用户名</label>
                <div class='col-sm-10'>
                  <input autocomplete="off" class="form-control" type="text"
                  v-bind:class="{'validate_error': (this.showError && ($validation.loginname.required || $validation.loginname.maxlength))}"
                  v-model="formdata.loginName" v-validate:loginname="{ required: true, maxlength: 20 }" />
                  <span style="color:red" v-if="this.showError && $validation.loginname.required">请输入用户名</span>
                  <span style="color:red" v-if="this.showError && $validation.loginname.maxlength">用户名长度不得超过20</span>
                </div>
              </div>
              <div  class='form-group'>
                <label class="control-label" for="user_username">头像</label>
                <div class='col-sm-10'>
                  <input autocomplete="off" class="form-control" type="text"
                  v-bind:class="{'validate_error': (this.showError && ($validation.avatar.required || $validation.avatar.maxlength))}"
                  v-model="formdata.avatar" v-validate:avatar="{ required: true, maxlength: 200 }" />
                  <span style="color:red" v-if="this.showError && $validation.avatar.required">请输入头像的URL地址</span>
                  <span style="color:red" v-if="this.showError && $validation.avatar.maxlength">用户名长度不得超过20</span>
                </div>
              </div>
              <div  class='form-group'>
                <label class="control-label" for="user_username">头像</label>
                <div class='col-sm-10'>
                  <img  style="height:50px;" :src="formdata.avatar"/>
                </div>
              </div>
            </fieldset>
            <fieldset >
              <legend>密码</legend>
              <div class='form-group'>
                <label class="control-label" for="user_username">密码</label>
                <div class='col-sm-10'>
                  <input v-model="formdata.plainpassword" autocomplete="off" class="form-control" type="password"
                  v-bind:class="{'validate_error': (this.showError && ($validation.plainpassword.required || $validation.plainpassword.maxlength))}"
                  v-model="formdata.plainpassword" v-validate:plainpassword="{ required: true, minlength: 6, maxlength: 20 }" />
                  <span style="color:red" v-if="this.showError && $validation.plainpassword.required">请输入密码</span>
                  <span style="color:red" v-if="this.showError && $validation.plainpassword.minlength">长度不得少于6位</span>
                  <span style="color:red" v-if="this.showError && $validation.plainpassword.maxlength">密码长度不得超过20</span>
                </div>
              </div>
              <div class='form-group'>
                <label class="control-label" for="user_username">确认密码</label>
                <div class='col-sm-10'>
                  <input autocomplete="off" class="form-control" type="password"
                  v-bind:class="{'validate_error': (this.showError
                  && ($validation.repassword.required
                  || $validation.repassword.maxlength
                  || $validation.repassword.value))}"
                  v-model="formdata.repassword" v-validate:repassword="{ required: true }" />
                  <span style="color:red" v-if="this.showError && $validation.repassword.required">请再次输入密码</span>
                  <span style="color:red" v-if="this.showError && $validation.repassword.value">两次密码输入不一致</span>
                </div>
              </div>
            </fieldset>
            <fieldset>
              <legend>访问</legend>
              <div class='form-group'>
                <label class="control-label" for="user_projects_limit">项目</label>
                <div class='col-sm-10'>
                  <select class="form-control" v-model="formdata.projectid">
                    <option v-for="project in projectList" value="{{project.id}}">{{project.name}}</option>
                  </select>
                </div>
              </div>
              <div v-if="!this.$route.params.userid" class='form-group'>
                <label class="control-label" for="user_projects_limit">角色</label>
                <div class='col-sm-10'>
                  <select class="form-control" v-model="formdata.role.id">
                    <option v-for="role in roleList " value="{{role.id}}">{{role.remark}}</option>
                  </select>
                </div>
              </div>
              <div class='form-group'>
                <label class="control-label" for="user_username">调试地址</label>
                <div class='col-sm-10'>
                  <input v-model="formdata.debugUrl" class="form-control" required="required" type="text"
                  v-bind:class="{'validate_error': (this.showError && ($validation.debugurl.required || $validation.debugurl.maxlength))}"
                  v-model="formdata.debugurl" v-validate:debugurl="{ required: true, maxlength: 200 }" />
                  <span style="color:red" v-if="this.showError && $validation.debugurl.required">请输入调试地址</span>
                  <span style="color:red" v-if="this.showError && $validation.debugurl.maxlength">调试地址长度不得超过200</span>
                </div>
              </div>
            </fieldset>
            <fieldset >
              <legend>简介</legend>
              <div class='form-group'>
                <label class="control-label" for="user_skype">备注</label>
                <div class='col-sm-10'>
                  <textarea v-model="formdata.remark" class="form-control" rows="6"></textarea>
                </div>
              </div>
            </fieldset>
            <div class='form-actions'>
              <input class="btn btn-create" name="commit" type="button" @click="save()" value="保存" />
              <a class="btn btn-cancel" href="javascript:;"  @click="back()">取消</a>
            </div>
          </form>
        </div>
      </div>
    </validator>
  </div>
</div>

</template>

<script>
import {Modal} from '@wxt/control'
import {saveUser} from './user-action.js'
import { callApi } from '../../api/api.js'

export default {
  data () {
    return {
      formdata: {
        avatar: '',
        projectid: '',
        role: {
          id: 'a1d58fc2fbef523d01cc2a51ceb36bc1',
          name: '',
          remark: '',
          sort: ''
        }
      },
      projectList: [],
      roleList: [{}],
      showModal: false,
      showError: false,
      ifEdit: true,
      title: '新建用户'
    }
  },
  methods: {
    save () {
      if (!this.$validation.valid && !this.ifEdit) {
        this.showError = true
      } else {
        this.saveUser(this.formdata)
        this.reset()
        window.history.back()
      }
    },
    back () {
      window.history.back()
    },
    reset () {
      this.formdata = {
        id: '',
        name: '',
        loginname: '',
        plainpassword: '',
        repassword: '',
        debugurl: '1',
        role: {
          id: 'a1d58fc2fbef523d01cc2a51ceb36bc1'
        },
        projectList: [],
        remark: ''
      }
    },
    initSelect () {
      /* 初始化下拉框 */
      // 查询项目列表
      callApi('project_list', {}, (data) => {
        console.log('project_list is ' + data)
        this.projectList = data
      })
      // 查询角色列表
      callApi('role_list', {}, (data) => {
        console.log('role_list is ' + data)
        this.roleList = data
      })
    },
    initUserInfo () {
      if (this.$route.params.userid !== undefined) {
        callApi('user_find', {userid: this.$route.params.userid}, (data) => {
          this.formdata = data
          this.ifEdit = true
          this.title = '修改用户信息'
        })
      } else {
        this.reset()
      }
    },
    open () {
      this.showModal = true
    }
  },
  vuex: {
    actions: {
      saveUser
    },
    getters: {
      developers: ({user}) => user.userDevelopers
    }
  },
  components: {
    Modal
  },
  route: {
    activate: function (transition) {
      this.initSelect()
      this.initUserInfo()
      this.showError = false
      this.ifEdit = true
      transition.next()
    }
  }
}
</script>
<style type="text/css">
  .validate_error{border:1px solid red !important;}
</style>
