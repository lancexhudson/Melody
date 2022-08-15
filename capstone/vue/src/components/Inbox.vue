<template>
  <div class="message-container">
    <p>Hello World</p>
    <p
      class="messageBody"
      v-for="message in this.$store.state.myMessages"
      v-bind:key="message.messageId"
    >
      {{ message.message }}
    </p>
  </div>
</template>

<script>
import MessageService from "@/services/MessageService.js";

export default {
  name: "inbox-message",

  computed: {
    name() {
      return MessageService;
    },
  },

  methods: {
    listMyMessages() {
      MessageService.getMessagesForUser(3).then((response) => {
        this.$store.commit("SET_MESSAGES", response.data);
      });
    },
  },
  created() {
    this.listMyMessages();
  },
};
</script>

<style lang="scss" scoped>
</style>