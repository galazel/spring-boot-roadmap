import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { ReactKeycloakProvider } from "@react-keycloak/web";
import keycloak from "./auth/Keycloak";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Root from "./routes/root.jsx";
import ErrorPage from "./pages/error-page.jsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    errorElement: <ErrorPage />,
    children: [{
      index: true,
    }],
  }
]);

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <RouterProvider router={router}>
      <ReactKeycloakProvider authClient={keycloak}></ReactKeycloakProvider>
    </RouterProvider>
  </StrictMode>,
);
