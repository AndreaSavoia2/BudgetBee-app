// fetchApi.ts
import { apiUrlLogin, basicAuthHeader } from './apiUrl';

export const loginUser = async (username: string, password: string) => {
  try {
    const response = await fetch(
      `${apiUrlLogin}?username=${username}&password=${password}`,
      {
        method: "GET",
        headers: {
          Authorization: basicAuthHeader,
          "Content-Type": "application/json",
        },
      }
    );
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    const data = await response.json();
    localStorage.setItem("user", JSON.stringify(data));
    return data;
  } catch (error) {
    throw new Error("Login failed.");
  }
};
