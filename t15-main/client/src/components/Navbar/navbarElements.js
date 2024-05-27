import { NavLink as Link } from 'react-router-dom'
import styled from 'styled-components'

export const Nav = styled.nav`
  font-size: 2vw;
  fontFamily: 'Open Sans';
  background: #52676e;
  display: flex;
  justify-content: space-between;
  padding: 2vw;
`

export const NavMenu = styled.div`
  display: flex;
  align-items: center;
`

export const NavLogo = styled.div`
  color: #cde7f0;
  padding: 0 1vw;
  display: flex;
  align-items: center;
  white-space: nowrap;
`

export const NavLink = styled(Link)`
  color: #bddfeb;
  display: flex;
  align-items: center;
  text-decoration: none;
  padding: 0 1vw;
  height: 100%;
  cursor: pointer;
  &.active {
    color: #f6fbfc;
  };
`