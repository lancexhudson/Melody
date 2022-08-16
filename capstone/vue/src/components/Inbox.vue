<template>
  <div class="message-container">
    <div>
      <router-link v-bind:to="{ name: 'home' }" class="logoContainer">
        <img
          src="..\assets\LOGO TRANSPARENCY white.png"
          alt="Melody Logo"
          class="homelogo"
        />
      </router-link>
    </div>
    <div
      class="smallTextBox"
      v-for="message in this.$store.state.myMessages"
      v-bind:key="message.messageId"
    >
      <p class="fromBand">From: {{ message.fromBand }}</p>
      <p class="messageBody">
        {{ message.message }}
      </p>
    </div>
  </div>
</template>

<script>
import MessageService from "@/services/MessageService.js";
export default {
  name: "inbox-message",

  methods: {
    listMyMessages() {
      MessageService.getMessagesForUser(this.$store.state.user.id).then(
        (response) => {
          this.$store.commit("SET_MESSAGES", response.data);
        }
      );
    },
  },
  created() {
    this.listMyMessages();
  },
};
</script>

<style>
/* .message-container {
  border: rgb(0, 0, 0) solid;
  background-color: rgb(0, 0, 0);
} */

.smallTextBox {
  border: white solid;
  border-radius: 1rem;
  background-color: 255, 255, 255, 0.8;

  backdrop-filter: blur(10px);
  margin: 1rem;
  margin-right: auto;
  max-width: 50rem;
  margin-left: auto;
}

.messageBody {
  color: white;
  font-size: 20pt;
}
.fromBand {
  color: white;
  font-size: 20pt;
  font-weight: 900;
}
</style>