import request from '@/utils/request'

// 查询精神病患者列表
export function listJsb(query) {
  return request({
    url: '/ttjm/jsb/list',
    method: 'get',
    params: query
  })
}

// 删除精神病患者信息
export function delJsb(sno) {
  return request({
    url: '/ttjm/jsb/' + sno,
    method: 'delete'
  })
}




// 解密精神病患者信息
export function jmJsb(sno) {
  return request({
    url: '/ttjm/jsb/' + sno,
    method: 'post'
  })
}

// 查询为没空啊吗列表
export function listfile(query) {
  return request({
    url: '/ttjm/jsb/list/file',
    method: 'get',
    params: query
  })
}

export function dataGet(json) {
  return request({
    url: '/ttjm/fxtj/tj',
    method: 'post',
    data: json
  })
}




