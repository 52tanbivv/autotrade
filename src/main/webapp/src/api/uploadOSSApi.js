import { callApi } from './api.js'

const process = (successCall, failCall) => {
  return (datas) => {
    try {
      console.log('uploadOSSApi process=' + datas + 'datas.code=' + datas.code + 'successCall=' + successCall)
      if (datas.code === 200) {
        successCall(datas.url)
      } else {
        if (failCall === undefined) {
        } else {
          failCall(datas.msg)
        }
      }
    } catch (e) {
      console.log('uploadOSSApi 服务器返回结果发生错误=' + JSON.stringify(datas))
      if (failCall !== undefined) {
        failCall({code: '505', 'msg': '返回值json解析错误：' + e + ',服务器返回结果：' + datas})
      }
    }
  }
}

// var sha1File = require('sha1-file')
// 调用后台服务器获取上传令牌
export function callUploadApi (localfile, success, progress, fail = (ret) => {
}) {
  console.log('系统错误，请联系管理员' + success)
  console.log('IN function callUploadApi localfile=' + localfile)
  callApi('ossclient_getOSSPolicyAndSignature', {}, (data) => {
    console.log('IN callUploadApi =' + JSON.stringify(data))
    processUpoadFile(localfile, data, success, progress, fail)
  })
}

function getUuid () {
  let chars = 'abcdef0123456789'
  let maxPos = chars.length
  let id = ''
  for (let i = 0; i < 32; i++) {
    id += chars.charAt(Math.floor(Math.random() * maxPos))
  }
  return id
}

// 处理客服端传输的文件1 文件hashkey 2 直接传输至OSS
function processUpoadFile (localfile, ossParam, success, progress, fail) {
  // sha1File(localfile, function (error, filehashkey) {
  //   if (error) return console.log(error)
  //   console.log('localfile hahskey=' + filehashkey)
  //   doUploadFileToOss(localfile, ossParam, filehashkey)
  // })
  // let hahskeyCallBack = (data) => {
  //   console.log('processUpoadFile 文件hashkey成功=' + data)
  //   doUploadFileToOss(localfile, ossParam, data, success, progress, fail)
  // }
  // hashme(localfile, hahskeyCallBack)
  doUploadFileToOss(localfile, ossParam, getUuid(), success, progress, fail)
}

// 上传至OSS
function doUploadFileToOss (localfile, ossParam, filehashkey, success, progress, fail) {
  console.log('doUploadFileToOss success=' + JSON.stringify(success))
  // uploadType image   OR  multiple æ¹éå¤ç
  var bucket = ossParam.bucket
  var OSSAccessKeyId = ossParam.OSSAccessKeyId
  var policyvalue = ossParam.policy
  var signaturevalue = ossParam.signature
  // policyvalue="ewoiZXhwaXJhdGlvbiI6ICIyMTIwLTAxLTAxVDEyOjAwOjAwLjAwMFoiLAoiY29uZGl0aW9ucyI6IFsKWyJjb250ZW50LWxlbmd0aC1yYW5nZSIsIDAsIDEwNDg1NzYwMF0KXQp9Cg==";
  // signaturevalue="nsrGHuy4mdskcSxErl3lmjIqg6Q=";
  // bucket="http://oss-cn-qingdao.aliyuncs.com/weixt-test";
  // var file = document.getElementById(''+fileid+'').files[0];
  var imageFileType = 'jpg'
  var fileName = localfile.name
  if (fileName.indexOf('.') > 0) {
    imageFileType = fileName.split('.')[fileName.split('.').length - 1]
  }
  var sendToOssData = {
    key: filehashkey + '.' + imageFileType,
    OSSAccessKeyId: OSSAccessKeyId,
    policy: policyvalue,
    signature: signaturevalue,
    success_action_status: '201'
  }
  var oMyForm = new window.FormData()
  for (var fieldName in sendToOssData) {
    oMyForm.append(fieldName, sendToOssData[fieldName])
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
  console.log('doUploadFileToOss success=' + JSON.stringify(success))
  // 上传结果
  oReq.onreadystatechange = function (e) {
    if (oReq.readyState === 4) {
      // 这里如果成功返回的是 success_action_status设置的值
      if (oReq.status === 201) {
        var serverData = oReq.responseText
        console.log('serverData=' + serverData)
        console.log('doUploadFileToOss success=' + JSON.stringify(success))
        let callback = process(success, fail)
        callback(parserXML(serverData))
      } else {
        console.log('上传失败')
      }
    }
  }
  oReq.open('POST', bucket)
  oReq.send(oMyForm)
}

function parserXML (serverData) {
  var xmlDoc
  if (window.DOMParser) {
    var parser = new window.DOMParser()
    xmlDoc = parser.parseFromString(serverData, 'text/xml')
  } else { // Internet Explorer
    xmlDoc = new window.ActiveXObject('Microsoft.XMLDOM')
    xmlDoc.async = 'false'
    xmlDoc.loadXML(serverData)
  }
  var Bucket = xmlDoc.getElementsByTagName('Bucket')[0].childNodes[0].nodeValue
  var Location = xmlDoc.getElementsByTagName('Location')[0].childNodes[0].nodeValue
  var serverDataJSON = {'url': Location, 'Bucket': Bucket, 'code': 200, 'message': 'sucess'}
  return serverDataJSON
}
