<template>
  <div class="app-container">
    <el-form inline>
      <el-form-item label="姓名">
        <el-input v-model="searchForm.name" placeholder="请输入学员姓名" clearable/>
      </el-form-item>
      <el-form-item label="学号">
        <el-input v-model="searchForm.studentNumber" placeholder="请输入学号" clearable/>
      </el-form-item>
      <el-form-item label="最高学历">
        <el-select clearable v-model="searchForm.highestDegree">
          <el-option :value="1" label="初中"/>
          <el-option :value="2" label="高中"/>
          <el-option :value="3" label="大专"/>
          <el-option :value="4" label="本科"/>
          <el-option :value="5" label="硕士"/>
          <el-option :value="6" label="博士"/>
        </el-select>
      </el-form-item>
      <el-form-item label="所属班级">
        <el-select clearable v-model="searchForm.classesId">
          <el-option v-for="(item, index) in classesList" :key="index" :value="item.id" :label="item.name"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="doSearch">查询</el-button>
        <el-button type="primary" @click="showAddDialog">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="dataList">
      <el-table-column label="序号" type="index"/>
      <el-table-column label="姓名" prop="name"/>
      <el-table-column label="学号" prop="studentNumber"/>
      <el-table-column label="班级" prop="classesName"/>
      <el-table-column label="性别">
        <template slot-scope="scope">
          <span v-if="scope.row.gender === 1">男</span>
          <span v-else-if="scope.row.gender === 2">女</span>
          <span v-else>未知</span>
        </template>
      </el-table-column>
      <el-table-column label="手机号" prop="phone"/>
      <el-table-column label="最高学历" prop="highestDegree">
        <template slot-scope="scope">
          <span v-if="scope.row.highestDegree === 1">初中</span>
          <span v-if="scope.row.highestDegree === 2">高中</span>
          <span v-if="scope.row.highestDegree === 3">大专</span>
          <span v-if="scope.row.highestDegree === 4">本科</span>
          <span v-if="scope.row.highestDegree === 5">硕士</span>
          <span v-if="scope.row.highestDegree === 6">博士</span>
        </template>
      </el-table-column>
      <el-table-column label="违纪次数" prop="disciplineTimes"/>
      <el-table-column label="违纪扣分" prop="disciplineScore"/>
      <el-table-column label="最后操作时间" prop="updateTime">
        <template slot-scope="scope">
          {{ scope.row.updateTime ? scope.row.updateTime.replace('T', ' ') : '' }}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="text" @click="showEditDialog(scope.row.id)">编辑</el-button>
          <el-button type="text" @click="showDeductionsDialog(scope.row.id)">违纪</el-button>
          <el-button type="text" @click="del(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <br>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="searchForm.page"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="searchForm.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pageTotal"
    >
    </el-pagination>
    <el-dialog
      :visible.sync="addOrUpdateVisible"
      @closed="hiddenDialog"
      :title="addOrUpdateForm.id ? '修改学员' : '添加学员'"
    >
      <el-form :model="addOrUpdateForm" label-width="80px" :rules="rules" ref="addOrUpdateForm">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="addOrUpdateForm.name" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="学号" prop="studentNumber">
          <el-input v-model="addOrUpdateForm.studentNumber" placeholder="请输入学号"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select clearable v-model="addOrUpdateForm.gender" placeholder="请选择性别">
            <el-option :value="1" label="男"/>
            <el-option :value="2" label="女"/>
          </el-select>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="addOrUpdateForm.phone" placeholder="请输入手机号"></el-input>
        </el-form-item>
        <el-form-item label="最高学历" prop="highestDegree">
          <el-select clearable v-model="addOrUpdateForm.highestDegree">
            <el-option :value="1" label="初中"/>
            <el-option :value="2" label="高中"/>
            <el-option :value="3" label="大专"/>
            <el-option :value="4" label="本科"/>
            <el-option :value="5" label="硕士"/>
            <el-option :value="6" label="博士"/>
          </el-select>
        </el-form-item>
        <el-form-item label="所属班级" prop="classesId">
          <el-select v-model="addOrUpdateForm.classesId" placeholder="请选择班级" clearable style="width: 100%;">
            <el-option
              v-for="(item, index) in classesList"
              :key="index"
              :value="item.id"
              :label="item.name"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button @click="submitAddOrUpdateForm" type="primary">保存</el-button>
          <el-button @click="dismiss" type="info">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog :visible.sync="deductionsVisible" title="学员违纪处理">
      <el-form ref="deductionsFormRef" :rules="deductionsRules" :model="deductionsForm">
        <el-form-item label="违纪扣分" prop="disciplineScore">
          <el-input-number :step="1" :min="1" v-model="deductionsForm.disciplineScore"/>
        </el-form-item>
        <el-form-item>
          <el-button @click="submitDeductionsForm" type="primary">确定</el-button>
          <el-button @click="dismissDeductionsDialog" type="info">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>

