<template>
   <aside class="col-md-3">
    <div class="admin-filter">
      <ul class="nav nav-pills nav-stacked">
        <li v-bind:class="[userState == 'ALL' ? 'active' : '']">
          <a href="javascript:;"  @click="filter('ALL')">{{stateCount.allTitle}}
            <small class="pull-right">{{stateCount.all}}</small>
          </a>
        </li>
        <li v-bind:class="[userState == 'ADMINS' ? 'active' : '']">
          <a href="javascript:;"  @click="filter('ADMINS')">管理员
            <small class="pull-right">{{stateCount.admins}}</small>
          </a>
        </li>
        <li v-bind:class="[userState == 'BLOCKED' ? 'active' : '']">
          <a href="javascript:;"  @click="filter('BLOCKED')">已过期用户
            <small class="pull-right">{{stateCount.blocked}}</small>
          </a>
        </li>
        <li v-bind:class="[userState == 'WOP' ? 'active' : '']">
          <a href="javascript:;" @click="filter('WOP')">无项目用户
            <small class="pull-right">{{stateCount.wop}}</small>
          </a>
        </li>
      </ul>
      <hr>
      <form accept-charset="UTF-8" class="form-inline" method="get"><div style="display:none"><input name="utf8" type="hidden" value="✓"></div>
        <div class="form-group">
          <input class="form-control" id="name" name="name" placeholder="输入姓名、用户名或邮箱" type="search" :value="userStr" @blur="updateStr">
        </div>
        <a class="btn btn-primary" name="button" @click="this.refresh()" ><i class="fa fa-search"></i>
        </a>
      </form>
      <hr>
      <a class="btn btn-cancel" @click="reset()">重置</a>
    </div>
  </aside>
</template>

<script>
import {refreshUser, changeParam, resetParam, refreshUserState} from './user-action.js'

export default {
  methods: {
    filter (filterState) {
      this.changeParam('userState', filterState)
      this.refreshUser()
    },
    updateStr: function (e) {
      this.changeParam('userStr', e.target.value)
    },
    refresh () {
      this.refreshUser()
      this.refreshUserState()
    },
    reset () {
      this.resetParam()
      this.refresh()
    }
  },
  vuex:
  {
    actions: {
      refreshUser,
      changeParam,
      resetParam,
      refreshUserState
    },
    getters: {
      stateCount: ({userstore}) => userstore.userStateCount,
      userState: ({userstore}) => userstore.queryParam.userState,
      userStr: ({userstore}) => userstore.queryParam.userStr
    }
  }
}
</script>
