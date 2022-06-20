<template>
   <div class="login-page">
      <transition name="fade">
        <div v-if="!registerActive"></div>
      </transition>
   </div>
</template>

<script>
export default {
  name: "LoginView"
  computed: {
    registerActive() {
      this.$store.state.registerActive;
    }
  }
}
</script>

<style scoped>

</style>