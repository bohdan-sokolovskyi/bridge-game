<template>
  <div class="nav-bar">
    <nav aria-label="main">
      <ul class="nav-ul">
        <li class="nav-li"><router-link to="/" class="link">home</router-link></li>
        <li class="nav-li"><router-link to="/about" class="link">about</router-link></li>
        <li class="nav-li" v-if="loggedIn"><router-link to="/profile" class="link">profile</router-link></li>
        <li class="nav-li" v-if="loggedIn"><router-link to="/new_game" class="link">new game</router-link></li>
        <li class="nav-li" v-if="loggedIn"><router-link to="/game_connect" class="link">game connect</router-link></li>
        <li class="nav-li" v-if="loggedIn"><router-link to="/users" class="link">users</router-link></li>
        <li class="nav-li" v-if="loggedIn"><router-link to="/games" class="link">games</router-link></li>
        <li class="nav-li" v-if="loggedIn"><router-link to="" @click="logout" class="link">logout</router-link></li>
        <li class="nav-li" v-if="!loggedIn"><router-link to="/login" class="link">login</router-link></li>
        <li class="nav-li" v-if="!loggedIn"><router-link to="/signup" class="link">signup</router-link></li>
      </ul>
    </nav>
  </div>
  <router-view/>
</template>

<script>
export default {
  name: 'app-view',
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },

  methods: {
    logout() {
      this.$store.dispatch('auth/logout').then(
          () => {
            this.$router.push('/home')
          }
      );
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  display: flex;
  flex-direction: row;
  padding: 12px 0;
  transition: .5s ease all;
  width: 90%;
  margin: 0 auto;
}

.nav-ul, .link {
  overflow-x:hidden;
  white-space:nowrap;

  font-weight: 500;
  color: black;
  list-style: none;
  text-decoration: none;
}

.nav-li {
  display:inline;
  text-transform: uppercase;
  padding: 16px;
  margin-left: 16px;
}

.link {
  font-size: 14px;
  transition: .5s ease all;
  padding-bottom: 4px;
  border-bottom: 1px solid transparent;
}

.link:hover {
  color: #42b983;
  border-color: #42b983;
}

</style>
