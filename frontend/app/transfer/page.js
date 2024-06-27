"use client";

import React from 'react';

const Transfer = () => {
  const recipientRef = React.useRef();
  const amountRef = React.useRef();
  const descriptionRef = React.useRef();

  const handleTransfer = (e) => {
    e.preventDefault();
    const recipient = recipientRef.current.value;
    const amount = amountRef.current.value;
    const description = descriptionRef.current.value;

    // Clear form fields after transfer
    recipientRef.current.value = '';
    amountRef.current.value = '';
    descriptionRef.current.value = '';

    console.log(`Transferred $${amount} to ${recipient} with description: ${description}`);
  };

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-semibold mb-4">Transfer to Other Accounts</h1>
      <form onSubmit={handleTransfer} className="max-w-md mx-auto bg-white p-8 shadow-md rounded-md">
        <div className="mb-4">
          <label className="block text-gray-700">Recipient Account</label>
          <input
            type="text"
            ref={recipientRef}
            defaultValue="Enter in account number here"
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
            defaultValue="100.00"
            className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="Enter amount"
            required
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Description</label>
          <textarea
            ref={descriptionRef}
            defaultValue="Monthly subscription"
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
    </div>
  );
};

export default Transfer;
