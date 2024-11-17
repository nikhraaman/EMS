import axios from "axios";

const baseUrl = "http://CDLVDIDEVMAN512:8082/";

const callback = axios.create({
  baseURL: baseUrl,
  withCredentials: false,
  "Access-Control-Allow-Origin": "*",
});

const loginCredVerification = async (data) => {
  console.log(data);
  return new Promise((resolve, reject) => {
    callback
      .get(`employee/login/${data?.empId}/${data?.password}`)
      .then((res) => {
        resolve(res);
      })
      .catch((error) => {
        reject(error);
      });
  });
};

const getPieStatusChartData = async (empId) => {
  return new Promise((resolve, reject) => {
    callback
      .get(`employee/getAllStatusAssociate/${empId}`)
      .then((res) => {
        resolve(res);
      })
      .catch((error) => {
        reject(error);
      });
  });
};

const getAllExpense = async (empId) => {
  return new Promise((resolve, reject) => {
    callback
      .get(`employee/getallexpensebyid/${empId}`)
      .then((res) => {
        resolve(res);
      })
      .catch((error) => {
        reject(error);
      });
  });
};

const getAllPendingExpense = async (empId) => {
  return new Promise((resolve, reject) => {
    callback
      .get(`manager/getallexpensebymanageridpending/${empId}`)
      .then((res) => {
        resolve(res);
      })
      .catch((error) => {
        reject(error);
      });
  });
};
// http://localhost:8082/manager/getallexpensebymanagerid/E17NH5C0CBD8F2FG
const getallexpensebymanagerid = async (empId) => {
  return new Promise((resolve, reject) => {
    callback
      .get(`manager/getallexpensebymanagerid/${empId}`)
      .then((res) => {
        resolve(res);
      })
      .catch((error) => {
        reject(error);
      });
  });
};

const getExpenseById = async (expId) => {
  return new Promise((resolve, reject) => {
    callback
      .get(`manager/getExpenseById/` + expId)
      .then((res) => {
        resolve(res);
      })
      .catch((error) => {
        reject(error);
      });
  });
};

const approveExpense = async (expId, status, ManagerRemarks) => {
  return new Promise((resolve, reject) => {
    callback
      .get(`manager/action/${expId}/${status}/${ManagerRemarks}`)
      .then((res) => {
        resolve(res);
      })
      .catch((error) => {
        reject(error);
      });
  });
};
const rejectExpense = async (expId, status, ManagerRemarks) => {
  return new Promise((resolve, reject) => {
    callback
      .get(`manager/action/${expId}/${status}/${ManagerRemarks}`)
      .then((res) => {
        resolve(res);
      })
      .catch((error) => {
        reject(error);
      });
  });
};

const getAllStatus = async (empId) => {
  return new Promise((resolve, reject) => {
    callback
      .get(`manager/getallexpensebymanagerid/${empId}`)
      .then((res) => {
        resolve(res);
      })
      .catch((error) => {
        reject(error);
      });
  });
};
// http://localhost:8082/manager/getAllStatusManager/E17NH5C0CBD8F2FG
const getAllStatusManagerExpense = async (empId) => {
  return new Promise((resolve, reject) => {
    callback
      .get(`manager/getAllStatusManager/${empId}`)
      .then((res) => {
        resolve(res);
      })
      .catch((error) => {
        reject(error);
      });
  });
};

const addExpenseDetails = async (data) => {
  return new Promise((resolve, reject) => {
    callback
      .post(`employee/addExpense`, data, {
        headers: { "content-type": "multipart/form-data" },
      })
      .then((res) => {
        resolve(res);
      })
      .catch((error) => {
        reject(error);
      });
  });
};

const getAmountChartEmpolyeeAllMonth = async (empId) => {
  return new Promise((resolve, reject) => {
    callback
      .get(`employee/getAmountChartEmpolyeeAllMonth/${empId}`)
      .then((res) => {
        resolve(res);
      })
      .catch((error) => {
        reject(error);
      });
  });
};

const getMonthData = async (empId) => {
  return new Promise((resolve, reject) => {
    callback
      .get(`manager/getMngAmountChart/${empId}`)
      .then((res) => {
        resolve(res);
      })
      .catch((error) => {
        reject(error);
      });
  });
};
// http://localhost:8082/employee/getAmountChartEmpolyeeAllMonth/E1MNF0AE01F0TRNB
export {
  loginCredVerification,
  getAllExpense,
  getAllStatus,
  addExpenseDetails,
  rejectExpense,
  approveExpense,
  getMonthData,
  getAllPendingExpense,
  getExpenseById,
  getPieStatusChartData,
  getallexpensebymanagerid,
  getAllStatusManagerExpense,
  getAmountChartEmpolyeeAllMonth,
};
