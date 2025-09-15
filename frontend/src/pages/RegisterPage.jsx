import { useState } from "react";
import axios from "axios";

export default function RegisterPage() {
  const [form, setForm] = useState({
    firstname: "",
    lastname: "",
    username: "",
    password: "",
    afm: "",
    identityNumber: "",
    dateOfBirth: "",
    fatherFirstname: "",
    motherFirstname: "",
    motherLastname: "",
    gender: "MALE",
    role: "CUSTOMER", // Default value
  });

  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const endpoint =
      form.role === "ADMIN"
        ? "http://localhost:8080/api/registerAsAdmin"
        : "http://localhost:8080/api/registerAsCustomer";

    try {
      await axios.post(endpoint, form, {
        headers: { "Content-Type": "application/json" },
      });
      setMessage("✅ Registration successful!");
    } catch (error) {
      setMessage(
        "❌ Registration failed: " +
          (error.response?.data?.description || "Unknown error")
      );
    }
  };

  return (
    <div>
      <h2>Register</h2>
      {message && <p>{message}</p>}
      <form onSubmit={handleSubmit}>
        <input
          name="firstname"
          placeholder="First Name"
          value={form.firstname}
          onChange={handleChange}
          required
        /><br/>
        <input
          name="lastname"
          placeholder="Last Name"
          value={form.lastname}
          onChange={handleChange}
          required
        /><br/>
        <input
          name="username"
          placeholder="Email"
          value={form.username}
          onChange={handleChange}
          required
        /><br/>
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={form.password}
          onChange={handleChange}
          required
        /><br/>
        <input
          name="afm"
          placeholder="AFM"
          value={form.afm}
          onChange={handleChange}
          required
        /><br/>
        <input
          name="identityNumber"
          placeholder="Identity Number"
          value={form.identityNumber}
          onChange={handleChange}
          required
        /><br/>
        <input
          type="date"
          name="dateOfBirth"
          value={form.dateOfBirth}
          onChange={handleChange}
          required
        /><br/>
        <input
          name="fatherFirstname"
          placeholder="Father's Firstname"
          value={form.fatherFirstname}
          onChange={handleChange}
          required
        /><br/>
        <input
          name="motherFirstname"
          placeholder="Mother's Firstname"
          value={form.motherFirstname}
          onChange={handleChange}
          required
        /><br/>
        <input
          name="motherLastname"
          placeholder="Mother's Lastname"
          value={form.motherLastname}
          onChange={handleChange}
          required
        /><br/>

        <select name="gender" value={form.gender} onChange={handleChange}>
          <option value="MALE">Male</option>
          <option value="FEMALE">Female</option>
        </select><br/>

        <select name="role" value={form.role} onChange={handleChange}>
          <option value="CUSTOMER">Customer</option>
          <option value="ADMIN">Admin</option>
        </select><br/>

        <button type="submit">Register</button>
      </form>
    </div>
  );
}
