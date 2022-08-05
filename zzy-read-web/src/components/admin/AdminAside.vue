 <template>
 <div class="container">
     <el-row>
            <el-col :span="12">
            <h2>管理员操作</h2>
            <el-menu
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            router>
            <el-submenu index="1">
                <template slot="title">
                <i class="el-icon-location"></i>
                <span>经典文学作品资料管理</span>
                </template>
                <el-menu-item-group>
                <template slot="title">操作</template>
                <el-menu-item index="/admin/insertessay">新增</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="文件">
                <el-menu-item index="/admin/allessay" @click="getAllEssay">所有文章</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <el-menu-item index="/admin/allmedio" @click="getAllMedio">
                <i class="el-icon-menu"></i>
                <span slot="title">学习视频管理</span>
            </el-menu-item>
            <el-menu-item index="/admin/alltopic" @click="updateTopic">
                <i class="el-icon-setting"></i>
                <span slot="title">参考答案管理</span>
            </el-menu-item>
            <el-menu-item index="/" @click="changeIsAdmin">
                <i class="el-icon-switch-button"></i>
                <span slot="title">退出</span>
            </el-menu-item>
            </el-menu>
            </el-col>
        </el-row>
 </div>
 </template>

<script>
import {mapActions} from 'vuex';
  export default {
    methods: {
      getAllMedio(){
        this.getAllMedio();
      },
     updateTopic(){
         this.getAllTopic();
     },
      getAllEssay(){
          this.getAllEssays();
      },
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }, 
      changeIsAdmin(){
          this.updateIsAdmin();
          this.$cookies.remove('admin');
          
      },
      ...mapActions(['updateIsAdmin','getAllEssays','getAllTopic','getAllMedio'])
    },
    

  }
</script>
<style scoped>
.el-col-12 {
    width: 100%;
}
</style>