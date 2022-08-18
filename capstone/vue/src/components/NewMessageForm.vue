<template>
  <div class="messageCard" v-if="isManager">
    <form class="new-message-form" v-on:submit.prevent="sendMessage">
      <div>
        <p class="sendMessage">MESSAGE YOUR FOLLOWERS</p>
      </div>
      <input
        v-model="newMessage.message"
        type="text"
        class="messageInput"
        placeholder="message"
      />
      <br />
      <button class="sendButton">Send</button>
    </form>
  </div>
</template>

<script>
import messageService from "@/services/MessageService.js";
import bandService from "@/services/BandService.js";
export default {
  name: "message-form",
  data() {
    return {
      newMessage: {
        userId: 0,
        bandId: this.$route.params.bandId,
        message: "",
      },
      myFollowers: [],
      myManager: 0,
    };
  },
  computed: {
    isManager() {
      return this.myManager == this.$store.state.user.id ? true : false;
    },
  },
  methods: {
    sendMessage() {
      if (this.myFollowers.length != 0) {
        this.myFollowers.forEach((user) => {
          this.newMessage.userId = user;
          messageService.createMessage(this.newMessage).then((response) => {
            if (response.status == 201) {
              this.$router.go(-1);
            }
          });
        });
      }
      this.newMessage = {
        userId: 0,
        bandId: this.$route.params.bandId,
        message: "",
      };
    },
    setMyFollowers() {
      bandService
        .usersFollowingMyBand(this.newMessage.bandId)
        .then((response) => {
          this.myFollowers = response.data;
        });
    },
    setMyManager() {
      bandService.getMyManager(this.newMessage.bandId).then((response) => {
        this.myManager = response.data;
      });
    },
  },
  created() {
    this.setMyFollowers();
    this.setMyManager();
  },
};
</script>

<style>
.sendMessage {
  font-size: 15pt;
  font-weight: 900;
  text-decoration: underline;
}
.messageInput {
  width: 330px;
  height: 50px;
  padding: 0;
  border: 3px;
  margin: 0;
}
.messageCard {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-width: auto;
  width: 350px;
  height: auto;
  min-height: 150px;
  border: solid rgb(255, 255, 255) 1px;
  background: rgba(0, 0, 0, 0.7);
  border-radius: 0.5rem;
}
</style>