import { apiUrlPostEntrance, apiUrlPostExit, basicAuthHeader } from './apiUrl';

export const postTransaction = async (
  formData: any,
  transactionType: string
) => {
  const apiUrl =
    transactionType === "Uscita" ? apiUrlPostExit : apiUrlPostEntrance;

  try {
    const response = await fetch(apiUrl, {
      method: "POST",
      headers: {
        Authorization: basicAuthHeader,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    });

    if (!response.ok) {
      throw new Error("Network response was not ok");
    }

    return response.json();
  } catch (error) {
    throw new Error("Error while posting transaction: " + error);
  }
};
