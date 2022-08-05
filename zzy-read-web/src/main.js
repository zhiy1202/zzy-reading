import Vue from 'vue'
import App from './App.vue'
import VueCookies from "vue-cookies";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(VueCookies);

import store from './store'
import router from './router'
import axios from 'axios';
axios.defaults.withCredentials = true

Vue.config.productionTip = false
Vue.use(ElementUI);

new Vue({
  router,
  store,
  render: h => h(App),
  created(){
    router.beforeEach((to,form,next)=>{
      if (to.path === '/' || to.path === '/register' || to.path === '/admin') {
          next();
      }else{
          if (this.$cookies.get('uid') && this.$cookies.get('user') || this.$cookies.get('admin')) {
              next();
          }else{
            alert('请登录')
          }
      }
      if(to.path === '/admin/insertessay' || to.path === '/admin/allessay' || to.path === '/admin/allmedio' || to.path === '/admin/alltopic' || to.path === '/admin/inserttopis' || to.path === '/admin/updateEssay'){
        if(this.$cookies.get('admin')){
          next();
        }else{
          alert('没有权限')
          this.$router.push({
            path: '/admin'
          })
        }
      }
  })
  }
}).$mount('#app')
