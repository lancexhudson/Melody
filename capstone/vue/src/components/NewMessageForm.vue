<template>
  <div>
    <form
      v-if="isManager"
      class="new-message-form"
      v-on:submit.prevent="sendMessage"
    >
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
              this.$router.back();
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
    areYouMyManager() {
      if (this.myManager === this.$store.state.user.id) {
        this.isMyManager = true;
      } else {
        this.isMyManager = false;
      }
    },
  },
  created() {
    this.setMyFollowers();
    this.setMyManager();
  },
};
</script>

<style lang="scss" scoped>
</style>