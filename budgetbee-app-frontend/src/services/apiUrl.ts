/* AUTH DATA */
export const usernameApi = process.env.REACT_APP_USERNAME;
export const passwordApi = process.env.REACT_APP_PASSWORD;

export const basicAuthHeader = "Basic " + btoa(usernameApi + ":" + passwordApi);
export const jsonString: any = localStorage.getItem("user");
export const userData = JSON.parse(jsonString);

/* REGISTRATION  */
export const apiUrlRegister: any = process.env.REACT_APP_API_URL_REGISTER
export const apiUrlCheckUser: any = process.env.REACT_APP_API_URL_CHECKUSER;

/* LOGIN */
export const apiUrlLogin: any = process.env.REACT_APP_API_URL_LOGIN;

/* CARD TRANSACTION */
export const apiUrlExit: any = "https://budgetbee-app-render.onrender.com/api/exits/bybudgetid";
export const apiUrlTransaction: any = "https://budgetbee-app-render.onrender.com/api/transaction";

/* CARD ENTRANCES */
export const apiUrlEntrance: any = "https://budgetbee-app-render.onrender.com/api/entrances/bybudgetid"
/* HEADER BUDGET */
export const apiUrlBudget: any = "https://budgetbee-app-render.onrender.com/api/budgets";

/* ADD TRANSACTION */
export const apiUrlPostExit: any = "https://budgetbee-app-render.onrender.com/api/exits";
export const apiUrlPostEntrance: any = "https://budgetbee-app-render.onrender.com/api/entrances";

/*  DELETE TRANSACTION  */
export const apiUrlDeleteExit: any = "https://budgetbee-app-render.onrender.com/api/exits";
export const apiUrlDeleteEntrance: any = "https://budgetbee-app-render.onrender.com/api/entrances";

/* PIE CHART  */
export const apiUrlChartExit: any = process.env.REACT_APP_API_URL_CHART_EXIT;
export const apiUrlChartEntrance: any = process.env.REACT_APP_API_URL_CHART_ENTRANCE;