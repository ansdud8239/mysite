import React from 'react';
import MySiteLayout from "../../layout/MySiteLayout";
import styles from '../../assets/scss/component/main/Main.scss';
import mainImg from '../../assets/images/component/main/main.png';

export default function Main() {
    return (
        <MySiteLayout>
            <div className={styles.Main}>
                <img id="profile" src={mainImg}/>
                <h2>안녕하세요. 조문영의 mysite에 오신 것을 환영합니다.</h2>
                <p>
                    이 사이트는 웹 프로그램밍 실습과제 예제 사이트입니다.<br/>
                    메뉴는 사이트 소개, 방명록, 게시판이 있구요. Java 수업 + 데이터베이스 수업 + 웹프로그래밍 수업 배운 거 있는거 없는 거 다 합쳐서 만들어 놓은 사이트 입니다.
                    <br/> <br/> <br/>
                    <a href="">방명록</a>
                            에 글 남기기<br/>
                </p>
            </div>
        </MySiteLayout>

    );
}