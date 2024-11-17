import React, { useContext, useEffect } from "react";
import { DataGrid } from "@mui/x-data-grid";
import { GridToolbar } from "@mui/x-data-grid/components";
import ExpenseContext from "../contexts/ExpenseContext";
import { getallexpensebymanagerid } from "../utils/api";
import { createTheme, ThemeProvider } from "@mui/material/styles";
export default function MyTable() {
  const darkTheme = createTheme({
    palette: {
      mode: "light",
    },
  });
  const { userDetails, expenseList, setExpenseList } =
    useContext(ExpenseContext);

  useEffect(() => {
    getallexpensebymanagerid(userDetails?.empId)
      .then((res) => {
        console.log(res);
        setExpenseList(res);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [userDetails?.empId]);

  const cols = [
    { field: "employeeId", headerName: "Employee ID", width: "180" },
    {
      field: "expenseId",
      headerName: "Expense ID",
      width: "140",
    },
    { field: "expenseCategory", headerName: "Expense Category", width: "190" },
    { field: "status", headerName: "Status", width: "140" },
    { field: "startdate", headerName: "Start Date", width: "140" },

    { field: "endDate", headerName: "endDate", width: "140" },
    { field: "claimedAmount", headerName: "Claimed Amount", width: "180" },
    {
      field: "expenseDescription",
      headerName: "Expense Description",
      width: "200",
    },
    { field: "managerRemarks", headerName: "Manager Remarks", width: "200" },
  ];

  const rowData =
    expenseList?.data &&
    expenseList?.data.length > 0 &&
    expenseList?.data?.map((user, index) => {
      return {
        id: index,
        employeeId: user?.empId,
        expenseId: user?.expId,
        expenseCategory: user?.expenseCategory,
        status:user?.status,
        startdate: user?.startDate,
        endDate: user?.endDate,
        claimedAmount: user?.amount,
        expenseDescription: user?.employeeDescription,
        managerRemarks: user?.managerRemarks,
      };
    });

  return (
    <ThemeProvider theme={darkTheme}>
      <div
        style={{
          width: "100%",
        
          maxWidth: "full",
          display:"flex",
          backgroundColor:"white",
          justifyContent:"center",
          alignItems:"start",
          padding:"2rem"
        }}
      >
        <DataGrid
          getRowId={(row) => row?.id}
          pageSize={10}
          checkboxSelection
          autoHeight
          showCellVerticalBorder={true}
          showColumnVerticalBorder={true}
          density="standard"
          aria-label="Employees Expense Data"
          rows={rowData ? rowData : []}
          columns={cols}
          slots={{ toolbar: GridToolbar }}
          sx={{
            width: "75vw",

            border: "none",
            headerAlign: "center",

            borderColor: "primary.light",
            "& .MuiDataGrid-toolbarContainer": {
              color: "red",
            },

            "& .MuiDataGrid-root": {
              border: "1px solid darkgrey",
            },
            "& .MuiDataGrid-cell": {
              borderBottom: "none",
              color: "black",
              border: "",
            },
            "& .name-column--cell": {
              color: "#2e7c67",
            },
            "& .MuiDataGrid-columnHeaders": {
              backgroundColor: "#26328c",
              borderBottom: "none",
              color: "white",
            },
            "& .MuiDataGrid-virtualScroller": {
              backgroundColor: "white",
            },
            "& .MuiDataGrid-footerContainer": {
              borderTop: "none",
              backgroundColor: "white",
            },

            "& .MuiDataGrid-cell:hover": {
              color: "#26328c",
            },
          }}
        />
      </div>
    </ThemeProvider>
  );
}
