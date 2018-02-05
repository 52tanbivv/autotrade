<!-- 页面头部文件 -->
<template>
  <li class="active">
    <a v-link="{ path: url, activeClass: 'active' }"> 
      <!-- i v-bind:class="[classfa, classicon]"></i-->
      <icon style="color:#777;" scale="0.625" :name="classicon"></icon>
      <span style=" padding-left:40px">
        {{title}}
      </span>
    </a>
  </li>
</template> 

<script>
  import { clickMenu } from '../../../action.js'
  import {Icon} from '@wxt/control'

  export default {
    props: {
      title: {
        default: ''
      },
      classfa: {
        default: 'fa'
      },
      classicon: {
        default: 'fa-files-o'
      },
      url: {
        default: '#'
      },
      isactive: {
        default: false
      },
      menuid: {
        default: ''
      }
    },
    vuex:
    {
      actions: {
        clickMenu
      }
    },
    components: {
      Icon
    }
  }
</script>

<style>
a.active svg{color:#222 !important;}
</style>
 