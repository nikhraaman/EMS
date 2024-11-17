import React, { useContext, useState } from "react";
import Header from "./header";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";

import TextField from "@mui/material/TextField";
import Link from "@mui/material/Link";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import { useNavigate } from "react-router-dom";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { loginCredVerification } from "../utils/api";
import ExpenseContext from "../contexts/ExpenseContext";
import { setCookie } from "nookies";

import LoginImg from "../images/ADP.jpg";
import { Typography } from "@mui/material";
import { validationLogin } from "../validationSchemas/ValidationLogin";
import {   useFormik } from "formik";

function Copyright(props) {
  return (
    <Box
      display="flex"
      justifyContent="center"
      sx={{
        backgroundColor: "white",
        padding: "1rem",
        boxShadow:
          "0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)",
      }}
    >
      {"Copyright © "}
      <Link color="inherit" href="#">
        ADP India PVT LTD
      </Link>{" "}
      {new Date().getFullYear()}
      {"."}
    </Box>
  );
}

const defaultTheme = createTheme();

export default function Login() {
  const {  setUserDetails } = useContext(ExpenseContext);
  const navigate = useNavigate();

  const [showLoginError, setShowLoginError] = useState();

  const initialValues = {
    empId: "",
    password: "",
  };

  const { values, errors, touched, handleBlur, handleChange, handleSubmit } =
    useFormik({
      initialValues,
      validationSchema: validationLogin,
      onSubmit: async (values, action) => {
        console.log(values);
        try {
          const credentials = await loginCredVerification(values);

          if (!credentials?.data) {
            setShowLoginError("Invalid User Id and Password");
            setTimeout(() => {
              setShowLoginError("");
            }, "5000");

            throw new Error();
          }

          credentials?.data === undefined
            ? setShowLoginError("Error Please try again later")
            : setUserDetails(credentials?.data);

          setCookie(null, "UserDetails", JSON.stringify(credentials?.data), {
            maxAge: 2 * 60 * 60,
          });

          if (credentials?.data?.role === "Manager") {
            navigate("/managerdashBoard");
          } else if (credentials?.data?.role === "Associate") {
            navigate("/dashBoard");
          }
        } catch (error) {
          console.log(error);
        }
      },
    });

  return (
    <>
      <ThemeProvider theme={defaultTheme}>
        <Box
          display="flex"
          flexDirection="column"
          sx={{ mx: "auto", width: "auto" }}
        >
          <Header />
          <Grid
            container
            component="main"
            sx={{
              my: "auto",
              height: "auto",
              // paddingLeft: "8rem",
              paddingRight: "8rem",
              paddingTop: "1rem",
              paddingBottom: "1rem",
            }}
            justifyContent="center"
            alignItems="center"
            justifyItems="center"
          >
            <Grid
              item
              xs={false}
              sm={false}
              md={7}
              sx={{
                backgroundImage: { LoginImg },
                backgroundRepeat: "no-repeat",
                backgroundSize: "cover",
                backgroundPosition: "center",
              }}
            >
              <img src={LoginImg} alt="hellologinimage" height={490} width={650} />
            </Grid>
            <Grid item xs={12} sm={8} md={5}>
              <Box
                sx={{
                  display: "flex",
                  flexDirection: "column",
                  alignItems: "center",
                  boxShadow:
                    "0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)",
                  padding: "50px",
                  borderRadius: "16px",
                  backgroundColor: "white",
                }}
              >
                <Avatar sx={{ m: 1, bgcolor: "#26328c" }}></Avatar>

                <Box
                  component="form"
                  onSubmit={handleSubmit}
                  noValidate
                  sx={{ mt: 1 }}
                >
                  <TextField
                    type="text"
                    required
                    id="empId"
                    label="Employee ID"
                    name="empId"
                    fullWidth
                    variant="outlined"
                    margin="dense"
                    placeholder="Employee ID"
                    value={values.empId}
                    onChange={handleChange}
                    onBlur={handleBlur}
                  />
                  {errors.empId && touched.empId ? (
                    <Typography variant="p" color="red">
                      {errors.empId}
                    </Typography>
                  ) : null}

                  <TextField
                    type="password"
                    required
                    label="Password"
                    name="password"
                    fullWidth
                    variant="outlined"
                    margin="dense"
                    placeholder="Password"
                    value={values.password}
                    onChange={handleChange}
                    onBlur={handleBlur}
                  />
                  {errors.password && touched.password ? (
                    <Typography variant="p" color="red">
                      {errors.password}
                    </Typography>
                  ) : null}

                  {showLoginError ? (
                    <Typography variant="p" color="red">
                      {showLoginError}
                    </Typography>
                  ) : (
                    <></>
                  )}
                  <Button
                    type="submit"
                    fullWidth
                    variant="contained"
                    sx={{
                      ":hover": {
                        bgcolor: "#5278d6",
                        color: "white",
                      },
                      mt: 3,
                      mb: 2,
                      backgroundColor: "#26328c",
                    }}
                  >
                    Log In
                  </Button>
                </Box>
              </Box>
            </Grid>
          </Grid>
          <Copyright></Copyright>
        </Box>
      </ThemeProvider>
    </>
  );
}
