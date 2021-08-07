<template>
  <div id="content">
    <div v-if="show">
      <div class="card-deck flex-column" id="collectArticle">
        <div
          class="card d-flex flex-row mb-2 shadow-sm p-3 bg-white rounded"
          v-for="collection in collections.slice(
            (currentPage - 1) * pagesize,
            currentPage * pagesize
          )"
          :key="collection.id"
        >
          <img
            class="card-img-top"
            :src="collection.jindian.posterurl"
            alt="collectionArticlePic"
          />
          <div class="card-body">
            <a
              href="#"
              @click="go(collection.type, collection.goodsid)"
              class="card-title"
              >{{ collection.jindian.name }}</a
            >
            <p
              class="card-text overflow-hidden"
              style="height: 4em;text-overflow:ellipsis;"
              v-html="collection.jindian.actor"
            ></p>
            <p class="text-muted">
              <i class="fa fa-map-marker mr-2" aria-hidden="true">
                {{ collection.jindian.isshow }}</i
              >
              
              <i class="el-icon-star-off mr-2" style="float:right">
                {{ collection.jindian.playinfo }}</i
              >
            </p>
          </div>
        </div>
      </div>
      <!-- 分页 -->
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
      <h3>暂无点赞攻略，快去看看吧~</h3>
    </div>
  </div>
</template>
<script>
export default {
  name: "UserCollection",
  data() {
    return {
      collections: [],
      currentPage: 1,
      allpages: null,
      pagesize: 4,
      show: false
    };
  },
  created() {
    let tuser =JSON.parse(localStorage.getItem("tuser")) 
    // 查询用户点赞
    this.$axios
      .post("/api/queryCollectByU",{userid:tuser.id})
      .then(res => {
        console.log(res)
        if (res.data) {
          this.show = true;
          this.collections = res.data;
          this.allpages = res.data.length;
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
    getPic(pic) {
      //给图片名加上服务器端访问路径
      if (pic == "cover" || pic == null) {
        pic = "primaryCover.jpg";
      }
      let path = "http://localhost:3000/coverPic/" + pic;
      return path;
    },
    getHeadPic(pic) {
      //给图片名加上服务器端访问路径
      let path = "";
      if (pic == null || pic == "" || pic == "headPic") {
        pic = "primaryHead.jpeg";
      }
      path = "http://localhost:3000/uploadHeadPic/" + pic;
      return path;
    },
    go(type, id) {
       console.log(type,id)
      var strategy = { type, id };
      var info = JSON.stringify(strategy);
      sessionStorage.setItem("info", info);
      this.$router.push("/index/fvstrategy");
    },
    current_change(currentPage) {
      this.currentPage = currentPage;
    }
  }
};
</script>
<style scoped>
h3 {
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
#collectArticle img:first-child {
  width: 12rem;
  height: 10rem;
}
.card-body {
  padding-bottom: 0;
  width: 10rem;
  height: 5rem;
}
.text-muted {
  margin: 0;
}
/* .text-muted img {
  width: 20px;
  height: 20px;
} */
.card {
  color: #666;
}
.card a {
  font-size: 20px;
  color: #333;
}
.card:hover a {
  color: #ff9d00;
}
</style>
