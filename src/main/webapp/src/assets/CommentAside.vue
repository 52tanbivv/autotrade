<template>
    <aside :show.sync="show" :header="header" :width="600">

      <div class="effort-aside-container">
        <div v-show="type === 'requirement' && this.status === 'product_acceped'"  class="effort-aside-item">
          <span style="width:100px;">上线时间：</span>
          <select  class="form-control input-sm" style="width:200px" v-model="enddate">
            <option value="近期">近期</option>
            <option value="中期">中期</option>
            <option value="远期">远期</option>
          </select>
          <span style="width:100px;padding-left:20px;">开发人天：</span>
          <input v-model="developDays" class="form-control input-sm" placeholder="请估算大致需要多少开发人天" style="width: 200px"/>
        </div>
        <div v-show="type === 'requirement' && this.status === 'develop_evaluted'" class="effort-aside-item">
          <span style="width:100px;">产品部评估：</span>
          <input disabled v-model="dayPriceStr" class="form-control input-sm" placeholder="" style="width: 200px"/>
          <span style="width:100px;padding-left:20px;">商务费用：</span>
          <input v-model="price" class="form-control input-sm" placeholder="通用需求可不填写" style="width: 200px"/>
        </div>
        <div  class="effort-aside-item" style="flex-direction:column;align-items: start">
          <div v-el:ueditor
            id="editor"
            v-ueditor="desc"
            :config="config"
            style="height:320px;"></div>
          <span style="padding-top:5px;color:#666">注：在编辑器中输入@+对方姓名，对方即可收到邮件提醒。</span>
        </div>
        <div v-show="type === 'requirement' && (this.status === 'new' || this.status === 'reopened')" class="effort-aside-item">
          <span style="width:100px;">设计人天：</span>
          <input v-model="designDays" class="form-control input-sm" placeholder="如通过该需求，请估算需要多少设计人天" style="width: 560px"/>
        </div>
      </div>
      <div class="modal-footer">
        <div>
            <button v-for="btn in typeBtnList" v-show="btn.visible && !btnBusy" type="button" @click="updateStatus(btn.target)" :class="'btn btn-' + btn.sclass">
              <icon :name="btn.icon" scale="0.75"></icon>
              {{btn.name}}
            </button>
            <span v-if="btnBusy" style="color:red;">保存中.....，请不要关闭本页面！</span>
            <button  v-show="(action === 'comment' || action === 'reply') && !btnBusy" type="button" @click="saveComment()" class="btn btn-success">
              <icon name="g-save" scale="0.75"></icon>
              保存
            </button>
            <button type="button" @click="close()" class="btn"  v-if="!btnBusy">
             <icon name="g-exit-to-app" scale="0.75"></icon>
              关闭
            </button>
          </div>
        </div>
      </div>
    </div>
    </aside>
</template>
<script>
import Datepicker from '../../components/datepicker'
import {Icon, Aside} from '@wxt/control'
import { callApi } from '../../api/api.js'
import {showAlert} from '../../action.js'
import { parseUrl } from '../../api/url'
import {listComment} from './requirement-action'

