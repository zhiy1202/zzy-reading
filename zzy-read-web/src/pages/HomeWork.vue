<template>
<ol>
    <li v-for="item in homework" :key="item.hid">
        <div>
            {{item.topic}}
        </div>
        <div class="content">
            <el-form :item="item">
                <el-form-item :prop="item.update">
                    <el-radio-group v-model="item.update"  @change="sendValue(item.update,item.hid)">
                        <el-radio :label="item.optionA" >A:{{item.optionA}}</el-radio>
                        <el-radio :label="item.optionB">B:{{item.optionB}}</el-radio>
                        <el-radio :label="item.optionC">C:{{item.optionC}}</el-radio>
                        <el-radio :label="item.optionD">D:{{item.optionD}}</el-radio>
                    </el-radio-group>
                </el-form-item>
            </el-form>
            
            <h3 v-if="isShow">答案为: {{item.answer}}</h3>
            <h4 v-if="isShow">{{hw.true ? "正确" : "错误"}}</h4>
        </div>
    </li>
    <h2 v-if="isShow">成绩: {{result.result}}</h2>
    <el-button type="success" @click.native="changeShow(hw.eid)">提交</el-button>
</ol>
</template>
<script>
import {mapGetters} from 'vuex';
import axios from 'axios';
export default {
    name:'HomeWork',
    data () {
      return {
        isShow: false,
        hw:{}, 
        result:{}

      };
    },
    computed:{
        ...mapGetters(['homework'])
    },
    methods: {
        changeShow(eid){
            // 查找homeworkresult 这篇文章用户是否还有权限修改
            axios({
                url: `http://localhost:8081/result/${eid}`,
                method: 'put'
            }).then((response)=>{
                this.result = response.data.data
                this.isShow=true
            })
            // 能修改 发送 homework请求  并修改homeworkresult中的is_update

            // 不能提示用户 题目已做过
            
        },
        sendValue(value,hid){
            console.log(value,hid);
            axios({
                url:`http://localhost:8081/homework/${hid}/${value}`,
                method:'get'
            }).then((response) => {
                this.hw = response.data.data
            })
        }
    }

}
</script>

<style scoped>
.content{
    margin-top: 25px;
}
ol li{
    margin-top: 30px;
}
</style>