<template>
  <div class="bar">
      <el-input prefix-icon="el-icon-search" @blur="clear" @input="getEssay()" v-model="word" placeholder="请输入文章名..."></el-input>
      <ul class="list" v-if="word">
        <li v-for="item in data" :key="item.eid" @click="updateEssay(item.eid)">{{item.title}}</li>
      </ul>
    </div>
</template>

<script>
import {mapActions} from 'vuex';
import axios from 'axios';
export default {
    data() {
        return {
            word: '',
            data:[]
        }
    },
    methods:{
        getEssay(){
            console.log(this.word)
            axios({
                url: `http://localhost:8081/search/${this.word}`
            }).then(res=>{
                console.log(res.data.data);
                this.isShow = true;
                this.data = res.data.data
            })
        },
        clear(){
        },
        updateEssay(eid){
        console.log(eid);
        this.updateDetail(eid)
        this.$router.push({
          path: `/essaydetail/${eid}`
        })
      },
      ...mapActions(['updateDetail'])
    }
}
</script>
<style scoped>
  .bar{
    width: 30%;
    float: right;
    position: relative;
  }
  .list{
      list-style: none;
      position: absolute;
      height: auto;
      width: 100%;
      z-index: 99;
      margin : 0;
      padding: 0;
      background-color: wheat;
  }
  .list li{
      height: 40px;
      line-height:40px;
      border-bottom:1px solid black;
  }
  .list li:hover{
      background-color: gray;
      cursor: pointer;
  }
</style>

