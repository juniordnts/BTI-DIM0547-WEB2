import { CircularProgress, Typography } from "@mui/material";
import React from "react";

export default function LoadingPage() {
  return (
    <div
      style={{
        width: "100vw",
        height: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        flexDirection: "column",
      }}
    >
      <CircularProgress size={80} />
      <Typography mt={5}>Carregando...</Typography>
    </div>
  );
}
