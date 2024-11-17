import Login from "./Components/login";
import ManagerDashboard from "./Components/Manager";
import EmployeeDashboard from "./Components/Profile";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Outlet,
} from "react-router-dom";
import AddExpense from "./Components/AddExpense";
import ApproveExpense from "./Components/ApproveExpense";
// import AllExpenses from "./Components/AllExpenses";
import Navbar from "./Components/Navbar";
import Sidebar from "./Components/sidebar";
import { Grid } from "@mui/material";
import ExpenseList from "./Components/ExpenseList";
import { Suspense } from "react";
import AllExpensesEmployee from "./Components/AllExpensesEmployee";
import AllExpensesManager from "./Components/AllExpensesManager";
import MyTable from "./Components/MyTable";
import ADP from "../src/images/MYBGiMG.png";
function EmployeeDashboardLayout({ children }) {
  return (
    <>
      <Navbar />

      <Grid container sx={{ marginBottom: "1rem" }}>
        <Grid
          item
          xs={0}
          sm={0}
          md={2.4}
          lg={2.4}
          sx={{
            height: "100vh",
            position: "sticky",
            top: 0,
            bottom: 0,
          }}
        >
          <Sidebar />
        </Grid>
        <Grid
          item
          xs={12}
          sm={12}
          md={9.6}
          lg={9.6}
          sx={{  width: "full", position:"relative",backgroundImage: { ADP },
          backgroundRepeat: "no-repeat",
          backgroundSize: "cover",
          backgroundPosition: "center",
         }}
          
        >
          <img src={ADP} alt="adp_logo" height="full" width="full" style={{position:'absolute',zIndex:"-1",width:"100%",height:"full", }}/>
          {children}
        </Grid>
      </Grid>
    </>
  );
}
function ManagerDashboardLayout({ children }) {
  return (
    <>
      <Navbar />

      <Grid container sx={{ marginBottom: "1rem" }}>
        <Grid
          item
          xs={0}
          sm={0}
          md={2.4}
          lg={2.4}
          sx={{
            height: "100vh",
            position: "sticky",
            top: 0,
            bottom: 0,
          }}
          
        
        > 
          
          <Sidebar />
        </Grid>
        <Grid
          item
          xs={12}
          sm={12}
          md={9.6}
          lg={9.6}
          sx={{  width: "full", position:"relative",backgroundImage: { ADP },
          backgroundRepeat: "no-repeat",
          backgroundSize: "cover",
          backgroundPosition: "center",}}
        >
        <img src={ADP} alt="adp_logo2" height="full" width="full" style={{position:'absolute',zIndex:"-1",width:"100%",height:"full", }}/>
          {children}
        </Grid>
      </Grid>
    </>
  );
}

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route
            path="/dashboard"
            element={
              <Suspense fallback={<p>Loading</p>}>
                <EmployeeDashboardLayout>
                  <Outlet />
                </EmployeeDashboardLayout>
              </Suspense>
            }
          >
            <Route index element={<EmployeeDashboard />} />
            <Route path="addexpense" element={<AddExpense />} />
            <Route path="viewexpenses" element={<ExpenseList />} />
            <Route path="analytics" element={<AllExpensesEmployee />} />
          </Route>

          <Route exact path="/" element={<Login />}></Route>

          <Route
            path="/managerdashBoard"
            element={
              <Suspense fallback={<p>Loading</p>}>
                <ManagerDashboardLayout>
                  <Outlet />
                </ManagerDashboardLayout>
              </Suspense>
            }
          >
            <Route index element={<ManagerDashboard />} />
            <Route path="approveexpense" element={<ApproveExpense />} />
            <Route path="viewexpenses" element={<MyTable />} />

            <Route path="analytics" element={<AllExpensesManager />} />
          </Route>
        </Routes>
      </Router>
    </>
  );
}

export default App;
