<template>
  <div style="width: 100%">
    <UserSearch></UserSearch>
    <el-descriptions direction="vertical" size="small" @click.native="getDetail(item.eid)" title="文章信息" border v-for="item in essay" :key="item.id">
        <el-descriptions-item label-class-name="descbar" label="文章名">{{item.title}}</el-descriptions-item>
        <el-descriptions-item label-class-name="descbar" label="作者">{{item.author}}</el-descriptions-item>
        <el-descriptions-item label-class-name="descbar" label="阅读量">{{item.isRead}}</el-descriptions-item>
        <el-descriptions-item label="简洁" label-class-name="descbar">
          <el-tag size="small" label-class-name="descbar">
            {{item.intro}}
          </el-tag>
        </el-descriptions-item>
    </el-descriptions>  
  </div>     
</template>
<script>
import {mapActions,mapGetters} from 'vuex'
import axios from 'axios';
import UserSearch from '.././components/UserSearch';
export default{
    
    name: "Grade",
    components:{UserSearch},
    methods: {
      getDetail(essayId){
        const router = this.$router
        router.push({
          path: `/essaydetail/${essayId}`
        })
        axios({
          method:'post',
          url:`http://localhost:8081/adduserhistory/${essayId}`
        }).then((response)=>{
          console.log(response.data);
        })
      this.updateDetail(essayId);
      },
      ...mapActions(['updateEssay','updateDetail'])

    },
    computed:{
      ...mapGetters(['essay'])
    }

}
</script>
<style>
.descbar{
  width: 500px; 
}
table{
  width: 100%;
}
</style>