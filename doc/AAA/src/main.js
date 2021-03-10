import Vue from 'vue'
import App from './App.vue'
// import permission from './permission.js'
import router from './router'
import '@/element-ui'                         // api: https://github.com/ElemeFE/element
import '@/icons'                              // api: http://www.iconfont.cn/
import '@/element-ui-theme'

Vue.config.productionTip = false

new Vue({
	
  router,
  render: h => h(App),
}).$mount('#app')
