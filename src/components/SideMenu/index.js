import * as React from "react";

import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import ListSubheader from "@mui/material/ListSubheader";

import EventIcon from "@mui/icons-material/Event";
import PeopleIcon from "@mui/icons-material/People";
import LocalActivityIcon from "@mui/icons-material/LocalActivity";
import VerifiedUserIcon from "@mui/icons-material/VerifiedUser";
import PointOfSaleIcon from "@mui/icons-material/PointOfSale";
import BallotIcon from "@mui/icons-material/Ballot";
import Groups2Icon from "@mui/icons-material/Groups2";
import ContactMailIcon from "@mui/icons-material/ContactMail";

import { Divider } from "@mui/material";
import { useNavigate } from "react-router-dom";
import useMainContext from "../../config/mainContext";

export default function SideMenu({ selected }) {
  const navigate = useNavigate();
  const { user } = useMainContext();

  return (
    <React.Fragment>
      <ListItemButton
        selected={selected === "agendamentos"}
        onClick={() => {
          navigate("/agendamentos");
        }}
      >
        <ListItemIcon>
          <EventIcon />
        </ListItemIcon>
        <ListItemText primary="Agendamentos" />
      </ListItemButton>

      <ListItemButton
        selected={selected === "pagamentos"}
        onClick={() => {
          navigate("/pagamentos");
        }}
      >
        <ListItemIcon>
          <PointOfSaleIcon />
        </ListItemIcon>
        <ListItemText primary="Pagamentos" />
      </ListItemButton>

      <ListItemButton
        selected={selected === "clientes"}
        onClick={() => {
          navigate("/clientes");
        }}
      >
        <ListItemIcon>
          <PeopleIcon />
        </ListItemIcon>
        <ListItemText primary="Clientes" />
      </ListItemButton>

      <ListItemButton
        selected={selected === "enderecos"}
        onClick={() => {
          navigate("/enderecos");
        }}
      >
        <ListItemIcon>
          <ContactMailIcon />
        </ListItemIcon>
        <ListItemText primary="Endereços" />
      </ListItemButton>

      <Divider sx={{ my: 1 }} />

      <ListSubheader component="div" inset>
        Gerenciamento
      </ListSubheader>

      <ListItemButton
        disabled={user.sub != "admin"}
        selected={selected === "cupons"}
        onClick={() => {
          navigate("/cupons");
        }}
      >
        <ListItemIcon>
          <LocalActivityIcon />
        </ListItemIcon>
        <ListItemText primary="Cupons" />
      </ListItemButton>

      <ListItemButton
        disabled={user.sub != "admin"}
        selected={selected === "colaboradores"}
        onClick={() => {
          navigate("/colaboradores");
        }}
      >
        <ListItemIcon>
          <Groups2Icon />
        </ListItemIcon>
        <ListItemText primary="Colaboradores" />
      </ListItemButton>

      <ListItemButton
        disabled={user.sub != "admin"}
        selected={selected === "procedimentos"}
        onClick={() => {
          navigate("/procedimentos");
        }}
      >
        <ListItemIcon>
          <BallotIcon />
        </ListItemIcon>
        <ListItemText primary="Procedimentos" />
      </ListItemButton>

      <ListItemButton
        disabled={user.sub != "admin"}
        selected={selected === "usuarios"}
        onClick={() => {
          navigate("/usuarios");
        }}
      >
        <ListItemIcon>
          <VerifiedUserIcon />
        </ListItemIcon>
        <ListItemText primary="Usuários" />
      </ListItemButton>
    </React.Fragment>
  );
}
