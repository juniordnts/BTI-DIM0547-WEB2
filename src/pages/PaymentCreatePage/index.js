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

export default function PaymentCreatePage() {
  const navigate = useNavigate();
  const { itemId } = useParams();

  const [loading, setLoading] = React.useState(false);

  const [item, setItem] = React.useState(false);

  const getItem = async () => {
    setLoading(true);

    Api.defaultGetOne("payment", itemId)
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
      code: data.get("code"),
      status: data.get("status"),
      qr: data.get("qr"),
      total: data.get("discount"),
    };

    const execFucntion = itemId
      ? Api.defaultUpdate("payment", itemId, payload)
      : Api.defaultCreate("payment", payload);

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
    Api.defaultDelete("payment", itemId)
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
    <MainPage active={"pagamentos"} title={itemId ? "Editar Pagamento" : "Criar Pagamento"}>
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
            disabled
            fullWidth
            label="Código"
            name="code"
            defaultValue={item.code}
          />

          <TextField type={"hidden"} name="qr" defaultValue={item.qr} />

          <TextField
            margin="normal"
            required
            disabled
            fullWidth
            label="Valor Total"
            name="total"
            defaultValue={item.total}
          />

          <FormControl fullWidth style={{ marginTop: 20 }}>
            <InputLabel id="demo-simple-select-label">Status</InputLabel>
            <Select name="status" label="Status" defaultValue={item.status}>
              <MenuItem value={"pending"}>Pending</MenuItem>
              <MenuItem value={"approved"}>Approved</MenuItem>
              <MenuItem value={"inprocess"}>Inprocess</MenuItem>
              <MenuItem value={"inmediation"}>Inmediation</MenuItem>
              <MenuItem value={"rejected"}>Rejected</MenuItem>
              <MenuItem value={"cancelled"}>Cancelled</MenuItem>
              <MenuItem value={"refunded"}>Refunded</MenuItem>
              <MenuItem value={"chargedback"}>Chargedback</MenuItem>
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

          {item?.qr ? (
            <div style={{ textAlign: "center", marginTop: 30 }}>
              <img
                alt="QR"
                style={{ width: "80%", maxHeight: "70vh", objectFit: "contain" }}
                src={"data:image/png;base64," + item?.qr}
              />
            </div>
          ) : null}
        </Box>
      ) : null}
    </MainPage>
  );
}
