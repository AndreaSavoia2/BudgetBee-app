import { apiUrlExit, basicAuthHeader } from './apiUrl';

export const fetchTransactions = async (budgetId: string) => {
  try {
    const response = await fetch(`${apiUrlExit}/${budgetId}`, {
      method: "GET",
      headers: {
        Authorization: basicAuthHeader,
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error("Network response was not ok");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    throw new Error("There was a problem with the fetch operation:" + error);
  }
};