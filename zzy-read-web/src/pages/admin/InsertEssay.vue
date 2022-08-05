<template>
    <div class="container">
        <el-form :model="essay" label-width="80px">
        <el-form-item label="文章名">
            <el-input v-model="essay.title"></el-input>
        </el-form-item>
        <el-form-item label="作者">
            <el-input v-model="essay.author"></el-input>
        </el-form-item>
        <el-form-item label="简介">
            <el-input v-model="essay.intro"></el-input>
        </el-form-item>
        <el-form-item label="分类">
            <el-radio-group v-model="essay.label">
            <el-radio label="xiaoxue">小学</el-radio>
            <el-radio label="chuzhong">初中</el-radio>
            <el-radio label="gaozhong">高中</el-radio>
            <el-radio label="out">课外阅读</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item label="内容">
            <el-input type="textarea" v-model="essay.content"></el-input>
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
        essay: {
          title: '',
          author: '',
          intro: '',
          label: '',
          content: ''
        }
      }
    },
    methods: {
      getTableData() {
        let formdata = new FormData();
        formdata.append('title',this.essay.title)
        formdata.append('author',this.essay.author)
        formdata.append('intro',this.essay.intro)
        formdata.append('label',this.essay.label)
        formdata.append('content',this.essay.content)
        return formdata;
      },
      onSubmit() {
        console.log('submit!');
        let tableData = this.getTableData();
        axios({
          url: 'http://localhost:8081/admin/insertessay',
          method: 'POST',
          data: tableData,
          headers: {'Content-Type': 'application/form-data'}
        }).then((res) =>{
          if (res.data.state === 200) {
            alert(res.data.message);
          }else {
            alert('...')
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