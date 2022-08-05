<template>
  <el-table
    :data="allMedio"
    style="width: 100%">
    <el-table-column
      label="文章教学视频"
      width="180">
      <template slot-scope="scope">
        <i class="el-icon-time"></i>
        <span style="margin-left: 10px">{{ scope.row.p1 }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="作者"
      width="180">
      <template slot-scope="scope">
        <el-popover trigger="hover" placement="top">
          <p>姓名: {{ scope.row.p3 }}</p>
          <p>住址: {{ scope.row.p4 }}</p>
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.p2 }}</el-tag>
          </div>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import {mapGetters,mapActions} from 'vuex';
import axios from 'axios';
  export default {
    data() {
      return {
        tableData: []
      }
    },
    computed: {
      ...mapGetters(['allMedio'])
    },
    methods: {
      handleEdit(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        axios.delete(`http://localhost:8081/admin/delmedio/${row.p5}`).then(res => {
          if (res.data.state === 200) {
            alert(res.data.message)
            this.getAllMedio()
          }
        })
      },
      ...mapActions(['getAllMedio'])
    }
  }
</script>