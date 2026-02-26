import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import { ReactKeycloakProvider } from "@react-keycloak/web";
import keycloak from "./auth/keycloak";
import { BrowserRouter } from "react-router-dom";

const keycloakInitOptions = {
  onLoad: "login-required",
  pkceMethod: "S256",
  checkLoginIframe: false,
};

ReactDOM.createRoot(document.getElementById("root")).render(
  <ReactKeycloakProvider
    authClient={keycloak}
    initOptions={keycloakInitOptions}
  >
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </ReactKeycloakProvider>,
);
