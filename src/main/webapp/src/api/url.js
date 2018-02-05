const apiurl = parseUrl(window.location.href)

export function parseUrl (url) {
  let urls = url.split('//')
  let httpstr = urls[0]
  let addressStrs = urls[1].split('/')
  return httpstr + '//' + addressStrs[0] + '/' + addressStrs[1] + '/'
}

export default {apiurl}
