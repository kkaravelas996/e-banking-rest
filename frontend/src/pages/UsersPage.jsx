import { useEffect, useState, useContext } from "react";
import axios from "axios";
import { AuthContext } from "../auth/AuthContext";

export default function UsersPage() {
  const { user } = useContext(AuthContext);
  const [users, setUsers] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/users", {
          headers: { Authorization: `Bearer ${user?.token}` },
        });
        setUsers(response.data.content || []);
      } catch (err) {
        setError("❌ Failed to load users: " + (err.response?.data?.description || "Unknown error"));
      }
    };
    fetchUsers();
  }, [user]);

  return (
    <div>
      <h2>Users</h2>
      {error && <p>{error}</p>}
      <ul>
        {users.map((u) => (
          <li key={u.id}>
            {u.firstname} {u.lastname} ({u.username})
          </li>
        ))}
      </ul>
    </div>
  );
}
