import React, { useContext, useEffect, useState} from "react";
import { DataGrid } from "@mui/x-data-grid";
import ExpenseContext from "../contexts/ExpenseContext";
import { approveExpense, rejectExpense } from "../utils/api";

import { Button } from "@mui/base";
import { getAllPendingExpense, getExpenseById } from "../utils/api";

function reHydrate(myArr, expId) {
  myArr.filter((element) => element.expId !== expId);
  return myArr;
}
export default function MyTable() {
  // const [filledRemarks, setFilledRemarks] = useState(false);

  const [removedExpid, setRemovedexpid] = useState();
  const [remarks, setRemarks] = useState("");

  const { userDetails } = useContext(ExpenseContext);
  const [pendingList, setPendingList] = useState([]);



  useEffect(() => {
    getAllPendingExpense(userDetails?.empId)
      .then((res) => {
        setPendingList(res?.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [userDetails?.empId, removedExpid]);

  const handleAccept = async (expId, status, currentRemarks) => {
    setRemovedexpid(expId);
    currentRemarks
      ? approveExpense(expId, status, currentRemarks)
          .then((res) => {
            alert(res?.data);
            // console.log(pendingList);
            getAllPendingExpense(userDetails?.empId)
              .then((res) => {
                console.log(res);

                setPendingList(reHydrate(res?.data, expId));
              })
              .catch((err) => {
                console.log(err);
              });
          })
          .catch((error) => {
            console.log(error);
          })
      : alert("Please add Remarks");
  };
  const handleReject = async (expId, status, currentRemarks) => {
    setRemovedexpid(expId);
    // console.log(currentRemarks);
    currentRemarks
      ? rejectExpense(expId, status, currentRemarks)
          .then((res) => {
            alert(res?.data);
            getAllPendingExpense(userDetails?.empId)
              .then((res) => {
                console.log(res);

                setPendingList(reHydrate(res?.data, expId));
              })
              .catch((err) => {
                console.log(err);
              });
          })
          .catch((error) => {
            console.log(error);
          })
      : alert("Please add Remarks");
  };

  const viewPdf = (expId) => {
    getExpenseById(expId)
      .then((response) => {
        const receipt = response.data.receipt;
        const decodedReceipt = atob(receipt);
        // console.log(decodedReceipt);
        const byteArrayPdf = new Uint8Array(decodedReceipt.length);
        for (let i = 0; i < decodedReceipt.length; i++) {
          byteArrayPdf[i] = decodedReceipt.charCodeAt(i);
        }
        // console.log(byteArrayPdf);
        const blob = new Blob([byteArrayPdf], { type: "application/pdf" });
        const pdfFile = URL.createObjectURL(blob);
        window.open(pdfFile);
      })
      .catch((error) => {
        console.log(error);
      });
  };
  const cols = [
    { field: "employeeId", headerName: "Employee ID", width: "180" },
    {
      field: "expenseId",
      headerName: "Expense ID",
      width: "140",
    },
    { field: "expenseCategory", headerName: "Expense Category", width: "190" },
    { field: "startdate", headerName: "Start Date", width: "140" },
    { field: "endDate", headerName: "endDate", width: "140" },
    { field: "claimedAmount", headerName: "Claimed Amount", width: "180" },
    {
      field: "expenseDescription",
      headerName: "Expense Description",
      width: "200",
    },
    {
      field: "receipt",
      headerName: "Receipt",
      width: "140",
      renderCell: (e) => {
        // console.log(e);
        return (
          <>
            {e?.row?.receipt ? (
              <a
                // href={URL.createObjectURL(
                //   new Blob([e?.row?.receipt], {
                //     type: "application/pdf",
                //   })
                // )}
                href={`data:application/pdf;base64,${e?.row?.receipt}`}
                target="blank"
                // download="receipt.pdf"
                onClick={() => viewPdf(e?.row?.expenseId)}
              >
                Download Receipt
              </a>
            ) : (
              "No receipt available"
            )}
          </>
        );
      },
    },
    { field: "status", headerName: "Status", width: "140" },
    {
      field: "actions",
      headerName: "Actions",
      width: "120",
      // editable: true,
      renderCell: (e) => {
        return (
          <>
            <Button
              onClick={() =>
                handleAccept(e?.row?.expenseId, "Approved", remarks)
              }
            >
              ✔️
            </Button>
            &nbsp; &nbsp; &nbsp;{" "}
            <Button
              onClick={() =>
                handleReject(e?.row?.expenseId, "Rejected", remarks)
              }
            >
              ❌
            </Button>{" "}
          </>
        );
      },
    },
    {
      field: "managerRemarks",
      headerName: "Manager Remarks",
      width: "200",
      editable: true,
    },
  ];

  const rowData =
    pendingList &&
    pendingList &&
    pendingList?.map((user, index) => {
      // console.log(user?.receipt);
      return {
        id: index,
        employeeId: user?.empId,
        expenseId: user?.expId,
        expenseCategory: user?.expenseCategory,
        startdate: user?.startDate,
        endDate: user?.endDate,
        claimedAmount: user?.amount,
        expenseDescription: user?.employeeDescription,
        status: user?.status,
        // receipt: URL.createObjectURL(
        //   new Blob([user?.receipt], {
        //     type: "application/pdf",
        //   })
        // ),
        receipt: user?.receipt,
        actions: null,
        managerRemarks: null,
      };
    });
  // console.log(myRemarksRef);
  return (
    <div
      style={{
       
        width: "100%",
        height:"100vh",
        // maxWidth: "full",
       
        backgroundColor:"white",
        // justifyContent:"center",
        // alignItems:"start",
        padding:"2rem"
      }}
    >
      <DataGrid
        editMode="cell"
        onCellEditStop={(param, e) => {
          // console.log(e, param);
          // myRemarksRef.current = e.target.value;
          setRemarks(e.target.value);
        }}
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
        sx={{
          width: "75vw",
          boxShadow: 2,
          border: 2,
          headerAlign: "center",
          borderColor: "primary.light",
          "& .MuiDataGrid-root": {
            border: "none",
            // backgroundColor:'white'
          },
          "& .MuiDataGrid-cell": {
            borderBottom: "none",
          },
          "& .name-column--cell": {
            color: "#2e7c67",
          },
          "& .MuiDataGrid-columnHeaders": {
            backgroundColor: "#3e4396",
            color:"white",
            borderBottom: "none",
          },
          "& .MuiDataGrid-virtualScroller": {
            backgroundColor: "#7276781",
          },
          "& .MuiDataGrid-footerContainer": {
            borderTop: "none",
            backgroundColor: "white",
          },

          "& .MuiDataGrid-cell:hover": {
            color: "primary.main",
          },
        }}
      />
    </div>
  );
}
