<template>
	<div class="footer">
		<!-- 	<div class="left">
			
		</div>
		<div class="right">
			
		</div> -->

		<div class="warp foot">
			<div class="foot_nav">
				<a href="/" class="link">网站首页</a>
				| <a href='/latestNews' class="link">最新资讯</a>
				| <a href="/threeInOne" class="link">三位一体</a>

			</div>

			<div class="foot_copy">
				<p>Copyright © 2012-2028 某某网络 版权所有 </p>
				<p>电 话：400-123-4567 手 机：138-0000-0000 传 真：+86-123-4567 E-mail：admin@youweb.com</p>
				<p>地 址：XXXXXXXXXXX</p>
				<p><a href="http://www.miibeian.gov.cn/" rel="nofollow" class="link">粤IP**********</a></p>
			</div>

			<div class="erweima">
				<img src="../assets/qrcode.jpg" width="148" height="148">
				<p>扫一扫关注微信公众帐号</p>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'Footer',
		props: {
			qrcode: " "
		}
	}
</script>

<style>
	.footer {
		padding: 0;
		background-color: #3C3C3C;
		height: 260px;
		width: 100%; 
		color: #FFFFFF;

	}

	.warp {
		position: relative;
		height: 210px;
		width: 65%;
		margin: 15px auto;
		clear: both;
	}

	.foot_nav {
		position: absolute;
		top: 0;
		left: -10px;
		font-size: 14px;
	}

	.foot_copy {
		position: absolute;
		top: 80px;
		left: 0;
		font-size: 14px;
		line-height: 30px;
	}

	.erweima {
		position: absolute;
		top: 18px;
		right: 0px;
		font-size: 14px;
		line-height: 40px;
	}

	p {
		margin: 0;
		padding: 0;
		text-align: left;
	}

	.link {
		color: #FFF;
		padding: 0 10px;
		text-decoration: none;
	}
</style>
