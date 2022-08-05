<template>
    <div class="container">
        <el-form :model="detail" label-width="80px">
        <el-form-item label="文章名">
            <el-input v-model="detail.title"></el-input>
        </el-form-item>
        <el-form-item label="作者">
            <el-input v-model="detail.author"></el-input>
        </el-form-item>
        <el-form-item label="简介">
            <el-input v-model="detail.intro"></el-input>
        </el-form-item>
        <el-form-item label="分类">
            <el-radio-group v-model="detail.label">
            <el-radio label="xiaoxue">小学</el-radio>
            <el-radio label="chuzhong">初中</el-radio>
            <el-radio label="gaozhong">高中</el-radio>
            <el-radio label="out">课外阅读</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item label="内容">
            <el-input type="textarea" v-model="detail.content"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit()">提交修改</el-button>
            <el-button type="primary" @click="handleTopis(detail.eid)">添加题目</el-button>
            <el-button
            size="mini"
            type="danger"
            @click="handleDelete(detail.eid)">删除</el-button>
            <el-upload
            class="upload-demo"
            action="http://localhost:8081/admin/upload"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            :data="{eid:eid}"
            :on-success="handleSuccess"
            :on-error="handleError"
            :with-credentials='true'
            multiple
            :limit="1"
            :on-exceed="handleExceed">
            <el-button size="small" type="primary" @click="sendMedioEid(detail.eid)">上传视频</el-button>
          </el-upload>
        </el-form-item>
    </el-form>
    </div>
</template>
<script>
import {mapGetters,mapActions} from 'vuex';
import axios from 'axios';
  export default {
    data() {
      return {
        eid:0,
        essay: {
          title: '',
          author: '',
          intro: '',
          label: '',
          content: ''
        }
      }
    },
    computed: {
        ...mapGetters(['detail'])
    },
    methods: {
      ...mapActions(['getAllEssays']),
      //处理删除
      handleDelete(eid) {
        console.log(eid);
        axios.delete(`http://localhost:8081/admin/deleteessay/${eid}`).then(res=>{
          console.log(res.data);
          if(res.data.state ==200) {
            alert(res.data.message);
            this.getAllEssays();
            this.$router.push({
              path: '/admin/allessay'
            })
          }
        })
      },
      //视频处理
      sendMedioEid(eid){
        this.eid = eid;
      },
      beforeRemove(){

      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
        axios.delete(`http://localhost:8081/admin/delmedio/${this.eid}`).then(res => {
          console.log(res);
        })
      },
      handlePreview(file) {
        console.log(file);
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      handleSuccess(res){
        if (res.state === 200) {
          alert(res.message);
        }else {
          alert(res.message)
        }
      },
      handleError(err){
        console.log(err);
      },
      //添加题目
      handleTopis(eid) {
        console.log(eid);
        this.$router.push({
          path: `/admin/inserttopis/${eid}`
        })
      },
      //得到表单提交数据
      getTableData() {
        let formdata = new FormData();
        // formdata.append('eId',eid)
        formdata.append('title',this.detail.title)
        formdata.append('author',this.detail.author)
        formdata.append('intro',this.detail.intro)
        formdata.append('label',this.detail.label)
        formdata.append('content',this.detail.content)
        return formdata;
      },
      //提交修改
      onSubmit() {
        let tableData = this.getTableData();
        let eid = this.$route.params.eid
        axios({
          url: `http://localhost:8081/admin/updateEssay/${eid}`,
          method: 'put',
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