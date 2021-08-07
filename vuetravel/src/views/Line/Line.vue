<template>
  <div>

    <div id="container">
      <br />
      <div class="con-content">
        <!-- 左边广告 -->
        <div id="con-left">
          <div class="left-card">
            <div class="card-txt">
              <p>现代主流-</p>
            </div>
            <img src="../../assets/images/origin2 (5).jpg" alt />
          </div>
          <div class="left-card">
            <div class="card-txt">
              <p>雪乡+雪谷，等你来拍</p>
            </div>
            <img src="../../assets/images/origin2 (24).jpg" alt />
          </div>
          <div class="left-card">
            <div class="card-txt">
              <!-- <p>Northern Lights in Norway</p> -->
            </div>
            <img class="advertise" src="../../assets/images/origin2 (1).jpg" alt />
          </div>
        </div>

        <div id="con-line">
          <div id="con-line-nav">
            <!-- 第1行导航 -->
            <nav>
              <div class="nav nav-tabs" id="nav-tab" role="tablist">
                <a
                  class="nav-item nav-link active"
                  id="nav-default-tab"
                  data-toggle="tab"
                  href="#nav-default"
                  role="tab"
                  aria-controls="nav-default"
                  aria-selected="true"
                  >全部</a
                >
                 <a
                  class="nav-item nav-link"
                  id="nav-default-tab"
                  data-toggle="tab"
                  href="#nav-default1"
                  role="tab"
                  aria-controls="nav-default"
                  aria-selected="true"
                  >国内</a
                >
                 <a
                  class="nav-item nav-link"
                  id="nav-default-tab"
                  data-toggle="tab"
                  href="#nav-default2"
                  role="tab"
                  aria-controls="nav-default"
                  aria-selected="true"
                  >日本</a
                >
                 <a
                  class="nav-item nav-link"
                  id="nav-default-tab"
                  data-toggle="tab"
                  href="#nav-default3"
                  role="tab"
                  aria-controls="nav-default"
                  aria-selected="true"
                  >欧洲</a
                >
                 <a
                  class="nav-item nav-link"
                  id="nav-default-tab"
                  data-toggle="tab"
                  href="#nav-default4"
                  role="tab"
                  aria-controls="nav-default"
                  aria-selected="true"
                  >海岛</a
                >
                 <a
                  class="nav-item nav-link"
                  id="nav-default-tab"
                  data-toggle="tab"
                  href="#nav-default5"
                  role="tab"
                  aria-controls="nav-default"
                  aria-selected="true"
                  >泰国</a
                >
              </div>

            </nav>
            <div class="tab-content" id="nav-tabContent">
              <!-- 综合排序内容 -->
              <div
                class="tab-pane fade show active"
                id="nav-default"
                role="tabpanel"
                aria-labelledby="nav-default-tab"
              >
                <div class="main_news" >
                  <ul>
                    <li
                      @click="go(strategy.rowname, strategy.Id)"
                      v-for="strategy in personalrecommendstrategy"
                      :key="strategy.Id"
                    >{{strategy.rowname}}
                      <a  target="_blank">
                        <img :src="strategy.url" />
                        <div class="txt">
                          <h3 v-html="strategy.content"></h3>
                          <!-- <span>{{strategy.rowname}}</span> -->
                        </div>
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
            <div
                class="tab-pane fade show"
                id="nav-default1"
                role="tabpanel"
                aria-labelledby="nav-default-tab"
              >
              <div class="main_news" >
                  <ul>
                    <li
                      @click="go(strategy.rowname, strategy.Id)"
                      v-for="strategy in personalrecommendstrategy"
                      :key="strategy.Id"
                      v-if="strategy.place=='国内'"
                    >{{strategy.rowname}}
                      <a  target="_blank">
                        <img :src="strategy.url" />
                        <div class="txt">
                          <h3 v-html="strategy.content"></h3>
                          <!-- <span>{{strategy.rowname}}</span> -->
                        </div>
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
              <div
                class="tab-pane fade show"
                id="nav-default2"
                role="tabpanel"
                aria-labelledby="nav-default-tab"
              >
              <div class="main_news" >
                  <ul>
                    <li
                      @click="go(strategy.rowname, strategy.Id)"
                      v-for="strategy in personalrecommendstrategy"
                      :key="strategy.Id"
                      v-if="strategy.place=='日本'"
                    >{{strategy.rowname}}
                      <a  target="_blank">
                        <img :src="strategy.url" />
                        <div class="txt">
                          <h3 v-html="strategy.content"></h3>
                          <!-- <span>{{strategy.rowname}}</span> -->
                        </div>
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
              <div
                class="tab-pane fade show"
                id="nav-default3"
                role="tabpanel"
                aria-labelledby="nav-default-tab"
              >
              <div class="main_news" >
                  <ul>
                    <li
                      @click="go(strategy.rowname, strategy.Id)"
                      v-for="strategy in personalrecommendstrategy"
                      :key="strategy.Id"
                      v-if="strategy.place=='欧洲'"
                    >{{strategy.rowname}}
                      <a  target="_blank">
                        <img :src="strategy.url" />
                        <div class="txt">
                          <h3 v-html="strategy.content"></h3>
                          <!-- <span>{{strategy.rowname}}</span> -->
                        </div>
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
              <div
                class="tab-pane fade show"
                id="nav-default4"
                role="tabpanel"
                aria-labelledby="nav-default-tab"
              >
                 <div class="main_news" >
                  <ul>
                    <li
                      @click="go(strategy.rowname, strategy.Id)"
                      v-for="strategy in personalrecommendstrategy"
                      :key="strategy.Id"
                      v-if="strategy.place=='海岛'"
                    >{{strategy.rowname}}
                      <a  target="_blank">
                        <img :src="strategy.url" />
                        <div class="txt">
                          <h3 v-html="strategy.content"></h3>
                          <!-- <span>{{strategy.rowname}}</span> -->
                        </div>
                      </a>
                    </li>
                  </ul>
                  </div>
              </div>
                <div
                class="tab-pane fade show"
                id="nav-default5"
                role="tabpanel"
                aria-labelledby="nav-default-tab"
              >
                 <div class="main_news" >
                  <ul>
                    <li
                      @click="go(strategy.rowname, strategy.Id)"
                      v-for="strategy in personalrecommendstrategy"
                      :key="strategy.Id"
                      v-if="strategy.place=='泰国'"
                    >{{strategy.rowname}}
                      <a  target="_blank">
                        <img :src="strategy.url" />
                        <div class="txt">
                          <h3 v-html="strategy.content"></h3>
                        </div>
                      </a>
                    </li>
                  </ul>
                  </div>
              </div>
              <div
                class="tab-pane fade"
                id="nav-people"
                role="tabpanel"
                aria-labelledby="nav-people-tab"
              >
              </div>



            </div>
          </div>
        </div>
        <br />
      </div>
      <!-- 最后div清除浮动 -->
      <div class="lastdiv"></div>
    </div>
    <el-backtop :bottom="100"></el-backtop>
  </div>
