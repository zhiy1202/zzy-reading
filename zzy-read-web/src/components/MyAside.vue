<template>
    <el-row class="row">
    <el-col :span="12">
    <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      router>

      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>课内阅读</span>
        </template>
        <!-- 课内阅读 -->
        <el-menu-item-group>
          <template slot="title">文章</template>
          <el-menu-item index="/grade/xiaoxue" @click.native="getGradeData">小学</el-menu-item>
          <el-menu-item index="/grade/chuzhong" @click.native="getGradeData">初中</el-menu-item>
          <el-menu-item index="/grade/gaozhong" @click.native="getGradeData">高中</el-menu-item>
        </el-menu-item-group>
        
      </el-submenu>
      <!-- 课外阅读 -->
      <el-menu-item index="/grade/out" @click.native="getGradeData">
        <i class="el-icon-menu"></i>
        <span slot="title">课外阅读</span>
        
      </el-menu-item>
        <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-setting"></i>
          <span>我的</span>
        </template>
        <el-menu-item-group>
          <template slot="title">操作</template>
          <el-menu-item index="/readhistory" @click.native="readhistory">阅读历史</el-menu-item>
          <el-menu-item index="/mylike" @click.native="getUserLikes">我的收藏</el-menu-item>
        </el-menu-item-group>
        <!-- 资源视频 答题 -->
        <el-submenu index="1-4" @click.native="getAllStudyGrades">
          <template slot="title">我的学习</template>
          <el-menu-item index="/selectgrades">成绩查询</el-menu-item>
          <el-menu-item index="/calendar" @click.native="getALlLock">今日打卡</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-menu-item index="/myerror" @click.native="getMyErrors">
        <i class="el-icon-warning-outline"></i>
        <span slot="title">高频错题</span>
      </el-menu-item>
      <el-menu-item index="/">
        <i class="el-icon-user"></i>
        <span slot="title">登录</span>
      </el-menu-item>
      <el-menu-item index="/exit" @click='exit'>
        <i class="el-icon-switch-button"></i>
        <span slot="title">退出</span>
      </el-menu-item>
      <el-menu-item index="/admin" @click="changeIsAdmin">
        <i class="el-icon-s-custom"></i>
        <span slot="title">管理员</span>
      </el-menu-item>

    </el-menu>
  </el-col>
</el-row>
</template>
<script>
import {mapActions} from "vuex";
import axios from 'axios';
  export default {
      name:'MyAside',
      data () {
        return{
          essay:{}
        }
      },
        methods: {
          getALlLock(){
            this.getAllUserLock();
          },
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        }, 
        getGradeData(){
          let label = this.$route.params.label;
          this.updateEssay(label);
        },
        getUserLikes(){
          this.updateUserLikes(this.$cookies.get('uid'));
        },
        readhistory(){
          this.updateUserHistorys(this.$cookies.get('uid'))
        },
        getAllStudyGrades(){
          this.updateAllStudyGrades(this.$cookies.get('uid'));
        },
        getMyErrors(){
          this.updateMyErrors();
        },
        changeIsAdmin(){
          this.updateIsAdmin();
        },
        exit(){
          this.$cookies.remove('uid');
          this.$cookies.remove('user');

          axios({
            url: 'http://localhost:8081/exit',
            method: 'get'
          }).then((res)=>{
            console.log(res.data);
          })
        },
        ...mapActions(['updateEssay','updateUserLikes','updateUserHistorys','updateAllStudyGrades','updateMyErrors','updateIsAdmin','getAllUserLock'])
        },
        
        
  }
</script>

<style scoped>
    .el-col-12 {
    width: 100%;
}
</style>