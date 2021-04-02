<template>
	<el-dialog :title="!dataForm.id ? '新增' : '修改'" :close-on-click-modal="false" :visible.sync="visible">

		<el-container style="height: 500px; border: 1px solid #eee">
			<el-aside width="200px">
				<el-tree :data="treeData" :props="defaulttreeProps" node-key="id" @node-click="handleNodeClick">
				</el-tree>
			</el-aside>

			<el-main>
				<el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
					label-width="80px">
					<el-form-item label="父id" prop="pid">
						<el-input v-model="dataForm.pid" placeholder="父id"></el-input>
					</el-form-item>
					<el-form-item label="编号" prop="code">
						<el-input v-model="dataForm.code" placeholder="编号"></el-input>
					</el-form-item>
					<el-form-item label="名称" prop="name">
						<el-input v-model="dataForm.name" placeholder="名称"></el-input>
					</el-form-item>
					<el-form-item label="数值" prop="value">
						<el-input v-model="dataForm.value" placeholder="数值"></el-input>
					</el-form-item>
					<el-form-item label="描述" prop="describe">
						<el-input v-model="dataForm.describe" placeholder="描述"></el-input>
					</el-form-item>
					<el-form-item label="状态" prop="status">
						<el-radio v-model="dataForm.status" label="1">启用</el-radio>
						<el-radio v-model="dataForm.status" label="0">禁用</el-radio>
					</el-form-item>
				</el-form>
			</el-main>
		</el-container>

		<span slot="footer" class="dialog-footer">
			<el-button @click="visible = false">取消</el-button>
			<el-button type="primary" @click="dataFormSubmit()">确定</el-button>
		</span>
	</el-dialog>
</template>

<script>
	export default {
		data() {
			return {
				visible: false,
				treeData: [{
					label: '',
					id: 1,
					children: []
				}],
				defaulttreeProps: {
					children: 'children',
					label: 'label'
				},
				dataForm: {
					id: 0,
					pid: '',
					code: '',
					name: '',
					value: '',
					describe: '',
					status: '1'
				},
				dataRule: {
					pid: [{
						required: true,
						message: '父id不能为空',
						trigger: 'blur'
					}],
					code: [{
						required: true,
						message: '编号不能为空',
						trigger: 'blur'
					}],
					name: [{
						required: true,
						message: '名称不能为空',
						trigger: 'blur'
					}],
					value: [{
						required: true,
						message: '数值不能为空',
						trigger: 'blur'
					}],
					describe: [{
						required: true,
						message: '描述不能为空',
						trigger: 'blur'
					}]
				}
			}
		},
		methods: {
			init(id) {
				this.dataForm.id = id || 0
				this.visible = true
				this.$nextTick(() => {
					this.$refs['dataForm'].resetFields()
					this.initTree()
					if (this.dataForm.id) {
						this.$http({
							url: this.$http.adornUrl(`/dictionary/info/${this.dataForm.id}`),
							method: 'get',
							params: this.$http.adornParams()
						}).then(({
							data
						}) => {
							if (data && data.code === 0) {
								this.dataForm.pid = data.sysDictionary.pid
								this.dataForm.code = data.sysDictionary.code
								this.dataForm.name = data.sysDictionary.name
								this.dataForm.value = data.sysDictionary.value
								this.dataForm.describe = data.sysDictionary.describe
								this.dataForm.status = data.sysDictionary.status
							}
						})
					}
				})
			},
			initTree() {
				this.$http({
					url: this.$http.adornUrl(`/dictionary/tree/${this.dataForm.id}`),
					method: 'get',
					params: this.$http.adornParams()
				}).then(({
					data
				}) => {
					if (data && data.code === 0) { 
						this.treeData=[]
						this.treeData.push(data.tree)  
					}
				})
			},
			// 表单提交
			dataFormSubmit() {
				this.$refs['dataForm'].validate((valid) => {
					if (valid) {
						this.$http({
							url: this.$http.adornUrl(
								`/dictionary/${!this.dataForm.id ? 'save' : 'update'}`),
							method: 'post',
							data: this.$http.adornData({
								'id': this.dataForm.id || undefined,
								'pid': this.dataForm.pid,
								'code': this.dataForm.code,
								'name': this.dataForm.name,
								'value': this.dataForm.value,
								'describe': this.dataForm.describe,
								'status': this.dataForm.status
							})
						}).then(({
							data
						}) => {
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
			},
			handleNodeClick(data) {
				console.log(data);
			}
		}
	}
</script>
