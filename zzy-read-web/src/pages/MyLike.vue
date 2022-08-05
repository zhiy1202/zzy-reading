  <template>
      <el-table
      :data="userLikes"
      style="width: 100%">
      <el-table-column
        prop="title"
        label="文章名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="author"
        label="作者">
      </el-table-column>
      <el-table-column
        prop="intro"
        label="简洁">
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="日期"
        width="180">
      </el-table-column>
      <el-table-column
        label="操作"
        width="180"
        >
        <template slot-scope="scope">
          <el-button type="success" size="mini" @click="goPage(scope.row.eid)"  round>前往</el-button>
          <el-button type="danger"  size="mini" @click="delLike(scope.row.eid)" round>取消收藏</el-button>
        </template>
      </el-table-column>
    </el-table>

  </template>

<script>
import {mapGetters,mapActions} from 'vuex';
import axios from 'axios';
  export default {
    computed:{
      ...mapGetters(['userLikes'])
    },
    methods: {
      goPage(eid){
        console.log(eid)
        this.updateDetail(eid);
        this.$router.push({
          path:`/essaydetail/${eid}`
        })
      },
      delLike(eid){
        console.log(eid)
        axios.delete(`http://localhost:8081/delUserLike/${eid}`).then(res=>{
          console.log(res.data)
          if(res.data.state === 200){
            this.updateUserLikes(this.$cookies.get('uid'))
          }
        })
      },
    ...mapActions(['updateDetail','updateUserLikes'])
    },
  }
</script>