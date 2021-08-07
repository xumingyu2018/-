<template>
  <div style="width: 80vw; margin-left: 10vw">
    <div style="width: 100%; display: inline-block">
       <beautiful-chat
      :participants="participants"
      :titleImageUrl="titleImageUrl"
      :onMessageWasSent="onMessageWasSent"
      :messageList="messageList"
      :newMessagesCount="newMessagesCount"
      :isOpen="isChatOpen"
      :close="closeChat"
      :icons="icons"
      :open="openChat"
      :showEmoji="true"
      :showFile="true"
      :showEdition="true"
      :showDeletion="true"
      :showTypingIndicator="showTypingIndicator"
      :showLauncher="true"
      :showCloseButton="true"
      :colors="colors"
      :alwaysScrollToBottom="alwaysScrollToBottom"
      :messageStyling="messageStyling"
      @onType="handleOnType"
      @edit="editMessage" />
      <a>摄影师：<span>{{userobj.username}}</span>
      <span style="margin-left:100px;">性别：{{userobj.usersex}}</span>
      </a>
      <p class="card-text" @click="openChat()">
        点击联系摄影师-></p>
    </div>
    <div class="col" v-for="(item, index) in ssarticles" :key="index" >
      <div class="card" @click="godetail(item,index)">
        <img :src="item.posterurl"  @click="godetail(item,index)" />
        <div class="card-body">
          <h5 class="card-title">
            <a @@click="godetail(item,index)">{{ item.name }}</a>
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
import CloseIcon from 'vue-beautiful-chat/src/assets/close-icon.png'
import OpenIcon from 'vue-beautiful-chat/src/assets/logo-no-bg.svg'
import FileIcon from 'vue-beautiful-chat/src/assets/file.svg'
import CloseIconSvg from 'vue-beautiful-chat/src/assets/close.svg'
export default {
  data() {
    return {
      ssarticles: [],
      keyWord: "",
      icons:{
        open:{
          img: OpenIcon,
          name: 'default',
        },
        close:{
          img: CloseIcon,
          name: 'default',
        },
        file:{
          img: FileIcon,
          name: 'default',
        },
        closeSvg:{
          img: CloseIconSvg,
          name: 'default',
        },
      },
      participants: [
        {
          id: 'user1',
          name: 'Matteo',
          imageUrl: 'https://avatars3.githubusercontent.com/u/1915989?s=230&v=4'
        },
        {
          id: 'user2',
          name: 'Support',
          imageUrl: 'https://avatars3.githubusercontent.com/u/37018832?s=200&v=4'
        }
      ], // the list of all the participant of the conversation. `name` is the user name, `id` is used to establish the author of a message, `imageUrl` is supposed to be the user avatar.
      titleImageUrl: 'https://a.slack-edge.com/66f9/img/avatars-teams/ava_0001-34.png',
      messageList: [
          { type: 'text', author: `me`, data: { text: `Say yes!` } },
          { type: 'text', author: `me`, data: { text: `Say yes!` } },
          { type: 'text', author: `me`, data: { text: `Say yes!` } },
          { type: 'text', author: `user1`, data: { text: `No.` } }
      ], // the list of the messages to show, can be paginated and adjusted dynamically
      newMessagesCount: 0,
      isChatOpen: false, // to determine whether the chat window should be open or closed
      showTypingIndicator: '', // when set to a value matching the participant.id it shows the typing indicator for the specific user
      colors: {
        header: {
          bg: '#4e8cff',
          text: '#ffffff'
        },
        launcher: {
          bg: '#4e8cff'
        },
        messageList: {
          bg: '#ffffff'
        },
        sentMessage: {
          bg: '#4e8cff',
          text: '#ffffff'
        },
        receivedMessage: {
          bg: '#eaeaea',
          text: '#222222'
        },
        userInput: {
          bg: '#f4f7f9',
          text: '#565867'
        }
      }, // specifies the color scheme for the component
      alwaysScrollToBottom: false, // when set to true always scrolls the chat to the bottom when new events are in (new message, user starts typing...)
      messageStyling: true,
      sheyingid: 0,
      userobj:{},
      timer: {}
    };
  },
  created() {
    if(localStorage.getItem("tuser")){
      let tuser =JSON.parse(localStorage.getItem("tuser")) 
    this.sheyingid = this.$route.params.keyWord;
    this.getcontetn()
    this.timer = setInterval(()=>{
      this.$axios
      .post("/api/getchatsinfo", {
          getterid:this.sheyingid,
          sendid: tuser.id
        })
      .then((res) => {
        this.messageList = []
        res.data.forEach(element => {
          if(element.sendid == tuser.id){
            this.messageList.push({ type: 'text', author: `me`, data: { text: `${element.content}`} })
          }else{
            this.messageList.push({ type: 'text', author: `user1`, data: { text: `${element.content}`} })
          }
        }); 
      })
      .catch((err) => {
        console.log("错误信息" + err);
      });
    },5000)
    }else{
      this.$router.push("/login")
    }
    
  },
  methods: {
    getcontetn(){
      this.$axios
      .post("/api/getUserByid", {
          id:this.sheyingid
        })
      .then((res) => {
        this.userobj = res.data[0];
      })
      .catch((err) => {
        console.log("错误信息" + err);
      });
    this.$axios
      .post("/api/getzuopingbyuserid", {
          wish:this.sheyingid
        })
      .then((res) => {
        this.ssarticles = res.data;
      })
      .catch((err) => {
        console.log("错误信息" + err);
      });
    },
    godetail(obj, index) {
      console.log(type,id,"---")
      var { type, id } = obj
      var strategy = { type, id };
      var info = JSON.stringify(strategy);
      sessionStorage.setItem("info", info);
      this.$router.push("/index/fvstrategy");
    },
     sendMessage (text) {
      if (text.length > 0) {
        this.newMessagesCount = this.isChatOpen ? this.newMessagesCount : this.newMessagesCount + 1
        this.onMessageWasSent({ author: 'support', type: 'text', data: { text } })
      }
    },
    onMessageWasSent (message) {
      // called when the user sends a message
      this.messageList = [ ...this.messageList, message ]
      console.log(message)
      let mycontent =  ""
      if(message.type == "emoji"){
        mycontent = message.data.emoji
        return;
      }else{
        mycontent = message.data.text
      }
       let tuser =JSON.parse(localStorage.getItem("tuser")) 
      this.$axios
      .post("/api/addchatsinfo", {
          getterid:this.sheyingid,
          sendid: tuser.id,
          content: mycontent
        })
      .then((res) => {
      })
      .catch((err) => {
        console.log("错误信息" + err);
      });
      this.$axios
      .post("/api/addlianxiinfo", {
          otherid:this.sheyingid,
          myid: tuser.id,
          myname: tuser.username,
          othername: this.userobj.username
        })
      .then((res) => {
      })
      .catch((err) => {
        console.log("错误信息" + err);
      });
    },
    openChat () {
      // called when the user clicks on the fab button to open the chat
       let tuser =JSON.parse(localStorage.getItem("tuser")) 
      this.$axios
      .post("/api/getchatsinfo", {
          getterid:this.sheyingid,
          sendid: tuser.id
        })
      .then((res) => {
        console.log(res)

        this.messageList = []
        res.data.forEach(element => {
          if(element.sendid == tuser.id){
            this.messageList.push({ type: 'text', author: `me`, data: { text: `${element.content}`} })
          }else{
            this.messageList.push({ type: 'text', author: `user1`, data: { text: `${element.content}`} })
          }
        }); 
      })
      .catch((err) => {
        console.log("错误信息" + err);
      });
      this.isChatOpen = true
      this.newMessagesCount = 0
    },
    closeChat () {
      // called when the user clicks on the botton to close the chat
      this.isChatOpen = false
    },
    handleScrollToTop () {
      // called when the user scrolls message list to top
      // leverage pagination for loading another page of messages
    },
    handleOnType () {
      console.log('Emit typing event')
    },
    editMessage(message){
      const m = this.messageList.find(m=>m.id === message.id);
      m.isEdited = true;
      m.data.text = message.data.text;
    },
    goSearch() {},
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
  beforeDestroy(){
    clearInterval(this.timer) 
  }
};
</script>

<style scoped>
/deep/ .sc-chat-window{
  z-index: 999;
}
.sc-message--text-content{
  margin-bottom: 0;
}
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
  z-index: 0;
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