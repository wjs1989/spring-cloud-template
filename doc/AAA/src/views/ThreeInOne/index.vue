<template>
	<!-- 三位一体 -->
	<div class="content">
		<WebHeader v-bind:msg="message" />

		<el-breadcrumb separator-class="el-icon-arrow-right">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item>三位一体</el-breadcrumb-item>
		</el-breadcrumb>

		<div class="middle-content">
			<div class="inline-table">
				<span class="main-title">三位一体
					<span style="font-size: 0.25rem;color: #CCC;">以下数据查询，均以2020年标准为准</span>
				</span>
			</div>
			<div class="inline-table">
				<span style="font-size: 0.25rem;">选中科目<span style="font-size: 0.25rem;color: #CCC;">(至少一项，至多3项 )</span></span>
				<el-checkbox-group v-model="checkboxGroup1" fill="red" size="mini">
					<el-checkbox-button v-for="city in cities" :label="city" :key="city">{{city}}</el-checkbox-button>
				</el-checkbox-group>
			</div>
			<div class="inline-table">
				<span style="font-size: 0.25rem;">学科等级：</span>

				<div style="display: inline-table;padding-left: 20px;">
					<div>
						<span style="padding:0 5px;">语文</span>
						<el-select v-model="selectValue" placeholder="请选择" size="mini" style="width: 80px;padding: 0 10px;">
							<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
						</el-select>

						<span style="padding:0 5px;">数学</span>
						<el-select v-model="selectValue" placeholder="请选择" size="mini" style="width: 80px;padding:0 10px;">
							<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
						</el-select>

						<span style="padding:0 5px;">物理</span>
						<el-select v-model="selectValue" placeholder="请选择" size="mini" style="width: 80px;padding:0 10px;">
							<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
						</el-select>

						<span style="padding:0 5px;">化学</span>
						<el-select v-model="selectValue" placeholder="请选择" size="mini" style="width: 80px;padding:0 10px;">
							<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
						</el-select>

						<span style="padding:0 5px;">技术</span>
						<el-select v-model="selectValue" placeholder="请选择" size="mini" style="width: 80px;padding:0 10px;">
							<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
						</el-select>
					</div>
					<div style="margin-top: 0.625rem;">

						<span style="padding:0 5px;">生物</span>
						<el-select v-model="selectValue" placeholder="请选择" size="mini" style="width: 80px;padding:0 10px;">
							<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
						</el-select>

						<span style="padding:0 5px;">历史</span>
						<el-select v-model="selectValue" placeholder="请选择" size="mini" style="width: 80px;padding:0 10px;">
							<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
						</el-select>

						<span style="padding:0 5px;">地理</span>
						<el-select v-model="selectValue" placeholder="请选择" size="mini" style="width: 80px;padding:0 10px;">
							<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
						</el-select>

						<span style="padding:0 5px;">政治</span>
						<el-select v-model="selectValue" placeholder="请选择" size="mini" style="width: 80px;padding:0 10px;">
							<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
						</el-select>
					</div>
				</div>
			</div>

			<div class="inline-table">
				<span style="font-size: 0.25rem;">特长生：</span>

				<div style="display: inline-table;padding-left: 20px;">
					<el-radio-group v-model="specialty" fill="red" size="mini">
						<el-radio-button label="是"></el-radio-button>
						<el-radio-button label="否"></el-radio-button>
					</el-radio-group>
					<p style="margin-top: 10px;color: #ccc;font-size: 10px;">注：xxxxxxxxxxxxxxxxxxxxxxxx</p>
				</div>
			</div>

			<div class="inline-table">
				<span style="font-size: 0.25rem;">校推荐：</span>
				<div style="display: inline-table;padding-left: 20px;">
					<el-radio-group v-model="recommend" fill="red" size="mini">
						<el-radio-button label="是"></el-radio-button>
						<el-radio-button label="否"></el-radio-button>
					</el-radio-group>
					<p style="margin-top: 10px;color: #ccc; font-size: 10px;">注：xxxxxxxxxxxxxxxxxxxxxxxx</p>
				</div>
			</div>

			<el-row :gutter="20">
				<el-col :span="4" offset="7" size="medium">
					<div class="grid-content bg-purple">
						<el-button type="primary" style="width: 100px;">查询</el-button>
					</div>
				</el-col>
				<el-col :span="4" size="medium">
					<div class="grid-content bg-purple">
						<el-button style="width: 100px;">重置</el-button>
					</div>
				</el-col>
			</el-row>

			<div class="inline-table">
				<span style="font-size: 0.25rem;"></span>
				<div style="display: inline-table;padding-left: 20px;">
					<p style="margin-top: 10px;color: #ccc; font-size: 10px;">注：xxxxxxxxxxxxxxxxxxxxxxxx</p>
				</div>
			</div>

			<div style="margin-top: 3.125rem;">  
				<el-table
				      :data="tableData"
				      style="width: 100%">
				      <el-table-column
				        prop="date"
				        label=""
				        width="180">
				      </el-table-column>
				      <el-table-column
				        prop="name"
				        label=""
				        width="180">
				      </el-table-column>
				      <el-table-column
				        prop="address"
				        label="">
				      </el-table-column>
				    </el-table>
			</div>
			<el-pagination
			  small
			  layout="prev, pager, next"
			  :total="50">
			</el-pagination>
			
			<div style="height: 1.25rem;"></div>
		</div>
		<Footer></Footer>
	</div>
