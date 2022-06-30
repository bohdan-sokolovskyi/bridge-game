<template>
  <div class="card card-container">
    <Form @submit="handlePlay" :validation-schema="schema">
      <div class="form-group">
        <label for="email">Email</label>
        <Field name="email" type="text" class="form-control" />
        <ErrorMessage name="email" class="error-feedback" />
      </div>

      <div class="form-group">
        <label for="gameName">Game name</label>
        <Field name="gameName" type="text" class="form-control" />
        <ErrorMessage name="password" class="error-feedback" />
      </div>

      <div class="form-group">
        <button class="btn btn-primary btn-block" :disabled="loading">
          <span
              v-show="loading"
              class="spinner-border spinner-border-sm"
          ></span>
          <span>play</span>
        </button>
      </div>

      <div class="form-group">
        <div v-if="message" class="alert alert-danger" role="alert">
          {{ message }}
        </div>
      </div>
    </Form>
  </div>
</template>

<script>
import {ErrorMessage, Field, Form} from "vee-validate";
import * as yup from "yup";

export default {
  name: "new-game-view",
  components: {
    Form,
    Field,
    ErrorMessage
  },

  data() {
    const schema = yup.object().shape({
      email: yup.string().required('email is required!'),
      gameName: yup.string().min(6).max(10).required('password is required!')
    });

    return {
      loading: false,
      message: '',
      schema
    }
  },

  computed: {
    inGame() {
      return this.$store.state.game.status.inGame;
    }
  },

  created() {
    if (this.inGame) {
      this.$router.push('/play');
    }
  },

  methods: {
    handlePlay(gameData) {
      this.loading = true;

      this.$store.dispatch('game/create', gameData).then(
          () => {
            this.$router.push('/play')
          },

          (error) => {
            this.loading = false;
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
}
</script>

<style scoped>

label {
  display: block;
  margin-top: 10px;
}
.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}
.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}
.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
.error-feedback {
  color: red;
}

</style>