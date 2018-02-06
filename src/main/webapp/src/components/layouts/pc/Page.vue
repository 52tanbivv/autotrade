<!-- pc端分页控件 可控制按钮个数 可跳转页数、上一页、下一页、第一页、最后一页 -->
<template>
  <div class="row">
  <div class="col-sm-4 hidden-xs">
    <div class="dataTables_info" v-if="showTotal && page.totalCount > 0 && showDataTables" id="xsjb-datatable_info">
      <strong>{{beginId}}</strong>-<strong>{{endId}}</strong> of <strong>{{totalCount}}</strong>
    </div>
  </div>
  <div class="col-sm-8  clearfix" :class="{'page__pagination--center': center}">
    <div class="dataTables_paginate paging_bootstrap">
      <ul v-if="page.pageCount >= 1 && page.totalCount > 0" class="pagination pagination-sm remove-margin">
        <li v-if="page.pageIndex > 1">
          <a href="javascript:;" @click="go(1)">第一页</a>
        </li>
        <li :class="[prev, page.pageCount > 0 && page.pageIndex > 1 ? '' : 'disabled']">
          <a href="javascript:void(0)" @click="go(page.pageIndex-1)">
            <icon name="g-arrow-back" scale="0.6"></icon>
          </a>
        </li>
        <li v-for="n in pageList" :class="page.pageIndex === n ? 'active' : ''">
          <a href="javascript:;" @click="go(n)">{{n}}</a>
        </li>
        <li  :class="[next, page.pageCount > 0 && page.pageIndex < page.pageCount ? '' : 'disabled']">
          <a href="javascript:void(0)" @click="go(page.pageIndex+1)">
            <icon name="g-arrow-forward" scale="0.6"></icon>
          </a>
        </li>
        <li v-if="page.pageIndex != page.pageCount">
           <a href="javascript:;" @click="go(page.pageCount)">最后一页</a>
        </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import {Icon} from '@wxt/control'
export default {
  components: {
    Icon
  },
  computed: {
    beginId () {
      return (this.page.pageIndex - 1) * this.page.pageSize + 1
    },
    endId () {
      if (this.page.pageIndex === this.page.pageCount) {
        return this.page.totalCount
      } else {
        return this.page.pageIndex * this.page.pageSize
      }
    },
    totalCount () {
      return this.page.totalCount
    },
    pageNum () {
      let result = {}
      if (this.page.pageCount <= 5) {
        result.startNum = 1
        result.endNum = this.page.pageCount
      } else {
        if (this.page.pageIndex <= 3) {
          result.startNum = 1
          result.endNum = 5
        } else if (this.page.pageIndex > 3 && this.page.pageIndex < this.page.pageCount - 2) {
          result.startNum = this.page.pageIndex - 2
          result.endNum = this.page.pageIndex + 2
        } else {
          result.startNum = this.page.pageCount - 4
          result.endNum = this.page.pageCount
        }
      }
      return result
    },
    pageList () {
      let result = []
      for (let i = this.pageNum.startNum; i <= this.pageNum.endNum; i++) {
        result.push(i)
      }
      return result
    }
  },
  data: function () {
    return {
    }
  },
  methods: {
    // 设置按钮开始和结束显示的页数
    go (index) {
      if (this.page.pageCount <= 0 || index < 1 || index > this.page.pageCount) {
        return
      }
      this.page.pageIndex = index
      this.callback(index)
    }
  },
  props: {
    showTotal: {
      type: Boolean,
      default: true
    },
    mini: {
      type: Boolean,
      default: false
    },
    center: {
      type: Boolean,
      default: false
    },
    page: {
      type: Object,
      default: () => {
        return {
          pageCount: 0,
          pageIndex: 1,
          pageSize: 10,
          totalCount: 0
        }
      }
    },
    callback: {
      type: Function,
      default: () => {}
    },
    // 按钮个数
    buttonNum: {
      type: Number,
      default: 5
    },
    // 是否显示前面1-6of111
    showDataTables: {
      type: Boolean,
      default: true
    }
  }
}
</script>
<style type="text/css" scoped >
.pagination > .active > a {
  background-color: green;
  border-color: green;
  color: white;
}

.pagination > .disabled > *{
  background-color: #efefef;
  border-color: #ddd;
  color: bbb;
}

.pagination > li > a:hover {
  background-color: #adde96;
  border-color: green;
  color: green;
}
.pagination > li > a {
  background-color: white;
  border-color: green;
  color: green;
}
.page__pagination--center {
  width: 100%;
  display: flex;
  align-items: center;
}
</style>
