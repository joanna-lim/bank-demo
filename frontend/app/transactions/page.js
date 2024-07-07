"use client";

import React, { useState, useEffect } from "react";
import { Button } from "@/components/ui/button";
import Link from "next/link";

const Transactions = () => {
  const [fromBankAccNum, setFromBankAccNum] = useState("");
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [userid, setUserid] = useState('');

  useEffect(() => {
    setUserid(localStorage.getItem('userid'));
  }, []);

  const handleSearch = (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    fetch(`http://localhost:8080/api/v1/transactions/account/${fromBankAccNum}`, { cache: 'no-store' })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        setTransactions(data);
        setLoading(false);
      })
      .catch((error) => {
        setError(error);
        setLoading(false);
      });
  };

  const formatDate = (datetime) => {
    const date = new Date(datetime);
    return date.toLocaleDateString("en-CA"); // 'en-CA' gives you the YYYY-MM-DD format
  };

  return (
    <>
      {userid ? (
        <div className="container mx-auto p-4">
          <h1 className="text-3xl font-bold tracking-tight my-4">Transaction History</h1>
          <form onSubmit={handleSearch} className="mb-4">
            <label className="block text-gray-700 mb-2">
              Enter Bank Account Number
            </label>
            <input
              type="text"
              value={fromBankAccNum}
              onChange={(e) => setFromBankAccNum(e.target.value)}
              className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 mb-2"
              placeholder="Enter bank account number"
              required
            />
            <button
              type="submit"
              className="bg-black text-white py-2 px-4 rounded-md hover:bg-zinc-800 transition duration-200"
            >
              Search
            </button>
          </form>
          {loading && <div>Loading...</div>}
          {error && <div>Error: {error.message}</div>}
          {transactions.length > 0 && (
            <div className="overflow-x-auto">
              <table className="min-w-full bg-white">
                <thead>
                  <tr>
                    <th className="py-2 px-4 text-left bg-gray-100">Date</th>
                    <th className="py-2 px-4 text-left bg-gray-100">Bank Account</th>
                    <th className="py-2 px-4 text-left bg-gray-100">Amount</th>
                  </tr>
                </thead>
                <tbody>
                  {transactions.map((transaction) => (
                    <tr key={transaction.transactionId} className="border-b">
                      <td className="py-2 px-4 text-left">{formatDate(transaction.datetime)}</td>
                      <td className="py-2 px-4 text-left">
                        {transaction.fromBankAccNum.toString() === fromBankAccNum
                          ? "To: " + transaction.toBankAccNum
                          : "From: " + transaction.fromBankAccNum}
                      </td>
                      <td
                        className={`py-2 px-4 text-left ${
                          transaction.fromBankAccNum.toString() === fromBankAccNum
                            ? "text-red-500"
                            : "text-green-500"
                        }`}
                      >
                        {transaction.fromBankAccNum.toString() === fromBankAccNum
                          ? "-"
                          : "+"}
                        ${Math.abs(transaction.amount).toFixed(2)}
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </div>
      ) : (
        <div className="flex flex-col justify-center items-center text-center mt-52">
          <p className="text-4xl font-semibold"> Please sign in to view your account</p>
          <Button asChild className="mx-2 w-2/5 text-2xl p-8 mt-6" size="lg">
            <Link href="/signin">Sign in</Link>
          </Button>
        </div>
      )}
    </>
  );
};

export default Transactions;
