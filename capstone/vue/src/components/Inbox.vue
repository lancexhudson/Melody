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
      <p class="fromBand">from: {{ message.fromBand }}</p>
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
.message-container {
  border: rgb(0, 0, 0) solid;
  background-color: rgb(0, 0, 0);
}

.smallTextBox {
  border: white solid;
  border-radius: 2rem;
  background-color: white;
  margin: 1rem;
  margin-right: 20rem;
  margin-left: 20rem;
}

.messageBody {
  color: black;
  font-size: 15pt;
}
.fromBand {
  color: black;
  font-size: 15pt;
}
</style>