"use client";

import React, { useRef, useState } from "react";

const Transfer = () => {
  const recipientRef = useRef();
  const amountRef = useRef();
  const descriptionRef = useRef();
  const [fromBankAccNum, setFromBankAccNum] = useState(""); // Add state for fromBankAccNum
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);

  const handleTransfer = (e) => {
    e.preventDefault();
    const recipient = recipientRef.current.value;
    const amount = parseFloat(amountRef.current.value);
    const description = descriptionRef.current.value;

    setError(null);
    setSuccess(null);

    fetch(`http://localhost:8080/api/v1/transactions/transfer`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        fromBankAccNum: fromBankAccNum,
        toBankAccNum: recipient,
        amount: amount,
        description: description, // Include description if needed
      }),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        // Clear form fields after transfer
        recipientRef.current.value = "";
        amountRef.current.value = "";
        descriptionRef.current.value = "";

        setSuccess(`Transferred $${amount} to ${recipient} successfully.`);
      })
      .catch((error) => {
        setError("Failed to complete the transfer. Please try again.");
        console.error("There was an error with the transfer:", error);
      });
  };

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-semibold mb-4">Transfer to Other Accounts</h1>
      <form onSubmit={handleTransfer} className="max-w-md mx-auto bg-white p-8 shadow-md rounded-md">
        <div className="mb-4">
          <label className="block text-gray-700">From Account</label>
          <input
            type="text"
            value={fromBankAccNum}
            onChange={(e) => setFromBankAccNum(e.target.value)}
            className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="Enter your account number"
            required
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Recipient Account</label>
          <input
            type="text"
            ref={recipientRef}
            className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="Enter recipient account"
            required
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Amount</label>
          <input
            type="number"
            ref={amountRef}
            className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="Enter amount"
            required
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Description</label>
          <textarea
            ref={descriptionRef}
            className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="Enter description"
            rows="3"
          />
        </div>
        <button
          type="submit"
          className="w-full bg-blue-500 text-white py-2 rounded-md hover:bg-blue-600 transition duration-200"
        >
          Transfer
        </button>
      </form>
      {error && <div className="text-red-500 mt-4">{error}</div>}
      {success && <div className="text-green-500 mt-4">{success}</div>}
    </div>
  );
};

export default Transfer;
