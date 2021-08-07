<template>
  <div style="width: 80vw; margin-left: 10vw">
       <div    style="width: 100%; display: inline-block;">
           <form
                  class="form-inline pull-right"
                  role="search"
                  method="get"
                  style="width: 100%; display: inline-block;text-align:center;margin:50px 0"
                  target="_blank"
                >
                  <input
                    id="search"
                    style="width: 420px"
                    name="search"
                    class="form-control mr-sm-2 text-muted"
                    type="search"
                    placeholder="搜索摄影师作品"
                    aria-label="搜索"
                    v-model="keyWord"
                    @keydown.enter="goSearch"
                  />
                  <a
                    style="background:none; margin-left:-2rem; color:#ff9d00;"
                    @click.prevent="goSearch"
                  >
                    <i class="fa fa-search" aria-hidden="true"></i>
                  </a>
                </form>
       </div>
    <div class="col" v-for="(item, index) in ssarticles" :key="index">
      <div class="card">
        <img :src="item.posterurl"    @click="go(item.rowname, item.id)" alt="" />
        <div class="card-body" >
          <h5 class="card-title">
            <a   @click="go(item.rowname, item.id)">{{ item.name }}</a>
            <span style="float: right; color: burlywood">
              ￥{{ item.score }}
            </span>
          </h5>

          <div class="date-author">
            {{ item.isshow }}
          </div>
          <p class="card-text" @click="addcart(item)">我要预约</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ssarticles: [],
    };
  },
  created() {
    this.$axios
      .post("/api/getGoods")
      .then((res) => {
        this.ssarticles = res.data;
        console.log(this.ssarticles);
      })
      .catch((err) => {
        console.log("错误信息" + err);
      });
  },
  methods: {
    goSearch(){

    },
       //页面跳转并传送类型和id
    go(type, id) {
       var strategy = { type, id };
      var info = JSON.stringify(strategy);
      sessionStorage.setItem("info", info);
      this.$router.push("/index/FVstrategy");
    },
    addcart(element) {
      console.log(element);
      var tuser = localStorage.getItem("tuser");
      if (tuser) {
        tuser = JSON.parse(tuser);
      } else {
        this.$alert("你还没登录，请先登录", "温馨提示", {
          confirmButtonText: "确定",
          callback: (action) => {
            this.$router.push({ path: "/login" });
          },
        });
      }
      let that = this;
      this.$axios
        .post("/api/addCart", {
          ...element,
          userid: tuser.id,
        })
        .then((response) => {
          that.$message({
            message: "加入购物车成功！",
            type: "success",
          });
        })
        .catch(function (error) {
          console.log(error);
        });

    },
  },
};
</script>

<style scoped>

* {
  box-sizing: border-box;
}

body {
  padding: 0;
  margin: 0;
}

a {
  text-decoration: none;
}

.mt-5 {
  margin-top: 5rem;
}

.our-blog {
  background-color: #f8f9fa;
  padding: 100px 0;
}

.container {
  max-width: 960px;
  margin: 0 auto;
}

.row {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}

.text-center {
  text-align: center;
  font-size: 18px;
}

.text-center h2 {
  font-weight: 500;
  font-size: 1.6em;
}

.text-center h2 span {
  font-weight: 800;
}

.text-center p {
  margin: 0 auto;
  color: #6c757d;
  line-height: 1.5;
  font-size: 1em;
}

.col {
  width: 273px;
  overflow: hidden;
  padding: 15px;
  display: inline-block;
}

.col .card {
  width: 100%;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);
  transition: transform 0.3s ease;
}

.col img {
  width: 100%;
  height: auto;
  border: 0;
  height: 180px;
}

.col .card-body {
  padding: 0 15px 15px 15px;
  background-color: #fff;
  border-bottom-left-radius: 15px;
  border-bottom-right-radius: 15px;
}

.card-body h5 {
  font-size: 24px;
  margin: 7px 0;
}

.card p {
  color: #6c757d;
}

.card-body .date-author {
  color: #e74c3c;

  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.card-body h5 a {
  color: #222;
}

@media (min-width: 577px) and (max-width: 768px) {
  .col {
    width: 50%;
  }
}

@media (max-width: 576px) {
  .col {
    width: 100%;
  }
}
.card-text {
  margin-bottom: 0;
  background: #efeae4;
  padding: 5px 20px;
  text-align: center;
  margin: 10px;
  cursor: pointer;
}
</style>