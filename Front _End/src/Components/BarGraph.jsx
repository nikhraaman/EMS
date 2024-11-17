import React, { useContext, useState, useEffect } from "react";
import { BarChart } from "@mui/x-charts/BarChart";
import { axisClasses } from "@mui/x-charts";
import { getMonthData } from "../utils/api";
import ExpenseContext from "../contexts/ExpenseContext";
import { Box, Typography } from "@mui/material";
export default function BarGraph() {
  const { userDetails } = useContext(ExpenseContext);
  const [monthDataset, setMonthDataset] = useState([]);

  const [isLoad, setIsLoad] = useState(true);
  const chartSetting = {
    yAxis: [
      {
        label: "Amount",
      },
    ],
    width: 750,
    height: 300,
    sx: {
      [`.${axisClasses.left} .${axisClasses.label}`]: {
        transform: "translate(-40px, 0)",
      },
    },
  };
  useEffect(() => {
    getMonthData(userDetails?.empId)
      .then((res) => {
        res?.data && setMonthDataset(res?.data);
        setIsLoad(false);
      })
      .catch((err) => {
        console.log(err);
      });

    // getAmountChartEmpolyeeAllMonth(userDetails?.empId)
    //   .then((res) => {
    //     console.log(typeof res?.data);
    //     console.log(res?.data);
    //     res?.data && setMonthSecondDataset(res?.data);
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //   });
    return () => {
      setIsLoad(true);
      setMonthDataset([]);
      // setMonthSecondDataset([]);
    };
  }, [userDetails?.empId]);

  const valueFormatter = (value) => `${value}`;
  return (
    <Box sx={{ display: "flex", flexDirection: "column" }}>
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
          dataset={monthDataset ? monthDataset : []}
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
    </Box>
  );
}