import { create, deleteById, page as studentPage, selectById, updateById, deductions } from '@/api/student'
import { page as classesPage } from '@/api/classes'

export default {
  data() {
    return {
      addOrUpdateVisible: false,
      deductionsVisible: false,
      searchForm: {
        page: 1,
        pageSize: 10,
        name: undefined,
        studentNumber: undefined,
        highestDegree: undefined,
        classesId: undefined,
        begin: undefined,
        end: undefined,
        beginAndEnd: []
      },
      pageTotal: 0,
      dataList: [],
      addOrUpdateForm: {
        'id': undefined,
        'name': undefined,
        'studentNumber': undefined,
        'gender': undefined,
        'phone': undefined,
        'classesId': undefined,
        'highestDegree': undefined
      },
      deductionsForm: {
        id: undefined,
        disciplineScore: 1
      },
      deductionsRules: {
        disciplineScore: [{ required: true, message: '分数不能为空', trigger: 'blur' }]
      },
      rules: {
        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        studentNumber: [{ required: true, message: '请输入学号', trigger: 'blur' }],
        gender: [{ required: true, message: '请选择性别', trigger: 'blur' }],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { min: 11, max: 11, message: '手机号必须是11位纯数字', trigger: 'blur' }
        ],
        classesId: [{ required: true, message: '请选择班级', trigger: 'blur' }]
      },
      classesList: []
    }
  },
  mounted() {
    this.listData()
    this.initClassesList()
  },
  methods: {
    submitDeductionsForm() {
      this.$refs['deductionsFormRef'].validate((valid) => {
        if (valid) {
          deductions(this.deductionsForm)
            .then(res => {
              if (res.data.code === 1) {
                this.deductionsVisible = false
                this.$message.success('扣分成功')
                this.listData()
              } else {
                this.$message.error(res.data.msg)
              }
            })
        } else {
          return false
        }
      })
    },
    dismissDeductionsDialog() {
      this.deductionsVisible = false
      this.deductionsForm.id = undefined
    },
    showDeductionsDialog(id) {
      this.deductionsForm.id = id
      this.deductionsVisible = true
    },
    initClassesList() {
      classesPage({
        page: 1,
        pageSize: 99
      }).then(res => {
        if (res.data.code === 1) {
          this.classesList = res.data.data.rows.map(item => {
            return {
              id: item.id,
              name: item.name
            }
          })
        }
      })
    },
    handleSizeChange(val) {
      this.searchForm.pageSize = val
      this.listData()
    },
    handleCurrentChange(val) {
      this.searchForm.page = val
      this.listData()
    },
    submitAddOrUpdateForm() {
      this.$refs['addOrUpdateForm'].validate((valid) => {
        if (valid) {
          if (this.addOrUpdateForm.id) {
            updateById(this.addOrUpdateForm)
              .then(res => {
                if (res.data.code === 1) {
                  this.hiddenDialog()
                  this.$message.success('更新成功')
                  this.listData()
                } else {
                  this.$message.error(`更新失败: ${res.data.msg}`)
                }
              })
          } else {
            create(this.addOrUpdateForm)
              .then(res => {
                if (res.data.code === 1) {
                  this.hiddenDialog()
                  this.$message.success('新增成功')
                  this.listData()
                } else {
                  this.$message.error(`新增失败: ${res.data.msg}`)
                }
              })
          }
        } else {
          return false
        }
      })
    },
    hiddenDialog() {
      this.addOrUpdateForm.id = undefined
      this.$refs['addOrUpdateForm'].resetFields()
      this.addOrUpdateVisible = false
    },
    dismiss() {
      this.hiddenDialog()
    },
    listData() {
      studentPage(this.searchForm)
        .then(res => {
          if (res.data.code === 1) {
            this.dataList = res.data.data.rows
            this.pageTotal = res.data.data.total
          }
        })
    },
    del(id) {
      this.$confirm(`此操作将永久删除ID=${id}的数据, 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteById(id)
          .then(res => {
            if (res.data.code === 1) {
              this.$message({
                type: 'success',
                message: '删除成功!'
              })
              this.listData()
            } else {
              this.$message({
                type: 'error',
                message: '删除失败!'
              })
            }
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    showEditDialog(id) {
      this.addOrUpdateVisible = true
      selectById(id)
        .then(result => {
          if (result.data.code === 1) {
            this.addOrUpdateForm = result.data.data
          } else {
            this.$message.error('回显接口数据获取失败')
          }
        })
    },
    showAddDialog() {
      this.addOrUpdateVisible = true
    },
    timeSelected(val) {
      if (val) {
        this.searchForm.begin = val[0]
        this.searchForm.end = val[1]
      } else {
        this.searchForm.begin = undefined
        this.searchForm.end = undefined
      }
    },
    doSearch() {
      this.listData()
    }
  }
}
</script>

