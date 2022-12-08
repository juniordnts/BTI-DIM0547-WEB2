import React from "react";

import { useNavigate, useParams } from "react-router-dom";

import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import SaveIcon from "@mui/icons-material/Save";

import DeleteIcon from "@mui/icons-material/Delete";

import Api from "../../services/Api";

import MainPage from "../../components/MainPage";
import { Button, CircularProgress, FormControlLabel, Switch, TextField } from "@mui/material";
import { Box } from "@mui/system";

export default function UserCreatePage() {
  const navigate = useNavigate();
  const { itemId } = useParams();

  const [loading, setLoading] = React.useState(false);

  const [item, setItem] = React.useState(false);

  const getItem = async () => {
    setLoading(true);

    Api.defaultGetOne("user", itemId)
      .then(async (res) => {
        console.log(res);
        setItem(res);
      })
      .catch((err) => {
        console.log(err);
        if ("message" in err) alert(err.message);
      })
      .finally(() => {
        setLoading(false);
      });
  };

  const postItem = async (e) => {
    e.preventDefault();
    const data = new FormData(e.currentTarget);

    setLoading(true);

    const payload = {
      username: data.get("username"),
      password: data.get("password"),
      admin: data.get("admin") === "on",
    };

    const execFucntion = itemId
      ? Api.defaultUpdate("user", itemId, payload)
      : Api.defaultCreate("user", payload);

    execFucntion
      .then(async (res) => {
        console.log(res);
        setItem(res);
        navigate(-1);
      })
      .catch((err) => {
        console.log(err);
        if ("message" in err) alert(err.message);
      })
      .finally(() => {
        setLoading(false);
      });
  };

  const deleteItem = async () => {
    Api.defaultDelete("address", itemId)
      .then(async (res) => {
        console.log(res);
        navigate(-1);
      })
      .catch((err) => {
        console.log(err);
        if ("message" in err) alert(err.message);
      })
      .finally(() => {
        setLoading(false);
      });
  };

  React.useEffect(() => {
    if (itemId) getItem();
  }, []);

  return (
    <MainPage active={"usuarios"} title={itemId ? "Editar Usuário" : "Criar Usuário"}>
      <div style={{ width: "100%", marginBottom: 15 }}>
        <Button variant="contained" onClick={() => navigate(-1)}>
          <ArrowBackIcon /> Voltar
        </Button>
      </div>

      {loading && (
        <div style={{ width: "100%", textAlign: "center" }}>
          <CircularProgress size={80} />
        </div>
      )}

      {!itemId || (itemId && item) ? (
        <Box component="form" onSubmit={postItem} noValidate sx={{ mt: 1 }}>
          <TextField
            margin="normal"
            required
            fullWidth
            label="Usuário"
            name="username"
            defaultValue={item.username}
          />

          <TextField
            margin="normal"
            required
            fullWidth
            type="password"
            label="Senha"
            name="password"
          />

          <FormControlLabel
            control={<Switch defaultChecked={item.admin} name="admin" />}
            label="Adminstrador"
          />

          <div style={{ width: "100%", textAlign: "right", marginTop: 15 }}>
            <Button variant="contained" type="submit">
              <SaveIcon /> Salvar
            </Button>

            {itemId ? (
              <Button
                style={{ marginLeft: 20 }}
                variant="contained"
                color="error"
                type="button"
                onClick={() => deleteItem()}
              >
                <DeleteIcon /> Remover
              </Button>
            ) : null}
          </div>
        </Box>
      ) : null}
    </MainPage>
  );
}
