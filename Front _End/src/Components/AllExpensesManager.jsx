import React from "react";

import { Grid } from "@mui/material";
import ManagerPieChart from "./ManagerPieChart";

import BarGraph from "./BarGraph";

export default function AllExpensesManager() {
  return (
    <>
      <Grid
        container
        direction="column"
        spacing={1}
        sx={{ justifyContent: "center", alignItems: "center" }}
      >
        <Grid
          item
          sx={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            // border: "1px solid black",
            // backgroundColor: "#E0E0E0",
            marginTop: "2rem",
            // boxShadow: "0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 black",
          }}
        >
          <ManagerPieChart />
        </Grid>{" "}
        <Grid item xs={2}></Grid>
        <Grid
          item
          sx={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            width: "840px",
            marginTop: "4px",
            backgroundColor: "white",
          }}
        >
          <BarGraph />
        </Grid>
      </Grid>
    </>
  );
}
