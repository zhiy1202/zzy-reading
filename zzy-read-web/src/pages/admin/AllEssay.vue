<template>
  <div>
    <Search></Search>
    <el-table
    :data="allEssays"
    style="width: 100%">
    <el-table-column
      label="文章名"
      width="180"
      >
      <template slot-scope="scope">
        <span :id="scope.row.eid" style="margin-left: 10px">{{ scope.row.title }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="作者"
      width="180">
      <template slot-scope="scope">
        <el-popover trigger="hover" placement="top">
          <p>简介: {{ scope.row.intro }}</p>
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.author }}</el-tag>
          </div>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="updateEssay(scope.$index, scope.row)">修改文章</el-button>
          <el-button
          size="mini"
          @click="handleTopis(scope.$index, scope.row)">添加题目</el-button>
          <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
          <el-button size="small" type="primary" @click="sendMedioEid(scope.$index, scope.row)">上传视频</el-button>
        </el-upload>
      </template>
    </el-table-column>
  </el-table>
  </div>
</template>

<script>
import {mapGetters,mapActions} from 'vuex';
import axios from 'axios';
import Search from '../.././components/Seach.vue'
  export default {
    data() {
      return {   
        eid:0  
      }
    },
    components: {Search},
    computed:{
        ...mapGetters(['allEssays'])
    },
    methods: {
      sendMedioEid(index,row){
      this.eid = row.eid;
    },
      updateEssay(index, row){
        this.updateDetail(row.eid)
        this.$router.push({
          path: `/admin/UpdateEssay/${row.eid}`
        })
      },
      handleTopis(index, row) {
        this.$router.push({
          path: `/admin/inserttopis/${row.eid}`
        })
      },
      beforeRemove(){

      },
      //处理删除
      handleDelete(index, row) {
        axios.delete(`http://localhost:8081/admin/deleteessay/${row.eid}`).then(res=>{
          if(res.data.state ==200) {
            alert(res.data.message);
            this.getAllEssays();
          }
        })
      },
      //视频处理
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
      //end
      ...mapActions(['getAllEssays','updateDetail'])
      
    }
  }
</script>
<style scoped>
.upload-demo{
  display: inline-block;
  margin-left: 10px;
  border-radius:10px;
}
</style>