export default {
  data () {
    return {
      btnBusy: false,
      dayPriceStr: '',
      header: '',
      type: '',
      action: '',
      status: '',
      show: false,
      requirement: {},
      desc: '',
      enddate: '',
      designDays: '',
      developeDays: '',
      price: '',
      btnList: [
        {visible: false, type: 'requirement', action: 'status', source: 'new|reopened', target: 'product_acceped', name: '通过', icon: 'g-check', 'sclass': 'success'},
        {visible: false, type: 'requirement', action: 'status', source: 'new|reopened|product_acceped', target: 'product_rejected', name: '拒绝', icon: 'g-block', 'sclass': 'danger'},
        {visible: false, type: 'requirement', action: 'status', source: 'product_acceped', target: 'develop_evaluted', name: '通过', icon: 'g-save', 'sclass': 'success'},
        {visible: false, type: 'requirement', action: 'status', source: 'develop_evaluted', target: 'confirmed', name: '确认价格', icon: 'g-check', 'sclass': 'success'},
        {visible: false, type: 'requirement', action: 'status', source: 'develop_evaluted|product_rejected', target: 'rejected', name: '关闭需求', icon: 'g-highlight-off', 'sclass': 'danger'},
        {visible: false, type: 'requirement', action: 'status', source: 'product_rejected', target: 'reopened', name: '重开需求', icon: 'g-add-circle', 'sclass': 'success'},
        {visible: false, type: 'requirement', action: 'status', source: 'rejected', target: 'reopened', name: '重开需求', icon: 'g-add-circle', 'sclass': 'success'},
        {visible: false, type: 'bug', action: 'status', source: 'new|reopened|assigned', target: 'rejected', name: '拒绝', icon: 'g-block', 'sclass': 'danger'},
        {visible: false, type: 'bug', action: 'status', source: 'closed|rejected|resolved', target: 'reopened', name: '重开', icon: 'g-add-circle', 'sclass': 'success'},
        {visible: false, type: 'story', action: 'status', source: 'tested', target: 'page_refactor', name: '页面重构', icon: 'g-language', 'sclass': 'warning'},
        {visible: false, type: 'story', action: 'status', source: 'tested', target: 'resolved', name: '已实现', icon: 'g-check', 'sclass': 'success'}
      ],
      config: {
        serverUrl: parseUrl(window.location.href) + 'ueditor/init',
        toolbars: [[
          'fullscreen', 'undo', 'redo', '|',
          'preview', 'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', 'insertorderedlist', 'insertunorderedlist', 'subscript', 'superscript', 'cleardoc', 'autotypeset', 'selectall', '|', 'forecolor', 'backcolor', '|',
          'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|',
          'simpleupload', 'inserttable', 'horizontal', 'pagebreak', '|', 'kityformula',
          'help'
        ]],
        compress: false,
        kityformula: true,
        onready: function () {
          this.on('showmessage', function (type, m) {
            if (m['content'] === '本地保存成功') {
              return true
            }
          })
        }
      }
    }
  },
  computed: {
    typeBtnList () {
      return this.btnList.filter(btn => btn.type === this.type && btn.action === this.action)
    }
  },
  methods: {
    btnVisible (btn) {
      let btnSourceStatus = btn.source.split('|')
      for (let i = 0; i < btnSourceStatus.length; i++) {
        let bstatus = btnSourceStatus[i]
        if (this.obj.status === bstatus) {
          console.log('btnvisible btn show', btn)
          return true
        }
      }
      console.log('btnvisible btn hide', btn)
      return false
    },
    notEmpty (value, text) {
      if (!value || value === '') {
        this.showAlert('danger', text + '不能为空！')
        return false
      }
      return true
    },
    validNum (num, numText, maxVal) {
      if (!this.notEmpty(num, numText)) {
        return false
      } else {
        if (isNaN(num)) {
          this.showAlert('danger', numText + '只能为数字！')
          return false
        } else if (parseInt(num) === 0) {
          this.showAlert('danger', numText + '不能为0！')
          return false
        } else if (parseInt(num) > maxVal) {
          this.showAlert('danger', numText + '不能超过' + maxVal + '！')
          return false
        }
      }
      return true
    },
    valid (status) {
      console.log('valid status=' + status)
      if (this.type === 'requirement') {
        if (!this.notEmpty(this.desc, '评估意见')) {
          return false
        } else if (status === 'product_acceped') {
          if (!this.validNum(this.designDays, '设计天数', 100)) {
            return false
          }
        } else if (status === 'develop_evaluted') {
          if (!this.notEmpty(this.enddate, '上线日期')) {
            return false
          }
          if (!this.validNum(this.developDays, '开发天数', 100)) {
            return false
          }
        }
      }
      return true
    },
    doUpdateStatus (params) {
      this.btnBusy = true
      let success = (data) => {
        this.showAlert('success', '保存成功')
        this.$dispatch('comment-save')
        this.close()
      }
      callApi(this.type + '_updateStatus', params, success, false)
    },
    addObjInfo (params, status) {
      if (this.type === 'requirement') {
        if (status === 'product_acceped') {
          params.designDays = this.designDays
        } else if (status === 'develop_evaluted') {
          params.developDays = this.developDays
          params.enddate = this.enddate
        } else if (status === 'confirmed') {
          params.price = this.price
        }
      }
    },
    saveComment () {
      if (!this.notEmpty(this.desc, '评论')) {
        return
      }
      this.btnBusy = true
      let params = {id: this.obj.id, desc: this.desc, type: this.type}
      if (this.action === 'reply') {
        params = {id: this.obj.dataid, desc: this.desc, type: this.type}
      }
      let success = (data) => {
        this.showAlert('success', '评论成功')
        let id = this.obj.id
        if (this.action === 'reply') {
          id = this.obj.dataid
        }
        this.listComment(this.type, id)
        this.close()
      }
      callApi('comment_save', params, success, false)
    },
    updateStatus (status) {
      if (!this.valid(status)) {
        return
      }
      console.log('params =', params)
      let params = {id: this.obj.id, desc: this.desc, status: status}
      this.addObjInfo(params, status)
      console.log('params =', params)
      this.doUpdateStatus(params)
    },
    getHeader (sourcestatus) {
      if (this.action === 'comment') {
        return '填写评论'
      } else if (this.action === 'reply') {
        return '回复评论'
      }
      if (this.type === 'requirement') {
        switch (sourcestatus) {
          case 'new': return '评估意见填写'
          case 'reopened': return '评估意见填写'
          case 'product_acceped': return '估算开发人天'
          case 'product_rejected': return '填写回复意见'
          case 'develop_evaluted': return '确认评估结果'
          case 'rejected': return '填写重开意见'
          default: return ''
        }
      } else if (this.type === 'bug') {
        switch (sourcestatus) {
          case 'assigned': return '拒绝缺陷'
          case 'reopened': return '拒绝缺陷'
          case 'rejected': return '重开缺陷'
          default: return ''
        }
      } else if (this.type === 'story') {
        switch (sourcestatus) {
          case 'tested': return '填写产品体验意见'
          default: return ''
        }
      }
    },
    open (action, obj, type) {
      this.btnBusy = false
      console.log('obj is', obj)
      this.show = true
      this.obj = obj
      let totalDays = parseInt(this.obj.designDays) + parseInt(this.obj.developDays)
      this.dayPriceStr = '工期' + totalDays + '天，费用' + 2000 * (totalDays) + '元'
      if (action === 'status') {
        this.status = obj.status
      } else {
        this.status = ''
      }
      this.action = action
      this.type = type
      if (this.action === 'reply') {
        this.desc = '<blockquote style="border-left: 6px solid #ddd;padding: 5px 0 5px 10px;margin: 15px 0 15px 15px;font-size:14px;color:#666" data-reply>' + obj.plainDesc + '<p><a style="color:#4298BA">@' + obj.user + '</a></p></blockquote><p></p>'
        console.log('desc is', this.desc)
      } else {
        this.desc = ''
      }
      this.developeDays = ''
      this.designDays = ''
      this.price = ''
      this.enddate = ''
      this.header = this.getHeader(obj.status)
      this.setBtnVisible()
    },
    setBtnVisible () {
      this.btnList.forEach(btn => {
        if (btn.type === this.type) {
          btn.visible = this.btnVisible(btn)
        } else {
          btn.visible = false
        }
      })
    },
    close () {
      this.show = false
    }
  },
  components: {
    Aside,
    Icon,
    Datepicker
  },
  vuex: {
    actions: {
      showAlert,
      listComment
    },
    getters: {
      user: (store) => {
        return store.user
      }
    }
  }
}
</script>

<style scoped>
  .effort-aside-container {
    display: flex;
    flex-direction: column;
    font-size: 13px;
    margin: 10px 5px;
  }

  .effort-aside-item {
    display: flex;
    border-radius: 4px;
    margin: 5px;
    color: #333;
    align-items: center;
  }

  .effort-story-table {
    width: 100%;
    margin: 25px 0px;
  }

  .effort-story-table th{
    background-color: #FAFAFA;
    padding: 0 15px;
    height: 28px;
    vertical-align: middle;
    text-align: left;
    border: 1px solid #e8e8e8;
  }

  .effort-story-table td {
    padding: 0 15px;
    border: 1px solid #e8e8e8;
    height: 28px;
  }

  blockquote {

  }

</style>
