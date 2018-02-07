import { parseUrl } from '../api/url'

export default {
  params: ['items', 'name'],
  twoWay: true,
  editorReady: false,
  bind: function () {
    var self = this
    var name = this.params.name === undefined ? 'content' : this.params.name
    var items = this.params.items
    window.KindEditor.ready(function (K) {
      self.editor = K.create('textarea[name="' + name + '"]', {
        cssPath: 'http://wxt-common.oss-cn-qingdao.aliyuncs.com/kindeditor/plugins/code/prettify.css',
        uploadJson: parseUrl(window.location.href) + 'filehandel/kindEditorUpload/yqdt/image',
        fileManagerJson: parseUrl(window.location.href) + 'filehandel/kindEditorFileManager',
        allowFileManager: true,
        items: items,
        afterChange: function () {
          self.set(this.html())
        }
      })
      self.editorReady = true
    })
  },
  update: function (newValue, oldValue) {
    if (this.editorReady) {
      this.editor.html(newValue)
    }
  },
  unbind: function () {
  }
}
