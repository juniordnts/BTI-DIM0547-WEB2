import React from "react";

import { useNavigate } from "react-router-dom";

import Typography from "@mui/material/Typography";
import Link from "@mui/material/Link";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";

import AddIcon from "@mui/icons-material/Add";

import Api from "../../services/Api";

import MainPage from "../../components/MainPage";
import { Button, CircularProgress } from "@mui/material";
import useMainContext from "../../config/mainContext";

export default function PaymentPage() {
  const navigate = useNavigate();

  const { user } = useMainContext();

  const [loading, setLoading] = React.useState(false);
  const [itemList, setItemList] = React.useState([]);

  const getList = async () => {
    setLoading(true);

    Api.defaultGetAll("payment")
      .then(async (res) => {
        console.log(res);
        setItemList(res);
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
    getList();
  }, []);

  return (
    <MainPage active={"pagamentos"} title={"Pagamentos"}>
      <div style={{ width: "100%", textAlign: "right", marginBottom: 15 }}>
        <Button
          disabled={user.sub != "admin"}
          variant="contained"
          onClick={() => navigate("criar")}
        >
          <AddIcon /> Criar
        </Button>
      </div>

      {loading && (
        <div style={{ width: "100%", textAlign: "center" }}>
          <CircularProgress size={80} />
        </div>
      )}

      {!loading && itemList.length > 0 && (
        <React.Fragment>
          <Table size="small">
            <TableHead>
              <TableRow>
                <TableCell>Código</TableCell>
                <TableCell>Status</TableCell>
                <TableCell>Total</TableCell>
                <TableCell align="right"></TableCell>
              </TableRow>
            </TableHead>

            <TableBody>
              {itemList.map((row) => {
                return (
                  <TableRow key={row.id}>
                    <TableCell>{row.code}</TableCell>
                    <TableCell>{row.status}</TableCell>
                    <TableCell>R$ {parseFloat(row.total).toFixed(2).replace(".", ",")}</TableCell>
                    <TableCell align="right">
                      <Button
                        disabled={user.sub != "admin"}
                        size="small"
                        variant="contained"
                        onClick={() => navigate(String(row.id))}
                      >
                        Editar
                      </Button>
                    </TableCell>
                  </TableRow>
                );
              })}
            </TableBody>
          </Table>
        </React.Fragment>
      )}

      {!loading && itemList.length === 0 && <Typography>Nenhum item encontrado</Typography>}
    </MainPage>
  );
}
