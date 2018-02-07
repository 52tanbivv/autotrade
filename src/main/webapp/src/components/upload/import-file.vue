<template>
  <el-button :type="type" @click="handleClick" :size="size">
    <span>{{content}}</span>
    <input style="dislplay:none" type="file" ref="input" class="import-file__input"
    @change="handleChange"
    :accept="accept">
  </el-button>
</template>
<script>
import {callParamsAndUploadApi} from '../../api/api'
import {callUploadApi} from '../../api/uploadOSSApi'
export default {
  // 按照字母顺序排列各类属性
  // 页面数据
  data: function () {
    return {
    }
  },
  // 页面方法
  methods: {
    handleChange (ev) {
      const files = ev.target.files
      if (!files) {
        return
      }
      if (!this.isImport) {
        this.$emit('change', files[0])
        return
      }
      this.$emit('before', files[0])
      if (this.api) {
        callParamsAndUploadApi(this.api, Object.assign({updloadState: true, uploadFilePath: this.$refs.input.value}, this.params), files[0], data => {
          this.$emit('success', data)
        }, () => {}, data => {
          this.$message.error('导入文件错误！')
          this.$emit('fail', data)
        })
      } else {
        callUploadApi(files[0], data => {
          this.$emit('success', data)
        }, () => {}, data => {
          this.$message.error('上传文件错误！')
          this.$emit('fail', data)
        })
      }
      this.$refs.input.value = null
    },
    handleClick () {
      this.$refs.input.click()
    }
  },
  // 组件属性
  props: {
    api: String,
    params: Object,
    accept: String,
    content: {
      type: String,
      default: '点击导入'
    },
    type: String,
    isImport: {
      type: Boolean,
      default: true
    },
    size: String
  }
}
</script>
<!-- 样式只应用于该组件，为style添加scoped属性 <style scoped> -->
<style type="text/css" scoped lang="scss">
@import '../../scss/index.scss';
.import-file__input {
  display: none;
}
</style>
