<template>
	<!-- 首页 -->
	<div class="content">
		<WebHeader v-bind:msg="message" />

		<div class="block">
			<el-carousel height="150px">
				<el-carousel-item v-for="item in 4" :key="item">
					<h3 class="small">{{ item }}</h3>
				</el-carousel-item>
			</el-carousel>
		</div>


		<div style="height: 1000px;;"></div>


		<Footer></Footer>
	</div>
</template>

<script>
	import WebHeader from '@/components/WebHeader.vue'
	import Footer from '@/components/Footer.vue'

	export default {
		name: "index",
		data() {
			return {
				// 版本号
				version: "3.3.0",
			};
		},
		components: {
			WebHeader,
			Footer
		},
		methods: {
			goTarget(href) {
				window.open(href, "_blank");
			},
		},
	};
</script>

<style>
	.content {
		min-height: 100%;
	}

	.el-carousel__item h3 {
		color: #475669;
		font-size: 14px;
		opacity: 0.75;
		line-height: 150px;
		margin: 0;
	}

	.el-carousel__item:nth-child(2n) {
		background-color: #99a9bf;
	}

	.el-carousel__item:nth-child(2n+1) {
		background-color: #d3dce6;
	}
</style>
