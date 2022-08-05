 <template>
    <div>
        <el-table
            :data="allTopic"
            style="width: 100%">
            <el-table-column
                prop="topic"
                label="文章名"
                width="180">
                <template  slot-scope="scope">
                    <el-input v-if="scope.row.updata" v-model="scope.row.topic" placeholder="请输入内容" @blur="updateOption(scope.row.hid,scope.row.topic,'topic')"></el-input>
                    <span v-if="!scope.row.updata">{{scope.row.topic}}</span>
                </template>
            </el-table-column>

            <el-table-column
                prop="optionA"
                label="选项A"
                width="180">
                <template  slot-scope="scope">  
                    <el-input v-if="scope.row.updata" v-model="scope.row.optionA" placeholder="请输入内容" @blur="updateOption(scope.row.hid,scope.row.optionA,'optionA')"></el-input>
                    <span v-if="!scope.row.updata">{{scope.row.optionA}}</span>
                </template>
            </el-table-column>

            <el-table-column
                prop="optionB"
                label="选项B"
                width="180">
                <template  slot-scope="scope">
                    <el-input v-if="scope.row.updata" v-model="scope.row.optionB" placeholder="请输入内容" @blur="updateOption(scope.row.hid,scope.row.optionB,'optionB')"></el-input>
                    <span v-if="!scope.row.updata">{{scope.row.optionB}}</span>
                </template>
            </el-table-column>

            <el-table-column
                prop="optionC"
                label="选项C"
                width="180">
                <template  slot-scope="scope">
                    <el-input v-if="scope.row.updata" v-model="scope.row.optionC" placeholder="请输入内容" @blur="updateOption(scope.row.hid,scope.row.optionC,'optionC')"></el-input>
                    <span v-if="!scope.row.updata">{{scope.row.optionC}}</span>
                </template>
            </el-table-column>

            <el-table-column
                prop="optionD"
                label="选项D"
                width="180">
                <template  slot-scope="scope">
                    <el-input v-if="scope.row.updata" v-model="scope.row.optionD" placeholder="请输入内容" @blur="updateOption(scope.row.hid,scope.row.optionD,'optionD')"></el-input>
                    <span v-if="!scope.row.updata">{{scope.row.optionD}}</span>
                </template>
            </el-table-column>

            <el-table-column
                prop="answer"
                label="答案"
                width="180">
                <template  slot-scope="scope">
                    <el-input v-if="scope.row.updata" v-model="scope.row.answer" placeholder="请输入内容" @blur="updateOption(scope.row.hid,scope.row.answer,'answer')"></el-input>
                    <span v-if="!scope.row.updata">{{scope.row.answer}}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作">
            <template slot-scope="scope">
                <el-button
                size="mini"
                type="primary"
                @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
            </el-table-column>

        </el-table>
    </div>
  </template>

  <script>
    import {mapGetters,mapActions} from 'vuex';
    import axios from 'axios';
    export default {
        data() {
            return{
                topics:[]
            }
        },
        computed: {
            ...mapGetters(['allTopic'])
        },
        methods: {
            updateOption(hid,value,option){
                axios.put(`http://localhost:8081/admin/updateOption/${hid}/${option}/${value}`).then(res=>{
                    console.log(res.data)
                })
            },
            handleEdit(index,data){
                data.updata = !data.updata;
            },
            handleDelete(index,row){
                axios.delete(`http://localhost:8081/admin/deletetopic/${row.hid}`).then(res=>{
                    if(res.data.state == 200){
                        this.getAllTopic();
                        alert(res.data.message)
                    }
                })

            },
            ...mapActions(['getAllTopic'])
        }
        
    }
  </script>