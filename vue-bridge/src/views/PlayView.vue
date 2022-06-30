<template>
  <h1> Game </h1>
  <button v-if="socket == null" @click="play">play</button>
  <button v-else @click="finish">finish</button>
  <hr>

  <h1 v-if="socket != null">{{thisCard()}}</h1>

  <select v-model="selected" v-if="socket != null">
    <option v-for="card in computerCards" :key="card" value="{{card}}">{{card}}</option>
  </select>

  <button v-if="computerCards != null" @click="send">send</button>

</template>

<script>
import {webConfig} from "@/web-config";
import * as SockJS from 'sockjs-client';
import {Stomp} from 'stomp';

export default {
  name: 'play-view',

  computed: {
    initCards() {
      return JSON.parse(localStorage.getItem('game')).cards;
    },

    gameId() {
      return JSON.parse(localStorage.getItem('game')).gameId;
    },
  },

  data() {
    return {
      thisCard: null,
      selected: null,
      socket: null,
      stompClient: null,
      currentCards: []
    }
  },

  created() {
    if (!this.$store.state.game.status.inGame) {
      this.$router.push('/home');
    }
  },

  methods: {
    finish() {
      this.$store.dispatch('game/finish').then(
          () => {
            this.$router.push('/home')
          }
      );
    },

    play() {
      this.currentCards = this.initCards;


      this.socket = new SockJS(webConfig.baseUrl + '/play');
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect({});
    },

    refresh(data) {
      console.log(data);
    },

    send() {
      this.stompClient.send("/topic/progress", {gameId: this.gameId, cards: [this.selected]});
      this.stompClient.subscribe("/topic/progress/", function(response) { this.thisCard = JSON.parse(response.body).card });
    },
  },

  beforeUnmount() {
    this.finish();
  }
}
</script>

<style scoped>

</style>