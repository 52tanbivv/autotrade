export default {
  params: ['config'],
  twoWay: true,
  bind: function () {
    console.log('bind this!!')
    var self = this
    console.log('uname el 1111=', this.editor)
    if (this.editor !== undefined) {
      return
    }
    this.el.id = 'ueditor' + new Date().getTime().toString()
    console.log('uname el=', this.el)
    console.log('uname=' + this.el.id)
    this._timeout = setTimeout(
    () => {
      this.editor = window.UE.getEditor(this.el.id, this.params.config)
      this.editor.ready(function () {
        self.editorReady = true
        self.editor.addListener('contentChange', function () {
          self.set(self.editor.getContent())
        })
        self.update(self.vm.$get(self.expression))
      })
    }, 100)
  },
  update: function (newValue, oldValue) {
    console.log('update this!!', this.editor)
    if (this.editorReady) {
      // this.editor.render()
      this._timeout = setTimeout(
      () => { this.editor.setContent(newValue) }, 100)
    }
  },
  unbind: function () {
    console.log('unbind this!!')
    this.editor.destroy()
    console.log('this.editor destoryed!!!', this.editor)
  }
}
