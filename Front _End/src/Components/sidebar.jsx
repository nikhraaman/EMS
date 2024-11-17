import React, { useContext } from "react";
import { useNavigate } from "react-router-dom";
import Box from "@mui/material/Box";
import List from "@mui/material/List";
import Divider from "@mui/material/Divider";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";

import MailIcon from "@mui/icons-material/Mail";
import AttachMoneyIcon from "@mui/icons-material/AttachMoney";
import QueryStatsIcon from "@mui/icons-material/QueryStats";

import LogoutIcon from "@mui/icons-material/Logout";
import ExpenseContext from "../contexts/ExpenseContext";

import { Container } from "@mui/system";
import SpaceDashboardIcon from "@mui/icons-material/SpaceDashboard";
import { destroyCookie } from "nookies";

export default function Sidebar() {
  const navigate = useNavigate();
  const {
    userDetails,
   
  } = useContext(ExpenseContext);


  const settingsData =
    userDetails?.role === "Associate"
      ? [
          "Add Expense",
          "View Expense",
          "Email Support",
          "Analytics",
          "DashBoard",
        ]
      : ["Add Expense", "View Expense", "Approval", "Analytics", "DashBoard"];

  const handleClickSidebar = (text) => {
    if (text === "Add Expense") {
      navigate("/dashboard/AddExpense");
    } else if (text === "Logout") {
      destroyCookie(null, "userData");
      navigate("/");
    } else if (text === "View Expense" && userDetails?.role === "Associate") {
      navigate("/dashboard/ViewExpenses");
    } else if (text === "View Expense" && userDetails?.role === "Manager") {
      navigate("/managerdashBoard/viewExpenses");
    } else if (text === "Approval") {
      navigate("/managerdashBoard/approveExpense");
    } else if (text === "Analytics" && userDetails?.role === "Manager") {
      navigate("/managerdashBoard/analytics");
    } else if (text === "Analytics" && userDetails?.role === "Associate") {
      navigate("/dashboard/analytics");
    } else if (text === "DashBoard" && userDetails?.role === "Associate") {
      navigate("/dashBoard");
    } else if (text === "DashBoard" && userDetails?.role === "Manager") {
      navigate("/managerdashBoard");
    }
    console.log(text);
  };

  return (
    <>
      <Container maxWidth="xs">
        <Box>
          <Divider />
          <List>
            {settingsData.map((text, index) => (
              <ListItem key={text} disablePadding>
                <ListItemButton onClick={() => handleClickSidebar(text)}>
                  <ListItemIcon>
                    {index === 0 ? (
                      <AttachMoneyIcon />
                    ) : index === 1 ? (
                      <QueryStatsIcon />
                    ) : index !== 2 ? (
                      <SpaceDashboardIcon />
                    ) : (
                      <div>
                        <a
                          href="mailto:kukrejakunal2001@gmail.com"
                          style={{ textDecoration: "none", all: "unset" }}
                        >
                          <MailIcon />
                        </a>
                      </div>
                    )}
                  </ListItemIcon>
                  <ListItemText primary={text} />
                </ListItemButton>
              </ListItem>
            ))}
          </List>
          <Divider />
          <List>
            <ListItemButton onClick={() => handleClickSidebar("Logout")}>
              <ListItemIcon>
                <LogoutIcon />
              </ListItemIcon>
              <ListItemText primary={"Logout"} />
            </ListItemButton>
          </List>
        </Box>
      </Container>
    </>
  );
}
