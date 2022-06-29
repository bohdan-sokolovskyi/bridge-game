<template>
  <h1 v-if="message !== ''">{{message}}</h1>
  <div v-if="items != null">
    <table-component
        :items="items"
        :headers="headers"
        :caption="caption"
    ></table-component>
  </div>
</template>

<script>
import infoService from '@/services/info-service';
import TableComponent from "@/components/TableComponent";

export default {
  name: 'games-view',
  components: {TableComponent},
  data() {
    return {
      caption: 'games',
      headers: ['game id', 'game name', 'players'],
      items: null,
      message: '',
    }
  },

  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },

  created() {
    if (!this.loggedIn) {
      this.$router.push('/home');
    }

    infoService.getGames().then(
        (response) => {
          this.items = response.data;
        },

        (error) => {
          this.message =
              (error.response &&
                  error.response.data &&
                  error.response.data.message) ||
              error.message ||
              error.toString();
        }
    );
  }
}
</script>

<style scoped>
h1 {
  margin-top: 20%;
  text-align: center;
}
</style>