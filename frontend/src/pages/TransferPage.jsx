import { useState, useContext } from "react";
import axios from "axios";
import { AuthContext } from "../auth/AuthContext";

export default function TransferPage() {
  const { user } = useContext(AuthContext);
  const [toAccount, setToAccount] = useState("");
  const [amount, setAmount] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post(
        "http://localhost:8080/api/transfer",
        { toAccount: { ibank: toAccount }, amount },
        { headers: { Authorization: `Bearer ${user?.token}` } }
      );
      setMessage("✅ Transfer successful!");
    } catch (error) {
      setMessage("❌ Transfer failed: " + (error.response?.data?.description || "Unknown error"));
    }
  };

  return (
    <div>
      <h2>Transfer Money</h2>
      {message && <p>{message}</p>}
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Recipient IBAN"
          value={toAccount}
          onChange={(e) => setToAccount(e.target.value)}
          required
        /><br/>
        <input
          type="number"
          placeholder="Amount"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
          required
        /><br/>
        <button type="submit">Send</button>
      </form>
    </div>
  );
}
