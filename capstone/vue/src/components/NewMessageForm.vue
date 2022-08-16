<template>
  <div>
    <form class="new-message-form" v-on:submit.prevent="sendMessage">
      <input
        v-model="newMessage.message"
        type="text"
        class="messageInput"
        placeholder="message"
      />
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
        userId: [],
        bandId: this.$route.params.bandId,
        message: "",
      },
    };
  },
  methods: {
    sendMessage() {
      messageService.createMessage(this.newMessage).then((response) => {
        if (response.status == 201) {
          this.$router.back();
        }
      });
      this.newMessage = {
        userId: 0,
        bandId: 0,
        message: "",
      };
    },
    setMyFollowers() {
      bandService
        .usersFollowingMyBand(this.newMessage.bandId)
        .then((response) => {
          this.newMessage.userId = response.data;
        });
    },
  },
  created() {
    this.setMyFollowers();
  },
};
</script>

<style lang="scss" scoped>
</style>