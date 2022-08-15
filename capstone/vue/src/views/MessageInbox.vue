<template>
  <div>
    <message-inbox></message-inbox>
  </div>
</template>

<script>
import messageInbox from "@/components/Inbox";
export default {
  name: "user-inbox",
  components: {
    messageInbox,
  },
};
</script>

<style lang="scss" scoped>
</style>