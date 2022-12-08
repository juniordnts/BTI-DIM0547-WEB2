import React from "react";

import { useNavigate, useParams } from "react-router-dom";

import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import SaveIcon from "@mui/icons-material/Save";
import DeleteIcon from "@mui/icons-material/Delete";

import Api from "../../services/Api";

import MainPage from "../../components/MainPage";
import {
  Button,
  Checkbox,
  CircularProgress,
  FormControl,
  FormControlLabel,
  FormGroup,
  InputLabel,
  MenuItem,
  Select,
  TextField,
} from "@mui/material";
import { Box } from "@mui/system";

export default function AppointmentCreatePage() {
  const navigate = useNavigate();
  const { itemId } = useParams();

  const [loading, setLoading] = React.useState(false);

  const [item, setItem] = React.useState(false);
  const [customers, setCustomers] = React.useState([]);
  const [employees, setEmployees] = React.useState([]);
  const [procedures, setProcedures] = React.useState([]);
  const [coupons, setCoupons] = React.useState([]);

  const [proceduresSelect, setProceduresSelect] = React.useState([]);

  const getCustomers = async () => {
    Api.defaultGetAll("customer")
      .then(async (res) => {
        console.log(res);
        setCustomers(res);
      })
      .catch((err) => {
        console.log(err);
        if ("message" in err) alert(err.message);
      });
  };

  const getEmployees = async () => {
    Api.defaultGetAll("employee")
      .then(async (res) => {
        console.log(res);
        setEmployees(res);
      })
      .catch((err) => {
        console.log(err);
        if ("message" in err) alert(err.message);
      });
  };

  const getProcedures = async () => {
    Api.defaultGetAll("procedure")
      .then(async (res) => {
        console.log(res);
        setProcedures(res);
      })
      .catch((err) => {
        console.log(err);
        if ("message" in err) alert(err.message);
      });
  };

  const getCoupons = async () => {
    Api.defaultGetAll("coupon")
      .then(async (res) => {
        console.log(res);
        setCoupons(res);
      })
      .catch((err) => {
        console.log(err);
        if ("message" in err) alert(err.message);
      });
  };

  //

  const getItem = async () => {
    setLoading(true);

    Api.defaultGetOne("appointment", itemId)
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

    //   "date": "2019-07-17",
    // "customer": 1,
    // "employee": 1,
    // "coupon": 1,
    // "procedures": [
    // 	3
    // ]

    const payload = {
      date: data.get("date"),
      customer: data.get("customer"),
      employee: data.get("employee"),
      coupon: data.get("coupon") || null,
      procedures: proceduresSelect,
    };

    const execFucntion = itemId
      ? Api.defaultUpdate("appointment", itemId, payload)
      : Api.defaultCreate("appointment", payload);

    execFucntion
      .then(async (res) => {
        console.log(res);
        setItem(res);
        setProceduresSelect(res.procedures);
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
    Api.defaultDelete("appointment", itemId)
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
    getCustomers();
    getEmployees();
    getProcedures();
    getCoupons();

    if (itemId) getItem();
  }, []);

  return (
    <MainPage active={"agendamentos"} title={itemId ? "Editar Agendamento" : "Criar Agendamento"}>
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
            label="Data"
            name="date"
            defaultValue={item.date}
          />

          <FormControl fullWidth style={{ marginTop: 20 }}>
            <InputLabel>Cliente</InputLabel>
            <Select name="customer" label="Cliente" defaultChecked={item.customer}>
              {customers.map((p) => (
                <MenuItem value={p.id} key={p.id} defaultValue={item.customer === p?.id}>
                  {p.name}
                </MenuItem>
              ))}
            </Select>
          </FormControl>

          <FormControl fullWidth style={{ marginTop: 20 }}>
            <InputLabel>Colaborador</InputLabel>
            <Select name="employee" label="Colaborador" defaultValue={item?.employee?.id}>
              {employees.map((p) => (
                <MenuItem value={p?.id} key={p?.id}>
                  {p.name}
                </MenuItem>
              ))}
            </Select>
          </FormControl>

          <FormControl fullWidth style={{ marginTop: 20 }}>
            <InputLabel>Cupom</InputLabel>
            <Select name="coupon" label="Cupom" defaultValue={item?.coupon?.id || undefined}>
              {coupons.map((p) => (
                <MenuItem value={p?.id} key={p?.id}>
                  {p.code} - {p.discount}%
                </MenuItem>
              ))}
            </Select>
          </FormControl>

          {/*  */}

          <FormGroup sx={{ mt: 2 }}>
            {procedures.map((k) => (
              <FormControlLabel
                value={k?.id}
                defaultValue={item?.procedures?.includes(k?.id) || undefined}
                onChange={(v) => {
                  if (v.currentTarget.checked) {
                    setProceduresSelect([...proceduresSelect, parseInt(v.currentTarget.value)]);
                  } else {
                    setProceduresSelect(
                      proceduresSelect.filter((i) => i !== parseInt(v.currentTarget.value))
                    );
                  }
                }}
                name="procedure"
                control={<Checkbox />}
                label={`${k.name} - R$ ${parseFloat(k.price).toFixed(2).replace(".", ",")}`}
              />
            ))}
          </FormGroup>

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