</template>

<script>
export default {
  // name: "Line",
  data() {
    return {
      info: [],
      platformRecommendstrategy: [],
      show01: false,

      personalrecommendstrategy: [],
      show02: false,
      show03: false,
      strategyType: "personalrow",
      people01: false,

      lineClass: "",
      lineType: "",
      people: []
    };
  },
  created() {
     this.$axios
      .post("/api/getwshrow")
      .then(res => {
        console.log(res)
          res.data.forEach(i => i.Id = i.id);
          this.personalrecommendstrategy = res.data;

      })
      .catch(err => {
        console.log("错误信息" + err);
      })

  },
  methods: {
    //页面跳转并传送攻略类型和id
    go(type, id) {
      var strategy = { type, id };
      var info = JSON.stringify(strategy);
      sessionStorage.setItem("info", info);
      this.$router.push("/index/linestrategy");
    },
    lineClassify() {

    }
  }
};
</script>

<style scoped>
.tenpx-line {
  width: 100%;
  height: 10px;
}
#loopImg {
  background-color: #23a393;
}
#loopImg img {
  width: 100%;
  height: 100%;
}
#container {
  width: 100%;
  background-color: #fff;
}

.con-title {
  font-size: 30px;
  width: 100%;
  height: 50px;
  line-height: 50px;
  margin: 0 auto;
  text-align: center;
}
#con-platformRecommend-main > a {
  text-decoration: none;
}
#con-personalRecommend-main > a {
  text-decoration: none;
}

.line-row {
  width: 80%;
  margin: 0 auto;
  text-align: center;
}
.line-row .nonedata {
  font-size: 30px;
  font-weight: 700px;
  color: #999;
  margin-bottom: 20px;
}
.box-shadow {
  margin: 10px;
  cursor: pointer;
}
.box-shadow:hover {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  transition: 0.5s all;
}
.box-shadow a {
  color: #666;
}
.box-shadow a:hover {
  color: #ff9d00;
}

#con-left {
  float: left;
  margin-left: 100px;
}

#con-left .left-card {
  width: 280px;
  background-color: white;
  margin-bottom: 25px;
}
#con-left .left-card .card-txt {
  text-align: center;
  padding: 10px 20px;
}
#con-left .left-card img {
  width: 280px;
  height: 200px;
}
#con-left .left-card .advertise {
  width: 280px;
  height: 480px;
  padding: 20px;
  box-shadow: 0px 5px 8px #eee;
}

#con-line {
  margin-left: 380px;
}
#con-line-nav {
  width: 60%;
  margin-left: 40px;
}
#con-line-nav .nonedata {
  font-size: 30px;
  font-weight: 700px;
  color: #999;
  text-align: center;
  margin-top: 20px;
}

.main_news ul {
  list-style: none;
  padding: 0;
  padding-top: 20px;
}
.main_news ul li {
  padding-bottom: 10px;
  width: 800px;
  border-bottom: 1px dotted #cbcbcb;
  margin-bottom: 10px;
}
.main_news ul li:hover {
  background-color: hsl(0, 0%, 93%);
}
.main_news ul li a {
  text-decoration: none;
  /* color: #ff9d00; */
}
.main_news ul li a > img {
  float: left;
  width: 260px;
  height: 150px;
}

.main_news ul li a .txt {
  display: block;
  padding-left: 15px;
  width: 520px;
  height: 150px;
  overflow: hidden;
}

.main_news ul li a .txt h3 {
  font-size: 18px;
  line-height: 36px;
  font-weight: 700;
  color: #454545;
}

.main_news ul li a .txt span {
  font-size: 14px;
  line-height: 18px;
  color: #737373;
}
/* .main_news ul li a .info {
  display: block;
  padding-left: 15px;
  width: 520px;
  height: 30px;
  overflow: hidden;
}
.main_news ul li a .info span {
  padding-left: 10px;
  padding-right: 10px;
}
.main_news ul li a .info .sp1 img {
  width: 30px;
  height: 30px;
  border-radius: 100%;
}
.main_news ul li a .info > span > span {
  padding-left: 5px;
  padding-right: 20px;
  font-size: 16px;
} */

.lastdiv {
  clear: both;
}
</style>
