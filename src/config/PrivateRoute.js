import React from "react";
import { Navigate, useLocation } from "react-router-dom";

import LoadingPage from "../pages/LoadingPage";

import Var from "./appVariables";
import useMainContext from "./mainContext";

export default function PrivateRoute({ children }) {
  const { user, setUser } = useMainContext();
  const location = useLocation();

  const hasLocalUser = window.localStorage.getItem(Var.USER);

  const loadUserFromLocalStorage = async () => {
    let userStored = await JSON.parse(hasLocalUser);
    setUser(userStored);
  };

  React.useEffect(() => {
    if (hasLocalUser) loadUserFromLocalStorage();
  }, []);

  if (!user) {
    return hasLocalUser ? (
      <LoadingPage />
    ) : (
      <Navigate to="/login" state={{ from: location }} replace />
    );
  }

  return children;
}
