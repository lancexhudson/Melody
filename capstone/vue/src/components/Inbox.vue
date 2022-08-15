<template>
  <div class="message-container">
    <p class="messageBody"></p>
  </div>
</template>

<script>
import MessageService from "@/services/MessageServices.js";

export default {
  name: "inbox-message",
  computed: {
    name() {
      return MessageService;
    },
  },
};
</script>

<style lang="scss" scoped>
</style>