<template>
	<!-- 最新资讯 -->
	<div class="content">
		<WebHeader v-bind:msg="message" />

		<el-breadcrumb separator-class="el-icon-arrow-right">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item>最新资讯</el-breadcrumb-item> 
		</el-breadcrumb>

		
		<Footer></Footer>
	</div>



</template>


<script>
	import WebHeader from '@/components/WebHeader.vue'
	import Footer from '@/components/Footer.vue'

	export default {
		name: "latestNews",
		data() {
			return {
				activeIndex: '1',
				activeIndex2: '1',
				contentStyleObj: window.innerHeight - 260 + "px"
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
	}
	
	
</style>
