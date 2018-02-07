KindEditor.plugin('jme', function(e){
    var editor = this, name = 'jme', lang = editor.lang(name + '.');
    editor.clickToolbar(name, function() {
        var dialog = editor.createDialog({
            name : name,
            width : 500,
            height : 300,
            title : editor.lang(name),
            body : '<div style="width:500px;height:300px;">' +
                '<iframe id="math_frame" style="width:500px;height:300px;" frameborder="no" src="'+parseUrl(window.location.href)+'mobile/mathdialog"></iframe></div>',
                 
            closeBtn : {
                name : '关闭',
                click : function(e) {
                        dialog.remove();
                }
            },
            yesBtn : {
                name : '确定',
                click : function(e) {
                    var thedoc = document.frames ? document.frames('math_frame').document : getIFrameDOM("math_frame");
                    var mathHTML = '<span class="mathquill-rendered-math" style="font-size:'
                        + '20px' + ';" >' + $("#jme-math",thedoc).html() + '</span><span>&nbsp;</span>';
                     
                    editor.insertHtml(mathHTML).hideDialog().focus();
                    return;                 
                }
            }
        });
    });
});
 
function getIFrameDOM(fid){
    var fm = getIFrame(fid);
    return fm.document||fm.contentDocument;
}
function getIFrame(fid){
    return document.getElementById(fid)||document.frames[fid];
}
function parseUrl (url) {
  var urls = url.split('//')
  var httpstr = urls[0]
  var addressStrs = urls[1].split('/')
  return httpstr + '//' + addressStrs[0] + '/' + addressStrs[1] + '/'
}