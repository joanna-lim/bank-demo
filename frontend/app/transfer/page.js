"use client";

import React, { useRef, useState, useEffect } from "react";
import { Button } from "@/components/ui/button";
import Link from "next/link";

const Transfer = () => {
  const recipientRef = useRef();
  const amountRef = useRef();
  const descriptionRef = useRef();
  const [fromBankAccNum, setFromBankAccNum] = useState(""); // Add state for fromBankAccNum
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const [userid, setUserid] = useState('');

  useEffect(() => {
    setUserid(localStorage.getItem('userid'));
  }, []);

  const handleTransfer = (e) => {
    e.preventDefault();
    const recipient = recipientRef.current.value;
    const amount = parseFloat(amountRef.current.value);
    const description = descriptionRef.current.value;

    setError(null);
    setSuccess(null);

    const params = new URLSearchParams();
    params.append("fromBankAccNum", fromBankAccNum);
    params.append("toBankAccNum", recipient);
    params.append("amount", amount);

    fetch(`http://localhost:8080/api/v1/transactions/transfer?${params.toString()}`, {
      method: "POST",
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
    <>
      {userid ? (
        <div className="container mx-auto p-4">
          <h1 className="text-3xl font-bold tracking-tight mt-4 mb-7">Transfer to Other Accounts</h1>
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
              className="w-full bg-black text-white py-2 rounded-md hover:bg-zinc-800 transition duration-200"
            >
              Transfer
            </button>
          </form>
          {error && <div className="text-red-500 mt-4 justify-center items-center flex text-xl font-semibold">{error}</div>}
          {success && <div className="text-green-500 mt-4 justify-center items-center flex text-xl font-semibold">{success}</div>}
        </div>
      ) : (
        <div className="flex flex-col justify-center items-center text-center mt-52">
          <p className="text-4xl font-semibold"> Please sign in to view account</p>
          <Button asChild className="mx-2 w-2/5 text-2xl p-8 mt-6" size="lg">
            <Link href="/signin">Sign in</Link>
          </Button>
        </div>
      )}
    </>
  );
};

export default Transfer;
