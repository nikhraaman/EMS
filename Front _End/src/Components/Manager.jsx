import React, { useEffect, useContext } from "react";
import { getAllStatus } from "../utils/api";
import ExpenseContext from "../contexts/ExpenseContext";
import { Grid } from "@mui/material";

import Profile from "./Profile";

export default function Manager() {
  const { userDetails, setApprovedList } = useContext(ExpenseContext);
  useEffect(() => {
    getAllStatus(userDetails?.empId)
      .then((res) => {
        res && setApprovedList(res);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [userDetails?.empId]);

  return (
    <>
      <Grid container spacing={0} direction="column" justifyContent="center">
        <Grid
          item
          xs={false}
          sm={false}
          // sx={{
          //   position: "relative",
          // }}
        >
          {/* <img
            src={ADP}
            style={{
              opacity: "0.5",
              zIndex: "-1",
              display: "block",
              width: "100%",
              height: "50%",
            }}
          /> */}
          <Grid item>
            <Profile />
          </Grid>
        </Grid>
      </Grid>
    </>
  );
}
