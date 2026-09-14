import { createContext, useContext, useEffect, useState } from 'react'
import api from '../services/api'

const AuthContext = createContext(null)

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const storedUser = localStorage.getItem('user')
    const token = localStorage.getItem('token')
    if (storedUser && token) setUser(JSON.parse(storedUser))
    setLoading(false)
  }, [])

  const persist = (data) => {
    localStorage.setItem('token', data.data.token)
    localStorage.setItem('user', JSON.stringify(data.data))
    setUser(data.data)
    return data
  }

  const login = async (email, password) => persist((await api.post('/auth/login', { email, password })).data)
  const register = async (name, email, password) => persist((await api.post('/auth/register', { name, email, password, currency: 'INR' })).data)
  const logout = () => { localStorage.removeItem('token'); localStorage.removeItem('user'); setUser(null) }

  return <AuthContext.Provider value={{ user, loading, login, register, logout }}>{children}</AuthContext.Provider>
}

export const useAuth = () => useContext(AuthContext)
