<template>
  <div>
    <message-inbox></message-inbox>
  </div>
</template>

<script>
import messageInbox from "@/components/Inbox";
export default {
  beforeCreate: function () {
    document.body.className = "inbox";
  },
  name: "user-inbox",
  components: {
    messageInbox,
  },
};
</script>

<style>
body.inbox {
  display: flex;
  text-align: center;
  flex-direction: column;
  justify-content: flex-center;

  align-content: center;
  background: black;
  color: white;
  background-image: url("~@/assets/crowd.png");
  background-position: bottom;
  background-size: cover;
  background-attachment: fixed;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  height: 100vh;
}
</style>