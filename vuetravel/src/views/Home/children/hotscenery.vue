<template>
  <div class="card-deck flex-column mb-5">
    <div v-if="show">
      <div
        class="card d-flex flex-row mb-2 shadow-sm p-3 bg-white rounded"
        v-for="article in ssarticles.slice(
          (currentPage - 1) * pagesize,
          currentPage * pagesize
        )"
        :key="article.strategyId"
        style="cursor: pointer;"
      >
        <img
          class="card-img-top"
          :src="article.posterurl"
          alt="Card image cap"
          style="width: 220px;height:160px"
        />
        <div class="card-body">
          <h5 class="card-title">
            <a
              class="card-link"
              @click="go(article.type, article.id)"
              >{{ article.name }}</a
            >
          </h5>
          <p
            class="card-text overflow-hidden"
            style="height:120px;display: -webkit-box;text-overflow:ellipsis;-webkit-box-orient: vertical;-webkit-line-clamp: 3;overflow: hidden;"
            v-html="article.actor"
          ></p>
          <p class="text-muted">
            <i class="fa fa-map-marker mr-2" aria-hidden="true">
              {{ article.isshow }}</i
            >
           
            <i class="mr-2" style="color:red"> ￥{{ article.score }}</i>
            <i class="fa el-icon-star-off mr-2 float-right" aria-hidden="true">
              {{ article.playinfo }}</i
            >
          </p>
        </div>
      </div>
      <div class="block">
        <el-pagination
          :page-size="pagesize"
          :pager-count="11"
          layout="prev, pager, next"
          :total="allpages"
          @current-change="current_change"
        ></el-pagination>
      </div>
    </div>
    <div v-else>
      <h1>暂无数据</h1>
    </div>
  </div>
</template>
<script>
export default {
  name: "hotscentery",
  data() {
    return {
      ssarticles: {
        strategyType: ""
      },
      show: false,
      pagesize: 7,
      currentPage: 1,
      allpages: ""
    };
  },
  created() {
    this.ssarticles.strategyType = "scenerystrategy";
    this.$axios
      .post("/api/getGoods")
      .then(res => {
        if (res.data) {
          this.show = true;
          console.log(res);
          this.ssarticles = res.data;
          this.allpages = res.data.length;
        } else {
          this.show = false;
        }
      })
      .catch(err => {
        console.log("错误信息" + err);
      })
  
  },
  methods: {
    go(type, id) {
      console.log(type,id)
      var strategy = { type, id };
      var info = JSON.stringify(strategy);
      sessionStorage.setItem("info", info);
      this.$router.push("/index/fvstrategy");
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
  height: 200px;
  overflow: hidden;
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
  text-decoration: none;
}
.card:hover a {
  color: #ff9d00;
}
</style>
