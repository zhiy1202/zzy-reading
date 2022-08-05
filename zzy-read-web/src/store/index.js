import Vue from 'vue'
import VueX from 'vuex'
import axios from 'axios'
Vue.use(VueX)
const state = {
    essay:{},
    detail:{},
    homework:{},
    userLikes:[],
    readhistorys:{},
    studyGrades:[],
    myErrors:[],
    isAdmin:false,
    allEssays:[],
    allTopic:[],
    allMedio:[],
    allUserLock:[]
}
const getters = {
    allUserLock(state){
        return state.allUserLock
    },
    allMedio(state){
        return state.allMedio
    },
    allTopic(state){
        return state.allTopic
    },
    detail(state){
        return state.detail
    },
    essay(state){
        return state.essay
    },
    homework(state){
        return state.homework
    },
    userLikes(state){
        return state.userLikes
    },
    readhistorys(state){
        return state.readhistorys
    },
    studyGrades(state){
        return state.studyGrades
    },
    myErrors(state){
        return state.myErrors
    },
    isAdmin(state){
        return state.isAdmin
    },
    allEssays(state){
        return state.allEssays
    }

}
const mutations = {
    UPDATEALLUSERLOCK(state,value){
        state.allUserLock = value;
    },
    UPDATEALLMEDIO(state,value){
        state.allMedio = value;
    },
    UPDATEaALLTOPIC(state,value){
        state.allTopic = value;
    },
    UPDATAESSAY(state,value){
        state.essay = value;
    },
    UPDATADETAIL(state,value){
        state.detail = value;
    },
    UPDATEHOMWORK(state,value){
        state.homework = value;
    },
    UPDATEUSERLIKES(state,value){
        state.userLikes = value;
    },
    UPDATEUSERHISTORYS(state,value){
        state.readhistorys = value;
    },
    UPDATESTUDYGRADES(state,value){
        state.studyGrades = value;
    },
    UPDATEMYERRORS(state,value){
        state.myErrors = value;
    },
    UPDATEISADMIN(state){
        state.isAdmin = !state.isAdmin;
    },
    UPDATEALLESSAYS(state,value){
        state.allEssays = value;
    }
}
const actions = {
    getAllUserLock(context){
        axios({
            method:'get',
            url:`http://localhost:8081/getAllUserLock`,
        }).then((response) => {
            const data = response.data.data;
            context.commit('UPDATEALLUSERLOCK',data)
        })
    },
    getAllMedio(context){
        axios({
            method:'get',
            url:`http://localhost:8081/admin/getAllMedio`,
        }).then((response) => {
            const data = response.data.data;
            context.commit('UPDATEALLMEDIO',data)
        })
    },
    getAllTopic(context){
        axios({
            method:'get',
            url:`http://localhost:8081/admin/getAllTopic`,
        }).then((response) => {
            const data = response.data.data;
            context.commit('UPDATEaALLTOPIC',data)
        })
    },
    getAllEssays(context){
        axios({
            method:'get',
            url:`http://localhost:8081/admin/getAllEssays`,
        }).then((response) => {
            const data = response.data.data;
            console.log(data);
            context.commit('UPDATEALLESSAYS',data)
        })
    },
    updateEssay(context,value){
        axios({
            method:'get',
            url:`http://localhost:8081/getlist/${value}`,
        }).then((response) => {
            const data = response.data.data;
            context.commit('UPDATAESSAY',data)
        })
    },
    updateDetail(context,value){
        axios({
            method:'get',
            url:`http://localhost:8081/getessay/${value}`,
        }).then((response) => {
            const data = response.data.data;
            context.commit('UPDATADETAIL',data)
        })
    },
    updateHomeWork(context,value){
        axios({
            method:'get',
            url:`http://localhost:8081/homework/${value}`,
        }).then((response) => {
            const data = response.data.data;
            context.commit('UPDATEHOMWORK',data)
        })
    },
    updateUserLikes(context,value){
        axios({
            method:'get',
            url:`http://localhost:8081/getuserlikes/${value}`,
        }).then((response) => {
            const data = response.data.data;
            context.commit('UPDATEUSERLIKES',data)
        })
    },
    updateUserHistorys(context,value){
        axios({
            method:'get',
            url:`http://localhost:8081/getuserhistorys/${value}`,
        }).then((response) => {
            const data = response.data.data;
            context.commit('UPDATEUSERHISTORYS',data)
        })
    },
    updateAllStudyGrades(context,value){
        axios({
            method:'get',
            url:`http://localhost:8081/selectgrades/${value}`,
        }).then((response) => {
            const data = response.data.data;
            context.commit('UPDATESTUDYGRADES',data)
        })
    },
    updateMyErrors(context){
        axios({
            method:'get',
            url:`http://localhost:8081/myerrors`,
        }).then((response) => {
            const data = response.data.data;
            context.commit('UPDATEMYERRORS',data)
        })
    },
    updateIsAdmin(context){
        context.commit('UPDATEISADMIN')
    },
}

const store =new VueX.Store({
    state,
    mutations,
    actions,
    getters

})

export default store



