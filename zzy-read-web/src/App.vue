<template>
  <el-container describedat="vertical">
    <el-header height="300px" width="100%">
        <MyHeader></MyHeader>
    </el-header>
  <el-container>
    <el-aside>
      <MyAside v-if="!isAdmin"></MyAside>
      <AdminAside v-if="isAdmin"></AdminAside>
    </el-aside>
    <el-main width="100%">
      <router-view></router-view>
    </el-main>
  </el-container>
</el-container>
</template>

<script>
import MyHeader from "./components/MyHeader.vue"
import MyAside from "./components/MyAside.vue"
import AdminAside from "./components/admin/AdminAside.vue"
import {mapGetters} from 'vuex';
export default {
  name: 'App',
  data() {
    return {
      
    }
  },
  computed:{
    ...mapGetters(['isAdmin'])
  },
  components: {
    MyHeader,
    MyAside,
    AdminAside
  },
   created() {
    //在页面加载时读取sessionStorage里的状态信息
    if (window.localStorage.getItem("list") ) {
        this.$store.replaceState(Object.assign({}, this.$store.state,JSON.parse(window.localStorage.getItem("list"))))
    } 

  window.addEventListener("beforeunload",()=>{
          window.localStorage.setItem("list",JSON.stringify(this.$store.state))
      })
    }
}
</script>

<style>
</style>
