import React from "react";
import jwt_decode from "jwt-decode";

import Var from "./appVariables";

const MainContext = React.createContext(null);

export function MainProvider({ children }) {
  const [user, setUser] = React.useState(null);

  //

  const authLogin = async (email, password) => {
    return new Promise(async (ok, err) => {
      try {
        // await setPersistence(auth, browserLocalPersistence);
        // await signInWithEmailAndPassword(auth, email, password);
        ok();
      } catch (error) {
        console.log(error);
        err(error);
      }
    });
  };

  const authSignup = async (email, password) => {
    return new Promise(async (ok, err) => {
      try {
        // await setPersistence(auth, browserLocalPersistence);
        // await createUserWithEmailAndPassword(auth, email, password);
        ok();
      } catch (error) {
        console.log(error);
        err(error);
      }
    });
  };

  const authLogout = async () => {
    window.localStorage.removeItem(Var.USER);
    window.localStorage.removeItem(Var.USERTOKEN);
    setUser(null);
    return;
  };

  //

  const loadUser = async (token) => {
    return new Promise(async (ok, err) => {
      try {
        var userData = await jwt_decode(token);
        var stringData = await JSON.stringify(userData);
        window.localStorage.setItem(Var.USER, stringData);
        window.localStorage.setItem(Var.USERTOKEN, token);
        setUser(userData);
        ok();
      } catch (error) {
        console.log(error);
        err(error);
      }
    });
  };

  //

  React.useEffect(() => {}, []);

  //

  const value = {
    user,
    setUser,
    loadUser,
    authLogin,
    authSignup,
    authLogout,
  };

  return <MainContext.Provider value={value}>{children}</MainContext.Provider>;
}

export default function useMainContext() {
  return React.useContext(MainContext);
}
