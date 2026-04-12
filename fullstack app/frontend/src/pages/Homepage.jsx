import { useKeycloak } from '@react-keycloak/web'

function Homepage() {
  const {keycloak} = useKeycloak()
  return (
    <div>
      <h1>Welcome to the Homepage</h1>
      <button onClick={() => keycloak.login({redirectUri: window.location.origin + "/user-dashboard"})}>Get Started</button>
     
    </div>
  )
}

export default Homepage
