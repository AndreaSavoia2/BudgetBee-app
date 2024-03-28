
import { apiUrlBudget, basicAuthHeader } from './apiUrl';

export const fetchUserData = async (budgetId: any) => {
  try {
    const response = await fetch(`${apiUrlBudget}/${budgetId}`, {
      method: "GET",
      headers: {
        Authorization: basicAuthHeader,
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error("Network response was not ok");
    }

    return await response.json();
  } catch (error) {
    throw new Error("Fetching user data failed.");
  }
};
