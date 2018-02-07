<!-- 页面头部文件 -->
<template>
  <div class="sidebar-wrapper">
    <ul class="project-navigation nav nav-sidebar">
      <leftmenu-item v-for="menuitem in menuitems" :title="menuitem.title" :url="menuitem.action" :classicon="menuitem.icon"></leftmenu-item>
    </ul>
    <div class="collapse-nav">
      <a style="cursor:pointer" :class="['toggle-nav-collapse', this.batState === 'expanded' ? 'toleft_icon' : 'toright_icon']" @click="this.chageBarState()" title="Open/Close">
      <icon scale="0.8" :name="leftRightIncon" ></icon></a>
      </a>
    </div>
  </div>
</template>

<script>
  import LeftmenuItem from './LeftmenuItem.vue'
  import {getAllMenus, chageBarState} from '../../../action.js'
  import {Icon} from '@wxt/control'

  export default {
    components: {
      LeftmenuItem,
      Icon
    },
    computed: {
      leftRightIncon: function () {
        if (this.batState === 'expanded') {
          return 'g-chevron-left'
        } else {
          return 'g-chevron-right'
        }
      },
      classLeftRightIncon: function () {
        if (this.batState === 'expanded') {
          return 'g-chevron-left'
        } else {
          return 'g-chevron-right'
        }
      }
    },
    vuex:
    {
      getters: {
        menuitems: state => state.menus,
        batState: state => state.batState
      },
      actions: {
        getAllMenus,
        chageBarState
      }
    }
  }
</script>

<style type="text/css">
  .toright_icon {
    padding:5px 15px 4px 17px!important
  }
  .toleft_icon {
    padding:5px 6px 4px 6px!important
  }
</style>
