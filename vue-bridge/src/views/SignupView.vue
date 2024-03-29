<template>
  <div class="col-md-12">
    <div class="card card-container">
      <Form @submit="handleRegister" :validation-schema="schema">
        <div v-if="!successful">
          <div class="form-group">
            <label for="firstName">First name</label>
            <Field name="firstName" type="text" class="form-control" />
            <ErrorMessage name="firstName" class="error-feedback" />
          </div>
          <div class="form-group">
            <label for="lastName">First name</label>
            <Field name="lastName" type="text" class="form-control" />
            <ErrorMessage name="lastName" class="error-feedback" />
          </div>
          <div class="form-group">
            <label for="email">Email</label>
            <Field name="email" type="email" class="form-control" />
            <ErrorMessage name="email" class="error-feedback" />
          </div>
          <div class="form-group">
            <label for="sex">Sex</label>
            <Field name="sex" class="form-control" as="select">
              <option value="male">male</option>
              <option value="female">female</option>
              <option value="other">other</option>
            </Field>
            <ErrorMessage name="sex" class="error-feedback" />
          </div>
          <div class="form-group">
            <label for="birth">Birth</label>
            <Field name="birth" type="date" class="form-control" />
            <ErrorMessage name="birth" class="error-feedback" />
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <Field name="password" type="password" class="form-control" />
            <ErrorMessage name="password" class="error-feedback" />
          </div>

          <div class="form-group">
            <button class="btn btn-primary btn-block" :disabled="loading">
              <span
                  v-show="loading"
                  class="spinner-border spinner-border-sm"
              ></span>
              Sign Up
            </button>
          </div>
        </div>
      </Form>

      <div
          v-if="message"
          class="alert"
          :class="successful ? 'alert-success' : 'alert-danger'"
      >
        {{ message }}
      </div>
    </div>
  </div>
</template>

<script>
import { Form, Field, ErrorMessage } from 'vee-validate';
import * as yup from 'yup';
import moment from 'moment';

export default {
  name: 'signup-view',
  components: {
    Form,
    Field,
    ErrorMessage
  },

  data() {
    const schema = yup.object().shape({
      firstName: yup
          .string()
          .required('first name is required!')
          .min(3, 'must be at least 3 characters!')
          .max(20, 'must be maximum 20 characters!'),
      lastName: yup
          .string()
          .required('last name is required!')
          .min(3, 'must be as least 3 characters!')
          .max(20, 'must be maximum 20 characters!'),
      email: yup
          .string()
          .required('email is required!')
          .email('email is invalid!')
          .max(50, 'must be maximum 50 characters!'),
      password: yup
          .string()
          .required('password is required!')
          .min(6, 'must be as least 6 characters!')
          .max(40, 'must be maximum 40 characters!'),
      sex: yup
          .string()
          .required('sex is required'),
      birth: yup
          .string()
          .transform((val) => moment(val).format('DD.MM.YYYY'))
          .required('date is required')
    });

    return {
      successful: false,
      loading: false,
      message: '',
      schema
    }
  },

  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },

  mounted() {
    if(this.loggedIn) {
      this.$router.push('/profile');
    }
  },


  methods: {
    handleRegister(user) {
      this.message = '';
      this.successful = false;
      this.loading = false;

      this.$store.dispatch('auth/register', user).then(
          (data) => {
            this.message = data.message;
            this.successful = true;
            this.loading = false;
          },

          (error) => {
            this.message =
                (error.response &&
                    error.response.data &&
                    error.response.data.message) ||
                error.message ||
                error.toString();
            this.successful = false;
            this.loading = false;
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