import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import { AuthProvider } from "./auth/AuthContext.jsx";

import RegisterPage from "./pages/RegisterPage";
import TransferPage from "./pages/TransferPage";
import UsersPage from "./pages/UsersPage";
import LoginPage from "./pages/LoginPage";

function App() {
  return (
    <AuthProvider>
      <Router>
        {/* Navigation menu */}
        <nav style={{ marginBottom: "20px" }}>
          <Link to="/login" style={{ marginRight: "10px" }}>Login</Link>
          <Link to="/register" style={{ marginRight: "10px" }}>Register</Link>
          <Link to="/transfer" style={{ marginRight: "10px" }}>Transfer</Link>
          <Link to="/users">Users</Link>
        </nav>

        {/* Routes */}
        <Routes>
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/transfer" element={<TransferPage />} />
          <Route path="/users" element={<UsersPage />} />
          <Route path="*" element={<h2>Welcome to E-Banking Frontend 🚀</h2>} />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;
