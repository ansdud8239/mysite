import React from 'react';
import styles from '../../assets/scss/component/about/Navigation.scss';
import { NavLink } from 'react-router-dom';

const Navigation = () => {
    return (
        <nav className={styles.Navigation}>
            <NavLink to={'/about/me'}>Kickscar</NavLink>
            <span/>
            <NavLink to={'/about/location'}>Location</NavLink>
        </nav>
    );
};

export default Navigation;