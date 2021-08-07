<template>
  <div>
    <!-- 上部导航 -->
    <ul
      class="nav nav-tabs justify-content-center mb-5"
      id="myTab"
      role="tablist"
    >
      <li class="nav-item">
        <a
          class="nav-link active"
          id="home-tab"
          data-toggle="tab"
          href="#home"
          role="tab"
          aria-controls="home"
          aria-selected="true"
          >个人信息</a
        >
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          id="collection-tab"
          data-toggle="tab"
          href="#collection"
          role="tab"
          aria-controls="collection"
          aria-selected="false"
          >我的点赞</a
        >
      </li>
        <li class="nav-item">
        <a
          class="nav-link"
          id="collection-tab"
          data-toggle="tab"
          href="#order"
          role="tab"
          aria-controls="order"
          aria-selected="false"
          >我的预约</a
        >
      </li> 
         <li class="nav-item">
        <a
          class="nav-link"
          id="collection-tab"
          data-toggle="tab"
          href="#message"
          role="tab"
          aria-controls="order"
          aria-selected="false"
          >我的消息</a
        >
      </li> 
    </ul>
    <!-- 内容 -->
    <div class="tab-content" id="myTabContent">
      <!-- 个人信息 -->
      <div
        class="tab-pane fade show active"
        id="home"
        role="tabpanel"
        aria-labelledby="home-tab"
      >
        <user-info></user-info>
      </div>
      <!-- 我的点赞 -->
      <div
        class="tab-pane fade"
        id="collection"
        role="tabpanel"
        aria-labelledby="collection-tab"
      >
        <user-collection></user-collection>
      </div>
      <!-- 我的预约 -->
      <div
        class="tab-pane fade"
        id="order"
        role="tabpanel"
        aria-labelledby="collection-tab"
      >
        <order></order>
      </div>
      <div
        class="tab-pane fade"
        id="message"
        role="tabpanel"
        aria-labelledby="collection-tab"
      >
        <List></List>
      </div>
    </div>
  </div>
</template>
<script>
import UserInfo from ".//UserInfo";
import UserArticle from "./UserArticle";
import order from "./order";
import List from "./List";
import UserCollection from "./UserCollection";

export default {
  name: "UserCenter",
  components: {
    UserInfo,
    UserArticle,
    UserCollection,
    order,
    List
  },
  data(){
    return{
      orderAll:[],
    }
  },
  methods:{
     handleClick(row) {
        console.log(row);
      }
  },
};
</script>
<style scoped>
#myTabContent {
  height: 900px;
}
</style>
