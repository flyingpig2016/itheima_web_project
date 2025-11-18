import request from '@/utils/request'

export const create = (classes) => {
  return request.post('/classes', classes)
}

export const deleteById = (id) => {
  return request.delete(`/classes/${id}`)
}

export const updateById = (classes) => {
  return request.put('/classes', classes)
}

export const selectById = (id) => {
  return request.get(`/classes/${id}`)
}

export const page = ({ name, begin, end, page, pageSize }) => {
  return request({
    url: '/classes',
    method: 'get',
    params: {
      name,
      begin,
      end,
      page,
      pageSize
    }
  })
}
