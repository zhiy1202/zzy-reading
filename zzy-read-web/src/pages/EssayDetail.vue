<template>
  <el-descriptions class="margin-top" title="文章详情" :column="2" :size="medium">
    <template slot="extra">
      <el-button type="primary" size="small" @click.native="setLike(detail.eid)">收藏</el-button>
      <el-button type="primary" size="small" v-if="detail.mid" @click.native="goMedio(detail.eid)">视频讲解</el-button>
      <el-button type="primary" size="small" v-if="detail.hid" @click.native="study(detail.eid)">课后习题</el-button>
    </template>
    <el-descriptions-item label="文章名">{{detail.title}}</el-descriptions-item>
    <el-descriptions-item label="作者">
      <el-tag size="small">{{detail.author}}</el-tag>
    </el-descriptions-item>
    <el-descriptions-item label="正文">
      <br>
      <br>
      <div class="content">
        <pro>{{detail.content}}</pro>
      </div>
    </el-descriptions-item>
  </el-descriptions>
</template>

<script>
import axios from "axios";
import {mapActions,mapGetters} from "vuex"
  export default {
    data () {
      return {
        size: '15px',
      };
    },
    computed:{
      ...mapGetters(['detail'])
    },
    methods: {
      setLike(eid){
        axios({
          url: `http://localhost:8081/setlike/${eid}`,
          method: 'post'
        }).then((resp)=>{
          const data = resp.data
          if(data.state === 200){ 
            alert(data.message);
          }else{
            alert(data.message);
          }
        })
      },
      goMedio(eid){
        console.log(eid);
        axios({
          url: `http://localhost:8081/getMedio/${eid}`,
        }).then((resp)=>{
          if (resp.data.state === 200) {
            window.open(resp.data.data, '_blank');
          }
        })
      },
      study(eid){
        // 发送请求 是否写过
        // 发送请求是否已写
        axios({
        url: `http://localhost:8081/result/${eid}`,
        method:'get'
      }).then((res)=>{
        const data = res.data
        if(data.state === 200){
          // 获取题目信息
          this.updateHomeWork(eid)
          //跳转页面
          this.$router.push({
          path:`/homework/${eid}`
        })
        }else{
          alert(res.data.message)
        }
      })
      },
      ...mapActions(['updateDetail','updateHomeWork'])
    },

  }
</script>
<style scoped>
.content {
  width: 70%;
  font-size: 24px;
}

</style>