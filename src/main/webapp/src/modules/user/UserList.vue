<template>

<div class='panel panel-default'>
  <div class='panel-heading'>用户 ({{userLists.length}})
    <div class='panel-head-actions'>
      <div class='dropdown inline'>
        <form accept-charset="UTF-8" class="form-inline" method="get">
          <div style="display:none"><input name="utf8" type="hidden" value="✓"></div>
          <div class="form-group">
            <input class="form-control" placeholder="输入姓名、用户名或邮箱" :value="queryParam.userStr" :value="queryParam.apiStr" @blur="updateStr" type="search" value=""  />
          </div>
          <a class="btn btn-sm btn-primary" name="button" @click="this.refreshUser()" ><icon name="g-zoom-in" scale="0.7"></icon>查询</a>
        </form>
      </div>
      <a class="btn btn-new btn-sm" v-link="{name: 'newuser'}"><icon name="g-control-point" scale="0.67"></icon>新建用户</a>
    </div>
  </div>
  <div class="list_head">
    <ul>
      <li>姓名</li>
      <li>账号</li>
      <li>用户类型</li>
      <li>操作</li>
    </ul>
  </div>
  <div class='well-list'>
    <li v-for="user in userLists">
      <ul>
        <li>
          <div class='list-item-name'>
            <img v-bind:src="user.avatar" class="avatar s24" />
            <a  v-link="{name: 'edituser', params: {userid: user.id}}" style="vertical-align:middle">{{user.name}}</a>
          </div>
        </li>
        <li>
          <span class='light'>
            <icon name="g-mail-outline"  scale="0.8"></icon>

            <a class="light" href="mailto:{{user.loginName}}">{{user.loginName}}</a>
          </span>
        </li>
        <li>
          <p>{{user.roleName}}</p>
        </li>
        <li>
        <div class='pull-right'>
          <a class="btn btn-sm btn-remove" @click="removeUser(user.id, user.name)" rel="nofollow">删除</a>
        </div>
        </li>
      </ul>
    </li>
  </div>
</div>


</template>

<script type="text/javascript">
import {refreshUserState, refreshUser, changeParam} from './user-action.js'
import {Icon} from '@wxt/control'

export default {
  methods: {
    resetQueryParam () {
      this.queryParam.userStr = ''
    },
    updateStr: function (e) {
      this.changeParam('userStr', e.target.value)
    },
    removeUser (userid, username) {
      this.$dispatch('remove-user', userid, username)
    }
  },
  components: {
    Icon
  },
  vuex: {
    actions: {
      refreshUserState,
      refreshUser,
      changeParam
    },
    getters: {
      queryParam: ({userstore}) => userstore.queryParam,
      userLists: ({userstore}) => userstore.users
    }
  }
}
</script>
<style>
.list_head{ width: 100%; height: 40px; }
.list_head ul{ width: 100%; padding: 0; margin: 0;border-bottom:1px solid #cecece; height: 40px; }
.list_head ul li{ width: 25%; float: left; padding:10px 0; text-align: center; color: #888;}
.list_head ul li:first-child{ text-align: left;text-indent: 2em; }
div.well-list li ul{ width: 100%; }
div.well-list li ul li{ width: 25%;float: left; padding: 0;margin:0; border: none; text-align: center;}
div.well-list li ul li:hover{border: none;}
div.well-list li ul li a{ text-decoration: none; }
</style>
