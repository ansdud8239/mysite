import React from 'react';
import MySiteLayout from "../../layout/MySiteLayout";
import styles from '../../assets/scss/component/user/SignUp.scss';

export default function SignUp() {
    return (
        <MySiteLayout>
            <div className={styles.SignUp}>
                <form>
                    <label className="block-label">이름</label>
                    <input id="name" name="name" type="text"/>
					<label className="block-label">이메일</label>
					<input id="email" name="email" type="text"/>
					<input type="button" value="중복체크"/>
					<label className="block-label">패스워드</label>
					<input name="password" type="password"/>

                    <fieldset>
						<legend>성별</legend>
						<label>여</label>
						<input type="radio" name="gender" value="female"/>
                        <label>남</label>
						<input type="radio" name="gender" value="male"/>
					</fieldset>
                    <fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y"/>
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					<input type="submit" value="가입하기"/>
					
				</form>
            </div>
        </MySiteLayout>
    );
}