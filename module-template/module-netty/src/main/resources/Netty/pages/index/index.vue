<template>
	<view class="content">
		<image class="logo" src="/static/logo.png"></image>
		<view class="text-area">
			<text class="title">{{title}}</text>
		</view>
		<view class="padding flex flex-direction">
			<button class="cu-btn bg-grey lg">玄灰</button>
			<button class="cu-btn bg-red margin-tb-sm lg" @click="send()">嫣红</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello',
				ws: null
			}
		},
		onLoad() {
			this.$mp.page.$vm.WebSocketTest();
		},
		methods: {
			WebSocketTest: function(p) {
				var that = this;
				if ("WebSocket" in window) {
					// alert("您的浏览器支持 WebSocket!");

					// 打开一个 web socket
					var ws = new WebSocket("ws://localhost:8088/websocket");
					that.ws = ws;

					ws.onopen = function() {
						// Web Socket 已连接上，使用 send() 方法发送数据
						ws.send("发送数据");

					};

					ws.onmessage = function(evt) {
						that.title = evt.data; 
					};

					ws.onclose = function() {
						// 关闭 websocket
						alert("连接已关闭...");
					};
				} else {
					// 浏览器不支持 WebSocket
					alert("您的浏览器不支持 WebSocket!");
				}
			},

			send: function() {
				this.ws.send("发送数据");
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
</style>
