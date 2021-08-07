<template>
  <div>
    <el-table :data="orderAll" border style="width: 100%">
      <el-table-column
        fixed
        prop="id"
        label="订单号"
        width="150"
      ></el-table-column>
      <el-table-column prop="info" label="用户" width="120"></el-table-column>
      <el-table-column
        prop="creattime"
        label="时间"
        width="180"
      ></el-table-column>
      <el-table-column
        prop="name"
        label="景点名称"
        width="120"
      ></el-table-column>
      <el-table-column prop="posterurl" label="图片" width="180">
        <template slot-scope="scope">
          <img style="height: 100px; width: 100px" :src="scope.row.posterurl" />
        </template>
      </el-table-column>
      <el-table-column prop="ordertype" label="订单状态" width="120">
        <template slot-scope="scope">
          <span v-if="scope.row.ordertype == 1" style="color: blue"
            >已预约</span
          >
          <span v-if="scope.row.ordertype == 2" style="color: red">已付款</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small"
            >查看</el-button
          >
          <el-button
            @click="paymoney(scope.row, scope.$index)"
            type="text"
            size="small"
            >付款</el-button
          >
          <el-button type="text" size="small" @click="del(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {
  data() {
    return {
      orderAll: [],
      tableData: [],
      alldata: [],
    };
  },
  methods: {
    handleClick(obj) {
      console.log(obj);
      let type = 1;
      let id = obj.goodsid;
      var strategy = { type, id };
      var info = JSON.stringify(strategy);
      sessionStorage.setItem("info", info);
      this.$router.push("/index/fvstrategy");
    },
    paymoney(obj, index) {
      this.$confirm("此操作将付款订单, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$axios
            .post("/api/paymoney", { id: this.alldata[index].id, ordertype: 2 })
            .then((response) => {
              this.getOrder();

              this.$message({
                type: "success",
                message: "付款成功!",
              });
            })
            .catch(function (error) {
              console.log(error);
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消",
          });
        });
    },
    del(obj) {
      this.$confirm("此操作将永久删除该订单, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$axios
            .post("/api/delOrder", { id: obj.id })
            .then((response) => {
              location.reload();
              this.$message({
                type: "success",
                message: "删除成功!",
              });
            })
            .catch(function (error) {
              console.log(error);
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    getOrder() {
      let userinfo = JSON.parse(localStorage.getItem("tuser"));
      this.$axios
        .post("/api/queryOrderByName", { username: userinfo.username })
        .then((res) => {
          console.log("res1===", res);
          this.orderAll = [];
          let temp = res.data;
          this.alldata = res.data;
          temp.forEach((element) => {
            let data = {
              id: element.id,
              info: element.info,
              creattime: element.creattime,
              name: element.orderDetail[0].name,
              posterurl: element.orderDetail[0].posterurl,
              playinfo: element.orderDetail[0].playinfo,
              goodsid: element.orderDetail[0].id,
              ordertype: element.ordertype,
            };
            this.orderAll.push(data);
          });
        });
    },
  },
  created() {
    this.getOrder();
  },
};
</script>