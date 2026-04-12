import { useKeycloak } from '@react-keycloak/web'
function Dashboard() {
  const {keycloak} = useKeycloak()
  return (
    <div>
      <h1>Dashboard</h1>
       <button onClick={() => keycloak.logout({redirectUri: window.location.origin})}>Logout</button>
    </div>
  )
}

export default Dashboard
