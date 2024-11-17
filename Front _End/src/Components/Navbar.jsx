import React, { useContext } from "react";
import Box from "@mui/material/Box";
import AdpLogo from "../images/adpRedLogo.png";
import AppBar from "@mui/material/AppBar";

import Typography from "@mui/material/Typography";

import IconButton from "@mui/material/IconButton";
import Menu from "@mui/material/Menu";
import Tooltip from "@mui/material/Tooltip";
import MenuItem from "@mui/material/MenuItem";

import AccountCircleIcon from "@mui/icons-material/AccountCircle";

import ExpenseContext from "../contexts/ExpenseContext";

export default function Navbar() {
  
  const { userDetails } = useContext(ExpenseContext);
  const settings = [userDetails.empName, "Profile"];

 
  const [anchorElUser, setAnchorElUser] = React.useState(null);

  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  return (
    <Box>
      <AppBar
        position="sticky"
        sx={{
          width: `100%`,
          backgroundColor: "white",
          display: "flex",
          flexDirection: "row",
          justifyContent: "space-between",
          height: "4.5rem",
        }}
      >
        <Box
          component="img"
          sx={{
            height: 40,
            width: 70,
            margin: "1rem",
            marginLeft: "2rem",
          }}
          alt="AdpLogo"
          src={AdpLogo}
        />
        <Typography
          variant="h4"
          fontWeight="bold"
          color="black"
          sx={{ paddingTop: "1rem", color: "#26328c" }}
        >
          Expense Management System
        </Typography>
        <Box sx={{ flexGrow: 0, marginRight: "2rem", paddingTop: "1rem" }}>
          <Tooltip title="Open settings">
            <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
              <AccountCircleIcon sx={{ height: "40px", width: "40px" }} />
            </IconButton>
          </Tooltip>
          <Menu
            sx={{ mt: "45px" }}
            id="menu-appbar"
            anchorEl={anchorElUser}
            anchorOrigin={{
              vertical: "top",
              horizontal: "right",
            }}
            keepMounted
            transformOrigin={{
              vertical: "top",
              horizontal: "right",
            }}
            open={Boolean(anchorElUser)}
            onClose={handleCloseUserMenu}
          >
            {settings.map((setting) => (
              <MenuItem key={setting} onClick={handleCloseUserMenu}>
                <Typography textAlign="center">{setting}</Typography>
              </MenuItem>
            ))}
          </Menu>
        </Box>
      </AppBar>
    </Box>
  );
}
