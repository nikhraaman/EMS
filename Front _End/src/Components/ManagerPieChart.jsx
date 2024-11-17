import React, { useContext, useState, useEffect } from "react";
import { PieChart } from "@mui/x-charts/PieChart";
import ExpenseContext from "../contexts/ExpenseContext";
import { getAllStatusManagerExpense } from "../utils/api";
import { Grid, Typography } from "@mui/material";
export default function ManagerPieChart() {
  const {
    userDetails,
    
    approvedList,
    
    viewExpenseToggle,
  } = useContext(ExpenseContext);

  const [pieApprovedData, setPieApprovedData] = useState([]);

  useEffect(() => {
    getAllStatusManagerExpense(userDetails?.empId)
      .then((res) => {
        console.log(res);
        res?.data && setPieApprovedData(res?.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [userDetails?.empId]);

  const pieChartApprovedData =
    pieApprovedData &&
    pieApprovedData?.map((item, index) => {
      return {
        id: index,
        value: item,
        label: index === 0 ? "Pending" : index === 1 ? "Approved" : "Rejected",
      };
    });

  const aggregatedExpenses =
    approvedList?.data !== undefined &&
    Object.entries(
      approvedList?.data?.reduce((result, expense) => {
        const category = expense?.expenseCategory;
        if (!result[category]) {
          result[category] = { totalAmount: 0, entries: [] };
        }
        result[category].totalAmount += expense.amount;
        result[category].entries.push(viewExpenseToggle);
        return result;
      }, {})
    )?.map(([category, data]) => ({ category, ...data }));

  const data =
    aggregatedExpenses?.length > 0
      ? aggregatedExpenses?.map((item, index) => ({
          id: index,
          value: item?.totalAmount,
          label: item?.category,
        }))
      : [];

  return (
    <>
      <Grid
        container
        direction="row"
        spacing={1}
        sx={{ justifyContent: "center", alignItems: "center" }}
      >
        <Grid
          item
          xs={12}
          sm={5}
          sx={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            flexDirection: "column",
            // border: "1px solid black",
            backgroundColor: "white",
            width: "480px",
            padding: "1rem",
            // boxShadow: "0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 black",
          }}
        >
          <Typography
            align="center"
            variant="h5"
            color="#26328c"
            sx={{ fontFamily: "proxima nova", fontWeight: "bold" }}
          >
            Category
          </Typography>
          &nbsp;
          <PieChart
            series={[
              {
                data,
                highlightScope: { faded: "global", highlighted: "item" },
                faded: {
                  innerRadius: 30,
                  additionalRadius: -30,
                  color: "gray",
                },
              },
            ]}
            height={200}
            width={400}
          />
        </Grid>{" "}
        <Grid item xs={0.4}></Grid>
        <Grid
          item
          xs={6}
          sm={5}
          sx={{
            display: "flex",
            padding: "1rem",
            flexDirection: "column",
            // border: "1px solid black",

            width: "480px",
          
            justifyContent: "center",
            alignItems: "center",
            backgroundColor: "white",
          }}
        >
          <Typography
            align="center"
            variant="h5"
            color="#26328c"
            sx={{ fontFamily: "proxima nova", fontWeight: "bold" }}
          >
            Approval Status
          </Typography>
          &nbsp;
          <PieChart
            series={[
              {
                data: pieChartApprovedData,
                highlightScope: { faded: "global", highlighted: "item" },
                faded: {
                  innerRadius: 30,
                  additionalRadius: -30,
                  color: "gray",
                },
              },
            ]}
            height={200}
            width={400}
          />
        </Grid>
      </Grid>
    </>
  );
}
