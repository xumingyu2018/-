/**导航栏 */
<template>
  <div class="index">
    <nav class="navbar navbar-expand-lg navbar-light bg-white fixed-top">
      <div class="container">
        <div class="row" style="margin: 0 auto">
          <!-- LOGO -->
          <a class="navbar-brand" href="/index/home" @click.prevent="goHome">
            <img style="height:70px;width:120px" src="../assets/logo.jpg" alt />
            约拍网
          </a>
          <button
            class="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarContent"
            aria-controls="navbarContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse text-center" id="navbarContent">
            <ul class="navbar-nav">
              <li class="nav-item active">
                <a class="nav-link" href="/index/home" @click.prevent="goHome">
                  首页
                  <span class="sr-only">(current)</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/index/line" @click.prevent="goLine"
                  >约拍单</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/index/private" @click.prevent="goPrviavte"
                  >摄影师</a
                >
              </li>
                <li class="nav-item">
                <a class="nav-link" href="/index/editor" @click.prevent="goeditor"
                  >发布</a
                >
              </li>
              <!-- 搜索框 -->
              <li class="nav-item d-flex justify-content-center">
                <form
                  class="form-inline pull-right"
                  role="search"
                  method="get"
                  target="_blank"
                >
                  <input
                    id="search"
                    style="width: 120px"
                    name="search"
                    class="form-control mr-sm-2 text-muted"
                    type="search"
                    placeholder="全站搜索..."
                    aria-label="搜索"
                    v-model="keyWord"
                    @keydown.enter="goSearch"
                  />
                  <a
                    style="background:none; margin-left:-2rem; color:#ff9d00;"
                    href="/index/search"
                    @click.prevent="goSearch"
                  >
                    <i class="fa fa-search" aria-hidden="true"></i>
                  </a>
                </form>
              </li>
              

              <div class="dropdown-divider"></div>
              <li class="nav-item" id="login">
                <a
                  class="nav-link"
                  href="/login"
                  @click.prevent="goLogin"
                  v-if="isShow === false"
                  >登录</a
                >
              </li>
              <li class="nav-item" id="register">
                <a
                  class="nav-link"
                  href="/register"
                  @click.prevent="goRegister"
                  v-if="isShow === false"
                  >注册</a
                >
              </li>
              <li class="nav-item dropdown ml-2">
                <a
                  class="nav-link dropdown-toggle d-flex align-items-center justify-content-center"
                  href="/index/userCenter"
                  id="userDropdown"
                  role="button"
                  data-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false"
                  v-if="isShow === true"
                >
                  <!-- 导航栏用户头像 -->
                  <a class="dropdown-item" href="#" @click.prevent="goUserCenter">
                    {{username}}
                  </a>
                </a>
                <div
                  class="dropdown-menu text-center"
                  aria-labelledby="userDropdown"
                  v-if="isShow === true"
                >
                  <a
                    class="dropdown-item"
                    href="/index/userCenter"
                    @click.prevent="goUserCenter"
                  >
                    <i class="fa fa-user-o mr-2" aria-hidden="true"></i>个人中心
                  </a>
                  <div class="dropdown-divider"></div>
                  <a
                    class="dropdown-item"
                    href="/index/home"
                    @click.prevent="goHome"
                    @click="logout"
                  >
                    <i class="fa fa-sign-out mr-2" aria-hidden="true"></i
                    >退出登录
                  </a>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </nav>
    <router-view></router-view>
    <!-- 页脚 -->
    <nav class="nav flex-column align-items-center bg-secondary text-white">
      <h6 class="mt-3">友情链接</h6>
      <a class="nav-link" href="https://www.mafengwo.cn">马蜂窝</a>
      <div class="dropdown-divider w-75"></div>
      <p class="nav-title">Copyright&nbsp;&copy;2021&nbsp;TravelNote</p>
    </nav>
  </div>
</template>
<script>
export default {
  data() {
    return {
      isShow: false,
      userInfo: [{ headPic: "" }],
      sysnumber: "",
      show: false,
      keyWord: "",
      username:""
    };
  },
  //组件创建完成后执行的操作
  created() {
    if(localStorage.getItem("mytoken")) this.isShow = true
    if(localStorage.getItem('tuser')){
      let tuser = JSON.parse(localStorage.getItem('tuser'))
      this.username = tuser.username
    }
    // 导航栏拉伸效果
    $(function() {
      $("#search").click(function() {
        $("#search").animate({ width: "220px" }, 1000);
      });
      $("#search").blur(function() {
        $("#search").animate({ width: "120px" }, 1000);
      });
    });

   
  },
  methods: {
    getHeadPic(pic) {
      //给图片名加上服务器端访问路径
      let path = "";
      if (pic == null || pic == "" || pic == "headPic") {
        pic = "primaryHead.jpeg";
      }
      path = "http://localhost:3000/uploadHeadPic/" + pic;
      return path;
    },
    goAim() {
      this.$router.push("/index/Aim");
    },
    goDelicious() {
      this.$router.push("/index/delicious");
    },
    goEditor() {
      if (this.userInfo[0].userStatus == -1) {
        this.$message({
          showClose: true,
          message: "警告哦，由于您的当前用户状态不正常，已禁止撰写攻略功能！",
          type: "warning",
          duration: 2000
        });
      } else {
        this.$router.push("/index/editor");
      }
    },
    goHome() {
      this.$router.push("/index/home");
    },
    goLine() {
      this.$router.push("/index/line");
    },
    goPrviavte() {
      this.$router.push("/index/private");
    },
    goeditor() {
      this.$router.push("/index/editor");
    },
    goLogin() {
      this.$router.push("/login");
    },
    goRegister() {
      this.$router.push("/register");
    },
    goSearch() {
      let keyWord = this.keyWord;
      // this.$router.push({
      //   path: `/index/search/${keyWord}`
      // });
      window.open(`/index/search/${keyWord}`);
    },
    goUserCenter() {
      this.$router.push("/index/userCenter");
    },
    goView() {
      this.$router.push("/index/view");
    },
    logout() {
      if (localStorage.mytoken) {
        localStorage.removeItem("mytoken");
        this.isShow = false;
      }
    }
  }
};
</script>
<style lang="css" scoped>
.fixed-top{
  position: sticky;
  z-index: 99;
}
#navHeadPic {
  width: 40px;
  height: 40px;
  display: inline-block;
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
}

#userDropdown {
  padding: 0;
}
#search {
  font-size: 15px;
}

.nav-item {
  font-size: 18px;
}
.el-badge {
  top: -8px;
  left: -12px;
}
</style>
