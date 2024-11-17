import React, { useContext, useEffect } from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import ExpenseContext from "../contexts/ExpenseContext";
import { getallexpensebymanagerid } from "../utils/api";

const myElements = [
  "Id",
  "Expense Type",
  "Start Date",
  "End Date",
  "Claimed Amount",
  "Comments",
  "Remarks",
  "Status",
  "Applied Date",
  "Approved Date",
];

export default function ExpenseList() {
  const { userDetails, setUserDetails, expenseList, setExpenseList } =
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
  console.log(expenseList);
  return (
    <TableContainer component={Paper}>
      <Table
        sx={{
          border: "2px solid black",
          borderRadius: "4px",
          width: "full",
          maxWidth: "md",
        }}
        size="small"
        aria-label="a dense table"
      >
        <TableHead sx={{ border: "2px solid black" }}>
          <TableRow
            sx={{
              border: "2px solid black",
              backgroundColor: "#a9a9a9",
            }}
          >
            {myElements.map((item, index) => {
              return (
                <TableCell
                  sx={{
                    border: "1px solid black",
                    fontWeight: "bold",
                    fontSize: "16px",
                    fontFamily: "proxima Nova",
                  }}
                  align="center"
                >
                  {item}
                </TableCell>
              );
            })}
          </TableRow>
        </TableHead>
        <TableBody>
          {expenseList?.data?.length > 0 &&
            expenseList?.data?.map((row, index) => (
              <TableRow
                key={row.name}
                sx={{
                  border: "1px solid black",
                  backgroundColor: index % 2 != 0 ? "#d3d3d3" : "white",
                }}
              >
                <TableCell
                  s
                  component="th"
                  scope="row"
                  sx={{ border: "1px solid black" }}
                >
                  {row?.expId}
                </TableCell>
                <TableCell sx={{ border: "1px solid black" }} align="center">
                  {row?.expenseCategory}
                </TableCell>
                
                <TableCell sx={{ border: "1px solid black" }} align="center">
                  {row?.startDate}
                </TableCell>
                <TableCell sx={{ border: "1px solid black" }} align="center">
                  {row?.endDate}
                </TableCell>
                <TableCell sx={{ border: "1px solid black" }} align="center">
                  {row?.amount}
                </TableCell>
                {/* <TableCell sx={{ border: "1px solid black" }} align="center">
                  {row?.receipt ? (
                    <a
                      href={URL.createObjectURL(
                        new Blob([row?.receipt], { type: "application/pdf" })
                      )}
                      download="receipt.pdf"
                    >
                      Download Receipt
                    </a>
                  ) : (
                    "No receipt available"
                  )}
                </TableCell> */}
                <TableCell sx={{ border: "1px solid black" }} align="center">
                  {row?.employeeDescription}
                </TableCell>
                <TableCell sx={{ border: "1px solid black" }} align="center">
                  {row?.managerRemarks}
                </TableCell>
                <TableCell sx={{ border: "1px solid black" }} align="center">
                  {row?.status}
                </TableCell>
                <TableCell sx={{ border: "1px solid black" }} align="center">
                  {row?.appliedDate}
                </TableCell>
                <TableCell sx={{ border: "1px solid black" }} align="center">
                  {row?.approvedDate}
                </TableCell>
              </TableRow>
            ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
