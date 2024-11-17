import React, { useContext, useState, useEffect } from "react";

import { PieChart } from "@mui/x-charts/PieChart";

import { Grid } from "@mui/material";

import { getAllExpense, getPieStatusChartData } from "../utils/api";

import { BarChart } from "@mui/x-charts/BarChart";
import { axisClasses } from "@mui/x-charts";
import {  getAmountChartEmpolyeeAllMonth } from "../utils/api";
import ExpenseContext from "../contexts/ExpenseContext";
import {Typography} from "@mui/material";
export default function AllExpensesEmployee() {
  const {
    userDetails,
    expenseList,
    setExpenseList,
    viewExpenseToggle,
    
  } = useContext(ExpenseContext);

  const [pieStatusChartData, setPieStatusChartData] = useState([]);

  const [isLoad, setIsLoad] = useState(true);
  const [monthSecondDataset, setMonthSecondDataset] = useState([]);

  useEffect(() => {
    getAllExpense(userDetails?.empId)
      .then((res) => {
        setExpenseList(res);
      })
      .catch((err) => {
        console.log(err);
      });

    getPieStatusChartData(userDetails?.empId)
      .then((res) => {
        res?.data.length > 0 && setPieStatusChartData(res?.data);
      })
      .catch((err) => {
        console.log(err);
      });
    getAmountChartEmpolyeeAllMonth(userDetails?.empId)
      .then((res) => {
        console.log(typeof res?.data);
        console.log(res?.data);
        res?.data && setMonthSecondDataset(res?.data);
        setIsLoad(false);
      })
      .catch((err) => {
        console.log(err);
      });
    return () => {
      setIsLoad(true);

      setMonthSecondDataset([]);
    };
  }, [userDetails?.empId]);

  const pieChartApprovedData =
    pieStatusChartData &&
    pieStatusChartData?.map((item, index) => {
      return {
        id: index,
        value: item,
        label: index === 0 ? "Pending" : index === 1 ? "Approved" : "Rejected",
      };
    });

  expenseList?.data &&
    expenseList?.data.length > 0 &&
    expenseList?.data?.sort((a, b) =>
      a.expenseCategory.localeCompare(b.expenseCategory)
    );

  const aggregatedExpenses =
    expenseList?.data &&
    Object.entries(
      expenseList?.data &&
        expenseList?.data.length > 0 &&
        expenseList?.data?.reduce((result, expense) => {
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

  const chartSetting = {
    yAxis: [
      {
        label: "Amount",
      },
    ],
    width: 740,
    height: 300,
    sx: {
      [`.${axisClasses.left} .${axisClasses.label}`]: {
        transform: "translate(-40px, 0)",
      },
    },
  };

  const valueFormatter = (value) => `${value}`;
  return (
    <>
      <Grid container direction="column" sx={{paddingBottom: "1rem"}}>
        <Grid
          item
          
          sx={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          
            
            // boxShadow: "0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 #26328c",
          }}
        >
          
          <Grid item sx={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            // border: "1px solid black",
            backgroundColor: "transparent",
            marginTop: "2rem",
            marginBottom:"1rem"
            // boxShadow: "0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 black",
          }} >
            
            <Grid
          item
          xs={6}
          sm={5}
          sx={{
            display: "flex",
            padding: "1rem",
            flexDirection: "column",
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
          <Grid xs={0.4}></Grid>
          <Grid
          item
          xs={6}
          sm={5}
          sx={{
            display: "flex",
            padding: "1rem",
            flexDirection: "column",
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
            Category Wise
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
          </Grid>
          </Grid>
        </Grid>
        <Grid item  sx={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            flexDirection:"column",
            width: "840px",
            marginTop: "4px",
            backgroundColor: "white",
            marginLeft:"5.2rem"
          }}>
            
            <Typography
        align="center"
        variant="h5"
        color="#26328c"
        sx={{ fontFamily: "proxima nova", fontWeight: "bold" }}
      >
        Month-Wise Status
      </Typography>
          {!isLoad ? (
            <BarChart
              dataset={monthSecondDataset ? monthSecondDataset : []}
              xAxis={[{ scaleType: "band", dataKey: "Month" }]}
              series={[
                { dataKey: "Approved", label: "Approved", valueFormatter },
                { dataKey: "Pending", label: "Pending", valueFormatter },
                { dataKey: "Rejected", label: "Rejected", valueFormatter },
              ]}
              {...chartSetting}
            />
          ) : (
            <p>Bar graph</p>
          )}
        </Grid>
      </Grid>
    </>
  );
}
