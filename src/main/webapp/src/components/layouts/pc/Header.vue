<!-- 页面头部文件 -->
<template>
	<header class="navbar navbar-fixed-top navbar-gitlab" style="z-index: 201">
		<div class="navbar-inner">
			<div class="container">

				<div class="app_logo">
					<a class="home has_bottom_tooltip" href="/" title="" data-original-title="Dashboard"><img alt="Logo white" src="http://weixt-static.oss-cn-qingdao.aliyuncs.com/vue-logo.png">
					</a>
				</div>
		        <div class="col-sm-2">
		          <select class="form-control project-select" @change="change">
		            <option v-for="project in projects" :value="project.id">
		              {{project.name}}
		            </option>
		          </select>
		        </div>
				<button class="navbar-toggle" data-target=".navbar-collapse" data-toggle="collapse" type="button">
					<span class="sr-only">Toggle navigation</span>
					<i class="fa fa-bars"></i>
				</button>

				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav">

						<li class="visible-sm visible-xs">
							<a class="has_bottom_tooltip" data-original-title="Search" href="/search" title=""><i class="fa fa-search"></i>
							</a>
						</li>

						<li class="hidden-xs">
							<div style="color:white;display:flex;align-items:center">
								<span style="color:white;cursor:pointer" @click="viewProfile">{{user.name}}</span>
								<a  style="cursor:pointer;display:flex;align-items:center;"  class="profile-pic has_bottom_tooltip" data-original-title="Your profile" @click="viewProfile" id="profile-pic"><img alt="User activity" style="width:30px; height:30px;border-radius:50%" :src="user.avatar">
								</a>
							</div>
						</li>

							<li>
								<a class="has_bottom_tooltip" data-method="delete" data-original-title="Logout" href="logout" rel="nofollow" title=""><icon name="g-exit-to-app" scale="0.8" ></icon>
								</a>
							</li>
					</ul>
				</div>
			</div>
		</div>
	</header>
</template>

<script>
  import {getCurrentUser, changeProject} from '../../../action.js'
  import {Icon} from '@wxt/control'

  export default {
    vuex:
    {
      getters: {
        user: state => state.user,
        projects: state => state.projects
      },
      actions: {
        getCurrentUser,
        changeProject
      }
    },
    methods: {
      viewProfile () {
        this.$router.go('/user/edituser/' + this.user.id)
      },
      change: function (e) {
        console.log('start change' + e.target.value)
        this.changeProject(e.target.value)
      }
    },
    components: {
      Icon
    },
    created () {
      this.getCurrentUser()
    }
  }
</script>

<style type="text/css">
  .project-select {
    margin-top: 7px!important;
    width:200px;
    height: 30px;
  }
</style>
