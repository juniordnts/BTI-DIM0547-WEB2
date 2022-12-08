import React from "react";

import { useNavigate, useParams } from "react-router-dom";

import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import SaveIcon from "@mui/icons-material/Save";

import DeleteIcon from "@mui/icons-material/Delete";

import Api from "../../services/Api";

import MainPage from "../../components/MainPage";
import {
  Button,
  CircularProgress,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  TextField,
} from "@mui/material";
import { Box } from "@mui/system";

export default function CustomerCreatePage() {
  const navigate = useNavigate();
  const { itemId } = useParams();

  const [loading, setLoading] = React.useState(false);

  const [item, setItem] = React.useState(false);
  const [addresses, setAddresses] = React.useState([]);

  const getAddresses = async () => {
    setLoading(true);

    Api.defaultGetAll("address")
      .then(async (res) => {
        console.log(res);
        setAddresses(res);
      })
      .catch((err) => {
        console.log(err);
        if ("message" in err) alert(err.message);
      })
      .finally(() => {
        setLoading(false);
      });
  };

  const getItem = async () => {
    setLoading(true);

    Api.defaultGetOne("address", itemId)
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
    // console.log(parseInt(data.get("address")));
    // return;
    setLoading(true);

    const payload = {
      name: data.get("name"),
      email: data.get("email"),
      cpf: data.get("cpf"),
      born_date: data.get("born_date"),
      address: parseInt(data.get("address")),
    };

    const execFucntion = itemId
      ? Api.defaultUpdate("customer", itemId, payload)
      : Api.defaultCreate("customer", payload);

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
    Api.defaultDelete("customer", itemId)
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
    getAddresses();
    if (itemId) getItem();
  }, []);

  return (
    <MainPage active={"clientes"} title={itemId ? "Editar Cliente" : "Criar Cliente"}>
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
            label="Email"
            name="email"
            defaultValue={item.email}
          />

          <TextField
            margin="normal"
            required
            fullWidth
            label="CPF"
            name="cpf"
            defaultValue={item.cpf}
          />

          <TextField
            margin="normal"
            required
            fullWidth
            label="Data de nascimento"
            name="born_date"
            defaultValue={item.born_date}
          />

          <FormControl fullWidth style={{ marginTop: 20 }}>
            <InputLabel id="demo-simple-select-label">Endereço</InputLabel>
            <Select name="address" label="Age">
              {addresses.map((p) => (
                <MenuItem
                  value={p.id}
                  key={p.id}
                >{`${p.city} - ${p.street}, ${p.number}`}</MenuItem>
              ))}
            </Select>
          </FormControl>

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
