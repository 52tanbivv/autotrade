import $ from 'jquery'
import Mock from 'MockJs'
import {parseUrl} from './url'
import {showAlert} from '../action'
import store from '../store.js'

let baseApiUrl = ''
let logApi = true
let debug = true

function replaceError (jsonStr) {
  // 回车换行替换
  let str = jsonStr.replace(/\r\n/g, '  ')
  // 换行替换
  str = str.replace(/\n/g, '  ')
  // 去掉制表符
  str = str.replace(/\t/g, '  ')
  str = str.replace(/[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, '  ')
  return str
}

const process = (successCall, failCall, mock) => {
  return (datas) => {
    datas = replaceError(datas)
    if (mock) {
      let template = JSON.parse(datas)
      let mockResult = Mock.mock(template)
      successCall(mockResult.data)
      return
    }
    try {
      if (datas === '') {
        return
      }
      // 如果没有登录，则需要重新登录
      if (datas.match('login-page')) {
        window.location.href = baseApiUrl + 'login'
        return
      }

      let dataJson = JSON.parse(datas)
      let ret = dataJson.ret
      if (ret.code === '200') {
        successCall(dataJson.data)
      } else {
        if (failCall === undefined) {
        } else {
          failCall(dataJson.api, ret)
        }
      }
    } catch (e) {
      failCall('', {code: '505', 'msg': '发生错误：' + e + '，服务器返回结果：' + datas})
    }
  }
}

// readonly 表示是否只读
export function callParamsAndUploadApi (api, params, localfile, success, progress, fail = (api, ret) => {
  if (debug) {
    console.log('call UploadApi 出错了！！')
    store.dispatch('showAlertError', '系统错误，请联系管理员,API:' + api + '调用错误：： ' + ret.msg)
  }
}) {
  // showLoading(store, '请稍候')
  if (baseApiUrl === '') {
    baseApiUrl = parseUrl(window.location.href)
  }
  let apiUrl = baseApiUrl + 'securityapi/' + api

  // var imageFileType = 'jpg'
  // var fileName = localfile.name
  // if (fileName.indexOf('.') > 0) {
  //   imageFileType = fileName.split('.')[fileName.split('.').length - 1]
  // }
  // params.fileType = imageFileType
  // params.updloadState = true
  let callParams = { params: params, readonly: false }
  var sendToData = {
    apiparams: JSON.stringify(callParams),
    readonly: false
  }
  var oMyForm = new window.FormData()
  for (var fieldName in sendToData) {
    oMyForm.append(fieldName, sendToData[fieldName])
  }
  oMyForm.append('file', localfile)

  var oReq = new window.XMLHttpRequest()
  // 进度
  oReq.upload.onprogress = function (e) {
    if (e.type === 'progress') {
      var percent = Math.round(e.loaded / e.total * 100, 2) + '%'
      progress && progress(percent)
      // $('#progress').html(percent);//æ¾ç¤ºè¿åº¦çå®¹å¨ èªè¡ä¿®æ¹
      // var percent = Math.round(bytesLoaded * 100 / bytesTotal);
      // var progressbar = self.progressbars[file.id]
      // progressbar.bar.css('width', Math.round(percent * 80 / 100) + 'px')
      // progressbar.percent.html(percent + '%')
      console.log('percent=' + percent)
    }
  }
  // 上传结果
  oReq.onreadystatechange = function (e) {
    if (oReq.readyState === 4) {
      // 这里如果成功返回的是 success_action_status设置的值
      if (oReq.status === 200) {
        var serverData = oReq.responseText
        console.log('serverData=' + serverData)
        if (logApi) {
          console.log('************************  api111=' + api + '  ************************\n\nparams=' + JSON.stringify(params) + '\n \nresult=' + JSON.stringify(serverData) + ' \n\n #####################################################################')
        }
        let mock = false
        let paramStr = JSON.stringify(params)
        if (paramStr !== '' && paramStr != null) {
          mock = paramStr.match('mockcall')
        }
        let callback = process(success, fail, mock)
        callback(serverData)
      } else {
        console.log('上传失败')
      }
    }
  }
  oReq.open('POST', apiUrl)
  oReq.send(oMyForm)
}

// readonly 表示是否只读
export function callApi (api, params, success, readonly = true, fail = (api, ret) => {
  showAlert(store, 'danger', ret.msg)
}) {
  if (baseApiUrl === '') {
    baseApiUrl = parseUrl(window.location.href)
  }
  let apiUrl = baseApiUrl + 'api/' + api + '/'
  let callParams = {params: params, readonly: readonly}
  let submitData = {apiparams: JSON.stringify(callParams)}

  $.ajax({
    cache: false,
    type: 'POST',
    url: apiUrl,
    data: submitData,
    success: function (datas) {
      let mock = false
      let paramStr = JSON.stringify(params)
      if (paramStr !== '' && paramStr != null) {
        mock = paramStr.match('mockcall')
      }
      // console.log('api=' + api + ' params=' + JSON.stringify(params, null, 4) + ' result=' + datas)
      let callback = process(success, fail, mock)
      callback(datas)
    }
  })
}
