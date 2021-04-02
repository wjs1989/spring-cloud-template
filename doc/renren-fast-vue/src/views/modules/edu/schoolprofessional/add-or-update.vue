<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="所属学校" prop="schoolId">
      <el-input v-model="dataForm.schoolId" placeholder="所属学校"></el-input>
    </el-form-item>
    <el-form-item label="专业名称" prop="professionalName">
      <el-input v-model="dataForm.professionalName" placeholder="专业名称"></el-input>
    </el-form-item>
    <el-form-item label="专业代号" prop="professionalCode">
      <el-input v-model="dataForm.professionalCode" placeholder="专业代号"></el-input>
    </el-form-item>
    <el-form-item label="专业编号" prop="professionalNumber">
      <el-input v-model="dataForm.professionalNumber" placeholder="专业编号"></el-input>
    </el-form-item>
    <el-form-item label="等级" prop="level">
      <el-input v-model="dataForm.level" placeholder="等级"></el-input>
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
          schoolId: '',
          professionalName: '',
          professionalCode: '',
          professionalNumber: '',
          level: ''
        },
        dataRule: {
          schoolId: [
            { required: true, message: '所属学校不能为空', trigger: 'blur' }
          ],
          professionalName: [
            { required: true, message: '专业名称不能为空', trigger: 'blur' }
          ],
          professionalCode: [
            { required: true, message: '专业代号不能为空', trigger: 'blur' }
          ],
          professionalNumber: [
            { required: true, message: '专业编号（自己定义的）不能为空', trigger: 'blur' }
          ],
          level: [
            { required: true, message: '1 一本，2 二本，3专科不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/edu/schoolprofessional/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.schoolId = data.schoolProfessional.schoolId
                this.dataForm.professionalName = data.schoolProfessional.professionalName
                this.dataForm.professionalCode = data.schoolProfessional.professionalCode
                this.dataForm.professionalNumber = data.schoolProfessional.professionalNumber
                this.dataForm.level = data.schoolProfessional.level
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
              url: this.$http.adornUrl(`/edu/schoolprofessional/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'schoolId': this.dataForm.schoolId,
                'professionalName': this.dataForm.professionalName,
                'professionalCode': this.dataForm.professionalCode,
                'professionalNumber': this.dataForm.professionalNumber,
                'level': this.dataForm.level
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
