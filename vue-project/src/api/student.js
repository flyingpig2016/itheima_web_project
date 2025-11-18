import request from '@/utils/request'

const baseURL = '/student'

export const create = (student) => {
  return request.post(baseURL, student)
}

export const deleteById = (id) => {
  return request.delete(`${baseURL}/${id}`)
}

export const updateById = (student) => {
  return request.put(baseURL, student)
}

export const selectById = (id) => {
  return request.get(`${baseURL}/${id}`)
}

export const page = ({ page, pageSize, name, highestDegree, classesId, studentNumber }) => {
  return request.get(baseURL, {
    params: {
      page, pageSize, name, highestDegree, classesId, studentNumber
    }
  })
}

export const deductions = (student) => {
  return request.put(`${baseURL}/deductions`, student)
}
