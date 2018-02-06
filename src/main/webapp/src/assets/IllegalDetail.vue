<template>
    <aside :show.sync="show" header="违规情况明细" :width="700">
      <div class="illegal-content">
        <table class="illegal-table">
            <tbody>
              <tr>
                <th style="width: 10%">检查时间</th>
                <th style="width: 10%">违规点</th>
                <th style="width: 30%">违规说明</th>
            </tr>
            <tr
             v-for="(index,item) in illegalList" style="background-color: white">
                <td >
                  <span>{{item.checkTime}}</span>
                </td>
                <td >
                  <span>{{item.ruleName}}</span>
                </td>
                <td >
                  <span>{{item.illegalDesc}}</span>
                </td>
            </tr>
          </tbody>
        </table>
      </div>
        <div class="modal-footer">
          <div>
             <button type="button" @click="close()" class="btn">关闭</button>
          </div>
          </div>
        </div>
      </div>
    </aside>
</template>
<script>
import {Icon, Aside} from '@wxt/control'
import { callApi } from '../../api/api.js'

export default {
  data () {
    return {
      illegalList: [
      ],
      show: false,
      type: ''
    }
  },
  computed: {
    validRuleList () {
      return this.illegalRuleList.filter(item => item.show)
    },
    typeName () {
      return this.type === 'page' ? '前端' : '后台'
    }
  },
  methods: {
    mouseOver (item) {
      item.mouseIn = true
    },
    mouseOut (item) {
      item.mouseIn = false
    },
    open (userid, ruleid) {
      this.show = true
      this.listIllegal(userid, ruleid, this.iterationid)
    },
    close () {
      this.show = false
    },
    listIllegal (userid, ruleid, iterationid) {
      let params = { userid: userid, ruleid: ruleid, iterationid: iterationid }
      callApi('illegal_listIllegalDetail', params, (data) => {
        this.illegalList = data
      }, false)
    }
  },
  components: {
    Aside,
    Icon
  },
  vuex: {
    getters: {
      user: (store) => {
        return store.user
      },
      iterationid: ({api}) => api.currentIteration
    }
  }
}
</script>

<style scoped>
  .illegal-type-aside-container {
    display: flex;
    flex-direction: column;
    font-size: 13px;
    min-height: 500px;
    margin: 10px 5px;
  }

  .illegal-type-aside-item {
    display: flex;
    margin: 8px;
    padding: 10px;
    color: #333;
    flex-direction: column;
    border: 1px solid green;
    background-color: #F4F9F3;
    border-radius: 4px;
  }

  .developer-avatar {
    height: 30px;
    weidth: 30px;
    border-radius: 50%;
  }

  .illegal-type-add-btn {
    width: 100%;
    height: 40px;
    display: flex;
    align-items: center;
    justify-content: center;
    color: green;
    border: 1px solid green;
    border-radius: 4px;
    cursor: pointer;
  }



  .illegal-table {
    width: 100%;
    margin: 25px 0px;
  }

  .illegal-table th{
    background-color: #FAFAFA;
    padding: 0 15px;
    height: 48px;
    vertical-align: middle;
    text-align: left;
    border: 1px solid #e8e8e8;
  }

  .illegal-table td {
    padding: 0 15px;
    border: 1px solid #e8e8e8;
    height: 48px;
  }
</style>
