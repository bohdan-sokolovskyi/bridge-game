<template>
  <div class="container">
    <h1>Profile</h1>
    <ul>
      <li><strong>First name: </strong>{{currentUser.firstName}}</li>
      <li><strong>Last name: </strong>{{currentUser.lastName}}</li>
      <li><strong>Email: </strong>{{currentUser.email}}</li>
      <li><strong>Sex: </strong>{{currentUser.sex}}</li>
      <li><strong>Birth: </strong>{{currentUser.birth}}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'profile-view',
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  }
};
</script>

<style scoped>
li {
  padding: 16px;
  margin-left: 16px;
  text-align: left;
}

strong {
  display: block;
}

ul {
  padding: 0;
  list-style: none;
}
</style>