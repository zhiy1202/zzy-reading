 <template>
    <div>
        <el-table
            :data="studyGrades"
            style="width: 100%">
            <el-table-column
                prop="p1"
                label="文章名"
                width="180">
            </el-table-column>
            <el-table-column
                prop="p2"
                label="作者"
                width="180">
            </el-table-column>
            <el-table-column
                prop="p4"
                label="得分">
            </el-table-column>
        </el-table>
    </div>
  </template>

  <script>
      import {mapGetters} from 'vuex';
    export default {
      computed:{
          ...mapGetters(['studyGrades'])
      }
    }
  </script>