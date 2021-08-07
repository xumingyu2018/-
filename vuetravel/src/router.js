import Vue from "vue";
import Router from "vue-router";

const Index = () =>
    import ("./views/Index.vue"); //导航栏
const Home = () =>
    import ("./views/Home/Home.vue"); //主页
const Login = () =>
    import ("./views/Login/Login.vue"); //登录
const Register = () =>
    import ("./views/Register/Register.vue"); //注册
const Forget_pwd = () =>
    import ("./views/Login/Forget_pwd.vue"); //忘记密码
const Private = () =>
    import ("./views/private/index.vue"); //酒店

const Line = () =>
    import ("./views/Line/Line.vue"); //用户发布
const LineStrategy = () =>
    import ("./views/Line/children/LineStrategy.vue"); //个性路线攻略

const FVstrategy = () =>
    import ("./views/FVstrategy.vue"); //攻略详情页
const Editor = () =>
    import ("./views/User/Editor.vue"); //编辑攻略
const UserCenter = () =>
    import ("./views/User/UserCenter.vue"); //用户中心
const Search = () =>
    import ("./views/Search/Search.vue"); //搜索
const takephoner = () =>
    import ("./views/takephoner/index.vue"); //摄影师列表
const NotFound = () =>
    import ("./views/Not404.vue"); //404

import jwt_decode from "jwt-decode";

const routerPush = Router.prototype.push;
Router.prototype.push = function push(location) {
    return routerPush.call(this, location).catch(error => error);
};

Vue.use(Router);
let router = new Router({
    mode: "history",
    base: process.env.BASE_URL,
    routes: [{
            path: "/",
            redirect: "/index/home" //路由跳转
        },
        {
            path: "/index",
            name: "index",
            component: Index,
            children: [{
                    path: "private",
                    name: "private",
                    component: Private
                },
                {
                    path: "editor",
                    name: "editor",
                    component: Editor
                },
                {
                    path: "home",
                    name: "home",
                    component: Home
                },
                {
                    path: "line",
                    name: "line",
                    component: Line
                },
                {
                    path: "linestrategy",
                    name: "linestrategy",
                    component: LineStrategy
                },
                {
                    path: "search/:keyWord",
                    name: "search",
                    component: Search
                },
                {
                    path: "takephoner/:keyWord",
                    name: "takephoner",
                    component: takephoner
                },
                {
                    path: "userCenter",
                    name: "userCenter",
                    component: UserCenter
                },
                {
                    path: "fvstrategy",
                    name: "fvstrategy",
                    component: FVstrategy
                }
            ]
        },
        {
            path: "/login",
            name: "login",
            component: Login
        },
        {
            path: "/register",
            name: "register",
            component: Register
        },
        {
            path: "/forget_pwd",
            name: Forget_pwd,
            component: Forget_pwd
        },
        {
            path: "/*", //路由匹配不成功时
            name: "notfound",
            component: NotFound
        }
    ]
});
//设置路由守卫
router.beforeEach((to, from, next) => {
    //除了login和register，其他的路由访问必须先登录
    let tokenIsExists = localStorage.getItem("mytoken") ? true : false; //检查本地存储中是否有token
    if (
        to.path == "/index/home" ||
        to.path == "/index/Aim" ||
        to.path == "/index/aimline" ||
        to.path == "/index/delicious" ||
        to.path == "/index/private" ||
        to.path == "/index/line" ||
        to.path == "/index/linestrategy" ||
        to.path == "/index/search" ||
        to.path == "/index/view" ||
        to.path == "/login" ||
        to.path == "/register" ||
        to.path == "/forget_pwd" ||
        to.path == "/index/FVstrategy" ||
        to.path == "/index/Fvstrategy#" ||
        to.path == "/index/fvstrategy#" ||
        to.path == "/index/fvstrategy" ||
        to.path == "/index/focus"
    ) {
        next(); //允许访问路由
    } else {
        if (tokenIsExists) {
            if (to.path == "/manage" || to.path == "/Article") {
                var role = jwt_decode(localStorage.getItem("mytoken")).role;
                if (role == "manage") {
                    next();
                } else {
                    next("/*");
                }
            } else {
                next(); //已经登录并取得token，允许访问路由
            }
        } else {
            next("/login"); //路由跳转到登录组件
        }
    }
});
export default router;