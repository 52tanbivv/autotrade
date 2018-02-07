<template>
  <div>
    <el-upload
        :on-preview="handlePreview"
        :on-success="handleSuccess"
        :on-remove="handleRemove"
        :on-error="handleError"
        :before-upload="handelBeforeUpload"
        :action="action"
        :data="uploadData"
        :file-list="value"
        :type="type"
        :list-type="listType"
        :disabled="disabled"
        :multiple="multiple"
        :show-file-list="showFileList"
        :accept="accept"
        :limitSize="limitSize"
        :preview-model="previewModel">
        <el-button
          v-if="!$slots.default"
          :disabled="disabled"
          size="small"
          type="primary">
            点击上传
        </el-button>
        <div
          v-if="!$slots.default"
          class="el-upload__tip"
          slot="tip">
          {{remind}}
        </div>
        <div v-else></div>
        <slot></slot>
    </el-upload>
    <el-dialog
      v-if="previewModel==='dialog'"
      v-model="dialogVisible"
      size="tiny">
      <img 
        width="100%" 
        :src="dialogImageUrl">
    </el-dialog>
  </div>
</template>

<script>
import {callApi} from '../../api/api'
export default {
  // 按照字母顺序排列各类属性
  computed: {
    remind () {
      return this.singleFile ? '只能上传一个文件' : '可上传多个文件'
    },
    disabled () {
      return this.singleFile && this.value.length > 0
    }
  },
  // 页面数据
  data: function () {
    return {
      action: '',
      uploadData: {},
      dialogImageUrl: '',
      dialogVisible: false
    }
  },
  // 页面方法
  methods: {
    uuid () {
      let chars = 'abcdef0123456789'
      let maxPos = chars.length
      let id = ''
      for (let i = 0; i < 32; i++) {
        id += chars.charAt(Math.floor(Math.random() * maxPos))
      }
      return id
    },
    getUrl (urlString) {
      let start = urlString.indexOf('<Location>') + 10
      let end = urlString.indexOf('</Location>')
      return urlString.substr(start, end - start)
    },
    /**
     * 在BeforeUpload回调是，file.uid 是 undefined。所以使用lastModified
     */
    handelBeforeUpload (file) {
      let _self = this
      let _file = file
      const isLimit = file.size / 1024 < this.limitSize
      if (!isLimit) {
        this.$message({
          type: 'error',
          message: '上传头像图片大小不能超过 50K!'
        })
      } else {
        return new Promise(function (resolve, reject) {
          callApi('ossclient_getOSSPolicyAndSignature', {}, (data) => {
            let fileType = 'jpg'
            let fileName = _file.name
            let uuid = _self.uuid()
            let index = fileName.lastIndexOf('.')
            if (index > 0) {
              fileType = fileName.slice(index, fileName.length)
            }
            if (_self.bucketName === '') {
              _self._data.action = data.bucket
            } else {
              _self._data.action = _self.bucketName
            }
            console.log('_self._data.action=' + _self._data.action)
            _self._data.uploadData = {key: `${uuid}${fileType}`, OSSAccessKeyId: data.OSSAccessKeyId, policy: data.policy, signature: data.signature, success_action_status: '201'}
            resolve(true)
          })
        })
      }
      return isLimit
    },
    /**
     * response 请求response
     * file 新增的文件
     * fileList 已上传文件列表
     */
    handleSuccess (response, file, fileList) {
      this.$emit('success', file, this.getUrl(response))
    },
    handleRemove (file) {
      if (file && file.response) {
        this.$emit('remove', file, this.getUrl(file.response))
      } else if (!file) {
        this.$emit('remove', file)
      } else {
        this.$emit('remove', file, this.getUrl(file.url))
      }
    },
    handlePreview (file) {
      let filetype = file.url.substring(file.url.lastIndexOf('.') + 1)
      if (this.previewModel === 'window') {
        if (filetype === 'doc' || filetype === 'docx' || filetype === 'xls' || filetype === 'xlsx' || filetype === 'ppt' || filetype === 'pptx') {
          window.open('https://view.officeapps.live.com/op/view.aspx?src=' + encodeURIComponent(file.url))
        } else {
          window.open(file.url)
        }
      } else if (this.previewModel === 'dialog') {
        this.dialogImageUrl = file.url
        this.dialogVisible = true
      }
    },

    handleError (error, response, file) {
      this.$message.error(`上传${file.name}失败，请重新上传。`)
      console.log('handleError ', error)
    }
  },
  // 组件属性
  props: {
    value: {
      type: Array,
      default () { return [] }
    },
    singleFile: {
      type: Boolean,
      default: false
    },
    bucketName: {
      type: String,
      default: ''
    },
    listType: {
      type: String,
      default: 'text'
    },
    previewModel: {
      type: String,
      default: 'window'
    },
    type: {
      type: String,
      default: 'select'
    },
    multiple: {
      type: Boolean,
      default: false
    },
    showFileList: {
      type: Boolean,
      default: true
    },
    accept: {
      type: String,
      default: ''
    },
    limitSize: {
      type: Number,
      default: 1000000
    }
  }
}
</script>
<!-- 样式只应用于该组件，为style添加scoped属性 <style scoped> -->
<style type="text/css" scoped lang="scss">
@import '../../scss/index.scss';
</style>
