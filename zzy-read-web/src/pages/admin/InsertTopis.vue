<template>
    <div class="container">
        <el-form :model="topics" label-width="80px">
        <el-form-item label="题目">
            <el-input v-model="topics.topic"></el-input>
        </el-form-item>
        <el-form-item label="选项A">
            <el-input v-model="topics.optionA"></el-input>
        </el-form-item>
        <el-form-item label="选项B">
            <el-input v-model="topics.optionB"></el-input>
        </el-form-item>
        <el-form-item label="选项C">
            <el-input v-model="topics.optionC"></el-input>
        </el-form-item>
        <el-form-item label="选项D">
            <el-input v-model="topics.optionD"></el-input>
        </el-form-item>
        <el-form-item label="答案">
            <el-input v-model="topics.answer"></el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="onSubmit">立即创建</el-button>
        </el-form-item>
    </el-form>
    </div>
</template>
<script>
import axios from 'axios';
  export default {
    data() {
      return {
        topics: {
          topic: '',
          optionA: '',
          optionB: '',
          optionC: '',
          optionD: '',
          answer: ''
        }
      }
    },
    methods: {
      getTableData(eid){
        let tableData = new FormData();
        tableData.append('eId',eid)
        tableData.append('topic',this.topics.topic)
        tableData.append('optionA',this.topics.optionA)
        tableData.append('optionB',this.topics.optionB)
        tableData.append('optionC',this.topics.optionC)
        tableData.append('optionD',this.topics.optionD)
        tableData.append('answer',this.topics.answer)
        return tableData;
      },
      onSubmit() {
        let eid = this.$route.params.eid
        let tabledata = this.getTableData(eid)
        axios({
          method:'post',
          url: `http://localhost:8081/admin/inserthomework`,
          data: tabledata,
           headers: {'Content-Type': 'application/form-data'}
        }).then((res) =>{
          if(res.data.state === 200){
            alert("添加题目成功")
          }
        })
      },
    }
  }
</script>
<style scoped>
.container{
    width:50%;
    margin:50px  auto;

}
 .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>