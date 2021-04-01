<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="姓名" prop="name">
      <el-input v-model="dataForm.name" placeholder="姓名"></el-input>
    </el-form-item>
    <el-form-item label="学校" prop="school">
      <el-input v-model="dataForm.school" placeholder="学校"></el-input>
    </el-form-item>
    <el-form-item label="班级" prop="grades">
      <el-input v-model="dataForm.grades" placeholder="班级"></el-input>
    </el-form-item>
    <el-form-item label="家长1" prop="parent1">
      <el-input v-model="dataForm.parent1" placeholder="家长1"></el-input>
    </el-form-item>
    <el-form-item label="手机1" prop="teleNo1">
      <el-input v-model="dataForm.teleNo1" placeholder="手机1"></el-input>
    </el-form-item>
    <el-form-item label="家长2" prop="parent2">
      <el-input v-model="dataForm.parent2" placeholder="家长2"></el-input>
    </el-form-item>
    <el-form-item label="手机2" prop="teleNo2">
      <el-input v-model="dataForm.teleNo2" placeholder="手机2"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          name: '',
          school: '',
          grades: '',
          parent1: '',
          teleNo1: '',
          parent2: '',
          teleNo2: ''
        },
        dataRule: {
          name: [
            { required: true, message: '姓名不能为空', trigger: 'blur' }
          ],
          school: [
            { required: true, message: '学校不能为空', trigger: 'blur' }
          ],
          grades: [
            { required: true, message: '班级不能为空', trigger: 'blur' }
          ],
          parent1: [
            { required: true, message: '家长1不能为空', trigger: 'blur' }
          ],
          teleNo1: [
            { required: true, message: '手机1不能为空', trigger: 'blur' }
          ],
          parent2: [
            { required: true, message: '家长2不能为空', trigger: 'blur' }
          ],
          teleNo2: [
            { required: true, message: '手机2不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/edu/student/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.eduStudent.name
                this.dataForm.school = data.eduStudent.school
                this.dataForm.grades = data.eduStudent.grades
                this.dataForm.parent1 = data.eduStudent.parent1
                this.dataForm.teleNo1 = data.eduStudent.teleNo1
                this.dataForm.parent2 = data.eduStudent.parent2
                this.dataForm.teleNo2 = data.eduStudent.teleNo2
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/edu/student/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'school': this.dataForm.school,
                'grades': this.dataForm.grades,
                'parent1': this.dataForm.parent1,
                'teleNo1': this.dataForm.teleNo1,
                'parent2': this.dataForm.parent2,
                'teleNo2': this.dataForm.teleNo2
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
