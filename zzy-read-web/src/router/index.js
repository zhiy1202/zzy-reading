import Vue from 'vue';
import Router from 'vue-router';
import OutsideRead from '../pages/OutsideRead'
import Grade from '../pages/Grade'
import MedieDetail from '../pages/MedieDetail'
import EssayDetail from '../pages/EssayDetail'
import ReadHistory from '../pages/ReadHistory'
import MyLike from '../pages/MyLike'
import HomeWork from '../pages/HomeWork'
import Login from '../pages/Login'
import Admin from '../pages/Admin'
import SelectGrade from '../pages/SelectGrade'
import MyError from '../pages/MyError'
import Calendar from '../pages/Calendar'

import InsertEssay from '../pages/admin/InsertEssay'
import AllEssay from '../pages/admin/AllEssay'
import AllMedio from '../pages/admin/AllMedio'
import AllTopis from '../pages/admin/AllTopis'
import InsertTopis from '../pages/admin/InsertTopis'
import UpdateEssay from '../pages/admin/UpdateEssay'

Vue.use(Router) 
const router =  new Router(
    {
        routes:[
            {
                path: '/outsidereadd',
                component: OutsideRead,
            },
            {
                path: '/grade/:label',
                component: Grade
            },
            {
                path: '/mediedetail',
                component: MedieDetail
            },
            {
                path:'/essaydetail/:id',
                component:EssayDetail
                
            },
            {
                path:'/readhistory',
                component:ReadHistory
                
            },
            {
                path:'/mylike',
                component:MyLike     
            },
            {
                path:'/homework/:eid',
                component:HomeWork     
            },
            {
                path:'/',
                component:Login     
            },
            {
                path:'/admin',
                component:Admin 
            },
            {
                path:'/exit',
                component:Login     
            },
            {
                path:'/study',
                component:ReadHistory     
            },
            {
                path:'/selectgrades',
                component:SelectGrade     
            },
            {
                path:'/myerror',
                component:MyError     
            },
            {
                path:'/admin/insertessay',
                component:InsertEssay  
            },
            {
                path:'/admin/allessay',
                component:AllEssay   
            },
            {
                path:'/admin/allmedio',
                component:AllMedio   
            },
            {
                path:'/admin/alltopic',
                component:AllTopis   
            },
            {
                path:'/admin/inserttopis/:eid',
                component:InsertTopis    
            },
            {
                path:'/admin/updateEssay/:eid',
                component:UpdateEssay    
            },
            {
                path:'/calendar',
                component:Calendar    
            },

        ]
    }
)
export default router;

