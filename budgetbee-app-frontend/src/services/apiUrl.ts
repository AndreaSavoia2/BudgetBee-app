/* AUTH DATA */
export const usernameApi = process.env.REACT_APP_USERNAME;
export const passwordApi = process.env.REACT_APP_PASSWORD;

export const basicAuthHeader = "Basic " + btoa(usernameApi + ":" + passwordApi);

/* REGISTRATION  */
export const apiUrlRegister: any = process.env.REACT_APP_API_URL_REGISTER;
export const apiUrlCheckUser: any = process.env.CHECKUSER;

/* LOGIN */
export const apiUrlLogin: any = process.env.REACT_APP_API_URL_LOGIN;

/* CARD TRANSACTION */
export const apiUrlExit: any = process.env.REACT_APP_API_URL_EXIT;

/* HEADER BUDGET */
export const apiUrlBudget: any = process.env.REACT_APP_API_URL_BUDGET;

/* ADD TRANSACTION */
export const apiUrlPostExit: any = process.env.REACT_APP_API_URL_POST_EXIT;
export const apiUrlPostEntrance: any = process.env.REACT_APP_API_URL_POST_ENTRANCE;