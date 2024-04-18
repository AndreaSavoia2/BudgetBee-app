/* AUTH DATA */
export const usernameApi = process.env.REACT_APP_USERNAME;
export const passwordApi = process.env.REACT_APP_PASSWORD;

export const basicAuthHeader = "Basic " + btoa(usernameApi + ":" + passwordApi);
export const jsonString: any = localStorage.getItem("user");
export const userData = JSON.parse(jsonString);

/* REGISTRATION  */
export const apiUrlRegister: any = "https://budget-bee.vercel.app/api/users"
export const apiUrlCheckUser: any = "https://budget-bee.vercel.app/api/users/checkusers";

/* LOGIN */
export const apiUrlLogin: any = "https://budget-bee.vercel.app/api/users/login";

/* CARD TRANSACTION */
export const apiUrlExit: any = "https://budget-bee.vercel.app/api/exits/bybudgetid";
export const apiUrlTransaction: any = "https://budget-bee.vercel.app/api/transaction";

/* CARD ENTRANCES */
export const apiUrlEntrance: any = "https://budget-bee.vercel.app/api/entrances/bybudgetid"
/* HEADER BUDGET */
export const apiUrlBudget: any = "https://budget-bee.vercel.app/api/budgets";

/* ADD TRANSACTION */
export const apiUrlPostExit: any = "https://budget-bee.vercel.app/api/exits";
export const apiUrlPostEntrance: any = "https://budget-bee.vercel.app/api/entrances";

/*  DELETE TRANSACTION  */
export const apiUrlDeleteExit: any = "https://budget-bee.vercel.app/api/exits";
export const apiUrlDeleteEntrance: any = "https://budget-bee.vercel.app/api/entrances";

/* PIE CHART  */
export const apiUrlChartExit: any = "https://budget-bee.vercel.app/api/exits/totalcategory/bybudgetid";
export const apiUrlChartEntrance: any = "https://budget-bee.vercel.app/api/entrances/totalcategory/bybudgetid";