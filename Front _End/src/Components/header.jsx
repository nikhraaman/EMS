import React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";

// import MenuIcon from "@mui/icons-material/Menu";
import AdpLogo from "../images/adpRedLogo.png";
import { Grid } from "@mui/material";

export default function header() {
  return (
    <>
      <Box sx={{ flexGrow: "1" }}>
        <AppBar
          position="static"
          sx={{ backgroundColor: "#ffff", padding: "4px" }}
        >
          <Toolbar>
            <Grid container spacing={2}>
              <Grid item md={4}>
                <Box
                  component="img"
                  sx={{
                    height: 50,
                    width: 80,
                  }}
                  alt="AdpLogo"
                  src={AdpLogo}
                />
              </Grid>
              <Grid item md={8}>
                <Box sx={{ display: "flex", justifyContent: "flex-start" }}>
                  <Typography
                    variant="h4"
                    sx={{
                      color: "#26328c",
                      fontWeight: "bold",
                      fontFamily: "proxima nova",
                    }}
                  >
                    Expense Management System
                  </Typography>
                </Box>
              </Grid>
            </Grid>
          </Toolbar>
        </AppBar>
      </Box>
    </>
  );
}
