<template>
    <div class="container">
      <h1>用户登录</h1>
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户名" prop="user">
                <el-input type="text" v-model="ruleForm.user" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="pass">
                <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass" v-if="ruleForm.isLogin">
                <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="registerForm()">注册</el-button>
                <el-button @click="loginForm()">登录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
import axios from "axios";
import {mapActions} from "vuex"
  export default {
    name:"Login",  
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        ruleForm: {
          pass: '',
          checkPass: '',
          user: '',
          isLogin:false
        },
        rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          user:[
              {validator: validatePass, trigger: 'blur'}
          ]
        }
      };
    },
    methods: {
      getFormData(){
        let formData = new FormData();
        formData.append('username',this.ruleForm.user);
        formData.append('password',this.ruleForm.pass);
        return formData;
      },
      registerForm() {
        this.ruleForm.isLogin = true;
        let formData = this.getFormData();
        if (this.ruleForm.user !== "" && this.ruleForm.pass !== "") {
          axios({
            method: 'post',
            url:'http://localhost:8081/register',
            data:formData
        }).then((response) => {
            if(response.data.state == 200){
                alert('注册成功请登录')
            }else{
                alert(response.data.message);
            }
        })
        }
        
      },
      loginForm() {
        this.ruleForm.isLogin=false;
        let formData = this.getFormData();
        if (this.ruleForm.user !== "" && this.ruleForm.pass !== "") {
          axios({
            method:'post',
            url:`http://localhost:8081/login`,
            data: formData,
            headers: {'Content-Type': 'application/form-data'},
        }).then((response) => {
            const data = response.data;
            if(data.state===200){
                this.updateEssay('xiaoxue')
                this.$router.push({
                    path:'/grade/xiaoxue'
                })
            }else{
                alert('error')
                this.ruleForm.user =""
                this.ruleForm.pass =""
                this.ruleForm.checkPass =""
            }
        })
        }
        
      },
      admiinForm(){
      },
      ...mapActions(['updateEssay'])
    }
  }
</script>
<style scoped>
.container {
    width:50%;
    margin:70px auto;
}
</style>