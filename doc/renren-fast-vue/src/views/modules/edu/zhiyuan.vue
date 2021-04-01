<template>
	<el-form ref="form" :model="form" label-width="80px" style="height: 100%; ">

		<el-tabs tab-position="left" style="min-height:500px; height: 100%;">
			<el-tab-pane label="考生信息">
				<el-form-item label="考生姓名:">
					<el-input v-model="form.name"></el-input>
				</el-form-item>
				<el-form-item label="所在学校:">
					<el-input v-model="form.school"></el-input>
				</el-form-item>
				<el-form-item label="所在班级:">
					<el-input v-model="form.grades"></el-input>
				</el-form-item>
				<el-form-item label="家长一:">
					<el-input v-model="form.parent1"></el-input>
				</el-form-item>
				<el-form-item label="手机号:">
					<el-input v-model="form.teleNo1"></el-input>
				</el-form-item>
				<el-form-item label="家长二:">
					<el-input v-model="form.parent2"></el-input>
				</el-form-item>
				<el-form-item label="手机号:">
					<el-input v-model="form.teleNo2"></el-input>
				</el-form-item>
				<el-form-item label="备注:">
					<el-input type="textarea" v-model="form.remark"></el-input>
				</el-form-item>
				<!-- 	<el-form-item label="活动区域">
					<el-select v-model="form.region" placeholder="请选择活动区域">
						<el-option label="区域一" value="shanghai"></el-option>
						<el-option label="区域二" value="beijing"></el-option>
					</el-select>
				</el-form-item> -->
			</el-tab-pane>
			<el-tab-pane label="考生成绩信息">
				<el-form-item label="语文:">
					<el-input v-model="form.name"></el-input>
				</el-form-item>
				<el-form-item label="数学:">
					<el-input v-model="form.name"></el-input>
				</el-form-item>
				<el-form-item label="英语:">
					<el-input v-model="form.name"></el-input>
				</el-form-item>
				<el-form-item label="自选一:">
					<el-row :gutter="10">
						<el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="3">
							<el-select v-model="optionValue1" placeholder="请选择">
								<el-option v-for="item in options" :key="item.value" :label="item.label"
									:value="item.value">
								</el-option>
							</el-select>
						</el-col>
						<el-col :xs="4" :sm="6" :md="8" :lg="9" :xl="11">
							<el-input v-model="form.name"></el-input>
						</el-col>
					</el-row>
				</el-form-item>
				<el-form-item label="自选二:">
					<el-row :gutter="10">
						<el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="3">
							<el-select v-model="optionValue2" placeholder="请选择">
								<el-option v-for="item in options" :key="item.value" :label="item.label"
									:value="item.value">
								</el-option>
							</el-select>
						</el-col>
						<el-col :xs="4" :sm="6" :md="8" :lg="9" :xl="11">
							<el-input v-model="form.name"></el-input>
						</el-col>
					</el-row>
				</el-form-item>
				<el-form-item label="自选三:">
					<el-row :gutter="10">
						<el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="3">
							<el-select v-model="optionValue3" placeholder="请选择">
								<el-option v-for="item in options" :key="item.value" :label="item.label"
									:value="item.value">
								</el-option>
							</el-select>
						</el-col>
						<el-col :xs="4" :sm="6" :md="8" :lg="9" :xl="11">
							<el-input v-model="form.name"></el-input>
						</el-col>
					</el-row>
				</el-form-item>
				<!-- <el-form-item label="活动时间">
					<el-col :span="11">
						<el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;">
						</el-date-picker>
					</el-col>
					<el-col class="line" :span="2">-</el-col>
					<el-col :span="11">
						<el-time-picker type="fixed-time" placeholder="选择时间" v-model="form.date2" style="width: 100%;">
						</el-time-picker>
					</el-col>
				</el-form-item> -->
			</el-tab-pane>
			<el-tab-pane label="志愿偏好信息">
				<!-- 	<el-form-item label="学校层次">
					<el-checkbox-group v-model="form.type">
						<el-checkbox label="985" name="type"></el-checkbox>
						<el-checkbox label="211" name="type"></el-checkbox>
						<el-checkbox label="不限" name="type"></el-checkbox> 
					</el-checkbox-group>
				</el-form-item> -->
				<el-form-item label="学校层次">
					<el-radio-group v-model="form.resource">
						<el-radio label="985"></el-radio>
						<el-radio label="211"></el-radio>
						<el-radio label="不限"></el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="名次上下调整">
					<el-radio-group v-model="form.resource1">
						<el-row :gutter="10">
							<el-col>
								<el-radio label="500"></el-radio>
								<el-radio label="1000"></el-radio>
								<el-radio label="2000"></el-radio>
								<el-radio label="3000"></el-radio>
								<el-radio label="5000"></el-radio>
								<el-radio label="10000"></el-radio>
								<el-radio label="20000"></el-radio>
								<el-radio label="不限"></el-radio>
							</el-col>
						</el-row>
					</el-radio-group>
				</el-form-item>

				<el-form-item label="地域选择">
					<el-row :gutter="10">

						<el-cascader placeholder="试试搜索：浙江" :options="cascaderoptions" filterable
							@change="cascaderChange" v-model="cascaderDefault"></el-cascader>

					</el-row>
					<el-row :gutter="10">
						<el-radio-group v-model="form.resource">
							<el-radio label="本科"></el-radio>
							<el-radio label="专科"></el-radio>
						</el-radio-group>
					</el-row>  
					<el-row :gutter="10">
						<el-form-item label="专业大类">
							<el-select v-model="optionValue3" placeholder="请选择">
								<el-option v-for="item in options" :key="item.value" :label="item.label"
									:value="item.value">
								</el-option>
							</el-select>
						</el-form-item>
					</el-row>
					<el-row :gutter="10">
						<el-form-item label="专业小类">
							<el-select v-model="optionValue3" placeholder="请选择">
								<el-option v-for="item in options" :key="item.value" :label="item.label"
									:value="item.value">
								</el-option>
							</el-select>
						</el-form-item>
					</el-row>
					<el-row :gutter="10">
						<el-form-item label="权重">
							<el-select v-model="optionValue3" placeholder="请选择">
								<el-option v-for="item in options" :key="item.value" :label="item.label"
									:value="item.value">
								</el-option>
							</el-select>
						</el-form-item>
					</el-row>
				</el-form-item>

			</el-tab-pane>
			<!-- <el-tab-pane label="定时任务补偿">
				<el-form-item label="特殊资源">
					<el-radio-group v-model="form.resource">
						<el-radio label="线上品牌商赞助"></el-radio>
						<el-radio label="线下场地免费"></el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="活动形式">
					<el-input type="textarea" v-model="form.desc"></el-input>
				</el-form-item>
			</el-tab-pane> -->
		</el-tabs>

		<el-form-item style="text-align: right;">
			<el-button type="primary" @click="onSubmit" :disabled = "isDisabled" >保存</el-button>
			<el-button>取消</el-button>
		</el-form-item>
	</el-form>
