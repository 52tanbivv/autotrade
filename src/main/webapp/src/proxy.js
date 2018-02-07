/*
    http-proxy 
*/
var http = require('http')
    ,httpProxy = require('http-proxy')  //http-proxy
    ,proxy = httpProxy.createProxyServer({})
    ,fs = require('fs')
    ,path = require('path') //自己定义  根据后缀转换Content-Type
;
 
 
var server = http.createServer(function(req, res) {  
		console.log('req='+req.url);
		var serverurl = req.url;
		if (serverurl.charAt(serverurl.length-1) == '/' || serverurl.indexOf('login')!=-1)
		{
        	proxy.web(req, res, { target: 'http://127.0.0.1:8080' }); 
        }
        else
        {
        	res.writeHead(404, {"Content-Type": "text/html"});
    	    res.end("<h1>404 Not Found</h1>");
        }
});
 
console.log("listening on port 80");
server.listen(8088);