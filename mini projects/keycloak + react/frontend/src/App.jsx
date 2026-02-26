import { Routes, Route } from "react-router-dom"
import { useKeycloak } from "@react-keycloak/web";
import { useNavigate } from "react-router-dom";

export default function App() {
  const { keycloak } = useKeycloak();
  const navigate = useNavigate();

  return <Routes>
    <Route path="/" element={<button onClick={() => {
      keycloak.logout();
      navigate("/");
    }}>Landing Page</button>} />
    <Route path="/profile" element={<h1>Profile Page</h1>} />
    <Route path="/home" element={<h1>Home Page</h1>} />
  </Routes>
 
}
