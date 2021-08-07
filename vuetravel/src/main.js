import Vue from 'vue'
import App from './App.vue'
import router from './router'
//加载和使用ElementUI框架
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);
//导入并使用axios
import axios from './http'
axios.defaults.baseURL = ''
Vue.prototype.$axios = axios

Vue.config.productionTip = false
import Chat from 'vue-beautiful-chat'
Vue.use(Chat)
new Vue({
    router,
    data: {},
    render: function(h) { return h(App) }
}).$mount('#app')