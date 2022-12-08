import React from "react";

import { useNavigate, useParams } from "react-router-dom";

import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import SaveIcon from "@mui/icons-material/Save";

import DeleteIcon from "@mui/icons-material/Delete";

import Api from "../../services/Api";

import MainPage from "../../components/MainPage";
import { Button, CircularProgress, TextField } from "@mui/material";
import { Box } from "@mui/system";

export default function ProcedureCreatePage() {
  const navigate = useNavigate();
  const { itemId } = useParams();

  const [loading, setLoading] = React.useState(false);

  const [item, setItem] = React.useState(false);

  const getItem = async () => {
    setLoading(true);

    Api.defaultGetOne("procedure", itemId)
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
      name: data.get("name"),
      price: data.get("price"),
    };

    const execFucntion = itemId
      ? Api.defaultUpdate("procedure", itemId, payload)
      : Api.defaultCreate("procedure", payload);

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
    <MainPage
      active={"procedimentos"}
      title={itemId ? "Editar Procedimento" : "Criar Procedimento"}
    >
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
            label="Nome"
            name="name"
            defaultValue={item.name}
          />

          <TextField
            margin="normal"
            required
            fullWidth
            label="Preço"
            name="price"
            defaultValue={item.price}
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
