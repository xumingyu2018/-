<template>
  <div class="card-deck flex-column mb-5">
    <div v-if="show">
      <div
        class="card d-flex flex-row mb-2 shadow-sm p-3 bg-white rounded"
        v-for="article in prarticles.slice(
          (currentPage - 1) * pagesize,
          currentPage * pagesize
        )"
        :key="article.strategyId"
        style="cursor: pointer;"
      >
        <img
          class="card-img-top"
          :src="article.url"
          alt="Card image cap"
          style="width: 14em;height: 12em;"
        />
        <div class="card-body">
          <h5 class="card-title">
            <a
              class="card-link"
              @click="go(article.rowname, article.Id)"
              >{{ article.rowname }}</a
            >
          </h5>
          <div
            class="card-text overflow-hidden"
            style="text-overflow:ellipsis;height: 150px;display: -webkit-box;  -webkit-box-orient: vertical;overflow: hidden;   -webkit-line-clamp: 5;"
            v-html="article.content"
          ></div>

        </div>
      </div>
      <div class="block">
        <el-pagination
          :page-size="pagesize"
          :pager-count="11"
          layout="prev, pager, next"
          :total="allpage"
          @current-change="current_change"
        ></el-pagination>
      </div>
    </div>
    <div v-else>
      <h1>暂无用户发布</h1>
    </div>
  </div>
</template>
<script>
export default {
  name: "hotrow",
  data() {
    return {
      prarticles: {
        strategyType: ""
      },
      show: false,
      allpage: "",
      currentPage: 1,
      pagesize: 5
    };
  },
  created() {
    this.prarticles.strategyType = "personalrow";
    this.$axios
      .post("/api/getwshrow")
      .then(res => {
        if (res.data) {
          this.show = true;
          res.data.forEach(i => i.Id = i.id);
          this.prarticles = res.data;
          this.allpage = res.data.length;
        } else {
          this.show = false;
        }
      })
      .catch(err => {
        console.log("错误信息" + err);
      })
      .finally(function() {
        // always executed
      });
  },
  methods: {
    getCoverPic(pic) {
      //给图片名加上服务器端访问路径
      if (pic == "cover" || pic == null) {
        pic = "primaryCover.jpg";
      }
      let path = "http://localhost:3000/coverPic/" + pic;
      return path;
    },
    //页面跳转并传送攻略类型和id
    go(type, id) {
       var strategy = { type, id };
      var info = JSON.stringify(strategy);
      sessionStorage.setItem("info", info);
      this.$router.push("/index/linestrategy");
    },
    goUser(userId) {
      sessionStorage.setItem("strategyuserId", userId);
      this.$router.push("/index/focus");
    },
    current_change(currentPage) {
      this.currentPage = currentPage;
    }
  }
};
</script>
<style scoped>
h1 {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: #ccc;
}
.block {
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translate(-50%, 0);
}
.card-body {
  padding-top: 5px;
  padding-bottom: 0;
  width: 10rem;
  height: 5rem;
}
.text-muted {
  margin: 0;
  height: 1rem;
  line-height: 1rem;
}
.text-muted a {
  color: #666;
}
.text-muted span {
  color: #ff9d00;
}
.text-muted img {
  width: 20px;
  height: 20px;
}
.card {
  color: #666;
}
.card:hover {
  color: #333;
  box-shadow: 0px 5px 5px #eee !important;
  background-color: rgba(250, 248, 248, 0.6) !important;
  transform: 0.3s all !important;
}
.card-title a {
  font-size: 20px;
  color: #333;
}
.card:hover a {
  color: #ff9d00;
}
</style>
