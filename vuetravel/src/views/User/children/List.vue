<template>
  <div>
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
    <el-table :data="alldata" border style="width: 100%">
      <el-table-column prop="othername" label="联系人"></el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button @click="openChat(scope.row)" type="primary" size="small"
            >打开聊天</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    
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
      orderAll: [],
      tableData: [],
      alldata: [],
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
      timer: {},
      tempobj:{}
    };
  },
  methods: {
    getcontetn(obj){
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
          getterid:this.tempobj.otherid,
          sendid: tuser.id,
          content: mycontent
        })
      .then((res) => {
      })
      .catch((err) => {
        console.log("错误信息" + err);
      });
    },
    openChat (obj) {
      this.tempobj = obj
      // called when the user clicks on the fab button to open the chat
       let tuser =JSON.parse(localStorage.getItem("tuser")) 
      this.$axios
      .post("/api/getchatsinfo", {
          getterid:obj.otherid,
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
    handleClick(obj) {
      console.log(obj);
      let type = 1;
      let id = obj.goodsid;
      var strategy = { type, id };
      var info = JSON.stringify(strategy);
      sessionStorage.setItem("info", info);
      this.$router.push("/index/fvstrategy");
    },
    getOrder() {
      let userinfo = JSON.parse(localStorage.getItem("tuser"));
      this.$axios
        .post("/api/getmychatinfo", { sendid: userinfo.id })
        .then((res) => {
          console.log("res1=2222==", res);
          this.alldata = res.data;
        });
    },
  },
  created() {
    this.getOrder();
  },
};
</script>
<style scoped>
/deep/ .sc-chat-window{
  z-index: 999;
}
</style>