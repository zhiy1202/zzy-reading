<template>
<div>
      <el-collapse accordion  v-for="item in readhistorys" :key="item.hid">
        <el-collapse-item :title="item.title" :name="item.hid">
            <div>作者:{{item.author}}</div>
            <div>文章简介: {{item.intro}}</div>
            <div>时间: {{item.createTime}}</div>
            <div class="bar">链接: <Button @click="goPage(item.eid)">跳转</Button></div>
            <div class="bar"><Button @click="delHistory(item.eid)">删除记录</Button></div>
        </el-collapse-item>
    </el-collapse>
</div>
</template>
<script>
import {mapGetters,mapActions} from 'vuex';
import axios from 'axios';
  export default {
    data() {
      return {}
    },
    computed:{
      ...mapGetters(['readhistorys'])
    },
    methods: {
      delHistory(eid){
        axios.delete(`http://localhost:8081/delHistory/${eid}`).then(res=>{
        console.log(res.data);
        if (res.data.state === 200) {
          alert(res.data.message)
          this.updateUserHistorys(this.$cookies.get('uid'));
        }
        })
      },
      goPage(eid){
        this.$router.push({
          path: `/essaydetail/${eid}`
        })
        this.updateDetail(eid)
      },
      ...mapActions(['updateDetail','updateUserHistorys'])
    }
  }
</script>
<style scoped>
  .bar{
    display: inline-block;
    margin-left: 5px;
  }
</style>