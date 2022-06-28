<template>
  <h1> About Bridge game </h1>
  <img :src="require('../assets/logo.png')" alt="logo" height="200" width="200">
  <p>
    Bridge is a yard card game. A deck of 36 cards is used. Each player is dealt 5 cards.
    The one who moves first is dealt four cards and one is placed on the table.
    Each player moves in turn and throws cards that have certain properties (see table below). Whoever runs out of cards is finished and the game is counted.
    Each card has a specific number of points (see points table below). Whoever has fewer points wins the game. Also, if someone puts the same card of different
    suits can perform a bridge and the game is over, after which there is a calculation of points according to the same rules.
  </p>

  <table-component
      :items="actionCardTable"
      :headers="actionTableHeaders"
      :caption="actionTableCaption">
  </table-component>
  <br>
  <table-component
      :items="scoreTable"
      :headers="scoreTableHeaders"
      :caption="scoreTableCaption">
  </table-component>

  <p id="author">Game created by Bohdan Sokolovskyi <a href="mailto:sokol.chemist@gmail.com">sokol.chemist@gmail.com</a></p>
</template>

<script>

import TableComponent from "@/components/TableComponent";
export default {
  name: "about-view",
  components: {TableComponent},
  data() {
    return {
      scoreTableCaption: 'Card points table:',
      scoreTableHeaders: ['card', 'points', 'exclusion'],
      scoreTable: [
        {
          card: 'A (any)',
          points: 15,
          exclusion: ''
        },
        {
          card: '10 (any)',
          points: 10,
          exclusion: ''
        },
        {
          card: 'J (pike)',
          points: 40,
          exclusion: 'if is last threw card, points must be invert'
        },
        {
          card: 'J (any other)',
          points: 20,
          exclusion:  'if is last threw card, points must be invert'
        },
        {
          card: 'Q (any)',
          points: 10,
          exclusion: ''
        },
        {
          card: 'K (any)',
          points: 10,
          exclusion: ''
        },
        {
          card: 'K (heart)',
          points: 50,
          exclusion: ''
        },
        {
          card: 'any other',
          points: 0,
          exclusion: ''
        }
      ],

      actionTableCaption: 'Card action table:',
      actionTableHeaders: ['card', 'action'],
      actionCardTable: [
        {
          card: 'A (any)',
          action: 'opponent skip move'
        },
        {
          card: '6 (any)',
          action: 'take a card until it covers'
        },
        {
          card: '7 (any)',
          action: 'opponent must take 2 card and skip move'
        },
        {
          card: '8 (any)',
          action: 'opponent must take 1 card'
        },
        {
          card: '9 (any)',
          action: 'hide a card in a deck'
        },
        {
          card: '10 (any)',
          action: 'nothing',
        },
        {
          card: 'J (any)',
          action: 'order any card suit'
        },
        {
          card: 'Q (any)',
          action: 'nothing'
        },
        {
          card: 'K (heart)',
          action: 'opponent must take 5 cards'
        },
        {
          card: 'K (any other)',
          action: 'nothing'
        }
      ]
    }
  }
}
</script>

<style scoped>
  #author {
    font-weight: bold;
    margin-top: 30px;
    text-align: left;
  }
</style>