<template>
	<div class="header">
		<ul>
			<li><a href='/'>首页</a></li>
			<li><a href='/latestNews'>最新资讯</a></li>
			<li><a href="/latestNews">三位一体</a></li>
		</ul>
	</div>
</template>

<script>
	export default {
		name: 'WebHeader',
		props: {
			msg: String
		}
	}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	h3 {
		margin: 40px 0 0;
	}

	ul {
		list-style-type: none;
		padding: 0;
	}

	li {
		display: inline-block;
		margin: 0 10px;
	}

	a {
		color: #42b983;
	}
</style>
