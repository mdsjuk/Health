import request from '@/utils/request'

// 查询糖尿病患者列表
export function listYlsj(query) {
  return request({
    url: '/system/ylsj/tnb/list',
    method: 'get',
    params: query
  })
}

// 查询高血压患者列表
export function listGxy(query) {
  return request({
    url: '/system/ylsj/gxy/list',
    method: 'get',
    params: query
  })
}

// 添加糖尿病患者信息
export function addTnb(data) {
  return request({
    url: '/system/ylsj/tnb',
    method: 'post',
    data: data
  })
}

// 添加高血压患者信息
export function addGxy(data) {
  return request({
    url: '/system/ylsj/gxy',
    method: 'post',
    data: data
  })
}

// 删除糖尿病患者信息
export function delTnb(idCardNo) {
  return request({
    url: '/system/ylsj/tnb/' + idCardNo,
    method: 'delete'
  })
}

// 删除高血压患者信息
export function delGxy(idCardNo) {
  return request({
    url: '/system/ylsj/gxy/' + idCardNo,
    method: 'delete'
  })
}


