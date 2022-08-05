<template>
<div>
   <el-button type="primary" @click="getData">打卡</el-button>
  <el-calendar>
    <template
      slot="dateCell"
      slot-scope="{data}">
      <p>
        {{ data.day.split('-').slice(2).join('-') }} </p>
        <div v-for="item in allUserLock" :key="item.hlId">
            <p v-if="item.lockTime == data.day">已打卡</p>
            
            
        </div>
    </template>
  </el-calendar>
</div>
</template>
<script>
import dayjs from 'dayjs';
import axios from 'axios';
import {mapGetters,mapActions} from 'vuex';
  export default {
    data(){
      return {
      }
    },
    computed: {
      ...mapGetters(['allUserLock'])
    },
    methods: {
      getData(){
        let time = dayjs().format("YYYY-MM-DD");
        let d = new FormData();
        d.append("lockTime",time);
        axios({
          url: 'http://localhost:8081/userlock',
          method: 'post',
          data:d,
          headers: {'Content-Type': 'application/form-data'}
        }).then(res=>{
          if (res.data.state === 200) {
            alert(res.data.message)
            this.getAllUserLock()
          }else{
            alert(res.data.message)
          }
        })
      },
      ...mapActions(['getAllUserLock'])
    }
  }
</script>

<style>
  .is-selected {
    color: #1989FA;
  }
</style>