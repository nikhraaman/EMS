import React, { useState } from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import { parseCookies } from "nookies";
import { addExpenseDetails } from "../utils/api";
import dayjs from "dayjs";
export default function AddExpense() {
  const cookies = parseCookies();
  const userData = JSON.parse(cookies["UserDetails"]);
  const [expenseFormValue, setExpenseFormValue] = useState({
    empId: userData.empId,
    expenseCategory: "",
    startDate: "",
    endDate: "",
    amount: "",
    employeeDescription: "",
    appliedDate: dayjs().format("YYYY-MM-DD"),
    receipt: null,
  });

  const handleDateInput = (date, identifier) => {
    setExpenseFormValue((prev) => {
      return {
        ...prev,
        [identifier]: date,
      };
    });
  };
  const handleFileUpload = (event) => {
    const file = event.target.files[0];
    setExpenseFormValue((prev) => {
      return {
        ...prev,
        receipt: file,
      };
    });
    console.log(file);
  };

  const handleInputChange = (e) => {
    setExpenseFormValue({
      ...expenseFormValue,
      [e.target.name]: e.target.value,
    });
  };
  const handleLogin = (e) => {
    e.preventDefault();

    addExpenseDetails(expenseFormValue)
      .then((res) => {
        alert("Expense Added successfully");
      })
      .catch((error) => console.log(error));
  };

  return (
    <>
      <Box
        display="flex"
        flexDirection="column"
        justifyContent="center"
        alignItems="center"
        sx={{
          mx: "auto",
          my: "1rem",
          padding: "1rem",
          maxWidth: "sm",
          borderRadius: "2rem",
          // backgroundColor: "#26328c",
        }}
      >
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            boxShadow: "0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 #26328c",
            padding: "30px",
            borderRadius: "16px",
            backgroundColor: "white",
          }}
        >
          <Box
            component="form"
            onSubmit={handleLogin}
            noValidate
            sx={{
              mt: 1,
              display: "flex",
              flexDirection: "column",
            }}
          >
            <Box sx={{}}>
              <FormControl fullWidth>
                <InputLabel id="demo-simple-select-label">
                  Expense Category
                </InputLabel>
                <Select
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  // value={age}
                  name="expenseCategory"
                  label="Expense Category"
                  onChange={handleInputChange}
                >
                  <MenuItem value="Internet">Internet</MenuItem>
                  <MenuItem value="Travel">Travel</MenuItem>
                  <MenuItem value="Food">Food</MenuItem>
                  <MenuItem value="Relocation">Relocation</MenuItem>
                  <MenuItem value="Cab">Cab</MenuItem>
                </Select>
              </FormControl>
            </Box>
            <Box sx={{ my: 1 }}>
              <DatePicker
                label="Start Date"
                value={
                  expenseFormValue?.startDate
                    ? expenseFormValue?.startDate
                    : null
                }
                onChange={(h) =>
                  handleDateInput(dayjs(h).format("YYYY-MM-DD"), "startDate")
                }
                sx={{ mr: 1 }}
              />
              <DatePicker
                label="End Date"
                value={
                  expenseFormValue?.endDate ? expenseFormValue?.endDate : null
                }
                onChange={(h) =>
                  handleDateInput(dayjs(h).format("YYYY-MM-DD"), "endDate")
                }
              />
            </Box>
            <TextField
              name="amount"
              onChange={handleInputChange}
              label="Please enter your amount"
              sx={{ mb: 1 }}
            ></TextField>
            <TextField
              id="outlined-multiline-static"
              label="Employee Description"
              multiline
              rows={3}
              name="employeeDescription"
              onChange={handleInputChange}
              defaultValue=""
              sx={{ mb: 1 }}
            />

            <TextField type="file" onChange={handleFileUpload} name="reciept">
              <Button variant="contained" color="primary" component="span">
                Upload
              </Button>
            </TextField>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{
                ":hover": {
                  bgcolor: "#5278d6", // theme.palette.primary.main
                  color: "white",
                },
                mt: 3,
                mb: 2,
                backgroundColor: "#26328c",
              }}
            >
              Submit Details
            </Button>
          </Box>
        </Box>
      </Box>
    </>
  );
}
