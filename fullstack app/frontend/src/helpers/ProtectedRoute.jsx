import React from 'react'
import { useKeycloak } from '@react-keycloak/web'
import Homepage from '../pages/Homepage';
function ProtectedRoute({children}) {
    const {keycloak} = useKeycloak();
    return keycloak.authenticated ? children : <Homepage/>
}

export default ProtectedRoute
