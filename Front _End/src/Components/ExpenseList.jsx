import React, { useContext, useEffect } from "react";
import { DataGrid } from "@mui/x-data-grid";
import { GridToolbar } from "@mui/x-data-grid/components";
import ExpenseContext from "../contexts/ExpenseContext";
import { getAllExpense } from "../utils/api";

export default function MyTable() {
  const { userDetails, expenseList, setExpenseList } =
    useContext(ExpenseContext);

  useEffect(() => {
    getAllExpense(userDetails?.empId)
      .then((res) => {
        console.log(res);
        setExpenseList(res);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [userDetails?.empId]);

  const cols = [
    {
      field: "expenseId",
      headerName: "Expense ID",
      width: "140",
    },
    { field: "expenseCategory", headerName: "Expense Category", width: "190" },
    { field: "startdate", headerName: "Start Date", width: "140" },
    { field: "endDate", headerName: "endDate", width: "140" },
    { field: "claimedAmount", headerName: "Claimed Amount", width: "180" },
    { field: "status", headerName: "status", width: "180" },
    {
      field: "expenseDescription",
      headerName: "Comments",
      width: "200",
    },
    { field: "managerRemarks", headerName: "Manager Remarks", width: "200" },
    { field: "appliedDate", headerName: "Applied Date", width: "200" },
    { field: "approvedDate", headerName: "Approved Date", width: "200" },
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
        startdate: user?.startDate,
        endDate: user?.endDate,
        claimedAmount: user?.amount,
        status:user?.status,
        expenseDescription: user?.employeeDescription,
        managerRemarks: user?.managerRemarks,
        appliedDate: user?.appliedDate,
        approvedDate: user?.approvedDate,
      };
    });

  return (
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
  );
}
