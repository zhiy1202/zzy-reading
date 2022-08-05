 <template>
    <div>
        <el-table
            :data="myErrors"
            style="width: 100%">
            <el-table-column
                prop="topic"
                label="文章名"
                width="180">
            </el-table-column>
            <el-table-column
                prop="optionA"
                label="选项A"
                width="180">
            </el-table-column>
            <el-table-column
                prop="optionB"
                label="选项B"
                width="180">
            </el-table-column>
            <el-table-column
                prop="optionC"
                label="选项C"
                width="180">
            </el-table-column>
            <el-table-column
                prop="optionD"
                label="选项D"
                width="180">
            </el-table-column>
            <el-table-column
                prop="answer"
                label="答案"
                width="180">
            </el-table-column>

        </el-table>
    </div>
  </template>

  <script>
      import {mapGetters} from 'vuex';
    export default {
      computed:{
          ...mapGetters(['myErrors'])
      }
    }
  </script>