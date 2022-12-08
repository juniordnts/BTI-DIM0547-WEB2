import React from "react";
import { BrowserRouter, Navigate, Routes, Route } from "react-router-dom";

import { MainProvider } from "./config/mainContext";
import PrivateRoute from "./config/PrivateRoute";

import LoginPage from "./pages/LoginPage";
import AppointmentPage from "./pages/AppointmentPage";
import PaymentPage from "./pages/PaymentPage";
import CustomerPage from "./pages/CustomerPage";
import CouponPage from "./pages/CouponPage";
import EmployeePage from "./pages/EmployeePage";
import ProcedurePage from "./pages/ProcedurePage";
import UserPage from "./pages/UserPage";
import ProcedureCreatePage from "./pages/ProcedureCreatePage";
import EmployeeCreatePage from "./pages/EmployeeCreatePage";
import CouponCreatePage from "./pages/CouponCreatePage";
import UserCreatePage from "./pages/UserCreatePage";
import AddressPage from "./pages/AddressPage";
import AddressCreatePage from "./pages/AddressCreatePage";
import CustomerCreatePage from "./pages/CustomerCreatePage";
import AppointmentCreatePage from "./pages/AppointmentCreatePage";
import PaymentCreatePage from "./pages/PaymentCreatePage";

function App() {
  return (
    <BrowserRouter>
      <MainProvider>
        <Routes>
          <Route exact path="/" element={<Navigate to={"/login"} />} />
          <Route exact path="/login" element={<LoginPage />} />

          {/*  */}

          <Route
            path="/agendamentos"
            exact
            element={
              <PrivateRoute>
                <AppointmentPage />
              </PrivateRoute>
            }
          />

          <Route
            path="/agendamentos/criar"
            exact
            element={
              <PrivateRoute>
                <AppointmentCreatePage />
              </PrivateRoute>
            }
          />

          <Route
            path="/agendamentos/:itemId"
            exact
            element={
              <PrivateRoute>
                <AppointmentCreatePage />
              </PrivateRoute>
            }
          />

          {/*  */}

          <Route
            path="/pagamentos"
            exact
            element={
              <PrivateRoute>
                <PaymentPage />
              </PrivateRoute>
            }
          />

          <Route
            path="/pagamentos/criar"
            exact
            element={
              <PrivateRoute>
                <PaymentCreatePage />
              </PrivateRoute>
            }
          />

          <Route
            path="/pagamentos/:itemId"
            exact
            element={
              <PrivateRoute>
                <PaymentCreatePage />
              </PrivateRoute>
            }
          />

          {/*  */}

          <Route
            path="/clientes"
            exact
            element={
              <PrivateRoute>
                <CustomerPage />
              </PrivateRoute>
            }
          />

          <Route
            path="/clientes/criar"
            exact
            element={
              <PrivateRoute>
                <CustomerCreatePage />
              </PrivateRoute>
            }
          />

          <Route
            path="/clientes/:itemId"
            exact
            element={
              <PrivateRoute>
                <CustomerCreatePage />
              </PrivateRoute>
            }
          />

          {/*  */}

          <Route
            path="/enderecos"
            exact
            element={
              <PrivateRoute>
                <AddressPage />
              </PrivateRoute>
            }
          />

          <Route
            path="/enderecos/criar"
            exact
            element={
              <PrivateRoute>
                <AddressCreatePage />
              </PrivateRoute>
            }
          />

          <Route
            path="/enderecos/:itemId"
            exact
            element={
              <PrivateRoute>
                <AddressCreatePage />
              </PrivateRoute>
            }
          />

          {/*  */}

          <Route
            path="/cupons"
            exact
            element={
              <PrivateRoute>
                <CouponPage />
              </PrivateRoute>
            }
          />

          <Route
            path="/cupons/criar"
            exact
            element={
              <PrivateRoute>
                <CouponCreatePage />
              </PrivateRoute>
            }
          />

          <Route
            path="/cupons/:itemId"
            exact
            element={
              <PrivateRoute>
                <CouponCreatePage />
              </PrivateRoute>
            }
          />

          {/*  */}

          <Route
            path="/colaboradores"
            exact
            element={
              <PrivateRoute>
                <EmployeePage />
              </PrivateRoute>
            }
          />

          <Route
            path="/colaboradores/criar"
            exact
            element={
              <PrivateRoute>
                <EmployeeCreatePage />
              </PrivateRoute>
            }
          />

          <Route
            path="/colaboradores/:itemId"
            exact
            element={
              <PrivateRoute>
                <EmployeeCreatePage />
              </PrivateRoute>
            }
          />

          {/*  */}

          <Route
            path="/procedimentos"
            exact
            element={
              <PrivateRoute>
                <ProcedurePage />
              </PrivateRoute>
            }
          />

          <Route
            path="/procedimentos/criar"
            exact
            element={
              <PrivateRoute>
                <ProcedureCreatePage />
              </PrivateRoute>
            }
          />

          <Route
            path="/procedimentos/:itemId"
            exact
            element={
              <PrivateRoute>
                <ProcedureCreatePage />
              </PrivateRoute>
            }
          />

          {/*  */}

          <Route
            path="/usuarios"
            exact
            element={
              <PrivateRoute>
                <UserPage />
              </PrivateRoute>
            }
          />

          <Route
            path="/usuarios/criar"
            exact
            element={
              <PrivateRoute>
                <UserCreatePage />
              </PrivateRoute>
            }
          />

          <Route
            path="/usuarios/:itemId"
            exact
            element={
              <PrivateRoute>
                <UserCreatePage />
              </PrivateRoute>
            }
          />

          {/* <Route
          exact
          path="/perfil"
          element={
            <PrivateRoute userRequired={"user"}>
            <Perfil />
            </PrivateRoute>
          }
        /> */}
        </Routes>
      </MainProvider>
    </BrowserRouter>
  );
}

export default App;
