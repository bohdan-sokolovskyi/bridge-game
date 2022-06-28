<template>
  <div class="nav-bar">
    <nav aria-label="main">
      <ul>
        <li><router-link to="/" class="link">home</router-link></li>
        <li><router-link to="/about" class="link">about</router-link></li>
        <li><router-link to="/dashboard" class="link">dashboard</router-link></li>
        <li><router-link v-if="!loggedIn" to="/login" class="link">login</router-link></li>
        <li><router-link v-if="!loggedIn" to="/signup" class="link">signup</router-link></li>
        <li><router-link v-if="loggedIn" to="/profile" class="link">profile</router-link></li>
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

ul, .link {
  overflow-x:hidden;
  white-space:nowrap;

  font-weight: 500;
  color: black;
  list-style: none;
  text-decoration: none;
}

li {
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
