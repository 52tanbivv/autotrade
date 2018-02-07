var express = require('express')
var webpack = require('webpack')
var config = require('./webpack.dev.conf')

var httpProxy = require('http-proxy')  //http-proxy
var proxy = httpProxy.createProxyServer({})

proxy.on('error', function (error, req, res) {
    var json;
    console.log('proxy error', error);
    if (!res.headersSent) {
        res.writeHead(500, { 'content-type': 'application/json' });
    }

    json = { error: 'proxy_error', reason: error.message };
    res.end(JSON.stringify(json));
});


var app = express()
var compiler = webpack(config)

var devMiddleware = require('webpack-dev-middleware')(compiler, {
  publicPath: config.output.publicPath,
  stats: {
    colors: true,
    chunks: false
  }
})

var hotMiddleware = require('webpack-hot-middleware')(compiler)
// force page reload when html-webpack-plugin template changes
compiler.plugin('compilation', function (compilation) {
  compilation.plugin('html-webpack-plugin-after-emit', function (data, cb) {
    hotMiddleware.publish({ action: 'reload' })
    cb()
  })
})

app.get("/huobi/login", function(req, res){
  proxy.web(req, res, { target: 'http://127.0.0.1:8080' });
});

app.get("/huobi/logout", function(req, res){
  proxy.web(req, res, { target: 'http://127.0.0.1:8080' });
});

app.get("/huobi/static/*", function(req, res){
  proxy.web(req, res, { target: 'http://127.0.0.1:8080' });
});
app.post("/huobi/kindeditor/*", function(req, res){
  console.log('call ueditor,url='+req.url)
  proxy.web(req, res, { target: 'http://127.0.0.1:8080' });
});
app.post("/huobi/ueditor/*", function(req, res){
  console.log('call ueditor,url='+req.url)
  proxy.web(req, res, { target: 'http://127.0.0.1:8080' });
});

app.post("/huobi/login", function(req, res){
  proxy.web(req, res, { target: 'http://127.0.0.1:8080' });
});
app.post("/huobi/api/*", function(req, res){
  console.log('proxy api,url='+req.url)
  proxy.web(req, res, { target: 'http://127.0.0.1:8080' });
});

// handle fallback for HTML5 history API
app.use(require('connect-history-api-fallback')())
// serve webpack bundle output
app.use(devMiddleware)
// enable hot-reload and state-preserving
// compilation error display
app.use(hotMiddleware)
// serve pure static assets
app.use('/static', express.static('./static'))

app.listen(8081, function (err) {
  if (err) {
    console.log(err)
    return
  }
  console.log('HHHA Listening at http://localhost:8080')
})
