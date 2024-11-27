import React, { useState, useEffect } from "react";
import "flag-icons/css/flag-icons.min.css"; // Import flag-icons styles

const WiseCard = () => {
  const [amount, setAmount] = useState(20000); // Default amount
  const [toCurrency, setToCurrency] = useState("AED"); // Default target currency
  const [convertedAmount, setConvertedAmount] = useState(null); // Holds the API result
  const [loading, setLoading] = useState(false); // Loading state for API call

  // Function to fetch conversion data
  const handleConvert = async () => {
    setLoading(true);
    try {
      const response = await fetch(
        "http://localhost:8080/api/v1/gateway/convert",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            sourceCurrency: "USD",
            targetCurrency: toCurrency,
            sourceAmount: amount,
          }),
        }
      );

      if (response.ok) {
        const data = await response.json();
        setConvertedAmount(data.data.targetAmount); // Update the converted amount
      } else {
        console.error("Failed to convert currency:", response.statusText);
      }
    } catch (error) {
      console.error("Error occurred while converting currency:", error);
    } finally {
      setLoading(false); // Stop loading indicator
    }
  };

  // Trigger conversion when amount or toCurrency changes
  useEffect(() => {
    handleConvert();
  }, [amount, toCurrency]);

  return (
    <div className="h-screen flex items-center justify-center bg-gradient-to-br from-green-50 to-blue-100">
      {/* Card Container */}
      <div className="max-w-md w-full bg-white shadow-md rounded-lg p-6">
        {/* Exchange Rate Info */}
        <div className="text-center font-medium text-gray-700 mb-4">
          <p>Real-time Conversion</p>
          {loading ? (
            <SkeletonLoader /> // Show skeleton loader when loading
          ) : (
            <p className="text-lg font-bold">
              {amount} USD = {convertedAmount} {toCurrency}
            </p>
          )}
        </div>

        <div className="space-y-4">
          {/* Amount Input */}
          <div>
            <label className="block text-sm font-medium text-gray-600">
              You send exactly
            </label>
            <div className="relative flex items-center">
              <span className="fi fi-us mr-2"></span> {/* USA flag */}
              <input
                type="number"
                value={amount}
                onChange={(e) => setAmount(Number(e.target.value))}
                className="w-full border rounded-md py-2 px-3 focus:outline-none focus:ring focus:ring-green-200"
              />
              <span className="absolute right-3 top-2.5 text-gray-500">
                USD
              </span>
            </div>
          </div>

          {/* Recipient Currency */}
          <div>
            <label className="block text-sm font-medium text-gray-600">
              Recipient currency
            </label>
            <div className="relative flex items-center">
              <span className={`fi fi-${toCurrency.toLowerCase()} mr-2`}></span>
              <select
                value={toCurrency}
                onChange={(e) => setToCurrency(e.target.value)}
                className="w-full border rounded-md py-2 px-3 focus:outline-none focus:ring focus:ring-blue-200 bg-white"
              >
                <option value="GBP">GBP (British Pound)</option>
                <option value="INR">INR (Indian Rupee)</option>
                <option value="AED">AED (Emirati Dirham)</option>
                <option value="EUR">EUR (Euro)</option>
              </select>
            </div>
          </div>
        </div>

        {/* Payment Method */}
        <div className="space-y-2 mt-4">
          <label className="block text-sm font-medium text-gray-600">
            Paying with
          </label>
          <button className="w-full border rounded-md py-2 px-3 flex justify-between items-center">
            <span>Bank transfer</span>
            <span className="text-blue-500">Change</span>
          </button>
        </div>

        {/* Delivery Info */}
        <p className="text-sm text-gray-500 mt-4">
          Conversion status: {loading ? "Calculating..." : "Completed"}
        </p>

        {/* Action Buttons */}
        <div className="mt-4 flex gap-4">
          <button
            onClick={handleConvert}
            className="w-full border border-green-500 text-green-500 rounded-md py-2"
            disabled={loading}
          >
            Refresh Conversion
          </button>
          <button
            className="w-full bg-green-500 text-white rounded-md py-2"
            disabled={loading}
          >
            Send money
          </button>
        </div>
      </div>
    </div>
  );
};

// Skeleton Loader Component
const SkeletonLoader = () => (
  <div className="animate-pulse">
    <div className="h-6 bg-gray-300 rounded w-3/4 mx-auto"></div>
    <div className="h-6 bg-gray-300 rounded w-1/2 mx-auto mt-2"></div>
  </div>
);

export default WiseCard;
