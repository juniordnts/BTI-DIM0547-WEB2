import axios from "axios";
import Var from "../config/appVariables";

const BASE_URL =
  process.env.NODE_ENV === "development" ? "http://localhost:8080" : "http://localhost:8080";

const AUTHORIZATION = (headers = {}) => {
  return {
    headers: {
      Authorization: `Bearer ${localStorage.getItem(Var.USERTOKEN) ?? undefined}`,
      ...headers,
    },
  };
};

const errorHandler = (err) => {
  process.env.NODE_ENV === "development" && console.log("Returned Err: ", err);
  if (err.response) {
    process.env.NODE_ENV === "development" && console.log("Returned Response: ", err.response);
    return err.response.data;
  }
  return { success: false, message: "Erro!" };
};

const successHandler = (Resolve, Reject, res) => {
  process.env.NODE_ENV === "development" && console.log(res.data);
  if (res.data.erro) {
    Reject(res.data);
  }
  Resolve(res.data);
};

const Api = {
  BASE_URL: BASE_URL,

  userLogin: (BODY) => {
    return new Promise((Resolve, Reject) => {
      let URL = BASE_URL + "/user/auth";
      axios
        .post(URL, BODY)
        .then((res) => {
          successHandler(Resolve, Reject, res);
        })
        .catch((err) => {
          Reject(errorHandler(err));
        });
    });
  },

  userSignin: (BODY) => {
    return new Promise((Resolve, Reject) => {
      let URL = BASE_URL + "/user";
      axios
        .post(URL, BODY)
        .then((res) => {
          successHandler(Resolve, Reject, res);
        })
        .catch((err) => {
          Reject(errorHandler(err));
        });
    });
  },

  userUpdate: (BODY) => {
    return new Promise((Resolve, Reject) => {
      let URL = BASE_URL + "/v1/user/update";
      axios
        .put(URL, BODY, AUTHORIZATION())
        .then((res) => {
          successHandler(Resolve, Reject, res);
        })
        .catch((err) => {
          Reject(errorHandler(err));
        });
    });
  },

  //

  defaultCreate: (RESOURCE, BODY) => {
    return new Promise((Resolve, Reject) => {
      let URL = BASE_URL + `/${RESOURCE}`;
      axios
        .post(URL, BODY, AUTHORIZATION())
        .then((res) => {
          successHandler(Resolve, Reject, res);
        })
        .catch((err) => {
          Reject(errorHandler(err));
        });
    });
  },

  defaultUpdate: (RESOURCE, ID, BODY) => {
    return new Promise((Resolve, Reject) => {
      let URL = BASE_URL + `/${RESOURCE}/${ID}`;
      axios
        .put(URL, BODY, AUTHORIZATION())
        .then((res) => {
          successHandler(Resolve, Reject, res);
        })
        .catch((err) => {
          Reject(errorHandler(err));
        });
    });
  },

  defaultGetOne: (RESOURCE, ID) => {
    return new Promise((Resolve, Reject) => {
      let URL = BASE_URL + `/${RESOURCE}/${ID}`;
      axios
        .get(URL, { ...AUTHORIZATION() })
        .then((res) => {
          successHandler(Resolve, Reject, res);
        })
        .catch((err) => {
          Reject(errorHandler(err));
        });
    });
  },

  defaultGetAll: (RESOURCE) => {
    return new Promise((Resolve, Reject) => {
      let URL = BASE_URL + `/${RESOURCE}/list`;
      axios
        .get(URL, { ...AUTHORIZATION() })
        .then((res) => {
          successHandler(Resolve, Reject, res);
        })
        .catch((err) => {
          Reject(errorHandler(err));
        });
    });
  },

  defaultDelete: (RESOURCE, ID) => {
    return new Promise((Resolve, Reject) => {
      let URL = BASE_URL + `/${RESOURCE}/${ID}`;
      axios
        .delete(URL, { ...AUTHORIZATION() })
        .then((res) => {
          successHandler(Resolve, Reject, res);
        })
        .catch((err) => {
          Reject(errorHandler(err));
        });
    });
  },

  //
};

export default Api;
