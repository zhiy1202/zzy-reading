<template>
    <div class="container">
        <h1>管理员登录</h1>
    <el-form status-icon  label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="pass">
            <el-input type="text" v-model="username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="checkPass">
            <el-input type="password" v-model="password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm(username,password)">Login</el-button>
        </el-form-item>
    </el-form>
    </div>
</template>
<script>
import axios from 'axios';
  export default {
    data() {
      return {
        username:"",
        password:""
        }
    },
    methods: {
      submitForm(username, password) {
        let formData = new FormData();
        formData.append('username',username);
        formData.append('password',password);
        axios({
          url: "http://localhost:8081/admin/login",
          method: "post",
          data: formData,
          headers: {'Content-Type': 'application/form-data'}
        }).then((res)=>{
          const data = res.data;
          if (data.state === 200) {
            this.$router.push({
              path: "/admin/insertessay"
            })
          }else{
            alert(data.message);
            this.username=''
            this.password=''
          }
        })
      },
    }
  }
</script>
<style scoped>
.container {
    width:50%;
    margin: 100px auto;
}
</style>