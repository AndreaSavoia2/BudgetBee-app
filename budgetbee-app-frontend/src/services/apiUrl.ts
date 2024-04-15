/* AUTH DATA */
export const usernameApi = process.env.REACT_APP_USERNAME;
export const passwordApi = process.env.REACT_APP_PASSWORD;

export const basicAuthHeader = "Basic " + btoa(usernameApi + ":" + passwordApi);
export const jsonString: any = localStorage.getItem("user");
export const userData = JSON.parse(jsonString);

/* REGISTRATION  */
export const apiUrlRegister: any = process.env.REACT_APP_API_URL_REGISTER;
export const apiUrlCheckUser: any = process.env.CHECKUSER;

/* LOGIN */
export const apiUrlLogin: any = process.env.REACT_APP_API_URL_LOGIN;

/* CARD TRANSACTION */
export const apiUrlExit: any = process.env.REACT_APP_API_URL_EXIT;
export const apiUrlTransaction: any = process.env.REACT_APP_API_URL_TRANSACTIONS;

/* CARD ENTRANCES */
export const apiUrlEntrance: any = process.env.REACT_APP_API_URL_ENTRANCE
/* HEADER BUDGET */
export const apiUrlBudget: any = process.env.REACT_APP_API_URL_BUDGET;

/* ADD TRANSACTION */
export const apiUrlPostExit: any = process.env.REACT_APP_API_URL_POST_EXIT;
export const apiUrlPostEntrance: any = process.env.REACT_APP_API_URL_POST_ENTRANCE;

/*  DELETE TRANSACTION  */
export const apiUrlDeleteExit: any = process.env.REACT_APP_API_URL_DELETE_EXIT;
export const apiUrlDeleteEntrance: any = process.env.REACT_APP_API_URL_DELETE_ENTRANCE;