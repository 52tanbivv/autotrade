<template>
  <div>
    <alert :show.sync="alert.show" :duration="2000" :type="alert.type" :callback="hideAlert" width="400px" placement="top" dismissable>
      <span class="icon-info-circled alert-icon-float-left"></span>
      <p>{{alert.msg}}</p>
    </alert>
    <pageheader></pageheader>
    <div v-bind:class="[page-with-sidebar, this.barState === 'expanded' ? 'page-sidebar-expanded' : 'page-sidebar-collapsed']"  class="page-sidebar-collapsed page-with-sidebar">
      <leftmenu></leftmenu>
      <div class="content-wrapper">
        <router-view  keep-alive></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import Pageheader from './components/layouts/pc/Header'
import Leftmenu from './components/layouts/pc/Leftmenu'
import store from './store.js'
import {Alert} from '@wxt/control'

export default {
  components: {
    Pageheader,
    Leftmenu,
    Alert
  },
  store: store,
  vuex: {
    getters: {
      alert: (state) => state.alert,
      barState: (state) => state.batState
    }
  }
}
</script>

<style>

  .bar_font {
    font-size: 12px;
    color: #693;
    position: absolute;
    width: 100px;
    height: 17px;
    line-height: 17px;
    top: 0;
    left: 0;
    text-align: center;
    margin-top:2px;
  }

  .container-flex {
    display: flex;
    flex-direction: column;
    padding: 0px 15px;
  }

  .page-group-title {
    font-size: 15px;
    color: #5994cc;
    font-weight: 700;
  }

  .page-header-container {
    display: flex;
    align-items: center;
    padding-bottom: 15px;
    border-bottom: 1px #ddd solid;
  }

  .page-container {
    display: flex;
    flex-direction: column;
  }

  .page-content {
    display: flex;
    flex-direction: column;
    padding: 0px 0px 10px 0px;
  }

  .page-grid-header {

  }

  .page-table {
    width: 100%;
    margin: 25px 0px;
  }

  .page-table th{
    background-color: #FAFAFA;
    padding: 0 15px;
    height: 48px;
    vertical-align: middle;
    text-align: left;
    border: 1px solid #e8e8e8;
  }

  .page-table td {
    padding: 0 15px;
    border: 1px solid #e8e8e8;
    height: 48px;
  }

  .page-item {
    display: flex;
    border-top: 1px solid #dfdfdf;
    padding: 10px;
  }

  .page-item-info {
    display: flex;
    flex-direction: column;
    width: calc( 100% - 700px);
  }

  .page-item-status {
    display: flex;
    width: 400px;
    flex-direction: column;
    padding-left: 20px;
  }
  .page-item-action {
    display: flex;
    width: 300px;
    align-items: center;
  }

  .page-item-title {
    display: flex;
    font-size: 18px;
    height: 50px;
    align-items: center;
    cursor:pointer
  }

  .page-item-desc {
    display: flex;
    align-items: center;
    color: #999;
  }

  .page-text-desc {
    color: #999;
  }

  .page-text-hint {
      color: #999;
  }

  .page-text-value {
    color: #999;
  }

  .line1 {
    -webkit-line-clamp: 1;
  }
  .dotdot {
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      display: box;
      -webkit-box-orient: vertical;
  }

  .page-container-header {
    display:flex;
    padding: 15px 0px;
    align-items:center;
    justify-content: space-between
  }

  .avatar-sm {
    height:16px;width:16px;border-radius:50%;border: 1px solid #dfdfdf;
  }

  .detail-page-title {
    margin: 0px;
    padding: 15px 5px;
  }

  .detail-view-title__desc {
    font-size: 13px;
    color: #666;
    padding-right: 30px;
    padding-left: 20px;
  }

  .detail-view-title {
    display: flex;
    width: 100%;
    flex-direction: column;
  }

  .detail-view-page {
    display: flex;
    flex-direction: column;
  }

  .detail-modal-footer {
    width: 800px;
    display: flex;
    justify-content: center;
    align-items: center;
    padding-top: 10px;
  }


  .detail-view-desc2 {
    width: calc( 100% - 200px );
    padding: 15px;
    display: flex;
    flex-direction: column;
  }

  .detail-view-desc {
    width: calc( 100% - 300px );
    padding: 15px;
    display: flex;
    flex-direction: column;
  }

  .detail-info-topbar2 {
    display:flex;
    align-items:center;
    border-radius:4px;
    border-bottom: 1px solid #dfdfdf;
    border-bottom-left-radius: 0;
    border-bottom-right-radius: 0;
    height:35px;
    background-color:#f6f8fa;
    margin: -5px -5px 10px -5px;
    font-size:14px;
    font-weight:500
  }
  .detail-info-topbar {
    display:flex;
    align-items:center;
    border-radius:4px;
    border-bottom: 1px solid #dfdfdf;
    border-bottom-left-radius: 0;
    border-bottom-right-radius: 0;
    height:40px;
    background-color:#f6f8fa;
    margin: -10px -10px 10px -15px;
    font-size:16px;
    font-weight:500
  }
  .detail-view-info2 {
    padding: 5px;
    width: 200px;
    display: flex;
    flex-direction: column;
    display: flex;
    background: #fafbfc;
    border: 1px solid #d1d5da;
    border-radius: 4px;
    margin: 10px 0px 10px 0px;
    box-shadow: 0 2px 3px rgba(0,47,55,.09);
  }

  .detail-view-info {
    padding: 10px;
    width: 300px;
    display: flex;
    flex-direction: column;
    display: flex;
    background: #fafbfc;
    border: 1px solid #d1d5da;
    border-radius: 4px;
    margin: 10px 0px 10px 10px;
    padding-left: 15px;
    box-shadow: 0 2px 3px rgba(0,47,55,.09);
  }

  .detail-view-valuetxt {
    width: 200px;
    font-size: 13px;
    color: #333;
  }


  .detail-view-valuetxt2 {
    width: 120px;
    font-size: 13px;
    color: #333;
  }

  .detail-view-titletxt {
    width: 80px;
    text-align: right;
    color: #999;
    padding-right: 10px;
  }

  .detail-view-titletxt2 {
    width: 60px;
    text-align: right;
    color: #999;
    padding-right: 10px;
  }

  .detail-view-title__desc {
      font-size: 13px;
      color: #666;
      padding-right: 30px;
      padding-left: 20px;
  }

  .detail-view-title {
    display: flex;
    width: 100%;
    flex-direction: column;
  }

  .detail-view-container {
    display: flex;
    font-size: 13px;
    min-height: 500px;
    margin: 10px 5px;
    width: 100%;
  }

  .detail-view-item {
    display: flex;
    margin: 5px;
    color: #333;
    align-items: center;
    height: 30px;
  }
</style>
