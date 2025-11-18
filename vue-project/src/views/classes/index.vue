<template>
  <div class="app-container">
    <el-form inline>
      <el-form-item label="班级名称">
        <el-input v-model="searchForm.name" placeholder="请输入班级名称" clearable/>
      </el-form-item>
      <el-form-item label="结束时间">
        <el-date-picker
          v-model="searchForm.beginAndEnd"
          clearable
          type="daterange"
          range-separator="至"
          start-placeholder="开始"
          end-placeholder="结束"
          value-format="yyyy-MM-dd"
          @change="timeSelected"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="doSearch">查询</el-button>
        <el-button type="primary" @click="showAddDialog">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="dataList">
      <el-table-column label="序号" type="index"/>
      <el-table-column label="班级名称" prop="name"/>
      <el-table-column label="班级教室" prop="classesNumber"/>
      <el-table-column label="开课时间" prop="startTime"/>
      <el-table-column label="结束时间" prop="finishTime"/>
      <el-table-column label="班主任" prop="empName"/>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="text" @click="showEditDialog(scope.row.id)">编辑</el-button>
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
      :title="addOrUpdateForm.id ? '修改班级' : '新增班级'"
    >
      <el-form :model="addOrUpdateForm" label-width="80px" :rules="rules" ref="addOrUpdateForm">
        <el-form-item label="班级名称" prop="name">
          <el-input v-model="addOrUpdateForm.name" placeholder="请输入班级名称"></el-input>
        </el-form-item>
        <el-form-item label="班级教室" prop="classesNumber">
          <el-input v-model="addOrUpdateForm.classesNumber" placeholder="请输入班级教室"></el-input>
        </el-form-item>
        <el-form-item label="开课时间" prop="startTime">
          <el-date-picker
            style="width: 100%;"
            v-model="addOrUpdateForm.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择开课时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结课时间" prop="finishTime">
          <el-date-picker
            style="width: 100%;"
            v-model="addOrUpdateForm.finishTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择结课时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="班主任" prop="empId">
          <el-select v-model="addOrUpdateForm.empId" placeholder="请选择班主任" clearable style="width: 100%;">
            <el-option v-for="(item, index) in empList" :key="index" :value="item.id" :label="item.name"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button @click="submitAddOrUpdateForm" type="primary">保存</el-button>
          <el-button @click="dismiss" type="info">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { create, deleteById, page as classessPage, selectById, updateById } from '@/api/classes'
import { page as empPage } from '@/api/emp'

export default {
  data() {
    return {
      empList: [],
      addOrUpdateVisible: false,
      searchForm: {
        page: 1,
        pageSize: 10,
        name: undefined,
        begin: undefined,
        end: undefined,
        beginAndEnd: []
      },
      pageTotal: 0,
      dataList: [],
      addOrUpdateForm: {
        'id': undefined,
        'name': undefined,
        'classesNumber': undefined,
        'startTime': undefined,
        'finishTime': undefined,
        'empId': undefined
      },
      rules: {
        name: [{ required: true, message: '请输入班级名称', trigger: 'blur' }],
        startTime: [{ required: true, message: '请输入班级名称', trigger: 'blur' }],
        finishTime: [{ required: true, message: '请输入班级名称', trigger: 'blur' }],
        empId: [{ required: true, message: '请输入班级名称', trigger: 'blur' }]
      }
    }
  },
  mounted() {
    this.listData()
    this.initEmpList()
  },
  methods: {
    handleSizeChange(val) {
      this.searchForm.pageSize = val
      this.listData()
    },
    handleCurrentChange(val) {
      this.searchForm.page = val
      this.listData()
    },
    initEmpList() {
      empPage('', '', '', '', 1, 99)
        .then(res => {
          if (res.data.code === 1) {
            this.empList = res.data.data.rows.map(item => {
              return {
                id: item.id,
                name: item.name
              }
            })
          }
        })
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
      classessPage(this.searchForm)
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
