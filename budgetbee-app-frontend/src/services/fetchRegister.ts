import { apiUrlCheckUser, apiUrlRegister, basicAuthHeader } from './apiUrl';

export const checkUsernameAvailability = async (username: string) => {
  try {
    const response = await fetch(
      `${apiUrlCheckUser}?username=${username}`,
      {
        method: "GET",
        headers: {
          Authorization: basicAuthHeader,
          "Content-Type": "application/json",
        },
      }
    );

    return response.ok;
  } catch (error) {
    throw new Error("Checking if username already exists failed.");
  }
};

export const registerUser = async (username: string, password: string, budget: any) => {
  try {
    const postUser = await fetch(
      `${apiUrlRegister}`,
      {
        method: "POST",
        headers: {
          Authorization: basicAuthHeader,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username: username, password: password, budget: { budget: budget } })
      }
    );

    if (!postUser.ok) {
      throw new Error("Network response was not ok");
    }

    const data = await postUser.json();
    localStorage.setItem("user", JSON.stringify(data));

    return data;
  } catch (error) {
    throw new Error("Registration failed.");
  }
};
