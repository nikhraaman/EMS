import ExpenseContext from "./ExpenseContext";
import React, { useState, useEffect } from "react";
import { parseCookies } from "nookies";
import { getallexpensebymanagerid } from "../utils/api";
const ExpenseStates = (props) => {
  const cookies = parseCookies();
  console.log(cookies);
  const userData = cookies["UserDetails"] && JSON.parse(cookies["UserDetails"]);
  const [userDetails, setUserDetails] = useState(userData);
  const [expenseList, setExpenseList] = useState([]);
  const [approvedList, setApprovedList] = useState([]);
  const [addExpenseData, setAddExpenseData] = useState({});
  useEffect(() => {
    getallexpensebymanagerid(userDetails?.empId)
      .then((res) => {
        console.log(res);
        res?.data && setExpenseList(res);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [userDetails?.empId]);

  return (
    <ExpenseContext.Provider
      value={{
        userDetails,
        setUserDetails,
        expenseList,
        setExpenseList,
        approvedList,
        setApprovedList,
        addExpenseData,
        setAddExpenseData,
      }}
    >
      {props.children}
    </ExpenseContext.Provider>
  );
};
export default ExpenseStates;
