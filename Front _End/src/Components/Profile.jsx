import React, { useContext } from "react";
import Box from "@mui/material/Box";
import { Grid } from "@mui/material";

import ADP from "../images/MYBGiMG.png"
import Typography from "@mui/material/Typography";
import ExpenseContext from "../contexts/ExpenseContext";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
export default function Profile() {

  const { userDetails } = useContext(ExpenseContext);

  return (
    <Box  sx={{
        width: "full", 
        backgroundImage: { ADP },
      display:"flex",
      justifyContent:"center",
      alignItems:"center"
      
      }}>
         
       
      <Box
        sx={{
          padding: "0.2rem",
           backgroundColor: "#26328c",
          // zIndex: "-1",
          marginTop: "6rem",
          
          
        }}
      >
        
        <Box
          sx={{
            width: "full",
            zIndex: "1",
            backgroundColor: "white",
            boxShadow: "0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 #26328c",
            
          }}
        >
          <Box
            sx={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "center",
              width: "700px",
              height:"350px",
             
            }}
          >
            <Box sx={{ display: "flex", flexDirection: "column" ,alignItems:"center",marginTop:"1rem",marginBottom:"1rem"}}>
              {" "}
              <AccountCircleIcon sx={{ height: "120px", width: "120px" }} />
              <Typography component="div">{userDetails?.empName}</Typography>
            </Box>

            <Grid container sx={{ padding: "1rem", }}>
              <Grid item xs={6} spacing={4} >
                <Box
                  sx={{
                    display: "flex",
                    flexDirection: "row",
                    justifyContent: "flex-start",
                    alignItems: "center",
                    gap: "3rem",
                  }}
                >
                  <Typography
                     variant="h6"
                     fontWeight="bold"
                     fontFamily="proxima Nova"
                  >
                    Associate Id
                  </Typography>
                  <Typography variant="p" >{userDetails?.empId}</Typography>
                </Box>
                <Box
                  sx={{
                    display: "flex",
                    flexDirection: "row",
                    justifyContent: "flex-start",
                    alignItems: "center",
                    gap: "6rem",
                  }}
                >
                  <Typography
                    variant="h6"
                    fontWeight="bold"
                    fontFamily="proxima Nova"
                  >
                    Email 
                  </Typography>
                  <Typography variant="p">{userDetails?.email}</Typography>
                </Box>
                <Box
                  sx={{
                    display: "flex",
                    flexDirection: "row",
                    justifyContent: "flex-start",
                    alignItems: "center",
                    gap: "3rem",
                  }}
                >
                  <Typography
                    variant="h6"
                    fontWeight="bold"
                    fontFamily="proxima Nova"
                  >
                    Designation
                  </Typography>
                  <Typography variant="p">
                    {userDetails?.designation}
                  </Typography>
                </Box>
              </Grid>
              <Grid item xs={6}>
                <Box
                  sx={{
                    display: "flex",
                    flexDirection: "row",
                    justifyContent: "flex-start",
                    alignItems: "center",
                    gap: "3rem",
                  }}
                >
                  <Typography
                    variant="h6"
                    fontWeight="bold"
                    fontFamily="proxima Nova"
                  >
                    Associate Type
                  </Typography>
                  <Typography variant="p">Permanent</Typography>
                </Box>
                <Box
                  sx={{
                    display: "flex",
                    flexDirection: "row",
                    justifyContent: "flex-start",
                    alignItems: "center",
                    gap: "8.4rem",
                  }}
                >
                  <Typography
                    variant="h6"
                    fontWeight="bold"
                    fontFamily="proxima Nova"
                  >
                    Title
                  </Typography>
                  <Typography variant="p">{userDetails?.role}</Typography>
                </Box>
                <Box
                  sx={{
                    display: "flex",
                    flexDirection: "row",
                    justifyContent: "flex-start",
                    alignItems: "center",
                    gap: "6.2rem",
                  }}
                >
                  <Typography
                    variant="h6"
                    fontWeight="bold"
                    fontFamily="proxima Nova"
                  >
                    Location
                  </Typography>
                  <Typography variant="p">Hyderabad</Typography>
                </Box>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Box>
    </Box>
  );

  
}


// sx={{
//   width: "full", 
//  backgroundImage: { ADP },
//  backgroundRepeat: "no-repeat",
//  backgroundSize: "cover",
//  backgroundPosition: "center",
// }}
// <img src={ADP} height="full" width="full" style={{position:'absolute',zIndex:"-1",width:"65vw",height:"100vh", top:"10px"}}/>