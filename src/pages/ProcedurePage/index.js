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

export default function ProcedurePage() {
  const navigate = useNavigate();

  const [loading, setLoading] = React.useState(false);
  const [itemList, setItemList] = React.useState([]);

  const getList = async () => {
    setLoading(true);

    Api.defaultGetAll("procedure")
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
    <MainPage active={"procedimentos"} title={"Procedimentos"}>
      <div style={{ width: "100%", textAlign: "right", marginBottom: 15 }}>
        <Button variant="contained" onClick={() => navigate("criar")}>
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
                <TableCell>
                  <b>Nome</b>
                </TableCell>
                <TableCell>
                  <b>Preço</b>
                </TableCell>
                <TableCell align="right"></TableCell>
              </TableRow>
            </TableHead>

            <TableBody>
              {itemList.map((row) => {
                return (
                  <TableRow key={row.id}>
                    <TableCell>{row.name}</TableCell>
                    <TableCell>
                      {`R$ ${parseFloat(row.price).toFixed(2).replace(".", ",")}`}
                    </TableCell>
                    <TableCell align="right">
                      <Button
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
