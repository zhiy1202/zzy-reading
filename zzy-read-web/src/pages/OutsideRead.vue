<template>
<div>
  <el-descriptions title="文章信息" border="true" @click.native="getDetail(item.eid)" v-for="item in out" :key="item.eid">
    <el-descriptions-item label="文章名">{{item.title}}</el-descriptions-item>
    <el-descriptions-item label="作者">{{item.author}}</el-descriptions-item>
    <el-descriptions-item label="分类">课外阅读</el-descriptions-item>
    <el-descriptions-item label="简洁">
      <el-tag size="small">{{item.intro}}</el-tag>
    </el-descriptions-item>
  </el-descriptions>     
</div>  
</template>
<script>
import axios from 'axios';
import {mapActions} from 'vuex';
export default{
    name: "OutsideRead",
    data(){
      return {
        out:{}
      }
    },
    methods:{
      getDetail(essayId){
        const router = this.$router
        router.push({
          path: `/essaydetail/${essayId}`
        })
      this.updateDetail(essayId);
      },
      ...mapActions(['updateEssay','updateDetail'])
    },
    mounted(){
      axios({
        method:'get',
        url:"http://localhost:8081/getlist/out"
      }).then((response) => {
        this.updateEssay(response.data.data)
        this.out = this.$store.state.essay
      })
    }
}
</script>