import Keycloak from 'keycloak-js';

const keycloak = new Keycloak({
    url: "http://localhost:8080",
    realm: "practice-realm",
    clientId: "fullstack-app-react"
});

export default keycloak;