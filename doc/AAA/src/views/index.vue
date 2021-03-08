<template>
	<!-- 首页 -->
	<div class="content" >
		<WebHeader v-bind:msg="message"/>  
			<h3>首页</h3>
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
</style>