</template>

<script>
	export default {
		data() {
			return {
				form: {
					id: 0,
					name: '',
					school: '', 
					grades: '',
					parent1: '',
					teleNo1: '',
					parent2: '',
					teleNo2: '',
					remark:'',
					date1: '',
					date2: '',
					delivery: false,
					type: [],
					resource: '',
					resource1: '',
					desc: ''
				},

				options: [{
					value: '1',
					label: '政治'
				}, {
					value: '2',
					label: '地理'
				}, {
					value: '3',
					label: '技术'
				}],
				optionValue1: '',
				optionValue2: '',
				optionValue3: '',

				cascaderoptions: [{
					value: 'zj',
					label: '浙江',
					children: [{
						value: 'hz',
						label: '杭州'
					}, {
						value: 'nb',
						label: '宁波'
					}],
				}, {
					value: 'js',
					label: '江西',
					children: [{
						value: 'sr',
						label: '上饶'
					}, {
						value: 'nc',
						label: '南昌'
					}],
				}],
				cascaderDefault: ['js', 'nc'],
				//提交按钮是否禁用
				isDisabled : false
			}
		},
		methods: {
			onSubmit() {
				this.isDisabled= true;
				this.$refs['form'].validate((valid) => {
				  if (valid) {
				    this.$http({
				      url: this.$http.adornUrl(`/edu/student/${!this.form.id ? 'save' : 'update'}`),
				      method: 'post',
				      data: this.$http.adornData({
				        'id': this.form.id || undefined,
				        'name': this.form.name,
				        'school': this.form.school,
				        'grades': this.form.grades,
				        'parent1': this.form.parent1,
				        'teleNo1': this.form.teleNo1,
				        'parent2': this.form.parent2,
				        'teleNo2':this.form.teleNo2,
						'remark': this.form.remark
				      })
				    }).then(({data}) => {
				      if (data && data.code === 0) {
						this.form.id  = data.id;
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
					  this.isDisabled= false;
				    })
				  }
				})
			},
			cascaderChange(e) {
				console.log(e[0]);
				console.log(e[1]);
			}

		}
	}
</script>

<style>
</style>