</template>


<script>
	import WebHeader from '@/components/WebHeader.vue'
	import Footer from '@/components/Footer.vue'

	export default {
		name: "threeInOne",
		data() {
			return {
				activeIndex: '1',
				activeIndex2: '1',
				checkboxGroup1: ['物理', '化学', '生物'],
				cities: ['物理', '化学', '生物', '技术', '历史', '政治', '地理'],
				options: [{
					value: 'A',
					label: 'A'
				}, {
					value: 'B',
					label: 'B'
				}, {
					value: 'C',
					label: 'C'
				}, {
					value: 'D',
					label: 'D'
				}, {
					value: 'E',
					label: 'E'
				}],
				selectValue: 'A',
				specialty: "否", //特长生
				recommend: "否", //推荐
				circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
				 tableData: [{
				            date: '2016-05-02',
				            name: '王小虎',
				            address: '上海市普陀区金沙江路 1518 弄'
				          }, {
				            date: '2016-05-04',
				            name: '王小虎',
				            address: '上海市普陀区金沙江路 1517 弄'
				          }, {
				            date: '2016-05-01',
				            name: '王小虎',
				            address: '上海市普陀区金沙江路 1519 弄'
				          }, {
				            date: '2016-05-03',
				            name: '王小虎',
				            address: '上海市普陀区金沙江路 1516 弄'
				          },{
				            date: '2016-05-02',
				            name: '王小虎',
				            address: '上海市普陀区金沙江路 1518 弄'
				          }, {
				            date: '2016-05-04',
				            name: '王小虎',
				            address: '上海市普陀区金沙江路 1517 弄'
				          }, {
				            date: '2016-05-01',
				            name: '王小虎',
				            address: '上海市普陀区金沙江路 1519 弄'
				          }, {
				            date: '2016-05-03',
				            name: '王小虎',
				            address: '上海市普陀区金沙江路 1516 弄'
				          }],
			};
		},
		components: {
			WebHeader,
			Footer
		},
		methods: {
			getHeight() {
				// 获取浏览器高度  768 1080一般笔记本浏览器高度
				if (window.innerHeight < 768 || window.innerHeight < 1080) {
					this.contentStyleObj.height = window.innerHeight - 260 + "px";
				} else {
					this.contentStyleObj.height = "auto";
				}
			},
			handleSelect(key, keyPath) {
				console.log(key, keyPath);
			}
		},
		created() {
			// 监听浏览器高度事件
			// window.addEventListener("resize", this.getHeight);
			// // 执行浏览器高度事件
			// this.getHeight();
		},
		destroyed() {
			// 页面一关闭，销毁监听事件
			//window.removeEventListener("resize", this.getHeight);
		}

	};
</script>

<style>
	.content {
		min-height: 100%;
		margin: 10px 10px;
	}

	.middle-content {
		margin: 10px 5%;
		min-height: 18.75rem;
		background-color: #FFF;
		border-radius: 2px;
		box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
	}

	.main-title {
		font-size: 1.25rem;
	}

	.el-checkbox-button,
	.el-checkbox-button__inner {
		padding: 0 8px;
		border-radius: 4px;
		box-shadow: none !important;
	}

	.el-checkbox-button--mini .el-checkbox-button__inner {
		border-radius: 4px;
	}

	.el-checkbox-button__inner {
		border-left: 1px solid #dcdfe6;
		border-radius: 4px;
	}

	.el-checkbox-button:first-child .el-checkbox-button__inner {
		border-radius: 4px;
	}

	.el-checkbox-button:last-child .el-checkbox-button__inner {
		border-radius: 4px;
	}

	.el-checkbox-group {
		width: 80%;
		display: inline;
	}

	.inline-table {
		text-align: left;
		padding-top: 25px;
		padding: 10px 30px;
	}

	.el-button--primary {
		background-color: red;
	}
</style>
