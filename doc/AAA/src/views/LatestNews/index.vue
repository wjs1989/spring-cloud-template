<template>
	<!-- 最新资讯 -->
	<div class="content" >
		<WebHeader v-bind:msg="message"/>  
		
		<h3>最新资讯</h3>
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